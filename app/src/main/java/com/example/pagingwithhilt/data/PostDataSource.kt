package com.example.pagingwithhilt.data

import com.example.pagingwithhilt.Network.APIService
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagingwithhilt.model.Data
import javax.inject.Inject

class PostDataSource @Inject constructor(private val apiService: APIService) :
    PagingSource<Int, Data>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
    try {
        val currentLoadingPageKey = params.key ?: 1
        val response = apiService.getListData(currentLoadingPageKey)
        val responseData = mutableListOf<Data>()
        val data = response.body()?.myData ?: emptyList()
        responseData.addAll(data)
        val nextKey = if (responseData.isEmpty()) null
                     else currentLoadingPageKey.plus(1)
        val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

        return LoadResult.Page(
            data = responseData,
            prevKey = prevKey,
            nextKey = nextKey
        )
    } catch (e: Exception) {
        return LoadResult.Error(e)
    }
}

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}




