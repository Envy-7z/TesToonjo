package com.tif.testoonjo.local.room.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
@Parcelize
@Entity(tableName = "contactTable")
data class ContactEntity(@ColumnInfo(name = "avatar")var avatar: String,
                         @ColumnInfo(name = "email")var email: String,
                         @ColumnInfo(name = "gender")var gender: String,
                         @ColumnInfo(name = "first_name")var first_name: String,
                         @ColumnInfo(name = "last_name")var last_name: String,
                         @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0): Parcelable


/**
val avatar: String,
val email: String,
val first_name: String,
val gender: String,
val id: Int,
val last_name: String
        */