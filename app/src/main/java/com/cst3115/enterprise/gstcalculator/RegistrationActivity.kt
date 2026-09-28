package com.cst3115.enterprise.gstcalculator

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit

class RegistrationActivity : AppCompatActivity() {
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser(){
        val username = etUsername.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val confirmPassword = etConfirmPassword.text.toString().trim()

        if(username.isEmpty()){
            etUsername.error="Username is required"
            etUsername.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(username).matches()){
            etUsername.error="Please enter a valid email"
            etUsername.requestFocus()
            return
        }

        if (password.isEmpty() || password.length < 8 || !password.matches(Regex(".*[A-Z].*")) ||
            !password.matches(Regex(".*[^a-zA-Z0-9].*"))){
            etPassword.error="Password must be at least 8 characters long, " +
                    "contain at least one uppercase letter and one special character"

            etPassword.requestFocus()
            return
        }

        if (password != confirmPassword){
            etConfirmPassword.error="Passwords do not match"
            etConfirmPassword.requestFocus()
            return
        }

        val preferences = getSharedPreferences("userInfo", MODE_PRIVATE)
        preferences.edit {

            putString("username", username)
            putString("password", password)
        }

        Toast.makeText(this, "User registered successfully",
            Toast.LENGTH_SHORT).show()

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}