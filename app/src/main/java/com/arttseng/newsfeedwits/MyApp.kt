package com.arttseng.newsfeedwits

import android.app.Application
import android.content.Context

class MyApp: Application() {

    //var config: RealmConfiguration? = null

    val Context.myApp: MyApp
        get() = applicationContext as MyApp

    companion object {
         var _instance :MyApp? = null
    }

    override fun onCreate() {
        super.onCreate()
        _instance = this
        //initRealm()
    }

    fun get():MyApp? {
        return _instance
    }


//    private fun initRealm() {
//        Realm.init(this)
//        config = RealmConfiguration.Builder()
//            .name("wits.realm")
//            .schemaVersion(1)
//            .deleteRealmIfMigrationNeeded()
//            .build()
//        Realm.setDefaultConfiguration(config)
//    }
//
//    fun getRealm(): Realm? {
//        return Realm.getInstance(config)
//    }

}