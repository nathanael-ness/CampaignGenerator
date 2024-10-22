package com.spiphy.campaigngenerator.data.model

import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(indices = [
    androidx.room.Index(value = ["name"], unique = true)
])
@Immutable
data class Region(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo(name = "name") val name: String

)
