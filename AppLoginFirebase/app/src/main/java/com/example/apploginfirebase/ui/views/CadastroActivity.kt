package com.example.apploginfirebase.ui.views

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.storage.FirebaseStorage
import android.os.Bundle
import android.app.ProgressDialog
import com.bumptech.glide.Glide
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.android.material.snackbar.Snackbar
import android.content.Intent
import android.provider.MediaStore
import android.view.View
import com.example.apploginfirebase.R
import com.example.apploginfirebase.databinding.ActivityCadastroBinding
import com.example.apploginfirebase.ui.controller.ControllerCredenciais
import com.example.apploginfirebase.ui.controller.ControllerUsuarios
import com.example.apploginfirebase.ui.models.Usuario
import java.io.ByteArrayOutputStream
import java.io.IOException

class CadastroActivity : AppCompatActivity() {

    var controllerUsuarios: ControllerUsuarios? = null
    var binding: ActivityCadastroBinding? = null
    var usuario = Usuario()
    var image_data: ByteArray? = null
    var currentUsuario: Usuario? = null
    var storageRef = FirebaseStorage.getInstance().getReference("usuarios")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val bundle = intent.extras
        if (bundle != null) {
            currentUsuario = bundle.getSerializable("usuario") as Usuario?
            preencherCampos()
        }

        controllerUsuarios = ControllerUsuarios(this)

        binding!!.salvar.setOnClickListener(salvarUsuario)
        binding!!.imageView.setOnClickListener(abrirGaleria)

    }

    fun preencherCampos() {

        val dialog = ProgressDialog(this)
        dialog.setTitle("Carregando dados do usuário")
        dialog.setCancelable(false)
        dialog.show()

        val imagemRef = storageRef
            .child(currentUsuario!!.id!!)
            .child("perfil/foto_perfil");

        imagemRef.downloadUrl.addOnSuccessListener { uri ->
            Glide.with(applicationContext)
                .load(uri)
                .into(binding!!.imageView)
            dialog.dismiss()
        }

        binding!!.nome.setText(currentUsuario!!.nome)
        binding!!.email.setText(currentUsuario!!.email)
    }

    fun byteArrayToBitmap(bytes: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size, null)
    }

    fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        return baos.toByteArray()
    }

    fun checkInputs(view: View?): Boolean {

        val nome = binding!!.nome.text.toString()
        val email = binding!!.email.text.toString()
        val pass = binding!!.senha.text.toString()
        if (nome.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            Snackbar.make(view!!, "Necessário informar nome, email e senha", Snackbar.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }

    fun salvarDadosCadastro(usuario: Usuario?) {

        binding!!.progressBar.visibility = View.VISIBLE
        val controllerCredenciais = ControllerCredenciais(this)
        if (usuario != null) {
            controllerCredenciais.salvarCredenciais(usuario, binding!!.senha.text.toString())
        }

    }

    fun atualizarDadosCadastrais() {

        val controllerUsuarios = ControllerUsuarios(this)

        currentUsuario!!.image_profile = image_data
        currentUsuario!!.nome = binding!!.nome.text.toString()
        currentUsuario!!.email = binding!!.email.text.toString()
        controllerUsuarios.salvarUsuario(currentUsuario!!)
    }

    var abrirGaleria = View.OnClickListener {

        var intent: Intent? =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent = Intent.createChooser(intent, "Escolha um app")
        startActivityForResult(intent, REQUEST_GALERY)
    }

    var salvarUsuario = View.OnClickListener { view ->
        if (currentUsuario != null) {
            atualizarDadosCadastrais()
            return@OnClickListener
        }

        if (checkInputs(view)) {
            usuario.nome = binding!!.nome.text.toString()
            usuario.email = binding!!.email.text.toString()

            if (image_data != null) usuario.image_profile = image_data
            salvarDadosCadastro(usuario)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            try {
                var bitmap: Bitmap? = null
                if (requestCode == REQUEST_GALERY) {
                    val uri = data!!.data
                    bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                    if (bitmap != null) {
                        binding!!.imageView.setImageBitmap(bitmap)
                        image_data = bitmapToByteArray(bitmap)
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        const val REQUEST_GALERY = 200
    }
}
