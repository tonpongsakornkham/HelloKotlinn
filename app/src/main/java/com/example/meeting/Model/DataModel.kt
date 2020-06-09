package com.example.meeting.Model

import com.google.gson.annotations.SerializedName


data class DataModel(

    @SerializedName("Address") val Address: String,
    @SerializedName("Body") val Body: String,
    @SerializedName("End") val End: String,
    @SerializedName("Invites") val Invites: List<String>,
    @SerializedName("Location") val Location: String,
    @SerializedName("Name") val Name: String,
    @SerializedName("OptionalAttendees") val OptionalAttendees: List<Any>,
    @SerializedName("Start") val Start: String,
    @SerializedName("StatusChecked") val StatusChecked: Int,
    @SerializedName("Subject") val Subject: String
)


