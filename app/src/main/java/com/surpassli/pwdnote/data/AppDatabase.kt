package com.surpassli.pwdnote.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.surpassli.common.utillties.ACCOUNT_DATA_FILENAME
import com.surpassli.common.utillties.DATABASE_NAME
import com.surpassli.pwdnote.data.dao.AccountDao
import com.surpassli.pwdnote.data.dao.UserDao
import com.surpassli.pwdnote.data.databean.SaveData
import com.surpassli.pwdnote.data.databean.User
import com.surpassli.pwdnote.workers.SeedDatabaseWorker
import com.surpassli.pwdnote.workers.SeedDatabaseWorker.Companion.KEY_FILENAME

/**
 * @Author surpassli
 * @Description
 * @Version 1.0
 * @Date 2021/7/2 5:24 PM
 */
@Database(entities = [SaveData::class, User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).allowMainThreadQueries().addCallback(object :RoomDatabase.Callback(){
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().setInputData(
                        workDataOf(KEY_FILENAME to ACCOUNT_DATA_FILENAME)).build()
                    WorkManager.getInstance(context).enqueue(request)
                }
            }).build()
        }
    }
}