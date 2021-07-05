package com.surpassli.pwdnote.data.dao

import androidx.room.*
import com.surpassli.pwdnote.data.databean.SaveData

/**
 * @Author surpassli
 * @Description 数据库操作接口
 * @Version 1.0
 * @Date 2021/7/5 11:31 AM
 */

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(account: SaveData)

    @Delete
    fun delete(account: SaveData)

    @Query("SELECT * FROM saveData")
    fun queryAllSaveData(): List<SaveData>?

    @Query("SELECT * FROM saveData WHERE id == :id")
    fun queryOneById(id: Int): SaveData

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateSaveData(saveData: SaveData)
}