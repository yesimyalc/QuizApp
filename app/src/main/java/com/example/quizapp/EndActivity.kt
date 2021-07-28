package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class EndActivity : AppCompatActivity() {

    companion object {

        const val NAME="name"
        const val SCORE="score"
        const val QUESTIONCOUNT="count"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end)
        val username=findViewById<TextView>(R.id.username)
        val score=findViewById<TextView>(R.id.score)
        val userNAME=intent.getStringExtra(NAME)
        val userSCORE=intent.getStringExtra(SCORE)
        val questionCOUNT=intent.getStringExtra(QUESTIONCOUNT)

        username.setText(userNAME)
        score.setText("Your score is "+userSCORE+" out of "+questionCOUNT)
    }

    fun onFinish(view: View)
    {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}