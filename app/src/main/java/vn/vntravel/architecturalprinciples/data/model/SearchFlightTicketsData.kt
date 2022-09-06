package vn.vntravel.architecturalprinciples.data.model

import com.google.gson.annotations.SerializedName

data class SearchFlightTicketsData(
    @SerializedName("requestId")
    val requestId: Long?,

    @SerializedName("polling")
    val polling: Boolean?,

    @SerializedName("waitFor")
    val waitFor: String?,

    @SerializedName("tickets")
    val tickets: List<Ticket>?
)
