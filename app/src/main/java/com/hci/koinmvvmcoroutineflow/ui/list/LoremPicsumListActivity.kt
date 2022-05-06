package com.hci.koinmvvmcoroutineflow.ui.list

import android.content.Intent
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hci.koinmvvmcoroutineflow.R
import com.hci.koinmvvmcoroutineflow.config.BaseActivity
import com.hci.koinmvvmcoroutineflow.databinding.ActivityLorempicsumListBinding
import com.hci.koinmvvmcoroutineflow.model.ImageInfo
import com.hci.koinmvvmcoroutineflow.ui.detail.LoremPicsumListAdapter
import com.hci.koinmvvmcoroutineflow.ui.LoremPicsumViewModel
import com.hci.koinmvvmcoroutineflow.ui.detail.LoremPicsumDetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoremPicsumListActivity:
    BaseActivity<ActivityLorempicsumListBinding>(R.layout.activity_lorempicsum_list) {

    private val viewModel: LoremPicsumViewModel by viewModel()
    private val imageListAdapter: LoremPicsumListAdapter = LoremPicsumListAdapter()

    override fun initView() {
        super.initView()
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(
                this@LoremPicsumListActivity,
                RecyclerView.VERTICAL,
                false)
            adapter = imageListAdapter
            imageListAdapter.onItemClickListener = object :LoremPicsumListAdapter.OnItemClickListener{
                override fun onItemClick(item: ImageInfo) {
                    val intent =Intent(context, LoremPicsumDetailActivity::class.java)
                    intent.putExtra("imageId",item.id)
                    startActivity(intent)
                }
            }
        }
    }

    override fun initViewModel() {
        super.initViewModel()
        binding.lifecycleOwner = this
    }

    override fun afterOnCreate() {
        super.afterOnCreate()
        lifecycleScope.launchWhenStarted {
            viewModel.fetchImageList().collect {
                imageListAdapter.submitData(it)
            }
        }
    }
}