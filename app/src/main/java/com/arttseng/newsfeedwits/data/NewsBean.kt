package com.arttseng.newsfeedwits.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class NewsBean:RealmObject() {
    @PrimaryKey
    var id: Long = 0
    val title: String=""
    val subtitle: String=""
    val author: String=""
    val date: String=""
    val time: String=""
    val category_id: Int=0
    val provider_id: Int=0
    var descriptive: String = ""
    var isRead: Boolean = false
}