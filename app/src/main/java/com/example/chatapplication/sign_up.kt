package com.example.chatapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class sign_up : AppCompatActivity() {


    private lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtpassword: EditText
    private lateinit var signup: Button

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.hide()

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtpassword = findViewById(R.id.edtpassword);

        signup= findViewById(R.id.signup);
        mAuth = FirebaseAuth.getInstance()

        signup.setOnClickListener {
            val name = edtName.text.toString()
            val email = edtEmail.text.toString()
            val password = edtpassword.text.toString()

            signup(name, email ,password)
        }

    }
    @SuppressLint("SuspiciousIndentation")
    private fun signup(name:String, email:String, password:String){

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    adduserToDatabase(name,email,mAuth.currentUser?.uid!!)

                    val intent = Intent(this@sign_up,MainActivity::class.java)

                       startActivity(intent)
                    finish()

                } else {
                      Toast.makeText(this@sign_up,"some error",Toast.LENGTH_SHORT).show()
                }
            }
    }



    private fun adduserToDatabase(name:String , email:String , uid:String){

        mDbRef = FirebaseDatabase.getInstance().getReference()

        mDbRef.child("user").child(uid).setValue(user(name,email,uid))

    }
}
