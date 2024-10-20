package com.example.campaigngenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.campaigngenerator.ui.theme.CampaignGeneratorTheme
import com.example.campaigngenerator.ui.viewmodel.RegionViewModel
import com.example.campaigngenerator.ui.viewmodel.RegionViewModelFactory

class MainActivity : ComponentActivity() {

    private val regionViewModel: RegionViewModel by viewModels {
        RegionViewModelFactory((application as CampaignGeneratorApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val region = regionViewModel.getRegions("Fantasy")
            CampaignGeneratorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CampaignGeneratorTheme {
        Greeting("Android")
    }
}