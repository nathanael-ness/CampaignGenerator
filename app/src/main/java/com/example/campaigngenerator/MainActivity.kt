package com.example.campaigngenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.campaigngenerator.ui.CampaignGeneratorApp
import com.example.campaigngenerator.ui.theme.CampaignGeneratorTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CampaignGeneratorTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    CampaignGeneratorApp()
                }
            }
        }
    }
}