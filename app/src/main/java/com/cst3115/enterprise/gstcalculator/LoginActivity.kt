package com.cst3115.enterprise.gstcalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener{
            loginUser()
        }
    }

    private fun loginUser(){
        val username = etUsername.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if(username.isEmpty()){
            etUsername.error="Username is required"
            etUsername.requestFocus()
            return
        }

        if(password.isEmpty()){
            etPassword.error="Password is required"
            etPassword.requestFocus()
            return
        }

        val preferences=getSharedPreferences("userInfo", MODE_PRIVATE)

        val savedUsername=preferences.getString("username", "")
        val savedPassword=preferences.getString("password", "")

        if(username == savedUsername && password == savedPassword){
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "User not registered", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }

}