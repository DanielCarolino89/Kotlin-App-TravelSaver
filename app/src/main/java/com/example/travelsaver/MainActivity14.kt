package com.example.travelsaver

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity14 : AppCompatActivity() {


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main14)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnVoltar = findViewById<Button>(R.id.btnVoltar)

        btnVoltar.setOnClickListener {
            val intent = Intent(this, MainActivity5::class.java)
            startActivity(intent)
        }

        val btnExcluir = findViewById<Button>(R.id.btnExcluir)

        btnExcluir.setOnClickListener {
            deleteData()
            val intent = Intent(this, MainActivity5::class.java)
            startActivity(intent)
        }

        readData()
    }

    private fun readData() {
        val textCampo = findViewById<TextView>(R.id.textCampo)

        val pesquisar = intent.getStringExtra("pesquisar").toString()
        val auth = FirebaseAuth.getInstance()
        val userId = auth.currentUser?.uid

        if (userId != null) {
            val databaseReference =
                FirebaseDatabase.getInstance().getReference("postagem").child(userId)
                    .child(pesquisar)

            databaseReference.get().addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    val texto = snapshot.child("texto").value
                    textCampo.text = texto.toString()

                }
            }
        }
    }

    private fun deleteData() {
        val pesquisar = intent.getStringExtra("pesquisar").toString()
        val auth = FirebaseAuth.getInstance()
        val userId = auth.currentUser?.uid

        if (userId != null) {
            val databaseReference = FirebaseDatabase.getInstance().getReference("postagem").child(userId).child(pesquisar)

            databaseReference.removeValue().addOnSuccessListener {
                Toast.makeText(this, "Postagem deletada com sucesso", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Erro ao deletar postagem", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Usuário não autenticado", Toast.LENGTH_SHORT).show()
        }
    }

}