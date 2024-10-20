package com.spiphy.campaigngenerator.data.model

import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "genre_region_mapping",
    primaryKeys = ["genre_id", "region_id"]
)
@Immutable
data class RegionGenre(
    @ColumnInfo(name = "genre_id") val genreId: Long,
    @ColumnInfo(name = "region_id") val regionId: Long
)
