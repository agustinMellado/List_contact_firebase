package com.example.recyclerview_firestore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterProductos(private var lista_de_productos: ArrayList<Productos>) :
    RecyclerView.Adapter<AdapterProductos.ProductoViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lista, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val p: Productos = lista_de_productos[position]

        holder.nombre.text =p.nombre
       // holder.imagen.text = p.imagen
        holder.precio.text = p.precio
        holder.descripcion.text = p.descripcion
        holder.oferta.text = p.oferta

    }

    override fun getItemCount(): Int {
        return lista_de_productos.size
    }

    class ProductoViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        // agregamos el listener a los parametros
        val nombre: TextView = itemView.findViewById(R.id.nombreProducto)
        //val imagen: TextView = itemView.findViewById(R.id.imagenProducto)
        val precio: TextView = itemView.findViewById(R.id.precioProducto)
        val descripcion: TextView = itemView.findViewById(R.id.descriProducto)
        val oferta: TextView = itemView.findViewById(R.id.ofertaProducto)



    }
}