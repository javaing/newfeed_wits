package com.arttseng.newsfeedwits.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arttseng.newsfeedwits.data.CategoryBean
import com.arttseng.newsfeedwits.data.NewsBean
import com.arttseng.newsfeedwits.data.ProviderBean

class NewsViewModel:ViewModel() {
    private val dataRepository = NewsRepository()

    val providerBean = MutableLiveData<List<ProviderBean>>()
    val categoryBean = MutableLiveData<List<CategoryBean>>()
    val filterNews = MutableLiveData<List<NewsBean>>()
    var allNews = mutableListOf<NewsBean>()

    fun getNewsProviders() {
        providerBean.value = dataRepository.getNewsProviders()
    }

    fun getNewsCategories() {
        categoryBean.value = dataRepository.getNewsCategories()
    }

    fun getNews() {
        allNews.addAll(dataRepository.getNews())
        filterNews.value = allNews.toList()
    }

    fun getNewsBy(providers: List<Int>, category_id:Int) {
        var net = allNews.filter { providers.contains(it.provider_id) }
        if (category_id!=0)
            net = net.filter { it.category_id==category_id }

        net = net.sortedWith(compareBy<NewsBean> { it.date }.thenBy { it.time })

        filterNews.value = net
    }
}