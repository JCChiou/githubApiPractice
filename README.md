# githubApiPractice
使用MVVM架構練習串接github Search API

## 改用Flow來傳遞API response
- 定義回傳格式為 flow
```kotlin
fun getRepositories(queryString: String): Flow<SearchDto>  = flow {
           emit(api.searchRepo(query = queryString))
    }
```
- 在view層collect
```kotlin
lifecycleScope.launch {
    homeViewModel.getRepositories(binding.edSearch.text.toString()).collect {
        if (it.items.isNotEmpty()) {
            adapter.settingItemsList = it.items
        }
    }
}
```
