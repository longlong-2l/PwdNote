package com.surpassli.pwdnote.data.databean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Author surpassli
 * @Description
 * @Version 1.0
 * @Date 2021/7/5 11:19 AM
 */
@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
    var userId: Int = 0,
    var userName: String = "",
    var userType: Int = 0,
    var userLv: Int = 0
) {

}