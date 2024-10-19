package com.example.campaigngenerator.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.campaigngenerator.data.model.*

@Database(
    entities = [Genre::class, Region::class, Subregion::class, Settlement::class], version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun regionReader(): RegionReader
}