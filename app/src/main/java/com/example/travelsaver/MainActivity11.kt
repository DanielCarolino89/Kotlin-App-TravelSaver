package com.example.travelsaver

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.DecimalFormat

class MainActivity11 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main11)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        resultado()
    }

    fun resultado() {
        val destino = intent.getStringExtra("destino")
        val valorViagem = intent.getStringExtra("valorViagem")?.toDoubleOrNull() ?: 0.0
        val valorSalario = intent.getStringExtra("valorSalario")?.toDoubleOrNull() ?: 0.0
        val valorMeses = intent.getStringExtra("valorMeses")?.toIntOrNull() ?: 1

        val DF = DecimalFormat("#,##")

        val QtaMensal = if(valorViagem != 0.0) valorViagem / valorMeses else 0.0
        val DF_QtaMensal = DF.format(QtaMensal)

        val QtaMensal2 = if(valorSalario != 0.0) valorSalario * 0.15 else 0.0
        val DF_QtaMensal2 = DF.format(QtaMensal2)

        val QtaMensal3 = if(valorViagem != 0.0) valorViagem / QtaMensal2 else 0.0
        val DF_QtaMensal3 = DF.format(QtaMensal3)

        val mensagem = "Destino: $destino,\n" +
                "Valor da Viagem: $valorViagem,\n\n" +
                "Opção 1: Para assegurar o valor da viagem, você terá que guardar R$$DF_QtaMensal, em $valorMeses meses\n" +
                "Opção 2: Com base em seu salário, para obter o valor da viagem, você terá que guardar R$$DF_QtaMensal2, em $DF_QtaMensal3 meses"

        val intent = Intent(this, MainActivity12::class.java)
        intent.putExtra("mensagem", mensagem)
        startActivity(intent)

    }
}
