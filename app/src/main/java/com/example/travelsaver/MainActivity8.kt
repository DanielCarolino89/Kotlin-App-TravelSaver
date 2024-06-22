package com.example.travelsaver

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity8 : AppCompatActivity() {

    private lateinit var editValorViagem: EditText
    private lateinit var btnProximoSoma: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main8)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editValorViagem = findViewById(R.id.editValorViagem)
        btnProximoSoma = findViewById(R.id.btnProximoSoma)

        btnProximoSoma.setOnClickListener {
            val valorViagem = editValorViagem.text.toString()
            if (valorViagem.isEmpty()) {
                Toast.makeText(this, "Insira um local", Toast.LENGTH_SHORT).show()
            }else {
                val destino = intent.getStringExtra("destino")
                val intent = Intent(this, MainActivity9::class.java)
                intent.putExtra("destino", destino)
                intent.putExtra("valorViagem", valorViagem)
                startActivity(intent)
            }
        }
    }
}