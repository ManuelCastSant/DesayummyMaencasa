package com.example.desayummymaencasa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.desayummymaencasa.model.User

class MyAdapter(private val userList: ArrayList<User>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return  MyViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user: User = userList[position]
        holder.nombre.text = user.nombre
        holder.region.text = user.region
        holder.ingrediente.text = user.ingrediente
        holder.pais.text = user.pais
        holder.precio.text = user.precio.toString()
    }

    public class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.txtPlatilloList)
        val region: TextView = itemView.findViewById(R.id.txtRegionList)
        val ingrediente: TextView = itemView.findViewById(R.id.txtIngredienteList)
        val pais: TextView = itemView.findViewById(R.id.txtPaisList)
        val precio: TextView = itemView.findViewById(R.id.txtPrecioList)
    }
}

