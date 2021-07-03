package com.example.myapplication.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

open class OrderBaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun Context.showMessage(message: String?){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }
}