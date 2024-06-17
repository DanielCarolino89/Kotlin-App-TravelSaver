package com.example.travelsaver

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.DecimalFormat

class MainActivity13 : AppCompatActivity() {

    private lateinit var textConversor: TextView
    private lateinit var editConversor: EditText
    private lateinit var btnConversor: Button
    private lateinit var ibtnVoltar: ImageButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main13)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textConversor = findViewById(R.id.textConversor)
        editConversor = findViewById(R.id.editConversor)
        btnConversor = findViewById(R.id.btnConversor)
        ibtnVoltar = findViewById(R.id.ibtnVoltar)

        ibtnVoltar.setOnClickListener {
            val intent = Intent(this, MainActivity5::class.java)
            startActivity(intent)
        }

        btnConversor.setOnClickListener {
            val moeda = editConversor.text.toString()
            val valor = moeda.toFloatOrNull() ?: 0.0f

            val dolar = "Dolar: $%.2f\n".format(valor / 5.41)
            val euro = "Euro: $%.2f\n".format(valor / 5.80)
            val libra = "Libra: $%.2f\n".format(valor / 6.87)
            val iene = "Iene: $%.2f\n".format(valor / 0.04)
            val franco = "Franco: $%.2f\n".format(valor / 5.99)
            val canadense = "Canadense: $%.2f\n".format(valor / 4.25)
            val australiano = "Australiano: $%.2f\n".format(valor / 3.95)
            val yuan = "Yuan: $%.2f\n".format(valor / 0.84)
            val sueca = "Sueca: $%.2f\n".format(valor / 0.65)
            val neozelandes = "Neozelandes: $%.2f\n".format(valor / 3.60)

            val mensagem = dolar + euro + libra + iene + franco + canadense +
                    australiano + yuan + sueca + neozelandes

            textConversor.text = mensagem
        }
    }
}