package com.example.desayummymaencasa


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnIngresar = findViewById<Button>(R.id.btnIngresar)
        val btnGenerar = findViewById<TextView>(R.id.btnCrearCuenta)
        val txtEmail = findViewById<TextInputEditText>(R.id.edtEmail)
        val txtContra = findViewById<TextInputEditText>(R.id.edtPassword)
        val btnOlvide = findViewById<TextView>(R.id.btn_olvidar)
        firebaseAuth = Firebase.auth

        btnIngresar.setOnClickListener {
            if (txtEmail.text.toString().trim().isEmpty() && txtContra.text.toString().trim().isEmpty()) {
                txtEmail.setError("Ingrese su email")
                txtContra.setError("Ingresa la contraseña")

                return@setOnClickListener
            }else if (txtContra.text.toString().trim().isEmpty()){
                txtContra.setError("Ingresa la contraseña")
                return@setOnClickListener
            }else{
                signIn(txtEmail.text.toString(),txtContra.text.toString())
            }
        }

        btnGenerar.setOnClickListener()
        {
            val i = Intent(this, CrearCuenta::class.java)
            startActivity(i)
        }

        btnOlvide.setOnClickListener(){
            val i = Intent(this, OlvideContrasena::class.java)
            startActivity(i)
        }

    }

        private fun signIn(email: String, password: String)
        {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){ task ->
                    if (task.isSuccessful){
                        val user = firebaseAuth.currentUser
                        //current user usuario actual
                        val verifica = user?.isEmailVerified

                        if (verifica==true)
                        {
                            Toast.makeText(baseContext,"Exito al ingresar", Toast.LENGTH_SHORT).show()
                            //Aqui vamos ir a la segunda activity
                            val i = Intent(this, MainActivity2::class.java)
                            startActivity(i)
                        }
                        else
                        {
                            Toast.makeText(baseContext,"No se ha verificado su correo", Toast.LENGTH_SHORT).show()
                        }

                    }
                    else
                    {
                        Toast.makeText(baseContext,"Error de email y/o contraseña", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    override fun onBackPressed() {
        return
    }
    }