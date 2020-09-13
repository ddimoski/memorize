package com.finki.mpip.memorize.model

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.finki.mpip.memorize.converters.UriConverters

@Entity
data class User (
    @PrimaryKey val id: String,
    @ColumnInfo(name = "display_name") var displayName: String,
    @ColumnInfo(name = "full_name") var fullName: String,
    @ColumnInfo(name = "email")  var email: String,
    @ColumnInfo(name = "photo") var photo: Uri
)