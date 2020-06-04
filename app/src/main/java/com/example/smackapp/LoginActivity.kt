package com.example.smackapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
    fun loginPageBtnClicked(view: View) {

    }
    fun signUpbtnLoginPageClicked(view: View){
        val createUserBtnIntent=Intent(this, CreateUserActivity::class.java)
        startActivity(createUserBtnIntent)

    }

}