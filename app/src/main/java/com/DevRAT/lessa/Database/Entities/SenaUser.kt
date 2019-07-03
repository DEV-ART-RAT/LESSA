package com.DevRAT.lessa.Database.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table", primaryKeys = ["usuario","palabra"])

data class SenaUser (
    @ColumnInfo(name = "usuario") val usuario: String,
    @ColumnInfo(name = "palabra") val palabra: String
)