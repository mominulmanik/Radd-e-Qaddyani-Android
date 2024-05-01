package com.mominul.radd_e_qadyyani.Model

import java.io.Serializable


data class QuestionAnswer(
    val question: String?,
    val answer: ArrayList<String>?,
    val questions: ArrayList<QuestionAnswer>?
) : Serializable
