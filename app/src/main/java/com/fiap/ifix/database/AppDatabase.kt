package com.fiap.ifix.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fiap.ifix.database.dao.MechanicDao
import com.fiap.ifix.migrations.MIGRATION_1_2
import com.fiap.ifix.model.MechanicItem

@Database(
    version = 1,
    entities = [MechanicItem::class],
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun mechanicDao(): MechanicDao

    companion object {
        @Volatile
        private var db: AppDatabase? = null

        fun instancia(context: Context?): AppDatabase {
            return db ?: Room.databaseBuilder(
                context!!,
                AppDatabase::class.java,
                "mechanic.db"
            ).addMigrations(MIGRATION_1_2)
                .build()
        }
    }

}