package com.example.campaigngenerator

import android.app.Application
import com.example.campaigngenerator.data.AppDatabase
import com.example.campaigngenerator.data.repository.RegionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CampaignGeneratorApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { RegionRepository(database.regionDao()) }

    override fun onCreate() {
        super.onCreate()
    }


}