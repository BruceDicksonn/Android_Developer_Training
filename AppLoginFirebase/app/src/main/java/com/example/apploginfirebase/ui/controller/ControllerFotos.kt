package com.example.apploginfirebase.ui.controller

import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import android.content.Intent
import android.app.Activity
import android.content.Context
import com.example.apploginfirebase.ui.views.HomeActivity

class ControllerFotos(var context: Context) {

    var auth = FirebaseAuth.getInstance()
    var storage = FirebaseStorage.getInstance().getReference("usuarios")
    var isSuccess = false

    fun salvarFotoUsuario(image_data: ByteArray?) {
        if (image_data != null) {

            //String nomeArquivo = UUID.randomUUID().toString();

            // Armazena em uma task de updload
            val uploadTask = storage.child(auth.uid!!)
                .child("perfil") //                    .child( nomeArquivo + ".jpeg" ) -> caso queira adicionar várias fotos
                .child("foto_perfil")
                .putBytes(image_data)

            uploadTask.addOnSuccessListener {
                val intent = Intent(context, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
                .addOnFailureListener((context as Activity)) { e ->
                    Toast.makeText(
                        context,
                        "Upload da imagem falhou: " + e.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }
}