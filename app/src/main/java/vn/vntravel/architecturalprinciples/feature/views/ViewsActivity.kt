package vn.vntravel.architecturalprinciples.feature.views

import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_views)
        viewModel.searchFlightTickets()
    }
}