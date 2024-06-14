package com.example.travelsaver

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity4 : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    private lateinit var btnProximo: Button
    private lateinit var editNome: EditText

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main4)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnProximo = findViewById(R.id.btnProximo)
        editNome = findViewById(R.id.editNome)

        verificarNome()

        btnProximo.setOnClickListener {
            val userId = auth.currentUser?.uid
            val nome = editNome.text.toString()

            if (userId != null && nome.isNotBlank()) {
                databaseReference = FirebaseDatabase.getInstance().getReference("cadastros")
                val users = UserData(userId, nome)
                databaseReference.child(userId).setValue(users)
                    .addOnSuccessListener {
                        val intent = Intent(this, MainActivity5::class.java)
                        startActivity(intent)
                        Toast.makeText(this, "Cadastro realizado", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Falha ao cadastrar", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Usuário não autenticado ou nome vazio", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun verificarNome() {
        auth = FirebaseAuth.getInstance()
        val userId = auth.currentUser?.uid

        if (userId != null) {
            databaseReference = FirebaseDatabase.getInstance().getReference("cadastros")
            databaseReference.child(userId).get().addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    val nome = snapshot.child("nome").value.toString()
                    if (nome != null && nome.isNotBlank()) {
                        Toast.makeText(this, "Nome encontrado!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity5::class.java))
                    }
                }
            }
        }
    }
}
