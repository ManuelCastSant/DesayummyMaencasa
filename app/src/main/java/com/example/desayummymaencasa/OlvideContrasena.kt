package com.example.desayummymaencasa

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class OlvideContrasena : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_olvide_contrasena)

        val txt_mail = findViewById<TextInputEditText>(R.id.txt_correo_recu)
        val btn_enviar = findViewById<Button>(R.id.btn_recuperacion)
        val btn_regresar = findViewById<Button>(R.id.btn_regresar)
        firebaseAuth = Firebase.auth

        btn_enviar.setOnClickListener()
        {
            if (txt_mail.text.toString().trim().isEmpty()){
                txt_mail.setError("Ingrese su email")
                return@setOnClickListener
            }else{
                resetCount(txt_mail.text.toString())
            }
        }

        btn_regresar.setOnClickListener()
        {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }



    }
    private fun resetCount(email:String)
    {
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener(){task ->
                if(task.isSuccessful){
                    Toast.makeText(baseContext,"Mensaje enviado de restablecimiento de contraseña", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toast.makeText(baseContext,"Error al enviar el restablecimiento", Toast.LENGTH_SHORT).show()

                }

            }
    }
}