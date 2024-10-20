package com.spiphy.campaigngenerator.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.spiphy.campaigngenerator.ui.screens.RegionScreen
import com.spiphy.campaigngenerator.ui.viewmodel.RegionViewModel

@Composable
fun CampaignGeneratorApp() {
    var regionViewModel: RegionViewModel = viewModel(factory = RegionViewModel.Companion.Factory)

    Scaffold { innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            RegionScreen(regionViewModel, innerPadding)
        }
    }
}