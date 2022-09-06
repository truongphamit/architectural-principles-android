package vn.vntravel.architecturalprinciples.feature.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import vn.vntravel.architecturalprinciples.R
import vn.vntravel.architecturalprinciples.feature.views.ViewsActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.btnViews).setOnClickListener {
            startActivity(Intent(this, ViewsActivity::class.java))
        }
    }
}