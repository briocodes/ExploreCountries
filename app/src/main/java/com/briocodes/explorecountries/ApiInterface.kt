package com.briocodes.explorecountries

import com.briocodes.explorecountries.dataclass.CountryDataItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("all")
    fun getData(): Call<List<CountryDataItem>>
}