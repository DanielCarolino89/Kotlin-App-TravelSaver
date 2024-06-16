package com.example.travelsaver

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity6 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main6)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        verificarNome()
    }

    fun verificarNome() {
        val auth = FirebaseAuth.getInstance()
        val userId = auth.currentUser?.uid

        if (userId != null) {
            val databaseReference = FirebaseDatabase.getInstance().getReference("cadastros")
            databaseReference.child(userId).get().addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    val nome = snapshot.child("nome").value.toString()
                    if (nome.isNotBlank()) {
                        Toast.makeText(this, "Bem Vindo!!!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity5::class.java))
                    } else {
                        startActivity(Intent(this, MainActivity4::class.java))
                    }
                } else {
                    startActivity(Intent(this, MainActivity4::class.java))
                }
            }.addOnFailureListener {
                // Handle the error
                Toast.makeText(this, "Erro ao buscar dados!", Toast.LENGTH_SHORT).show()
            }
        } else {
            // Handle case where userId is null
            Toast.makeText(this, "Usuário não autenticado!", Toast.LENGTH_SHORT).show()
        }
    }
}