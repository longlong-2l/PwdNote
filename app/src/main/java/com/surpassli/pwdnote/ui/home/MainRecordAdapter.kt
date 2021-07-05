package com.surpassli.pwdnote.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.surpassli.common.utils.LogUtil
import com.surpassli.pwdnote.R
import com.surpassli.pwdnote.data.databean.SaveData

/**
 * @Author surpassli
 * @Description
 * @Version 1.0
 * @Date 2021/7/2 5:58 PM
 */
class MainRecordAdapter : RecyclerView.Adapter<MainRecordAdapter.MainRecordVH> {
    private lateinit var mData: List<SaveData>
    private lateinit var mContext: Context

    constructor(context: Context, data: List<SaveData>) {
        mData = data
        mContext = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecordVH {
        return MainRecordVH(LayoutInflater.from(mContext).inflate(R.layout.item_main_record, parent, false))
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: MainRecordVH, position: Int) {
        holder.recordTv.setOnClickListener {
        }
    }

    inner class MainRecordVH(view: View) : RecyclerView.ViewHolder(view) {
        val recordTv: TextView = view.findViewById(R.id.pwdRecord)
    }
}