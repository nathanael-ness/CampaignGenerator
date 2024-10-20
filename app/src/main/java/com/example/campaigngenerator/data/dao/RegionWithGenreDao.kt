package com.example.campaigngenerator.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.campaigngenerator.data.model.Genre
import com.example.campaigngenerator.data.model.GenreWithRegion
import com.example.campaigngenerator.data.model.Region
import com.example.campaigngenerator.data.model.RegionGenre

@Dao
interface RegionWithGenreDao {
    @Insert
    fun insert(genre: Genre) : Long

    @Insert
    fun insert(region: Region) : Long

    @Insert
    fun insert(regenGenre: RegionGenre) : Long

    @Transaction
    @Query("""SELECT * FROM region as r
        JOIN genre_region_mapping as grm ON r.id = grm.region_id
        JOIN genre as g ON grm.genre_id = g.id
        WHERE g.name = :genre""")
    fun getRegions(genre: String): GenreWithRegion
}