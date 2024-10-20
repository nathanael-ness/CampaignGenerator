package com.example.campaigngenerator.data.repository

import androidx.annotation.WorkerThread
import com.example.campaigngenerator.data.dao.RegionWithGenreDao
import com.example.campaigngenerator.data.model.Genre
import com.example.campaigngenerator.data.model.Region
import com.example.campaigngenerator.data.model.RegionGenre

class RegionRepository(private val dao: RegionWithGenreDao) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getRegions(genre: String) = dao.getRegions(genre)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(region: Region, genre: Genre) {
        val genreId =dao.insert(genre)
        val regionId = dao.insert(region)
        dao.insert(RegionGenre(regionId = regionId, genreId = genreId))
    }
}