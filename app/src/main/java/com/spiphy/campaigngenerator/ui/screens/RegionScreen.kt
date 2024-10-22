package com.spiphy.campaigngenerator.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.spiphy.campaigngenerator.data.model.Region
import com.spiphy.campaigngenerator.ui.theme.CampaignGeneratorTheme
import com.spiphy.campaigngenerator.ui.viewmodel.RegionUiState
import com.spiphy.campaigngenerator.ui.viewmodel.RegionViewModel

@Composable
fun RegionScreen(
    regionViewModel: RegionViewModel,
    innerPadding: PaddingValues
) {
    val regionUiState = regionViewModel.regionUiState
    when(regionUiState) {
        is RegionUiState.Error -> Greeting("Error", Modifier.padding(innerPadding))
        is RegionUiState.Loading -> Greeting("Loading", Modifier.padding(innerPadding))
        is RegionUiState.Success -> Greeting(regionUiState.regions, Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Greeting(regions: List<Region>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(regions) { item ->
            Text(
                text = "Region: ${item.name}"
            )
        }
    }
}

@Composable
fun Greeting(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CampaignGeneratorTheme {

    }
}