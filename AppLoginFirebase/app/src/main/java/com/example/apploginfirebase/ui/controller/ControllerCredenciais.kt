package com.example.apploginfirebase.ui.controller

import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import android.content.Context
import com.example.apploginfirebase.ui.models.Usuario

class ControllerCredenciais(var context: Context) {
    var auth = FirebaseAuth.getInstance()

    //    DatabaseReference database = FirebaseDatabase.getInstance().getReference("usuarios");
    //    StorageReference storage = FirebaseStorage.getInstance().getReference("usuarios");
    var isSuccess = false
    fun salvarCredenciais(usuario: Usuario, password: String?) {
        auth.createUserWithEmailAndPassword(usuario.email!!, password!!)
            .addOnSuccessListener {
                val controllerUsuarios = ControllerUsuarios(context)
                usuario.id = auth.currentUser!!.uid
                controllerUsuarios.salvarUsuario(usuario)
            }.addOnFailureListener { e ->
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
    }
}