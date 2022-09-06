package vn.vntravel.architecturalprinciples.data.remote

import retrofit2.http.Body
import retrofit2.http.POST
import vn.vntravel.architecturalprinciples.data.model.BaseResponse
import vn.vntravel.architecturalprinciples.data.model.SearchFlightTicketRequest
import vn.vntravel.architecturalprinciples.data.model.SearchFlightTicketsData

interface FlightApi {
    @POST("flights/searchOneDirectionTickets")
    suspend fun search(@Body request: SearchFlightTicketRequest): BaseResponse<SearchFlightTicketsData>
}