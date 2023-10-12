package com.example.track_tel_android.data.trackday

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.track_tel_android.data.Track
import com.example.track_tel_android.data.TrackDao
import com.example.track_tel_android.data.TrackDatabase
import com.example.track_tel_android.data.typeconverter.DateTimeConverter
import com.example.track_tel_android.data.typeconverter.ListConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(
    entities = [TrackDay::class],
    version = 1
)
@TypeConverters(DateTimeConverter::class, ListConverter::class)
abstract class TrackDayDatabase : RoomDatabase() {

    abstract val trackDayDao : TrackDayDao

    companion object {
        @Volatile
        private var INSTANCE: TrackDayDatabase? = null
        private var coroutineScope = CoroutineScope(Dispatchers.Main)
        fun getInstance(context: Context): TrackDayDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TrackDayDatabase::class.java,
                        "track_day_data_database"
                    )
                        .addCallback(databaseCallback(context))
                        //.addMigrations(MIGRATION_1_2)
                        .build()
                }
                return instance
            }
        }

        private fun databaseCallback(context: Context): Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    coroutineScope.launch(Dispatchers.IO) {
                        //TrackDatabase.populateDatabase(context, getInstance(context))
                    }
                }
            }
        }

    }

}