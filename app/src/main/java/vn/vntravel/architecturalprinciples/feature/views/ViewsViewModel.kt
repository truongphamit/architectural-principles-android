package vn.vntravel.architecturalprinciples.feature.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import vn.vntravel.architecturalprinciples.data.model.SearchFlightTicketRequest
import vn.vntravel.architecturalprinciples.domain.usecase.SearchFlightTicketsUseCase
import javax.inject.Inject

@HiltViewModel
class ViewsViewModel @Inject constructor(private val searchFlightTicketsUseCase: SearchFlightTicketsUseCase) :
    ViewModel() {

    fun searchFlightTickets() {
        val request = SearchFlightTicketRequest(
            fromAirport = "HAN",
            toAirport = "SGN",
            depart = "06-010-2022",
            numAdults = 1,
            numChilds = 0,
            numInfants = 0,
            oneWay = true,
            currency = "VND",
            lang = "vi"
        )
        viewModelScope.launch {
            try {
                searchFlightTicketsUseCase.invoke(request).toList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}