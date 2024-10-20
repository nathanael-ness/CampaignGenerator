package com.example.campaigngenerator.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class GenreWithRegion(
    @Embedded val genre: Genre,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        entity = Region::class
    ) val regions: List<Region>
)
