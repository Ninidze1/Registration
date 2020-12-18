package com.example.registrationtonothing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class CheckActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    private lateinit var MailInput: TextView
    private lateinit var PasswordInput: TextView
    private lateinit var CheckifExistsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check)

        mAuth = FirebaseAuth.getInstance()

        MailInput = findViewById(R.id.checkEmailInputEditText)
        PasswordInput = findViewById(R.id.checkPasswordInputEditText)
        CheckifExistsButton = findViewById(R.id.CheckButton)

        CheckifExistsButton.setOnClickListener {

            val email = MailInput.text.toString()
            val password = PasswordInput.text.toString()

            if (email.isEmpty() || password.isEmpty()) {

                Toast.makeText(this, "ველები ცარიელია, ხელახლა სცადეთ", Toast.LENGTH_SHORT).show()

            } else {

                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful) {

                            Toast.makeText(this, "ეს მომხმარებელი არის Firebase-ის ბაზაში", Toast.LENGTH_LONG).show()

                        } else {

                            Toast.makeText(this, "მომხმარებელი არ არის დარეგისტრირებული", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainActivity::class.java))

                        }

                    }

            }

        }

    }

}