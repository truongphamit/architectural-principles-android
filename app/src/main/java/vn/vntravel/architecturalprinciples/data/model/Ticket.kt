package vn.vntravel.architecturalprinciples.data.model

import com.google.gson.annotations.SerializedName

data class Ticket(
    @SerializedName("tid")
    val tid: String?
)