package com.example.apploginfirebase.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.FirebaseAuth
import com.example.apploginfirebase.R
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import android.widget.Toast
import com.example.apploginfirebase.ui.models.Usuario
import com.google.firebase.database.DatabaseError

class HomeActivity : AppCompatActivity() {
    var databaseRef = FirebaseDatabase.getInstance().getReference("usuarios")
    var authRef = FirebaseAuth.getInstance()
    var currentUser: Usuario? = Usuario()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val query = databaseRef.orderByKey().equalTo(authRef.uid)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children) {
                    currentUser = dataSnapshot.getValue(Usuario::class.java)
                    if (currentUser != null) {
                        break
                    }
                }
                Toast.makeText(
                    this@HomeActivity,
                    "Seja bem-vindo " + currentUser!!.nome,
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.perfil -> {

                val intent = Intent(applicationContext, CadastroActivity::class.java)
                intent.putExtra("usuario", currentUser)
                startActivity(intent)

            }
            R.id.logout -> {

                authRef.signOut()
                startActivity(Intent(applicationContext, LoginActivity::class.java))
                finish()

            }
        }
        return super.onOptionsItemSelected(item)
    }
}