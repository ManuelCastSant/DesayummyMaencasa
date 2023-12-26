package com.example.desayummymaencasa

import android.annotation.SuppressLint
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desayummymaencasa.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class consultarDatos : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<User>
    private lateinit var myAdapter: MyAdapter
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar_datos)
        db = Firebase.firestore
        firebaseAuth = Firebase.auth

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        userArrayList = arrayListOf()
        myAdapter = MyAdapter(userArrayList)
        recyclerView.adapter = myAdapter

        EventChangeListener()

    }

    private fun EventChangeListener() {
        db = FirebaseFirestore.getInstance()
        db.collection("Platillo").addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if (error != null) {
                    Log.e(ContentValues.TAG,"Firestore error")
                    return
                }
                for (dc: DocumentChange in value?.documentChanges!!) {
                    if (dc.type == DocumentChange.Type.ADDED) {
                        userArrayList.add(dc.document.toObject(User::class.java))
                    }
                }
                myAdapter.notifyDataSetChanged()
            }
        })
    }
}
