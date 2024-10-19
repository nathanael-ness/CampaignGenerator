package com.example.campaigngenerator.data.model

import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
@Immutable
data class Region(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String
)
