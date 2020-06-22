package com.skyeng.api

import com.skyeng.model.MeaningDetails
import com.skyeng.model.SearchResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IExternalApi {
    @GET("words/search")
    fun searchWords(@Query("search") search: String,
                    @Query("page") page: Int?,
                    @Query("pageSize") pageSize: Int?): Single<List<SearchResult>>

    @GET("meanings")
    fun meanings(@Query("ids") ids: Array<Int>): Single<List<MeaningDetails>>
}