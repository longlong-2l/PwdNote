package com.surpassli.pwdnote.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.stream.JsonReader
import com.surpassli.pwdnote.data.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

/**
 * @Author surpassli
 * @Description 通过协程的方式创建数据库
 * @Version 1.0
 * @Date 2021/7/5 2:45 PM
 */
class SeedDatabaseWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val filename = inputData.getString(KEY_FILENAME)
            if (filename != null) {
                applicationContext.assets.open(filename).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
//                        val plantType = object : TypeToken<List<Plant>>
                        val database = AppDatabase.getInstance(applicationContext)
                        Result.success()
                    }
                }
            } else {
                Result.failure()
            }
        } catch (ex: Exception) {
            Result.failure()
        }
    }

    companion object {
        const val KEY_FILENAME = "PLANT_DATA_FILENAME"
    }
}