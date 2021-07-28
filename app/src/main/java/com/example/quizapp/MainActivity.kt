package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val enteredName=findViewById<androidx.appcompat.widget.AppCompatEditText>(R.id.enteredName)
        val startButton=findViewById<Button>(R.id.startButton)

        startButton.setOnClickListener{
            if(enteredName.text.toString().isEmpty())
                Toast.makeText(this, "Please Enter Your Name", Toast.LENGTH_SHORT).show()
            else {
                val intent = Intent(this, QuizActivity::class.java)
                intent.putExtra(EndActivity.NAME, enteredName.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}