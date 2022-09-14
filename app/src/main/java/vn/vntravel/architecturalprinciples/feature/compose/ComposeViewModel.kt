package vn.vntravel.architecturalprinciples.feature.compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import vn.vntravel.architecturalprinciples.data.model.SearchFlightTicketRequest
import vn.vntravel.architecturalprinciples.data.model.Ticket
import vn.vntravel.architecturalprinciples.domain.usecase.SearchFlightTicketsUseCase
import javax.inject.Inject

interface ComposeUiState {
    val isLoading: Boolean

    data class NoTickets(override val isLoading: Boolean) : ComposeUiState

    data class HasTickets(
        override val isLoading: Boolean,
        val tickets: List<Ticket>
    ) : ComposeUiState
}

private data class ComposeViewModelState(
    val tickets: List<Ticket>? = null,
    val isLoading: Boolean = false,
) {
    fun toUiState(): ComposeUiState = if (tickets.isNullOrEmpty()) {
        ComposeUiState.NoTickets(isLoading = isLoading)
    } else {
        ComposeUiState.HasTickets(isLoading = isLoading, tickets = tickets)
    }
}

@HiltViewModel
class ComposeViewModel @Inject constructor(private val searchFlightTicketsUseCase: SearchFlightTicketsUseCase) :
    ViewModel() {

    private val viewModelState = MutableStateFlow(ComposeViewModelState(isLoading = true))

    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        searchFlightTickets()
    }

    private fun searchFlightTickets() {
        val request = SearchFlightTicketRequest(
            fromAirport = "HAN",
            toAirport = "SGN",
            depart = "12-10-2022",
            numAdults = 1,
            numChilds = 0,
            numInfants = 0,
            oneWay = true,
            currency = "VND",
            lang = "vi"
        )
        viewModelScope.launch {
            try {
                searchFlightTicketsUseCase.invoke(request)
                    .onCompletion {
                        viewModelState.update { it.copy(isLoading = false) }
                    }
                    .collect { tickets ->
                        viewModelState.update { it.copy(tickets = tickets) }
                    }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}