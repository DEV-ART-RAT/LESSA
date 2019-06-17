package com.DevRAT.lessa.Database.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "category_table")

data class Category (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id" )val id: Int,
    @ColumnInfo (name = "Categoria") val categoria: String,
    @ColumnInfo (name = "caratula") val caratula: Int
)