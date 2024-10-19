package com.example.campaigngenerator.data.model

import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "genre_region_mapping",
    foreignKeys = [androidx.room.ForeignKey(
        entity = Genre::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("genre_id")
    ), androidx.room.ForeignKey(
        entity = Region::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("region_id")
    )]
)
@Immutable
data class GenreRegionMapping(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "genre_id") val genreId: Int,
    @ColumnInfo(name = "region_id") val regionId: Int
)
