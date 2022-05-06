package com.hci.koinmvvmcoroutineflow.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hci.koinmvvmcoroutineflow.api.LoremPicsumApiService
import com.hci.koinmvvmcoroutineflow.model.ImageInfo

class LoremPicsumDataSource(
    private val service: LoremPicsumApiService
): PagingSource<Int, ImageInfo>() {

    override fun getRefreshKey(state: PagingState<Int, ImageInfo>): Int {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageInfo> {
        return try {
            val page = params.key?: 0
            val results = service.imageList(page).map { it.mapper() }.toMutableList()
            val nextPage = if(results.count() == 20) page + 1 else null
            LoadResult.Page(data = results, nextKey = nextPage, prevKey = null)
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}