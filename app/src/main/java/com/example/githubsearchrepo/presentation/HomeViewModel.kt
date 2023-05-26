package com.example.githubsearchrepo.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubsearchrepo.data.remote.dto.SearchDto
import com.example.githubsearchrepo.data.remote.gitSearchApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val api: gitSearchApi) : ViewModel() {

    private val _apiResult = MutableLiveData<SearchDto>()
    val apiResult: LiveData<SearchDto>
        get() = _apiResult

    // 發api拿資料
    fun getRepositories(queryString: String) {
        viewModelScope.launch {
            _apiResult.postValue(api.searchRepo(query = queryString))
        }
    }
}