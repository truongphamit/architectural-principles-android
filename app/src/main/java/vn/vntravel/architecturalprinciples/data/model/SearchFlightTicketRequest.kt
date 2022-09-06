package vn.vntravel.architecturalprinciples.data.model

import com.google.gson.annotations.SerializedName

data class SearchFlightTicketRequest(
    @SerializedName("waitFor")
    var waitFor: String? = null,

    @SerializedName("from_airport")
    var fromAirport: String? = null,

    @SerializedName("to_airport")
    var toAirport: String? = null,

    @SerializedName("depart")
    var depart: String? = null,

    @SerializedName("num_adults")
    var numAdults: Int? = null,

    @SerializedName("num_childs")
    var numChilds: Int? = null,

    @SerializedName("num_infants")
    var numInfants: Int? = null,

    @SerializedName("one_way")
    var oneWay: Boolean? = null,

    @SerializedName("currency")
    var currency: String? = null,

    @SerializedName("lang")
    var lang: String? = null,
)
