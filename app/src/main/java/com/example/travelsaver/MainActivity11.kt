package com.example.travelsaver

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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

        val QtaMensal = if (valorViagem != 0.0) valorViagem / valorMeses else 0.0

        val QtaMensal2 = if (valorSalario != 0.0) valorSalario * 0.15 else 0.0

        val QtaMensal3 = if (valorViagem != 0.0) (valorViagem / QtaMensal2).toInt() else 0

        val mensagem = "Destino: $destino,\n" +
                "Valor da Viagem: $${"%.2f".format(valorViagem)},\n\n" +
                "Opção 1:\nPara assegurar o valor da viagem, você terá que guardar R$${"%.2f".format(QtaMensal)}, em $valorMeses meses\n\n" +
                "Opção 2:\nCom base em seu salário, para obter o valor da viagem, você terá que guardar R$${"%.2f".format(QtaMensal2)}, em $QtaMensal3 meses"

        val intent = Intent(this, MainActivity12::class.java)
        intent.putExtra("mensagem", mensagem)
        startActivity(intent)

        addUserEntry(destino.toString(), mensagem)
    }

    fun addUserEntry(titulo: String, texto: String) {
        val auth = FirebaseAuth.getInstance()
        val userId = auth.currentUser?.uid.toString()

        val databaseReference = FirebaseDatabase.getInstance().getReference("postagem")

        val query = databaseReference.child(userId).orderByKey().limitToLast(1)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var nextId = 1

                for (entrySnapshot in snapshot.children) {
                    nextId = entrySnapshot.key?.toInt()?.plus(1) ?: nextId
                }

                val entry = UserEntry(nextId.toString(), titulo, texto)

                databaseReference.child(userId).child(nextId.toString()).setValue(entry)
                    .addOnSuccessListener {
                        Toast.makeText(applicationContext, "Entrada adicionada com sucesso", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(applicationContext, "Erro ao adicionar entrada: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}
