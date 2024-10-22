package com.spiphy.campaigngenerator.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.spiphy.campaigngenerator.data.model.Genre
import com.spiphy.campaigngenerator.data.model.Region
import com.spiphy.campaigngenerator.data.model.RegionGenre

@Dao
interface RegionWithGenreDao {
    @Insert(entity = Genre::class, onConflict = OnConflictStrategy.IGNORE)
    fun insert(genre: Genre) : Long

    @Insert(entity = Region::class, onConflict = OnConflictStrategy.IGNORE)
    fun insert(region: Region) : Long

    @Insert(entity = RegionGenre::class, onConflict = OnConflictStrategy.IGNORE)
    fun insert(regenGenre: RegionGenre) : Long

    @Query("""SELECT * FROM genre where name = :genreName""")
    fun getGenre(genreName: String): Genre?

    @Query("""
        SELECT r.* 
        FROM region as r
        JOIN genre_region_mapping as grm ON r.id = grm.region_id
        JOIN genre as g ON grm.genre_id = g.id
        WHERE g.name = :genre""")
    fun getRegions(genre: String): List<Region>
}