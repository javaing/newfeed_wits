package com.arttseng.newsfeedwits.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CategoryBean:RealmObject(){
    @PrimaryKey
    var id: Long = 0
    val name: String=""
    var desc: String="desc"
}