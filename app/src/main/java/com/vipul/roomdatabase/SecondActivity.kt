package com.vipul.roomdatabase

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.vipul.roomdatabase.database.AppDatabase
import com.vipul.roomdatabase.database.entities.User
import com.vipul.roomdatabase.databinding.ActivitySecondBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondActivity : AppCompatActivity() {

    private val userDao by lazy {
        AppDatabase.getDatabase(this).userDao()
    }

    private val binding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }

    private val adapterUser by lazy {
        AdapterUser()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        lifecycleScope.launch {
            try {
                val user = withContext(Dispatchers.IO) {
                    userDao.getAllUsers()
                }

                setRecyclerView(user)
            } catch (e: Exception) {
                e.printStackTrace()
            }


        }


    }

    private fun setRecyclerView(user: List<User>) {
        binding.apply {
            recyclerView.apply {
                layoutManager =
                    LinearLayoutManager(this@SecondActivity, LinearLayoutManager.VERTICAL, false)
                adapter = adapterUser.apply {
                    setData(user)
                }
            }
        }
    }
}