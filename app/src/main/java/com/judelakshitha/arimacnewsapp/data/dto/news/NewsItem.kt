package com.judelakshitha.arimacnewsapp.data.dto.news

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = false)
@Parcelize
data class NewsItem(

    @Json(name = "card")
    val card: String = "",
    @Json(name = "country")
    val country: String = "",
    @Json(name = "description")
    val description: String = "",
    @Json(name = "id")
    val id: String = "",
    @Json(name = "image")
    val image: String = "",
    @Json(name = "incompatibilities")
    val incompatibilities: String = "",
    @Json(name = "keywords")
    val keywords: List<String> = listOf(),
    @Json(name = "name")
    val name: String = "",
    @Json(name = "time")
    val time: String = "",
    @Json(name = "user")
    val user: User = User(),

):Parcelable
