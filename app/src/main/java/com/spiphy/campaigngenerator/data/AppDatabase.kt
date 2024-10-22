package com.spiphy.campaigngenerator.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.spiphy.campaigngenerator.data.dao.RegionDao
import com.spiphy.campaigngenerator.data.dao.RegionWithGenreDao
import com.spiphy.campaigngenerator.data.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [
        Genre::class,
        Region::class,
        Subregion::class,
        Settlement::class,
        RegionGenre::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun regionDao(): RegionWithGenreDao

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
                ).setJournalMode(JournalMode.TRUNCATE).build()
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
                        populateDatabase(database.regionDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(dao: RegionWithGenreDao) {
            val genreId = dao.insert(Genre(name = "Fantasy"))
            val regionId = dao.insert(Region(name = "Mountain"))
            val rg1 = dao.insert(RegionGenre(regionId = regionId, genreId = genreId))
            val r2 = dao.insert(Region(name = "River"))
            val rg2 = dao.insert(RegionGenre(regionId = r2, genreId = genreId))
            Log.i("populateDatabase", "rgs: $rg1 $rg2")
            Log.i("populateDatabase", "regionId: $regionId regionId2: $r2 genreId: $genreId")


        }
    }
}