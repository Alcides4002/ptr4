package com.example.evaluacion4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AdaptadorRegistro (
   val listaRegistro:MutableList<Registro> ,
   val listener: AdaptadorListener
 ):RecyclerView.Adapter<AdaptadorRegistro.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val vista =
            LayoutInflater.from(parent.context).inflate(R.layout.rcregistros, parent, false)
        return ViewHolder(vista)
    }

      override fun onBindViewHolder(holder:AdaptadorRegistro.ViewHolder,position:Int) {
          val registro = listaRegistro[position]
          holder.etNombre.text = registro.nombre
          holder.etDescripcion.text = registro.descripcion
          holder.etCiudad.text = registro.ciudad

          holder.constrain.setOnClickListener {
              listener.onEditItemClick(registro)
          }

          holder.btnEliminar.setOnClickListener {
              listener.onDeleteItemClick(registro)
          }
          holder.btnActualizar.setOnClickListener {
              listener.onUpdateItemClick(registro)
          }
      }
    override fun getItemCount(): Int {
        return listaRegistro.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var etNombre = itemView.findViewById<TextView>(R.id.etNombre)
        var etDescripcion = itemView.findViewById<TextView>(R.id.etDescripcion)
        var etCiudad = itemView.findViewById<TextView>(R.id.etCiudad)
        var btnActualizar = itemView.findViewById<FloatingActionButton>(R.id.floatingActionButton2)
        var btnEliminar = itemView.findViewById<FloatingActionButton>(R.id.floatingActionButton3)
        var constrain = itemView.findViewById<ConstraintLayout>(R.id.ctRecycler)
    }

}