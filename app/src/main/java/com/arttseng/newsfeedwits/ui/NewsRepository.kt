package com.arttseng.newsfeedwits.ui

import com.arttseng.newsfeedwits.MyApp
import com.arttseng.newsfeedwits.data.CategoryBean
import com.arttseng.newsfeedwits.data.NewsBean
import com.arttseng.newsfeedwits.data.ProviderBean
import io.realm.Realm
import io.realm.RealmObject
import io.realm.kotlin.where

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

    fun getDummyProviders():List<ProviderBean> {
        val realm = MyApp._instance?.getRealm()
        val result = realm?.where<ProviderBean>()?.findAll()

        realm?.use {
            if (result?.size==0) {
                realm.beginTransaction()
                val data = arrayListOf<ProviderBean>()
                data.add(ProviderBean(0,"All Sources", "desc"))
                data.add(ProviderBean(1,"Daily Times", "desc1"))
                data.add(ProviderBean(2,"News Express", "desc2"))
                data.add(ProviderBean(3,"Daily Bugle", "desc3"))
                data.add(ProviderBean(4,"New News", "desc4"))
                data.add(ProviderBean(5,"News Now", "desc5"))
                realm.copyToRealmOrUpdate<RealmObject>(data)
                realm.commitTransaction()
            }
        }

        return result?.toList() ?: listOf()
    }

    fun getDummyCategory():List<CategoryBean> {
        val realm = MyApp._instance?.getRealm()
        val result = realm?.where<CategoryBean>()?.findAll()

        realm?.use {
            if (result?.size==0) {
                realm.beginTransaction()
                val data = arrayListOf<CategoryBean>()
                data.add(CategoryBean(0,"All Categories"))
                data.add(CategoryBean(1,"Crime"))
                data.add(CategoryBean(2,"Business"))
                data.add(CategoryBean(3,"Cars"))
                data.add(CategoryBean(4,"Entertainment"))
                data.add(CategoryBean(5,"Family"))
                data.add(CategoryBean(6,"Health"))
                data.add(CategoryBean(7,"Politics"))
                data.add(CategoryBean(8,"Religion"))
                data.add(CategoryBean(9,"Science"))
                realm.copyToRealmOrUpdate<RealmObject>(data)
                realm.commitTransaction()
            }
        }
        return result?.toList() ?: listOf()
    }

    fun getDummyNews():List<NewsBean> {
        val realm = MyApp._instance?.getRealm()
        val result = realm?.where<NewsBean>()?.findAll()

        realm?.use {
            if (result?.size==0) {
                realm.beginTransaction()
                val data = arrayListOf<NewsBean>()
                data.add(NewsBean(1, "Business title", "sub", "RJ", "2021/8/21", "10:27:01",  2,1,"first news"))
                data.add(NewsBean(2, "Entertainment title", "sub", "C. W. Shin", "2021/8/21", "10:27:01",  4,2))
                data.add(NewsBean(3, "Science title", "sub", "Mike Chen", "2021/8/21", "10:27:01",  9,3))
                data.add(NewsBean(4, "Crime title", "sub", "Murakami Haruki", "2021/8/21", "10:27:01",  1,1))
                data.add(NewsBean(5, "Crime title", "sub", "Lawrence Wright", "2021/8/22", "10:27:01",  1,5))
                data.add(NewsBean(6, "Religion title", "sub", "Henry Parson", "2021/8/23", "10:27:01",  8,5))
                data.add(NewsBean(7, "Health title", "sub", "Yoko Ono", "2021/8/23", "10:27:01",  6,5))
                data.add(NewsBean(8, "Family title", "sub", "John Lennon", "2021/8/23", "10:27:01",  5,2))
                data.add(NewsBean(9, "Religion title", "sub", "Sex Pistol", "2021/8/23", "10:27:01",  8,3))
                data.add(NewsBean(10, "Health title", "sub", "London Calling", "2021/8/23", "10:27:01",  6,1))
                realm.copyToRealmOrUpdate<RealmObject>(data)
                realm.commitTransaction()
            }
        }
        return result?.toList() ?: listOf()
    }
}