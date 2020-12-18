package com.example.registrationtonothing

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var confPassInput: EditText
    private lateinit var submitButton: Button
    private lateinit var CheckButton: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Intent(this, BackgroundSoundService::class.java).also { intent ->
            startService(intent)
        }

        mAuth = FirebaseAuth.getInstance()

        emailInput = findViewById(R.id.emailInputEditText)
        passwordInput = findViewById(R.id.passwordInputEditText)
        confPassInput = findViewById(R.id.confPasswordInputEditText)
        submitButton = findViewById(R.id.submitButton)
        CheckButton = findViewById(R.id.CheckButton)

        submitButton.setOnClickListener {

            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            val confirmedPassword = confPassInput.text.toString()

            if (email.isEmpty() || password.isEmpty() || confirmedPassword.isEmpty()) {

                Toast.makeText(this, "ველები ცარიელია, ხელახლა სცადეთ", Toast.LENGTH_SHORT).show()

            } else if (password != confirmedPassword) {

                Toast.makeText(this, "პაროლის ველები არ ემთხვევა ერთმანეთს", Toast.LENGTH_SHORT).show()

            }

            else if ((email.isNotEmpty() || password.isNotEmpty() || confirmedPassword.isNotEmpty()) && password == confirmedPassword) {

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->

                        if (task.isSuccessful) {
                            startActivity(Intent(this,MainActivity::class.java))
                            finish()
                        }
                        else {
                            Toast.makeText(this, "მოხვდა რაღაც შეცდომა", Toast.LENGTH_SHORT).show()
                        }

                    }

            }

        }

        CheckButton.setOnClickListener {

            startActivity(Intent(this, CheckActivity::class.java))

        }

    }

}