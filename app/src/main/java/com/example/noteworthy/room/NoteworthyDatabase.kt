package com.example.noteworthy.room

import android.app.Application
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.noteworthy.room.dao.BaseNoteDao
import com.example.noteworthy.room.dao.CommonDao
import com.example.noteworthy.room.dao.LabelDao

@TypeConverters(Converters::class)
@Database(entities = [BaseNote::class, Label::class], version = 3)
abstract class NoteworthyDatabase : RoomDatabase() {

    abstract fun getLabelDao(): LabelDao
    abstract fun getCommonDao(): CommonDao
    abstract fun getBaseNoteDao(): BaseNoteDao

    fun checkpoint() {
        getBaseNoteDao().query(SimpleSQLiteQuery("pragma wal_checkpoint(FULL)"))
    }

    companion object {

        const val DatabaseName = "NoteworthyDatabase"

        @Volatile
        private var instance: NoteworthyDatabase? = null

        fun getDatabase(app: Application): NoteworthyDatabase {
            return instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(app, NoteworthyDatabase::class.java, DatabaseName)
                    .addMigrations(Migration2, Migration3)
                    .build()
                this.instance = instance
                return instance
            }
        }

        object Migration2 : Migration(1, 2) {

            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE `BaseNote` ADD COLUMN `color` TEXT NOT NULL DEFAULT 'DEFAULT'")
            }
        }

        object Migration3 : Migration(2, 3) {

            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE `BaseNote` ADD COLUMN `images` TEXT NOT NULL DEFAULT `[]`")
            }
        }
    }
}