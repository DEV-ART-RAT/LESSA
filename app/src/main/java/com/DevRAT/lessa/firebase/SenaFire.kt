package com.DevRAT.lessa.firebase

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

class SenaFire {

    companion object Factory {
        fun create(): SenaFire = SenaFire()
    }

    var palabra: String? = null
    var sena: List<String>? = null
    var categoria: String? = null
    //var favorito: Boolean? = false
    var nivel : Int? = 0
    var imagen : List<String>? = null

    constructor():this("", listOf(""),0,"", listOf()) {

    }

    constructor(palabra: String?, sena: List<String>?,nivel : Int?, category: String?,imagen : List<String>?) {
        this.palabra = palabra
        this.sena = sena
        this.nivel = nivel
        this.categoria = category
        this.imagen = imagen
    }
}