package com.example.smackapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class CreateUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)
    }

    fun generateUserAvatar(view: View){
        val generateAvatarIntent= Intent(this, CreateUserActivity::class.java)
        startActivity(generateAvatarIntent)
    }

    fun generateBgroundColorClicked(view: View){
        val generateColorIntent= Intent(this, CreateUserActivity::class.java)
        startActivity(generateColorIntent)
    }

    fun createUserbtnClicked(view: View){

    }
}