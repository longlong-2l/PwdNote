package com.surpassli.pwdnote.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.surpassli.pwdnote.data.databean.User
import kotlinx.coroutines.flow.Flow

/**
 * @Author surpassli
 * @Description 用户数据操作类，早期应该用不上
 * @Version 1.0
 * @Date 2021/7/5 1:36 PM
 */
@Dao
interface UserDao {

    @Query("SELECT * FROM user ORDER BY userName")
    fun getUsers(): Flow<List<User>>

    @Query("SELECT * FROM user WHERE id == :userId")
    fun getUser(userId: Int): Flow<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<User>)
}