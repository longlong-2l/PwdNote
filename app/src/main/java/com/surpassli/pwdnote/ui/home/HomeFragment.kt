package com.surpassli.pwdnote.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.surpassli.pwdnote.R
import com.surpassli.pwdnote.data.AppDatabase
import com.surpassli.pwdnote.databinding.FragmentHomeBinding

/**
 * 首页，也是主要页面，记录账号密码
 */
class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        context?.let {
            val data = AppDatabase.getInstance(it).accountDao().queryAllSaveData()
            binding.rvMainRecord.layoutManager = LinearLayoutManager(it)
            if (data != null) {
                binding.rvMainRecord.adapter = MainRecordAdapter(it, data)
            }
        }
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
        })
        binding.fab.setOnClickListener {
            startActivity(Intent(context, EditAccountActivity::class.java))
        }
        return binding.root
    }
}