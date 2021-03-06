package com.hci.koinmvvmcoroutineflow.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.hci.koinmvvmcoroutineflow.data.LoremPicsumRepository
import com.hci.koinmvvmcoroutineflow.model.ImageInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoremPicsumViewModel(private val repository: LoremPicsumRepository): ViewModel() {

    private val _imageInfo: MutableLiveData<ImageInfo> = MutableLiveData()
    val imageInfo: LiveData<ImageInfo> get() = _imageInfo

    private val _errorMsg: MutableLiveData<String> = MutableLiveData()
    val errorMsg: LiveData<String> get() = _errorMsg

    fun getImageInfo(imageId: String) {
        viewModelScope.launch {
            repository.getImageInfo(imageId)
                .catch {
                    _errorMsg.postValue(it.message)
                }.collectLatest {
                    _imageInfo.postValue(it)
                }
        }
    }

    fun fetchImageList(): Flow<PagingData<ImageInfo>> {
        return repository.fetchImageList()
    }
}