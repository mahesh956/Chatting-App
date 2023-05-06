package com.example.chattingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Signup : AppCompatActivity() {
    private lateinit var name: EditText

    private lateinit var Email: EditText
    private lateinit var password: EditText
    private lateinit var signup: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        supportActionBar?.hide()
        mAuth= FirebaseAuth.getInstance()
        name=findViewById(R.id.name);
        Email=findViewById(R.id.Email);
        password=findViewById(R.id.password);
        signup=findViewById(R.id.signup);

        signup.setOnClickListener{
            val email=Email.text.toString()
            val password=password.text.toString()

            signup(email,password)
        }

    }
    private fun signup(email: String,password: String){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    val intent= Intent(this@Signup,MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this@Signup,"Error Occured",Toast.LENGTH_SHORT).show()
                }
            }
    }
}