package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var edtEmail: EditText
    private lateinit var edtpassword: EditText
    private lateinit var login: Button
    private lateinit var signup: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        edtEmail = findViewById(R.id.edtEmail)
        edtpassword = findViewById(R.id.edtpassword)
        login = findViewById(R.id.login)
        signup = findViewById(R.id.signup)

        signup.setOnClickListener {
            val intent = Intent(this, sign_up::class.java)
            startActivity(intent)
        }

        login.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtpassword.text.toString()

            loginUser(email, password)
        }
    }


        private fun loginUser(email: String, password: String) {

            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this@Login, MainActivity::class.java)
                        finish()
                        startActivity(intent)

                    } else {
                        Toast.makeText(this@Login, "User does not exist", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
