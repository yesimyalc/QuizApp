package com.example.quizapp

import java.util.*

class Question constructor(id:Int, question:String, img:Int, a1: String, a2:String, a3:String, a4:String, answer:Int)
{
    val id:Int
    var questionBody:String
        private set
    val image:Int
    var answer1:String=a1
        private set
    var answer2:String=" "
        private set(newAnswer:String)
        {
            var setAnswer:String=newAnswer
            while(setAnswer.equals(answer1)){
                println("Answer 2 already exists. Please enter again.")
                setAnswer=readLine()!!
            }
            field=setAnswer
        }
    var answer3:String=" "
        private set(newAnswer:String)
        {
            var setAnswer=newAnswer
            while(setAnswer.equals(answer1) && setAnswer.equals(answer2)){
                println("Answer 3 already exists.. Please enter again.")
                setAnswer=readLine()!!
            }
            field=setAnswer
        }
    var answer4:String=" "
        private set(newAnswer:String)
        {
            var setAnswer=newAnswer
            while(setAnswer.equals(answer1) && setAnswer.equals(answer2) && setAnswer.equals(answer3)){
                println("Answer 4 already exists. Please enter again.")
                setAnswer=readLine()!!
            }
            field=setAnswer
        }
    var rightAnswer:Int=0
        private set(newAnswer:Int)
        {
            var setAnswer=newAnswer
            val reader = Scanner(System.`in`)
            while(setAnswer!=1 && setAnswer!=2 && setAnswer!=3 && setAnswer!=4){
                println("You have entered an invalid right answer for the question. Please enter again.")
                setAnswer=reader.nextInt()
            }
            field=setAnswer
        }

    init{
        this.id=id
        image=img
        questionBody=question
        answer2=a2
        answer3=a3
        answer4=a4
        rightAnswer=answer
    }

}