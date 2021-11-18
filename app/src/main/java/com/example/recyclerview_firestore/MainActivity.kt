package com.example.recyclerview_firestore

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*

class MainActivity : AppCompatActivity() {
    //Declaracion de variables
    private lateinit var db: FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var lista_de_Productos: ArrayList<Productos>
    private lateinit var adapter: AdapterProductos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //instanciamos recycler con la vista recyclerView_Lista
        recyclerView = findViewById(R.id.recyclerView_Lista)
        //le asigno el manager
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        //Instancio el arreglo
        lista_de_Productos= arrayListOf()

        adapter= AdapterProductos(lista_de_Productos)
        recyclerView.adapter=adapter

        leerData()

    }

    private fun leerData() {
        //Le asigno a la variable la instancia de la base de datos
        db = FirebaseFirestore.getInstance()
        //voy a la coleccion dentro de firebase y con el listener captamos los movimiento dentro de esta
        db.collection("productos").
        addSnapshotListener(object : EventListener<QuerySnapshot> {

            override fun onEvent(
                value: QuerySnapshot?,
                error: FirebaseFirestoreException?
            ) {
                if (error != null) {
                    //mostramos que se produjo un error
                    Log.e("error", error.message.toString())
                    return
                }
                // si no hay error mostramos por pantalla la lista
                for (dc: DocumentChange in value?.documentChanges!!){
                    if (dc.type == DocumentChange.Type.ADDED){
                        lista_de_Productos.add(dc.document.toObject(Productos::class.java)) //lo agregamos usando la clase que creamos para los contacts
                    }
                }
                adapter.notifyDataSetChanged() // notificamos al adaptador para que cree las tarjetas
            }
        })
    }

}