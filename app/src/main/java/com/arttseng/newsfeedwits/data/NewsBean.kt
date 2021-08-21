package com.arttseng.newsfeedwits.data

data class NewsBean(
    var id: Long = 0,
    val title: String="",
    val subtitle: String="",
    val author: String="",
    val date: String="",
    val time: String="",
    val category_id: Int=0,
    val provider_id: Int=0,
    var descriptive: String = newsDummyText,
    var isRead: Boolean = false
)

val newsDummyText = """
        newsDummyText,newsDummyText,newsDummyText,newsDummyText
        newsDummyText
        newsDummyText
        newsDummyText,newsDummyText,newsDummyText,newsDummyText
        newsDummyText
        newsDummyText
        newsDummyText,newsDummyText,newsDummyText,newsDummyText
        newsDummyText
        newsDummyText
        newsDummyText,newsDummyText,newsDummyText,newsDummyText
        newsDummyText,newsDummyText,newsDummyText,newsDummyText
        newsDummyText,newsDummyText,newsDummyText,newsDummyText
        newsDummyText,newsDummyText,newsDummyText,newsDummyTextnewsDummyText,newsDummyText,newsDummyText,newsDummyText
        newsDummyText,newsDummyText,newsDummyText,newsDummyTextnewsDummyText,newsDummyText,newsDummyText,newsDummyText
        newsDummyText,newsDummyText,newsDummyText,newsDummyTextnewsDummyText,newsDummyText,newsDummyText,newsDummyText
        newsDummyText,newsDummyText,newsDummyText,newsDummyTextnewsDummyText,newsDummyText,newsDummyText,newsDummyText
        newsDummyText,newsDummyText,newsDummyText,newsDummyTextnewsDummyText,newsDummyText,newsDummyText,newsDummyText
        newsDummyText,newsDummyText,newsDummyText,newsDummyTextnewsDummyText,newsDummyText,newsDummyText,newsDummyText
        newsDummyText,newsDummyText,newsDummyText,newsDummyTextnewsDummyText,newsDummyText,newsDummyText,newsDummyText
        newsDummyText,newsDummyText,newsDummyText,newsDummyTextnewsDummyText,newsDummyText,newsDummyText,newsDummyText
        newsDummyText,newsDummyText,newsDummyText,newsDummyTextnewsDummyText,newsDummyText,newsDummyText,newsDummyText
        newsDummyText,newsDummyText,newsDummyText,newsDummyTextnewsDummyText,newsDummyText,newsDummyText,newsDummyText
        newsDummyText,newsDummyText,newsDummyText,newsDummyTextnewsDummyText,newsDummyText,newsDummyText,newsDummyText
        newsDummyText,newsDummyText,newsDummyText,newsDummyText
        newsDummyText
        newsDummyText
    """.trimIndent()