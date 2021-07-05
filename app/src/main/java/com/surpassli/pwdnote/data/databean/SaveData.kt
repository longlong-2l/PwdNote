package com.surpassli.pwdnote.data.databean

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Author surpassli
 * @Description
 * @Version 1.0
 * @Date 2021/7/5 11:19 AM
 */
@Entity(tableName = "saveData")
data class SaveData(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var userId: Int = 0,          //用户id，用于表关联
    var dataType: Int = 0,        //数据类型，暂时无用，可以表标识为金融类、学习类、生活类等
    var dataName: String = "",       //数据名字，一般自己命名，比如支付宝、铁路12306、京东、github等
    var dataAccount: String = "",    //账号
    var dataPwd: String = "",        //密码或密码提示
    var dataDes: String = "",        //描述，备注之类的
    var dataImg: String = ""        //可以用作头像，比如支付宝logo、12306logo等
) {
    override fun toString() = dataName
}