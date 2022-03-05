package com.busealkan.covid19.data.model
import com.google.gson.annotations.SerializedName


data class StatisticsModel(
    @SerializedName("response")
    val response: List<StatisticsResponse>,
    @SerializedName("results")
    val results: Int
)

data class StatisticsResponse(
    @SerializedName("cases")
    val cases: Cases?,
    @SerializedName("continent")
    val continent: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("day")
    val day: String?,
    @SerializedName("deaths")
    val deaths: Deaths?,
    @SerializedName("population")
    val population: Int?,
    @SerializedName("tests")
    val tests: Tests?,
    @SerializedName("time")
    val time: String?
)

data class Cases(
    @SerializedName("active")
    val active: String?,
    @SerializedName("critical")
    val critical: String?,
    @SerializedName("1M_pop")
    val mPop: String?,
    @SerializedName("new")
    val new: String?,
    @SerializedName("recovered")
    val recovered: String?,
    @SerializedName("total")
    val total: String?
)

data class Deaths(
    @SerializedName("1M_pop")
    val mPop: String?,
    @SerializedName("new")
    val new: String?,
    @SerializedName("total")
    val total: String?
)

data class Tests(
    @SerializedName("1M_pop")
    val mPop: String?,
    @SerializedName("total")
    val total: String?
)

