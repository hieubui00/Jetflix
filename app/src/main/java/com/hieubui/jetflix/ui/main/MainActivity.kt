package com.hieubui.jetflix.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.hieubui.jetflix.JetflixApplication
import com.hieubui.jetflix.R
import com.hieubui.jetflix.inject.component.MainComponent

class MainActivity : AppCompatActivity() {
    lateinit var component: MainComponent
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        setupComponent()
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(R.layout.activity_main)
    }

    private fun setupComponent() {
        component = (application as JetflixApplication).component
            .mainComponent()
            .create(this)
            .also { it.inject(this) }
    }
}
