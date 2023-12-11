package com.example.apploginfirebase.ui.views

import android.os.Bundle
import android.content.Intent
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.example.apploginfirebase.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.GoogleAuthProvider
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp

class LoginActivity : AppCompatActivity() {

    var binding: ActivityLoginBinding? = null
    lateinit var auth:FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(
            layoutInflater
        )
        setContentView(binding!!.root)

        FirebaseApp.initializeApp(this);
        auth = FirebaseAuth.getInstance();

        binding!!.login.setOnClickListener(login)
        binding!!.cadastro.setOnClickListener(cadastrar)
        binding!!.google.setOnClickListener(logarComGoogle)
    }

    override fun onResume() {
        super.onResume()
        if (auth.currentUser != null) {
            startActivity(Intent(applicationContext, HomeActivity::class.java))
            finish()
        }
    }

    fun checkInputs(view: View?): Boolean {
        val email = binding!!.username.text.toString()
        val pass = binding!!.password.text.toString()
        if (email.isEmpty() || pass.isEmpty()) {
            Snackbar.make(view!!, "Necessário informar email e senha", Snackbar.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }

    var logarComGoogle = View.OnClickListener {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("270306745067-5sush0dcvekcmii6vgp8nsrqv77q3r7h.apps.googleusercontent.com")
            .requestEmail()
            .build()
        val mGoogleApiClient = GoogleApiClient.Builder(applicationContext)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build()
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(signInIntent, 200)
    }
    var cadastrar = View.OnClickListener {
        val intent = Intent(applicationContext, CadastroActivity::class.java)
        startActivity(intent)
        finish()
    }

    // autenticação por email e senha
    var login = View.OnClickListener { view ->
        if (checkInputs(view)) {
            val email = binding!!.username.text.toString()
            val pass = binding!!.password.text.toString()
            auth.signInWithEmailAndPassword(email, pass)
                .addOnSuccessListener {
                    startActivity(Intent(applicationContext, HomeActivity::class.java))
                    finish()
                }
                .addOnFailureListener { e ->
                    if (e is FirebaseAuthEmailException || e is FirebaseAuthWeakPasswordException) {
                        Snackbar.make(
                            view,
                            "Email ou senha estão incorretos",
                            Snackbar.LENGTH_SHORT
                        )
                            .show()
                    } else if (e is FirebaseAuthInvalidUserException) {
                        Snackbar.make(view, "Usuário não encontrado", Snackbar.LENGTH_SHORT)
                            .show()
                    }
                }
        }
    }

    // autenticação via google
    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount?) {
        val credential = GoogleAuthProvider.getCredential(acct!!.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task: Task<AuthResult?> ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                } else {
                    // Tratar o erro de autenticação
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 200) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data!!)
            if (result!!.isSuccess) {
                val account = result.signInAccount
                firebaseAuthWithGoogle(account)
            } else {
                // Tratar o erro de login
            }
        }
    }
}