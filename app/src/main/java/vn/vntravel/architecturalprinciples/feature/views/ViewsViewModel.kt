package vn.vntravel.architecturalprinciples.feature.views

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import vn.vntravel.architecturalprinciples.data.model.SearchFlightTicketRequest
import vn.vntravel.architecturalprinciples.data.model.Ticket
import vn.vntravel.architecturalprinciples.domain.usecase.SearchFlightTicketsUseCase
import javax.inject.Inject

@HiltViewModel
class ViewsViewModel @Inject constructor(private val searchFlightTicketsUseCase: SearchFlightTicketsUseCase) :
    ViewModel() {

    private val _tickets = MutableLiveData<List<Ticket>>()
    val tickets get() = _tickets

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading get() = _isLoading

    private val _isPolling = MediatorLiveData<Boolean>().apply {
        addSource(_isLoading) { isLoading ->
            value = isLoading && tickets.value?.isNotEmpty() == true
        }
        addSource(_tickets) { tickets ->
            value = tickets.isNotEmpty() && isLoading.value == true
        }
    }
    val isPolling: LiveData<Boolean> get() = _isPolling

    private val _isEmptyLoading = MediatorLiveData<Boolean>().apply {
        addSource(_isLoading) { isLoading ->
            value = isLoading && tickets.value.isNullOrEmpty()
        }
        addSource(_tickets) { tickets ->
            value = tickets.isNullOrEmpty() && isLoading.value == true
        }
    }
    val isEmptyLoading: LiveData<Boolean> get() = _isEmptyLoading

    init {
        searchFlightTickets()
    }

    private fun searchFlightTickets() {
        val request = SearchFlightTicketRequest(
            fromAirport = "HAN",
            toAirport = "SGN",
            depart = "10-10-2022",
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
                    .onStart {
                        _isLoading.value = true
                    }
                    .onCompletion {
                        _isLoading.value = false
                    }
                    .collect { tickets ->
                        _tickets.value = tickets
                    }
            } catch (e: Exception) {
                _isLoading.value = false
                e.printStackTrace()
            }
        }
    }
}