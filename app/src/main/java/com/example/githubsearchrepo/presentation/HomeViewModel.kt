package com.example.githubsearchrepo.presentation

import androidx.lifecycle.ViewModel
import com.example.githubsearchrepo.data.remote.GitSearchApi
import com.example.githubsearchrepo.data.remote.dto.SearchDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val api: GitSearchApi) : ViewModel() {

    // 發api拿資料
    fun getRepositories(queryString: String): Flow<SearchDto>  = flow {
           emit(api.searchRepo(query = queryString))
    }
}