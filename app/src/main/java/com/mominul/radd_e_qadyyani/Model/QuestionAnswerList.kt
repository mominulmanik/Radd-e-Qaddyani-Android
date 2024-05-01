package com.mominul.radd_e_qadyyani.Model

import java.io.Serializable


data class QuestionAnswerList(
    val questions: ArrayList<QuestionAnswer>
) : Serializable