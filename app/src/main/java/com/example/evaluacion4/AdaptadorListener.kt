package com.example.evaluacion4

interface AdaptadorListener{
    fun onEditItemClick(registro: Registro)
    fun onDeleteItemClick(registro: Registro)
    fun onUpdateItemClick(registro: Registro)
}