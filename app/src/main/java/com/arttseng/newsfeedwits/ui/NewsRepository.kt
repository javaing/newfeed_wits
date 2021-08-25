package com.arttseng.newsfeedwits.ui

import com.arttseng.newsfeedwits.data.CategoryBean
import com.arttseng.newsfeedwits.data.NewsBean
import com.arttseng.newsfeedwits.data.ProviderBean

class NewsRepository {
    fun getNewsProviders():List<ProviderBean> {
        return getDummyProviders()
    }

    fun getNewsCategories():List<CategoryBean> {
        return getDummyCategory()
    }

    fun getNews():List<NewsBean> {
        return getDummyNews()
    }


    private fun getDummyProviders():List<ProviderBean> {
        val data = arrayListOf<ProviderBean>()
        data.add(ProviderBean(0,"Sources", "desc"))
        data.add(ProviderBean(1,"Daily Times", "desc1"))
        data.add(ProviderBean(2,"News Express", "desc2"))
        data.add(ProviderBean(3,"Daily Bugle", "desc3"))
        data.add(ProviderBean(4,"New News", "desc4"))
        data.add(ProviderBean(5,"News Now", "desc5"))
        return data.toList()
    }

    private fun getDummyCategory():List<CategoryBean> {
        val data = arrayListOf<CategoryBean>()
        data.add(CategoryBean(0,"Categories"))
        data.add(CategoryBean(1,"Crime"))
        data.add(CategoryBean(2,"Business"))
        data.add(CategoryBean(3,"Cars"))
        data.add(CategoryBean(4,"Entertainment"))
        data.add(CategoryBean(5,"Family"))
        data.add(CategoryBean(6,"Health"))
        data.add(CategoryBean(7,"Politics"))
        data.add(CategoryBean(8,"Religion"))
        data.add(CategoryBean(9,"Science"))
        return data.toList()
    }

    private fun getDummyNews():List<NewsBean> {
        val data = arrayListOf<NewsBean>()
        data.add(NewsBean(1, "Business title", "sub provide1", "RJ", "2021/8/21", "23:27:01",  2,1))
        data.add(NewsBean(2, "Entertainment title", "sub provide2", "C. W. Shin", "2021/8/21", "10:27:01",  4,2))
        data.add(NewsBean(3, "Science title", "sub provide3", "Mike Chen", "2021/8/21", "10:27:02",  9,3))
        data.add(NewsBean(4, "Crime title", "sub provide1", "Murakami Haruki", "2021/8/21", "10:27:01",  1,1))
        data.add(NewsBean(5, "Crime title", "sub provide5", "Lawrence Wright", "2021/7/22", "10:27:01",  1,5))
        data.add(NewsBean(6, "Religion title", "sub provide5", "Henry Parson", "2021/7/23", "10:27:01",  8,5))
        data.add(NewsBean(7, "Health title", "sub provide5", "Yoko Ono", "2021/8/23", "10:27:01",  6,5))
        data.add(NewsBean(8, "Family title", "sub provide2", "John Lennon", "2021/8/23", "10:27:01",  5,2))
        data.add(NewsBean(9, "Religion title", "sub provide3", "Sex Pistol", "2021/8/23", "10:27:01",  8,3))
        data.add(NewsBean(10, "Health title", "sub provide1", "London Calling", "2021/8/23", "10:27:01",  6,1))
        data.add(NewsBean(11, "Business title", "sub provide1", "RJ", "2021/9/1", "10:27:01",  2,1))
        data.add(NewsBean(12, "Entertainment title", "sub provide2", "C. W. Shin", "2021/9/1", "10:27:01",  4,2))
        data.add(NewsBean(13, "Science title", "sub provide3", "Mike Chen", "2021/9/1", "10:27:01",  9,3))
        data.add(NewsBean(14, "Crime title", "sub provide1", "Murakami Haruki", "2021/9/1", "10:27:01",  1,1))
        data.add(NewsBean(15, "Crime title", "sub provide5", "Lawrence Wright", "2021/7/1", "10:27:01",  1,5))
        data.add(NewsBean(16, "Religion title", "sub provide5", "Henry Parson", "2021/7/1", "10:27:01",  8,5))
        data.add(NewsBean(17, "Health title", "sub provide5", "Yoko Ono", "2021/9/1", "10:27:01",  6,5))
        data.add(NewsBean(18, "Family title", "sub provide2", "John Lennon", "2021/9/1", "10:27:01",  5,2))
        data.add(NewsBean(19, "Religion title", "sub provide3", "Sex Pistol", "2021/9/1", "10:27:01",  8,3))
        data.add(NewsBean(20, "Health title", "sub provide1", "London Calling", "2021/9/1", "10:27:01",  6,1))
        return data.toList()
    }


}