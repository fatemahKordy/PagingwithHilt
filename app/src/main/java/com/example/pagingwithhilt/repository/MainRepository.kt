package com.example.pagingwithhilt.repository

import com.example.pagingwithhilt.Network.APIService
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.pagingwithhilt.data.PostDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(private val apiService: APIService)  {
    val listData = Pager(PagingConfig(pageSize = 6, prefetchDistance = 2)) {
        PostDataSource(apiService)
    }.flow
}