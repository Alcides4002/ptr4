package com.example.evaluacion4


import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [Registro::class],
    version = 1

)
 abstract class dbApp:RoomDatabase() {
    abstract  fun daoRegistro():daoRegistro
 }





