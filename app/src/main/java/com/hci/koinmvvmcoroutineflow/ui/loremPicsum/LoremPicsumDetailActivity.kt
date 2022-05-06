package com.hci.koinmvvmcoroutineflow.ui.loremPicsum

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.hci.koinmvvmcoroutineflow.R
import com.hci.koinmvvmcoroutineflow.config.BaseActivity
import com.hci.koinmvvmcoroutineflow.databinding.ActivityLorempicsumDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoremPicsumDetailActivity :
    BaseActivity<ActivityLorempicsumDetailBinding>(R.layout.activity_lorempicsum_detail) {

    private val viewModel: LoremPicsumViewModel by viewModel()

    override fun initViewModel() {
        super.initViewModel()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    override fun afterOnCreate() {
        super.afterOnCreate()

        lifecycleScope.launchWhenStarted {
            viewModel.getImageInfo("1011")
        }
    }

}

@BindingAdapter("url")
fun setImageViewUrl(view: AppCompatImageView, url: String?) {
    url?.let {
        Glide.with(view.context).load(it).into(view)
    }
}