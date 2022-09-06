package vn.vntravel.architecturalprinciples.domain.usecase

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import vn.vntravel.architecturalprinciples.data.model.SearchFlightTicketRequest
import vn.vntravel.architecturalprinciples.data.model.Ticket
import vn.vntravel.architecturalprinciples.data.repository.FlightRepository

class SearchFlightTicketsUseCase(private val flightRepository: FlightRepository) {
    suspend fun invoke(request: SearchFlightTicketRequest): Flow<List<Ticket>> {
        return channelFlow {
            do  {
                val response = flightRepository.search(request)
                request.waitFor = response.data?.waitFor
                send(response.data?.tickets ?: arrayListOf())
                delay(1000)
            } while (response.data?.polling == true)
        }
    }
}