package com.example.apploginfirebase.ui.controller
import com.google.android.gms.tasks.OnSuccessListener
import android.widget.Toast
import android.content.Intent
import android.content.Context
import com.example.apploginfirebase.ui.models.Usuario
import com.example.apploginfirebase.ui.views.HomeActivity
import com.google.firebase.database.FirebaseDatabase
import java.lang.Exception

class ControllerUsuarios(var context: Context) {
    var database = FirebaseDatabase.getInstance().getReference("usuarios")
    var isSuccess = false
    fun createUser(user: Usuario?): Boolean {
        database.push().setValue(user)
            .addOnSuccessListener { onused: Void? ->
                Toast.makeText(context, "Usuário salvo com sucesso!", Toast.LENGTH_SHORT).show()
                isSuccess = true
            }
            .addOnFailureListener { e: Exception ->
                Toast.makeText(
                    context,
                    "Erro ao salvar usuário:    " + e.message,
                    Toast.LENGTH_SHORT
                ).show()
                isSuccess = false
            }
        return isSuccess
    }

    fun findUser(cpf: String?): Usuario? {
        return null
    }

    fun updateUser(usuario: Usuario?): Usuario? {
        return null
    }

    fun delete(cpf: String?): Boolean {
        return false
    }

    fun salvarUsuario(usuario: Usuario) {
        val newUser = Usuario(
            usuario.id,
            usuario.nome,
            usuario.email
        )
        database.child(usuario.id!!)
            .setValue(newUser)
            .addOnSuccessListener(OnSuccessListener {
                val controllerFotos = ControllerFotos(context)
                if (usuario.image_profile != null) {
                    controllerFotos.salvarFotoUsuario(usuario.image_profile)
                    return@OnSuccessListener
                }
                val intent = Intent(context, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }).addOnFailureListener { e ->
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
    }
}