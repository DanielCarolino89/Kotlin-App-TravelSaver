package com.example.travelsaver

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity5 : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var textNomePessoa: TextView
    private lateinit var ibtnAdicionar: ImageButton
    private lateinit var ibtnHome: ImageButton
    private lateinit var ibtnDinheiro: ImageButton


    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main5)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()

        textNomePessoa = findViewById(R.id.textNomePessoa)
        ibtnAdicionar = findViewById(R.id.ibtnAdicionar)
        ibtnHome = findViewById(R.id.ibtnHome)
        ibtnDinheiro = findViewById(R.id.ibtnDinheiro)

        perfil()

        ibtnDinheiro.setOnClickListener {
            val intent = Intent(this, MainActivity13::class.java)
            startActivity(intent)
        }

        ibtnAdicionar.setOnClickListener {
            val intent = Intent(this, MainActivity7::class.java)
            startActivity(intent)
        }

        ibtnHome.setOnClickListener {
            auth.signOut()
            Toast.makeText(this, "Desconectado!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun perfil() {
        auth = FirebaseAuth.getInstance()
        val userId = auth.currentUser?.uid

        if (userId != null) {
            databaseReference = FirebaseDatabase.getInstance().getReference("cadastros")
            databaseReference.child(userId.toString()).get().addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    val nome = snapshot.child("nome").value.toString()

                    textNomePessoa.text = "Olá, $nome"
                } else {
                    Toast.makeText(this, "Usuário não encontrado no banco de dados", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener { e ->
                Toast.makeText(this, "Erro ao buscar dados: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Usuário não autenticado", Toast.LENGTH_SHORT).show()
        }
    }
}