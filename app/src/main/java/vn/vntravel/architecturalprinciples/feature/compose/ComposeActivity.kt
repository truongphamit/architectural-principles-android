package vn.vntravel.architecturalprinciples.feature.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import vn.vntravel.architecturalprinciples.data.model.Ticket

@AndroidEntryPoint
class ComposeActivity : AppCompatActivity() {

    private val viewModel: ComposeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val uiState by viewModel.uiState.collectAsState()
                Content(uiState = uiState)
            }
        }
    }
}

@Composable
fun Content(uiState: ComposeUiState) {
    when (uiState) {
        is ComposeUiState.HasTickets -> TicketList(uiState.tickets, uiState.isLoading)
        is ComposeUiState.NoTickets -> if (uiState.isLoading) Loading() else Empty()
    }
}

@Composable
fun Loading() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun Empty() {
    Text(
        text = "Không có chuyến bay nào...",
        textAlign = TextAlign.Center
    )
}

@Composable
fun TicketList(tickets: List<Ticket>, isLoading: Boolean) {
    if (isLoading) {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
        )
    }
    LazyColumn {
        items(tickets) { ticket ->
            TicketCard(ticket)
        }
    }
}

@Composable
fun TicketCard(ticket: Ticket) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clickable { },
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 6.dp),
            text = ticket.tid ?: ""
        )
    }
}