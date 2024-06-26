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

        btnProximo.setOnClickListener {
            val auth = FirebaseAuth.getInstance()
            val userId = auth.currentUser?.uid
            val nome = editNome.text.toString()

            if (nome.isEmpty()) {
                Toast.makeText(this, "Digite seu nome", Toast.LENGTH_SHORT).show()
            } else {
                if (userId != null) {
                    val databaseReference = FirebaseDatabase.getInstance().getReference("cadastros")
                    val user = UserData(userId, nome)
                    databaseReference.child(userId).setValue(user)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Entrando...", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MainActivity5::class.java)
                            startActivity(intent)
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "Falha ao entrar, verifique os campos digitados", Toast.LENGTH_SHORT).show()
                        }
                }
            }
        }
    }
}