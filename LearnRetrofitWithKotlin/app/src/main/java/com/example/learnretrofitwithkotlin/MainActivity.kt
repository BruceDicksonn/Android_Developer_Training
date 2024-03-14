package com.example.learnretrofitwithkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.example.learnretrofitwithkotlin.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity() {

    lateinit var listaPosts: List<PostEntity>
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = RetrofitClient.getService(PostService::class.java)
        val callback = retrofit.list()

        callback.enqueue( object : Callback<List<PostEntity>> {
            override fun onResponse(call: Call<List<PostEntity>>,response: Response<List<PostEntity>>) {
                if(response.isSuccessful) {
                    listaPosts = response.body() ?: ArrayList()
                    initComponents()
                }
            }

            override fun onFailure(call: Call<List<PostEntity>>, t: Throwable) {
                throw (t)
            }

        })

    }

    fun initComponents() {
        binding.lista.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaPosts)
    }
}