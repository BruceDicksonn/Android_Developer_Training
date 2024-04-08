package com.example.learnretrofitwithkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.learnretrofitwithkotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.Exception

@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity() {

    val retrofit: PostService by lazy { RetrofitClient.getService(PostService::class.java) }
    var listaPosts: List<PostEntity> = ArrayList()

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch {
            listaPosts = getList()
            withContext(Dispatchers.Main) {
                if( isActive ) initComponents()
            }
        }

    }

    fun getList() : List<PostEntity> {

        lateinit var response : Response<List<PostEntity>>
        try {
            response = retrofit.list().execute();
            if(response.isSuccessful)  {return response.body() ?: ArrayList()}

            throw(Exception(response.message()))

        } catch (ex: Exception) {
            Log.e("getList", ex.stackTraceToString())
        }

        return ArrayList();
    }

    fun initComponents() {
        binding.lista.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaPosts)
        binding.lista.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, view: View?, index: Int, p1: Long) {

                Toast.makeText(applicationContext, listaPosts[index].title, Toast.LENGTH_LONG).show()
            }
        }
    }
}