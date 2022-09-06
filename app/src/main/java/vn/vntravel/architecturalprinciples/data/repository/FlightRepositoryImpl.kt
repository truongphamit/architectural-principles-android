package vn.vntravel.architecturalprinciples.data.repository

import vn.vntravel.architecturalprinciples.data.model.BaseResponse
import vn.vntravel.architecturalprinciples.data.model.SearchFlightTicketRequest
import vn.vntravel.architecturalprinciples.data.model.SearchFlightTicketsData
import vn.vntravel.architecturalprinciples.data.remote.FlightApi

class FlightRepositoryImpl(private val flightApi: FlightApi) : FlightRepository {
    override suspend fun search(request: SearchFlightTicketRequest): BaseResponse<SearchFlightTicketsData> {
        return flightApi.search(request)
    }
}