package vn.vntravel.architecturalprinciples.feature.views

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import vn.vntravel.architecturalprinciples.R
import vn.vntravel.architecturalprinciples.databinding.ActivityViewsBinding

@AndroidEntryPoint
class ViewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewsBinding

    private val viewModel: ViewsViewModel by viewModels()

    private val adapter: TicketAdapter by lazy {
        TicketAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_views)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.rvTickets.adapter = adapter

        viewModel.tickets.observe(this) { tickets ->
            adapter.submitList(tickets)
        }
    }
}