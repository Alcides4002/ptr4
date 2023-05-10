package com.example.evaluacion4

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "registros")
data class Registro(
    @ColumnInfo(name = "nombre") var nombre: String,
    @ColumnInfo(name = "descripcion") var descripcion: String,
    @ColumnInfo(name = "ciudad") var ciudad: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)


