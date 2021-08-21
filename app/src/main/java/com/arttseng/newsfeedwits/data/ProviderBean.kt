package com.arttseng.newsfeedwits.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class ProviderBean: RealmObject() {
    @PrimaryKey
    var id: Long = 0
    var name: String=""
    var desc: String = ""
    var isSubscrib: Boolean = true
}