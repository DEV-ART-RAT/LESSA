package com.DevRAT.lessa.Database.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "senas_table")

data class Senas (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id" )val id: Int,
    @ColumnInfo (name = "Palabra") val palabra: String,
    @ColumnInfo (name = "seña") val seña: Int,
    @ColumnInfo (name = "saludo") var saludo: Boolean,
    @ColumnInfo (name = "comida") var comida: Boolean,
    @ColumnInfo (name = "numeros") var numeros: Boolean,
    @ColumnInfo (name = "ropa") var ropa: Boolean,
    @ColumnInfo (name = "familia") var familia: Boolean,
    @ColumnInfo (name = "verbos") var verbos: Boolean,
    @ColumnInfo (name = "estados") var estados: Boolean,
    @ColumnInfo (name = "comunes") var comunes: Boolean,
    @ColumnInfo (name = "oficios") var oficios: Boolean,
    @ColumnInfo (name = "profeciones") var profeciones: Boolean,
    @ColumnInfo (name = "calendario") var calendario: Boolean,
    @ColumnInfo (name = "color") var color: Boolean
)