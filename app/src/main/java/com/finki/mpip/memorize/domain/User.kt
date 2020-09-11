package com.finki.mpip.memorize.domain

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey val id: String,
    @ColumnInfo(name = "display_name") val displayName: String,
    @ColumnInfo(name = "full_name") val fullName: String,
    @ColumnInfo(name = "email")  val email: String,
    @ColumnInfo(name = "photo") val photo: Uri
)