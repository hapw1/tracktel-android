package com.example.track_tel_android.data

import android.content.Context
import android.util.Log
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Track::class],
    version = 2
)
abstract class TrackDatabase : RoomDatabase() {

    abstract val trackDao : TrackDao

    companion object {
        @Volatile
        private var INSTANCE : TrackDatabase? = null
        private var coroutineScope = CoroutineScope(Dispatchers.Main)
        fun getInstance(context: Context): TrackDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TrackDatabase::class.java,
                        "track_data_database"
                    )
                        .addCallback(databaseCallback(context))
                        //.addMigrations(MIGRATION_1_2)
                        .build()
                }
                return instance
            }
        }

        private fun databaseCallback(context: Context): Callback{
            return object: Callback(){
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    coroutineScope.launch(Dispatchers.IO){
                        populateDatabase(context, getInstance(context))
                    }
                }
            }
        }

        /**
        val MIGRATION_1_2 = object: Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                Log.d("migrate", "migrating from version 1 to 2")

                //database.execSQL("ALTER TABLE 'track_table' ADD COLUMN 'finishLineLeftLat'")
                database.execSQL("DROP TABLE 'track_table'")
                database.execSQL("CREATE TABLE 'track_table' (id INTEGER, " +
                        "name TEXT, " +
                        "county TEXT, " +
                        "length DECIMAL, " +
                        "turns INTEGER, " +
                        "distanceFromUser DECIMAL, " +
                        "finishLineLeftLat DECIMAL, " +
                        "finishLineLeftLong DECIMAL, " +
                        "finishLineRightLat DECIMAL, " +
                        "finishKLineRightLong DECIMAL")
            }
        }
        */

        private suspend fun populateDatabase(context: Context, instance: TrackDatabase){

            val dao = instance.trackDao

            val tracks: MutableList<Track> = arrayListOf()

            tracks.add(Track(
                0,
                "Abingdon Park",
                "Nottingham, Leicestershire",
                1.8F,
                8,
                1.0,
                1.0,
                1.0,
                1.0,
                1.0,
                1.0,
                1.0,
                true,
                167845L,
                "abingdon_map"))

            tracks.add(Track(
                0,
                "Anglesey",
                "Anglesey, Wales",
                2.1F,
                10,
                1.0,
                53.18856,
                4.49645,
                53.18826,
                4.49635,
                53.18841,
                4.49648,
                true,
                167845L,
                "anglesey_map"))

            tracks.add(Track(
                0,
                "Bedford Autodrome",
                "Thurleigh, Bedfordshire, ",
                1.7F,
                0,
                1.0,
                52.23530,
                0.47447,
                52.23503,
                0.47414,
                52.23511,
                0.47436,
                true,
                167845L,
                "bedford_map"))

            tracks.add(Track(
                0,
                "Blyton Park",
                "Gainsborough, Lincolnshire",
                1.6F,
                9,
                1.0,
                1.0,
                1.0,
                1.0,
                1.0,
                1.0,
                1.0,
                true,
                167845L,
                "blyton_map"))

            tracks.add(Track(
                0,
                "Brands Hatch",
                "Longfield, Kent",
                1.2F,
                6,
                1.0,
                51.36045,
                0.26027,
                51.36018,
                0.26047,
                51.36028,
                0.26028,
                true,
                167845L,
                "brandshatch_map"))

            tracks.add(Track(
                0,
                "Cadwell Park",
                "Louth, Lincolnshire",
                2.173F,
                11,
                1.0,
                53.31021,
                0.05925,
                53.31032,
                0.05965,
                53.31032,
                0.05943,
                true,
                167845L,
                "cadwellpark_map"))

            tracks.add(Track(
                0,
                "Castle Combe",
                "Castle Combe, Wiltshire",
                1.198F,
                6,
                1.0,
                51.49288, 2.21608,
                51.49257, 2.21594,
                51.49271, 2.21612,
                true,
                167845L,
                "castlecombe_map"))

            tracks.add(Track(
                0,
                "Croft",
                "Dalton-on-Tees, North Yorkshire",
                2.127F,
                10,
                1.0,
                54.45543,
                1.55581,
                54.45535,
                1.55529,
                54.45531,
                1.55559,
                true,
                167845L,
                "croft_mapgif"))

            tracks.add(Track(
                0,
                "Donington Park",
                "Nottingham, Leicestershire",
                2.5F,
                9,
                1.0,
                52.82967,
                1.37960,
                52.83001,
                1.37949,
                52.82984,
                1.37954,
                true,
                167845L,
                "donington_map"))

            tracks.add(Track(
                0,
                "Goodwood",
                "Goodwood, Chichester",
                2.367F,
                9,
                1.0,
                50.85797,
                0.75240,
                50.85793,
                0.75283,
                50.85802,
                0.75263,
                true,
                167845L,
                "goodwood_map"))

            tracks.add(Track(
                0,
                "Kirkistown",
                "Kirkistown, Northern Ireland",
                1.5F,
                0,
                1.0,
                54.45680,
                5.47218,
                54.45656,
                5.47214,
                54.45667,
                5.47226,
                true,
                167845L,
                "kirkistown_map"))

            tracks.add(Track(
                0,
                "Knockhill",
                "Fife, Scotland",
                1.3F,
                8,
                1.0,
                56.13105,
                3.50657,
                56.13077,
                3.50662,
                56.13092,
                3.50673,
                true,
                167845L,
                "knockhill_map"))

            tracks.add(Track(
                0,
                "Lydden Hill",
                "Denton-on-Wootton, Kent",
                1F,
                7,
                1.0,
                51.17725,
                1.19814,
                51.17750,
                1.19835,
                51.17733,
                1.19833,
                true,
                167845L,
                "lyddenhill_map"))

            tracks.add(Track(
                0,
                "Mallory Park",
                "Kirkby Mallory, Leicester",
                1.198F,
                6,
                1.0,
                52.59867,
                1.33677,
                52.59867,
                1.33622,
                52.59873,
                1.33698,
                true,
                167845L,
                "mallorypark_map"))

            tracks.add(Track(
                0,
                "Mondello Park",
                "County Kildare, Northern Ireland",
                2.268F,
                13,
                1.0,
                53.25718,
                6.74498,
                53.25709,
                6.74539,
                53.25720,
                6.74523,
                true,
                167845L,
                "mondellopark_map"))

            tracks.add(Track(
                0,
                "Oultan Park",
                "Little Budworth, Cheshire",
                2.692F,
                8,
                1.0,
                53.18005,
                2.61255,
                53.17999,
                2.61305,
                53.18010,
                2.61284,
                true,
                167845L,
                "oulton_map"))

            tracks.add(Track(
                0,
                "Prescott Hill Climb",
                "Cheltenham, Gloucesteshire",
                0.64F,
                8,
                1.0,
                51.96309,
                2.02383,
                51.96315,
                2.02415,
                51.96311,
                2.02400,
                false,
                167845L,
                "prescott_map"
            ))

            tracks.add(Track(
                0,
                "Silverstone Circuit",
                "Banbury, Oxfordshire",
                3.67F,
                18,
                85.0,
                52.06932,
                1.02246,
                52.06910,
                1.02202,
                52.06922,
                1.02226,
                true,
                167845L,
                "silverstone_map"
            ))

            tracks.add(Track(
                0,
                "Snetterton",
                "Snetterton, Norfolk",
                1.952F,
                7,
                1.0,
                52.46325,
                0.94480,
                52.46356,
                0.94477,
                52.46343,
                0.94491,
                true,
                167845L,
                "snetterton_map"))

            tracks.add(Track(
                0,
                "Thruxton",
                "Thruxton, Hampshire",
                2.356F,
                11,
                1.0,
                51.20759,
                1.60919,
                51.20773,
                1.60876,
                51.20758,
                1.60894,
                true,
                167845L,
                "thruxton_track"))




            dao.insertAllTracks(tracks)

        }
    }
}