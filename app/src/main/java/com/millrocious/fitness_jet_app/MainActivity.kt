package com.millrocious.fitness_jet_app

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.core.content.ContextCompat
import com.millrocious.fitness_jet_app.core.presentation.App
import com.millrocious.fitness_jet_app.feature_map_tracker.presentation.MapViewModel
import com.millrocious.fitness_jet_app.ui.theme.SportAndHealthManagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                //viewModel.getDeviceLocation(fusedLocationProviderClient)
            }
        }

    private fun askPermissions() = when {
        ContextCompat.checkSelfPermission(
            this,
            ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED -> {
            //viewModel.getDeviceLocation(fusedLocationProviderClient)
        }
        else -> {
            requestPermissionLauncher.launch(ACCESS_FINE_LOCATION)
        }
    }

    //private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val viewModel: MapViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        //fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        askPermissions()

        setContent {
            SportAndHealthManagerTheme {
                Surface {
                    App()
                }
            }
        }
    }
}