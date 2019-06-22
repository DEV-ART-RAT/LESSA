package com.DevRAT.lessa.Database.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "senas_table")

data class Senas (
    //@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id" )val id: Int,
    @PrimaryKey @ColumnInfo (name = "Palabra") val palabra: String,
    @ColumnInfo (name = "seña") val seña: Int,
    @ColumnInfo (name = "categoria") var categoria: String,
    @ColumnInfo (name = "favorito") var favorito: Boolean

)