package com.shan.chathuranga.ormlite;

import com.shan.chathuranga.ormlite.models.events.gson.EventParser;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by ChathurangaShan on 1/17/2018.
 */

public interface APIService {

    @GET("events")
    Single<List<EventParser>> getEvents();
}
