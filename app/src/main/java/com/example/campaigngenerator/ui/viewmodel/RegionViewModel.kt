package com.example.campaigngenerator.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.campaigngenerator.data.model.Genre
import com.example.campaigngenerator.data.model.GenreWithRegion
import com.example.campaigngenerator.data.model.Region
import com.example.campaigngenerator.data.repository.RegionRepository
import kotlinx.coroutines.launch

class RegionViewModel(private val regionRepository: RegionRepository) : ViewModel() {
    fun getRegions(genre: String) = viewModelScope.launch{
        regionRepository.getRegions(genre)
    }

    fun insert(region: Region, genre: Genre) = viewModelScope.launch{
        regionRepository.insert(region, genre)
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