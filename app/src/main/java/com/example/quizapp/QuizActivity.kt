package com.example.quizapp

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.quizapp.Constants

class QuizActivity : AppCompatActivity() {

    private var mCurrentPosition:Int=1
    private var mQuestionsList:ArrayList<Question>?=null
    private var mSelectedOption:Int=0
    private var rightAnswered:Int=0
    private var submitted=false
    private var username:String?=null

    private lateinit var question:TextView
    private lateinit var questionImage:ImageView
    private lateinit var answerOne:TextView
    private lateinit var answerTwo:TextView
    private lateinit var answerThree:TextView
    private lateinit var answerFour:TextView
    private lateinit var progressBar:ProgressBar
    private lateinit var progress:TextView
    private lateinit var currentQuestion:Question
    private lateinit var submit:Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        username=intent.getStringExtra(EndActivity.NAME)

        mQuestionsList=Constants.getQuestions()
        mCurrentPosition=1
        showQuestion()
    }

    private fun showQuestion()
    {
        currentQuestion=mQuestionsList!!.get(mCurrentPosition-1)
        question=findViewById<TextView>(R.id.question)
        questionImage=findViewById<ImageView>(R.id.questionImage)
        answerOne=findViewById<TextView>(R.id.answerOne)
        answerTwo=findViewById<TextView>(R.id.answerTwo)
        answerThree=findViewById<TextView>(R.id.answerThree)
        answerFour=findViewById<TextView>(R.id.answerFour)
        progressBar=findViewById<ProgressBar>(R.id.progressBar)
        progress=findViewById<TextView>(R.id.progress)
        submit=findViewById(R.id.submit)

        defaultOptionsView()
        question.text = currentQuestion.questionBody
        questionImage.setImageResource(currentQuestion.image)
        answerOne.text = currentQuestion.answer1
        answerTwo.text = currentQuestion.answer2
        answerThree.text = currentQuestion.answer3
        answerFour.text = currentQuestion.answer4
        progress.text = ""+mCurrentPosition+"/"+progressBar.max
        progressBar.progress=mCurrentPosition
    }

    private fun defaultOptionsView()
    {
        val options=ArrayList<TextView>()
        options.add(answerOne)
        options.add(answerTwo)
        options.add(answerThree)
        options.add(answerFour)

        for(option in options)
        {
            option.typeface= Typeface.DEFAULT
            option.background=ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    private fun selectedOptionView(tv: TextView, optionNo: Int)
    {
        defaultOptionsView()
        mSelectedOption=optionNo

        tv.typeface=Typeface.DEFAULT_BOLD
        tv.background=ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }

    private fun rightAnswerView()
    {
        defaultOptionsView()
        var rightAnswer:TextView
        if(currentQuestion.rightAnswer==1)
            rightAnswer=answerOne
        else if(currentQuestion.rightAnswer==2)
            rightAnswer=answerTwo
        else if(currentQuestion.rightAnswer==3)
            rightAnswer=answerThree
        else
            rightAnswer=answerFour

        rightAnswer.typeface= Typeface.DEFAULT_BOLD
        rightAnswer.background=ContextCompat.getDrawable(this, R.drawable.right_answer_border_bg)

    }

    private fun wrongAnswerView()
    {
        var wrongAnswer:TextView
        if(mSelectedOption==1)
            wrongAnswer=answerOne
        else if(mSelectedOption==2)
            wrongAnswer=answerTwo
        else if(mSelectedOption==3)
            wrongAnswer=answerThree
        else
            wrongAnswer=answerFour

        wrongAnswer.typeface= Typeface.DEFAULT
        wrongAnswer.background=ContextCompat.getDrawable(this, R.drawable.wrong_answer_border__bg)
    }

    fun onAnswerClick(view: View)
    {
        if(submitted==false)
        {
            if((view as TextView).id==R.id.answerOne)
                selectedOptionView(view, 1)
            else if((view).id==R.id.answerTwo)
                selectedOptionView(view, 2)
            else if(view.id==R.id.answerThree)
                selectedOptionView(view, 3)
            else if(view.id==R.id.answerFour)
                selectedOptionView(view, 4)
        }
    }

    fun onSubmit(view: View)
    {
        if(submit.text.toString()=="SUBMIT" && mSelectedOption!=0 && mCurrentPosition!=mQuestionsList!!.size) {
            rightAnswerView()
            if (currentQuestion.rightAnswer != mSelectedOption)
                wrongAnswerView()
            else
                rightAnswered++

            println(rightAnswered)
            submit.setText("GO TO NEXT QUESTION")
            submitted=true
        }
        else if(submit.text.toString()=="SUBMIT" && mSelectedOption!=0 && mCurrentPosition==mQuestionsList!!.size)
        {
            rightAnswerView()
            if (currentQuestion.rightAnswer != mSelectedOption)
                wrongAnswerView()
            else
                rightAnswered++

            println(rightAnswered)
            submit.setText("END QUIZ")
            submitted=true
        }
        else if(submit.text.toString()=="GO TO NEXT QUESTION")
        {
            defaultOptionsView()
            submit.setText("SUBMIT")
            submitted=false
            mCurrentPosition++
            currentQuestion=mQuestionsList!!.get(mCurrentPosition-1)
            mSelectedOption=0
            showQuestion()
        }
        else if(submit.text.toString()=="END QUIZ")
        {
            val intent = Intent(this, EndActivity::class.java)
            intent.putExtra(EndActivity.NAME, username)
            intent.putExtra(EndActivity.SCORE, rightAnswered.toString())
            intent.putExtra(EndActivity.QUESTIONCOUNT, mQuestionsList!!.size.toString())
            startActivity(intent)
            finish()
        }
    }
}