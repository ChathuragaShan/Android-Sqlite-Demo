package com.shan.chathuranga.ormlite.features.display_event;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.shan.chathuranga.ormlite.APIService;
import com.shan.chathuranga.ormlite.DatabaseHelper;
import com.shan.chathuranga.ormlite.Global;
import com.shan.chathuranga.ormlite.R;
import com.shan.chathuranga.ormlite.dagger.components.ActivityComponents;
import com.shan.chathuranga.ormlite.dagger.components.DaggerActivityComponents;
import com.shan.chathuranga.ormlite.dagger.modules.EventListModule;
import com.shan.chathuranga.ormlite.models.events.gson.EventParser;
import com.shan.chathuranga.ormlite.models.events.ormlight.ActorTable;
import com.shan.chathuranga.ormlite.models.events.ormlight.CommitTable;
import com.shan.chathuranga.ormlite.models.events.ormlight.EventTable;
import com.shan.chathuranga.ormlite.models.events.ormlight.RepoTable;
import com.shan.chathuranga.ormlite.utility.NetworkStatus;
import com.shan.chathuranga.ormlite.utility.RecyclerViewDividerVertical;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class EventListActivity extends AppCompatActivity {

    @BindView(R.id.list)
    RecyclerView recyclerView;

    @BindView(R.id.parent_layout)
    ConstraintLayout parentLayout;

    @Inject
    DatabaseHelper databaseHelper;

    @Inject
    APIService apiService;

    @Inject
    NetworkStatus status;

    @Inject
    EventListAdapter listAdapter;

    private DisposableSingleObserver<List<EventParser>> eventDisposableSingleObserver;
    private ArrayList<EventParser> eventParserDataList;
    private ArrayList<EventTable> eventTableDataList;
    private static final String TAG = EventListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ActivityComponents activityComponent = DaggerActivityComponents.builder()
                .eventListModule(new EventListModule(this))
                .globalComponents(Global.application().getGlobalComponent())
                .build();

        activityComponent.inject(this);

        ButterKnife.bind(this);
        Stetho.initializeWithDefaults(this);

        initialization();
        checkNetworkConnectivity();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Inject
    public void checkNetworkConnectivity() {
        if (status.isOnline()) {
            getDataFromServer();
        } else {
            Snackbar snackbar = Snackbar
                    .make(parentLayout, "Showing Offline Data", Snackbar.LENGTH_LONG);
            snackbar.show();
            getDataFromDatabase();
        }
    }

    private void initialization() {

        eventParserDataList = new ArrayList<>();
        eventTableDataList = new ArrayList<>();
    }

    public void getDataFromServer() {

        Single<List<EventParser>> eventsAsSingle = apiService.getEvents();

        eventDisposableSingleObserver = eventsAsSingle
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<EventParser>>() {
                    @Override
                    public void onSuccess(@NonNull List<EventParser> eventParsers) {

                        //Remove previous data from tables
                        try {
                            databaseHelper.deleteAll(ActorTable.class);
                            databaseHelper.deleteAll(RepoTable.class);
                            databaseHelper.deleteAll(CommitTable.class);
                            databaseHelper.deleteAll(EventTable.class);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        eventParserDataList.addAll(eventParsers);
                        listAdapter.notifyDataChange(eventParserDataList,null);
                        recyclerView.setAdapter(listAdapter);
                        recyclerView.addItemDecoration(new RecyclerViewDividerVertical(5));
                        recyclerView.setLayoutManager(new LinearLayoutManager(EventListActivity.this));

                        Intent intent = new Intent(EventListActivity.this, EventListDataCacheService.class);
                        intent.putParcelableArrayListExtra("event_data", eventParserDataList);
                        startService(intent);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "on Error : " + e.getMessage());
                        e.printStackTrace();
                    }
                });
    }

    private void getDataFromDatabase() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        try {

            List<EventTable> allEvents = dbHelper.getAll(EventTable.class);
            eventTableDataList.addAll(allEvents);

            listAdapter.notifyDataChange(null,eventTableDataList);
            recyclerView.setAdapter(listAdapter);
            recyclerView.addItemDecoration(new RecyclerViewDividerVertical(5));
            recyclerView.setLayoutManager(new LinearLayoutManager(EventListActivity.this));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        //LocalBroadcastManager.getInstance(this).unregisterReceiver(imageLoaded);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (eventDisposableSingleObserver != null && !eventDisposableSingleObserver.isDisposed()) {
            eventDisposableSingleObserver.dispose();
        }

        if (databaseHelper != null) {
            databaseHelper.close();
        }

        super.onDestroy();

    }
}
