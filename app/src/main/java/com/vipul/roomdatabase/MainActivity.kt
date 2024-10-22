package com.vipul.roomdatabase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.vipul.roomdatabase.database.AppDatabase
import com.vipul.roomdatabase.database.entities.User
import com.vipul.roomdatabase.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val userDao by lazy {
        AppDatabase.getDatabase(this).userDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setListener()

    }

    private fun setListener() {

        binding.apply {
            save.setOnClickListener {
                try {
                    val name = tvName.text.toString().trim()
                    val mobileNumber = tvMobile.text.toString().trim()

                    if (name.isNotEmpty() && mobileNumber.isNotEmpty()) {
                        val newUser = User(name = name, mobileNumber = mobileNumber)
                        lifecycleScope.launch {
                            withContext(Dispatchers.IO) {
                                userDao.insert(newUser)
                            }

                            withContext(Dispatchers.Main) {
                                tvName.text.clear()
                                tvMobile.text.clear()
                                Toast.makeText(this@MainActivity,"Data inserted successfully.",Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                } catch (e: Exception) {
                    Log.e("TAG===>", e.printStackTrace().toString())
                }
            }

            viewData.setOnClickListener {
                startActivity(Intent(this@MainActivity, SecondActivity::class.java))
            }
        }
    }

}