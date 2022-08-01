package com.example.pagingwithhilt.viewModel

import com.example.pagingwithhilt.Network.APIService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.pagingwithhilt.data.PostDataSource
import com.example.pagingwithhilt.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    val userData = repository.listData.cachedIn(viewModelScope)
}

