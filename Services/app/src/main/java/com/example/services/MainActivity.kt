package com.example.services

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.services.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //activity_main ==>> Activity_Main_Binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            binding.button.setOnClickListener {
                startService(Intent(this@MainActivity, MyService::class.java))
                Log.e("myTag", "success")
            }

            binding.button2.setOnClickListener {
                stopService(Intent(this@MainActivity, MyService::class.java))
                Log.e("myTag", "stopped")

            }

            binding.button3.setOnClickListener {
                Intent(this@MainActivity, MyForeGroundService::class.java).also {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        startForegroundService(it)
                    } else {
                        startService(it)
                    }
                }

            }

            binding.button4.setOnClickListener {
                Intent(this@MainActivity, MyForeGroundService::class.java).also {
                        stopService(it)
                }
            }
        }
    }
}