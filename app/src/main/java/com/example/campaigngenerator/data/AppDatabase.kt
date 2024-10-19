package com.example.campaigngenerator.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.campaigngenerator.data.dao.RegionReader
import com.example.campaigngenerator.data.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [
        Genre::class,
        Region::class,
        Subregion::class,
        Settlement::class,
        GenreRegionMapping::class
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun regionReader(): RegionReader

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        const val DATABASE_NAME = "app-database"

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }

        private class AppDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch {
                        // database.regionReader().insertGenre()
                    }
                }
            }
        }

        suspend fun populateDatabase(dao: RegionReader) {
            dao.insertGenre(Genre(name = "Fantasy"))
            dao.insertRegion(Region(name = "Forest"))
        }
    }
}