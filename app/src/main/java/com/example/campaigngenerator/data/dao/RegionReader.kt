package com.example.campaigngenerator.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RegionReader {
    @Query(
        """SELECT r.name as name FROM region r
            JOIN genre_region_mapping grm ON r.id = grm.region_id
            JOIN genre g ON grm.genre_id = g.id
            WHERE g.id = :genreUDD"""
    )
    fun getRegionsByGenreUID(genreID: String): List<String>

    @Insert
    fun insertGenre(genre: Genre)

    @Insert
    fun insertRegion(region: Region)

    @Insert
    fun insertGenreRegionMapping(genreRegionMapping: GenreRegionMapping)

}