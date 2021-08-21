package com.arttseng.newsfeedwits.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arttseng.newsfeedwits.data.CategoryBean
import com.arttseng.newsfeedwits.data.NewsBean
import com.arttseng.newsfeedwits.data.ProviderBean

class NewsViewModel:ViewModel() {
    val dataRepository = NewsRepository()

    val providerBean = MutableLiveData<List<ProviderBean>>()
    val categoryBean = MutableLiveData<List<CategoryBean>>()
    val newsBean = MutableLiveData<List<NewsBean>>()
    var allNews = mutableListOf<NewsBean>()
    var newsItem = MutableLiveData<NewsBean>()

    fun getNewsProviders() {
        //viewModelScope.launch {
            providerBean.value = dataRepository.getNewsProviders()
        //}
    }

    fun getNewsCategories() {
        //viewModelScope.launch {
            categoryBean.value = dataRepository.getNewsCategories()
        //}
    }

    fun getNews() {
        //viewModelScope.launch {
            allNews.addAll(dataRepository.getNews())
            newsBean.value = allNews.toList()
        //}
    }

    fun getNewsItem(askId:Long) {
        if(allNews.size==0) {
            allNews.addAll(dataRepository.getNews())
        }
        val result = allNews.filter { it.id==askId}
        newsItem.value = result[0]
    }

    fun getNewsBy(provider_id:Int, category_id:Int) {
        newsBean.value = when {
            provider_id==0 && category_id==0 -> allNews
            provider_id==0 -> allNews.filter { it.category_id==category_id }
            category_id==0 -> allNews.filter { it.provider_id==provider_id }
            else -> allNews.filter { it.category_id==category_id && it.provider_id==provider_id }
        }
    }
}