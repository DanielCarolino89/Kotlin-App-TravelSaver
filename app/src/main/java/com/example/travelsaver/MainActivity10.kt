package com.example.travelsaver

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity10 : AppCompatActivity() {

    private lateinit var editMeses: EditText
    private lateinit var btnProximoSoma: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main10)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editMeses = findViewById(R.id.editMeses)
        btnProximoSoma = findViewById(R.id.btnProximoSoma)

        btnProximoSoma.setOnClickListener {
            val destino = intent.getStringExtra("destino")
            val valorViagem = intent.getStringExtra("valorViagem")
            val ValorMeses = intent.getStringExtra("ValorMeses")
            val valorSalario = editMeses.text.toString()
            val intent = Intent(this, MainActivity8::class.java)
            intent.putExtra("ValorMeses ", ValorMeses)
            intent.putExtra("valorSalario", valorSalario)
            intent.putExtra("valorViagem", valorViagem)
            intent.putExtra("destino", destino)
            startActivity(intent)
        }

    }

}