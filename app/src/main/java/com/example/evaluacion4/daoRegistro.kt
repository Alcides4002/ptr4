package com.example.evaluacion4

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface daoRegistro {
    @Query("SELECT * FROM registros")
    suspend fun obtenerRegistros():MutableList<Registro>
    @Insert
    suspend fun agregarRegistros(registros: Registro)

    @Query("UPDATE registros SET nombre =:nombre, descripcion =:descripcion, ciudad=:ciudad WHERE id=:id")
    suspend fun actualizarRegistros(id:Int , nombre:String,descripcion:String,ciudad:String )

    @Query("DELETE FROM registros WHERE id =:id")
    suspend fun deleteRegistros(id: Int)

}