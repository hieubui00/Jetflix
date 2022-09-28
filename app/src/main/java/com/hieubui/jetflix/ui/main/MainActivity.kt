package com.hieubui.jetflix.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import com.hieubui.jetflix.R

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(R.layout.activity_main)
    }
}