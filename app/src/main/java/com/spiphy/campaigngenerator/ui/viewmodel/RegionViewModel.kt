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
import com.spiphy.campaigngenerator.data.model.Region
import com.spiphy.campaigngenerator.data.repository.RegionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

sealed interface RegionUiState {
    data class Success(val regions: List<Region>) : RegionUiState
    object Loading : RegionUiState
    object Error : RegionUiState
}

class RegionViewModel(private val regionRepository: RegionRepository) : ViewModel() {
    var regionUiState: RegionUiState by mutableStateOf(RegionUiState.Loading)
        private set

    init {
        insert(region = Region(name = "Mountain"), genre = Genre(name = "Fantasy"))
        insert(region = Region(name = "Jungle"), genre = Genre(name = "Fantasy"))
        insert(region = Region(name = "Swamp"), genre = Genre(name = "Fantasy"))
        getRegions("Fantasy")
    }

    fun getRegions(genre: String = "Fantasy") {
        viewModelScope.launch{
            viewModelScope.launch() {
                withContext(Dispatchers.IO) {
                    regionUiState = try {
                        val genreWithRegion = regionRepository.getRegions(genre)
                        Log.i("RegionViewModel", "genreWithRegion: $genreWithRegion")
                        RegionUiState.Success(genreWithRegion)
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
                    Log.i("RegionViewModel", "inserted: ${region.name} ${genre.name}")
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