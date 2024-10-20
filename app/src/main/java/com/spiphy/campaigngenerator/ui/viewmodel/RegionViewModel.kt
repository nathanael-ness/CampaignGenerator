package com.spiphy.campaigngenerator.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.spiphy.campaigngenerator.CampaignGeneratorApplication
import com.spiphy.campaigngenerator.data.model.Genre
import com.spiphy.campaigngenerator.data.model.GenreWithRegion
import com.spiphy.campaigngenerator.data.model.Region
import com.spiphy.campaigngenerator.data.repository.RegionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

sealed interface RegionUiState {
    data class Success(val genreWithRegion: GenreWithRegion) : RegionUiState
    object Loading : RegionUiState
    object Error : RegionUiState
}

class RegionViewModel(private val regionRepository: RegionRepository) : ViewModel() {
    var regionUiState: RegionUiState by mutableStateOf(RegionUiState.Loading)
        private set

    init {
        getRegions("Fantasy")
    }

    fun getRegions(genre: String) {
        viewModelScope.launch{
            viewModelScope.launch() {
                withContext(Dispatchers.IO) {
                    regionUiState = try {
                        RegionUiState.Success(regionRepository.getRegions(genre))
                    } catch (e: Exception) {
                        Log.e("RegionViewModel", e.toString())
                        RegionUiState.Error
                    }
                }
            }
        }
    }

    fun insert(region: Region, genre: Genre) {
        viewModelScope.launch{
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    regionRepository.insert(region, genre)
                    getRegions(genre.name)
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CampaignGeneratorApplication)
                RegionViewModel(application.repository)
            }
        }
    }
}

class RegionViewModelFactory(private val regionRepository: RegionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegionViewModel(regionRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}