package com.millrocious.fitness_jet_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import com.millrocious.fitness_jet_app.core.presentation.App
import com.millrocious.fitness_jet_app.ui.theme.SportAndHealthManagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            SportAndHealthManagerTheme {
                Surface {
                    App()
                }
            }
        }
    }
}