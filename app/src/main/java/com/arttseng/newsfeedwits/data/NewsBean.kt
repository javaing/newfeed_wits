package com.arttseng.newsfeedwits.data

import android.os.Parcel
import android.os.Parcelable

data class NewsBean(
    var id: Long = 0,
    val title: String? ="",
    val subtitle: String? ="",
    val author: String? ="",
    val date: String? ="",
    val time: String? ="",
    val category_id: Int=0,
    val provider_id: Int=0,
    var descriptive: String? = newsDummyText,
    var isRead: Boolean = false
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(title)
        parcel.writeString(subtitle)
        parcel.writeString(author)
        parcel.writeString(date)
        parcel.writeString(time)
        parcel.writeInt(category_id)
        parcel.writeInt(provider_id)
        parcel.writeString(descriptive)
        parcel.writeByte(if (isRead) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewsBean> {
        override fun createFromParcel(parcel: Parcel): NewsBean {
            return NewsBean(parcel)
        }

        override fun newArray(size: Int): Array<NewsBean?> {
            return arrayOfNulls(size)
        }
    }

}

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