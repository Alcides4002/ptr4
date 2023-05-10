package com.example.evaluacion4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.evaluacion4.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity:AppCompatActivity(),AdaptadorListener {
    lateinit var binding: ActivityMainBinding
    lateinit var adatador: AdaptadorRegistro
    lateinit var registro: Registro
    lateinit var room: dbApp
    var listaRegistro: MutableList<Registro> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvRegistro.layoutManager = LinearLayoutManager(this)
        room = Room.databaseBuilder(this, dbApp::class.java, "dbApp").build()
        obtenerRegistros(room)

        with(binding) {
            binding.floatingActionButton1.setOnClickListener {
                val nombre = etNombre.text.toString().trim()
                val descripcion = etDescripcion.text.toString().trim()
                val ciudad = etCiudad.text.toString().trim()

                if (nombre.isNotEmpty() && descripcion.isNotEmpty() && ciudad.isNotEmpty()) {
                    registro = Registro(nombre, descripcion, ciudad)
                    agregarRegistros(room, registro)

                    etNombre.text.clear()
                    etDescripcion.text.clear()
                    etCiudad.text.clear()

                } else {
                    Toast.makeText(this@MainActivity, "Llenar todos los campos", Toast.LENGTH_LONG).show()
                }
            }
        }

    }



    fun obtenerRegistros(room: dbApp) {
        //utilizamos corrutinas para lo que vamos a recibir de la bd si hay usuario cuando
        //abramos la app

        lifecycleScope.launch {
            listaRegistro = room.daoRegistro().obtenerRegistros()
            adatador = AdaptadorRegistro(listaRegistro, this@MainActivity)
            binding.rvRegistro.adapter = adatador

        }
    }
    fun agregarRegistros(room: dbApp, registro: Registro) {
        lifecycleScope.launch {
            room.daoRegistro().agregarRegistros(registro)
            obtenerRegistros(room)


        }
    }
    fun actualizarRegistros(room: dbApp, registro: Registro) {
        lifecycleScope.launch {
            room.daoRegistro().actualizarRegistros(
                registro.id,
                registro.nombre,
               registro.descripcion,
                registro.ciudad,

            )
            obtenerRegistros(room)

        }
    }

  override fun onUpdateItemClick(registro: Registro) {
        with(binding) {
            if (etNombre.text.toString() == "" || etDescripcion.text.toString() == "" || etCiudad.text.toString() == "") {
                Toast.makeText(
                    this@MainActivity,
                    "Todos los campos son requeridos",
                    Toast.LENGTH_LONG
                ).show()
            } else {

                registro.nombre = etNombre.text.toString().trim()
                registro.descripcion = etDescripcion.text.toString().trim()
                registro.ciudad = etCiudad.text.toString().trim()
                actualizarRegistros(room, registro)
            }
        }

    }

    override fun onEditItemClick(registro: Registro) {
        this.registro = registro
        var id = this.registro.id
        binding.etNombre.setText(this.registro.nombre)
        binding.etDescripcion.setText(this.registro.descripcion)
        binding.etCiudad.setText(this.registro.ciudad)

    }
    override fun onDeleteItemClick(registro: Registro) {
        with(binding) {
            lifecycleScope.launch {
                room.daoRegistro().deleteRegistros(registro.id)
                adatador.notifyDataSetChanged()
                obtenerRegistros(room)

            }
        }
    }



}