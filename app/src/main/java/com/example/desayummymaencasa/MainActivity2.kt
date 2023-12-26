package com.example.desayummymaencasa

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity2 : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val db = Firebase.firestore
        firebaseAuth = Firebase.auth

        var txtId = findViewById<TextInputEditText>(R.id.txtId)
        var txtnombre = findViewById<TextInputEditText>(R.id.txtnombre)
        var txtregion = findViewById<TextInputEditText>(R.id.txtregion)
        var txtingrediente = findViewById<TextInputEditText>(R.id.txtIngrediente)
        var txtpais = findViewById<TextInputEditText>(R.id.txtpais)
        var txtprecio = findViewById<TextInputEditText>(R.id.txtprecio)

        val btnCerrarSesion = findViewById<Button>(R.id.btn_logout)
        val btnClean = findViewById<Button>(R.id.btnClean)
        val btnInsert = findViewById<Button>(R.id.btnInsert)
        val btnIr = findViewById<Button>(R.id.btnIr)
        val btnIr2 = findViewById<Button>(R.id.btnIr2)

        btnIr.setOnClickListener(){
            val intent = Intent(this, consultarDatos::class.java)
            startActivity(intent)
        }
        btnIr2.setOnClickListener(){
            val intent = Intent(this, update_delete::class.java)
            startActivity(intent)
        }


        btnInsert.setOnClickListener {
            db.collection("Platillo").document(txtId.text.toString()).set(
                hashMapOf(
                    "id" to txtId.text.toString(),
                    "nombre" to txtnombre.text.toString(),
                    "region" to txtregion.text.toString(),
                    "ingrediente" to txtingrediente.text.toString(),
                    "pais" to txtpais.text.toString(),
                    "precio" to txtprecio.text.toString().toInt()


                )

            )
            Toast.makeText(baseContext,"Exito al registrar", Toast.LENGTH_SHORT).show()

        }

        btnCerrarSesion.setOnClickListener {
            signOut()
        }

        btnClean.setOnClickListener {
            txtId.setText(" ")
            txtnombre.setText(" ")
            txtregion.setText(" ")
            txtingrediente.setText(" ")
            txtpais.setText(" ")
            txtprecio.setText(" ")
        }
    }



    override fun onBackPressed() {
    }

    private fun signOut() {
        firebaseAuth.signOut()
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

}
