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

class MainActivity7 : AppCompatActivity() {

    private lateinit var btnProximoSoma: Button
    private lateinit var editDestino: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main7)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnProximoSoma = findViewById(R.id.btnProximoSoma)
        editDestino = findViewById(R.id.editDestino)



        btnProximoSoma.setOnClickListener {
            val destino = editDestino.text.toString()

            if (destino.isEmpty()) {
                Toast.makeText(this, "Insira um destino", Toast.LENGTH_SHORT).show()
            }else {
                val intent = Intent(this, MainActivity8::class.java)
                intent.putExtra("destino", destino)
                startActivity(intent)
            }
        }

    }
}