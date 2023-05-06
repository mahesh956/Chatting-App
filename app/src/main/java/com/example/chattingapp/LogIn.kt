package com.example.chattingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {

    private lateinit var Email:EditText
    private lateinit var password:EditText
    private lateinit var login: Button
    private lateinit var signup:Button
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        supportActionBar?.hide()
        mAuth= FirebaseAuth.getInstance();
        Email=findViewById(R.id.Email);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        signup=findViewById(R.id.signup);

        signup.setOnClickListener{
            val intent=Intent(this,Signup::class.java)
            startActivity(intent)
        }
        login.setOnClickListener{
            val email=Email.text.toString()
            val password=password.text.toString()

            login(email,password);
        }
    }

    private fun login(email: String,password: String){
        mAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    val intent= Intent(this@LogIn,MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this@LogIn,"User does not Exist", Toast.LENGTH_SHORT).show()
                }
            }
    }
}