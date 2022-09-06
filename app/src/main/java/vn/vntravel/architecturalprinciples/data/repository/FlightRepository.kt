package vn.vntravel.architecturalprinciples.data.repository

import vn.vntravel.architecturalprinciples.data.model.BaseResponse
import vn.vntravel.architecturalprinciples.data.model.SearchFlightTicketRequest
import vn.vntravel.architecturalprinciples.data.model.SearchFlightTicketsData

interface FlightRepository {
    suspend fun search(request: SearchFlightTicketRequest): BaseResponse<SearchFlightTicketsData>
}