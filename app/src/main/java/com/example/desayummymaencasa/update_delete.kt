package com.example.desayummymaencasa

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class update_delete : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_delete)

        var db = Firebase.firestore
        firebaseAuth = Firebase.auth


        var txtIdQ = findViewById<TextInputEditText>(R.id.txtIdQ)
        var txtnombreQ = findViewById<TextInputEditText>(R.id.txtnombreQ)
        var txtregionQ = findViewById<TextInputEditText>(R.id.txtregionQ)
        var txtingredienteQ = findViewById<TextInputEditText>(R.id.txtIngredienteQ)
        var txtpaisQ = findViewById<TextInputEditText>(R.id.txtpaisQ)
        var txtprecioQ = findViewById<TextInputEditText>(R.id.txtprecioQ)

        val btnQuery = findViewById<Button>(R.id.btnQuery)
        val btnReturn = findViewById<Button>(R.id.btnReturn)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        val btnDelete = findViewById<Button>(R.id.btnDelete)
        val btnClean = findViewById<Button>(R.id.btnClean)

        val platoId = intent.getStringExtra("id")
        txtIdQ.setText(platoId)

        btnQuery.setOnClickListener(){
            db = FirebaseFirestore.getInstance()

            db.collection("Platillo").document(txtIdQ.text.toString()).get().addOnSuccessListener { documentSnapshot ->
                // Obtén el ID del documento
                val documentId = documentSnapshot.id

                // Utiliza el ID como necesites (puedes imprimirlo, almacenarlo, etc.)
                Toast.makeText(this, "ID del platillo: $documentId", Toast.LENGTH_SHORT).show()

                // Resto del código para llenar los campos con los valores del documento
                txtnombreQ.setText(documentSnapshot.get("nombre") as String?)
                txtregionQ.setText(documentSnapshot.get("region") as String?)
                txtingredienteQ.setText(documentSnapshot.get("Ingrediente") as String?)
                txtpaisQ.setText(documentSnapshot.get("pais") as String?)
                val yearValue = documentSnapshot.get("precio") as? Long
                txtprecioQ.setText(yearValue?.toString() ?: "")
            }
        }

        btnUpdate.setOnClickListener(){
            db.collection("Platillo").document(txtIdQ.text.toString()).set(
                hashMapOf(
                    "nombre" to txtnombreQ.text.toString(),
                    "region" to txtregionQ.text.toString(),
                    "ingrediente" to txtingredienteQ.text.toString(),
                    "pais" to txtpaisQ.text.toString(),
                    "precio" to txtprecioQ.text.toString().toInt()
                )
            )
            Toast.makeText(baseContext,"Se actualizo correctamente el platillo", Toast.LENGTH_SHORT).show()

        }

        btnDelete.setOnClickListener(){
            db = FirebaseFirestore.getInstance()
            db.collection("Platillo").document(txtIdQ.text.toString()).delete()
            Toast.makeText(baseContext,"Se elimino el platillo correctamente", Toast.LENGTH_SHORT).show()

        }

        btnReturn.setOnClickListener()
        {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        btnClean.setOnClickListener(){
            txtIdQ.setText(" ")
            txtnombreQ.setText(" ")
            txtregionQ.setText(" ")
            txtingredienteQ.setText(" ")
            txtpaisQ.setText(" ")
            txtprecioQ.setText(" ")
        }

    }
}