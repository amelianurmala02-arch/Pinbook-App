package com.amelia.pinbook.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amelia.pinbook.data.dao.BorrowingDao
import com.amelia.pinbook.data.entity.BorrowingEntity

@Database(
    entities = [BorrowingEntity::class],
    version = 2,  // ✅ UBAH DARI 1 KE 2
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun borrowingDao(): BorrowingDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "pinbook_db"
                )
                    .fallbackToDestructiveMigration()  // 🆕 TAMBAHAN BARU!
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}