package com.example.travelsaver

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity9 : AppCompatActivity() {

    private lateinit var editValorSalario: EditText
    private lateinit var btnProximoSoma: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main9)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editValorSalario = findViewById(R.id.editValorSalario)
        btnProximoSoma = findViewById(R.id.btnProximoSoma)

        btnProximoSoma.setOnClickListener {
            val destino = intent.getStringExtra("destino")
            val valorViagem = intent.getStringExtra("valorViagem")
            val valorSalario = editValorSalario.text.toString()
            val intent = Intent(this, MainActivity8::class.java)
            intent.putExtra("valorSalario", valorSalario)
            intent.putExtra("valorViagem", valorViagem)
            intent.putExtra("destino", destino)
            startActivity(intent)
        }
    }
}