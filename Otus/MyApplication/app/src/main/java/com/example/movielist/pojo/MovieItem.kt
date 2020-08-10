package com.example.movielist.pojo

import android.os.Parcel
import android.os.Parcelable

data class MovieItem(
    val movieImage: Int,
    val movieName: String?,
    val movieDescription: String?,
    val movieFullDescription: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(movieImage)
        parcel.writeString(movieName)
        parcel.writeString(movieDescription)
        parcel.writeString(movieFullDescription)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieItem> {
        override fun createFromParcel(parcel: Parcel): MovieItem {
            return MovieItem(parcel)
        }

        override fun newArray(size: Int): Array<MovieItem?> {
            return arrayOfNulls(size)
        }
    }
}