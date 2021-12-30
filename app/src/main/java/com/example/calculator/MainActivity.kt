package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var firstNumber: Double = 0.0
    private var operation: String = ""
    private var secondNumber: Double? = null
    private var clearFlag = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_0.setOnClickListener(digitListener)
        btn_1.setOnClickListener(digitListener)
        btn_2.setOnClickListener(digitListener)
        btn_3.setOnClickListener(digitListener)
        btn_4.setOnClickListener(digitListener)
        btn_5.setOnClickListener(digitListener)
        btn_6.setOnClickListener(digitListener)
        btn_7.setOnClickListener(digitListener)
        btn_8.setOnClickListener(digitListener)
        btn_9.setOnClickListener(digitListener)
        btn_0.setOnClickListener(digitListener)
        btn_dot.setOnClickListener(digitListener)
        btn_sign.setOnClickListener(digitListener)

        // operation Buttons :
        btn_divide.setOnClickListener(operatorListener)
        btn_multiplication.setOnClickListener(operatorListener)
        btn_minus.setOnClickListener(operatorListener)
        btn_add.setOnClickListener(operatorListener)

        // equal Button :
        btn_equal.setOnClickListener {
            execute()
        }

        // percent Button :
        btn_percent.setOnClickListener {
            val result = text_display.text.toString().toDouble() / 100
            text_display.text = result.toString()
        }

        // clear Button :
        btn_clear.setOnClickListener {
            clearTextDisplay()
            firstNumber = 0.0
            secondNumber = null
            operation = ""
            clearFlag = true
        }

    }


    private val digitListener = View.OnClickListener {
        it as Button
        if (clearFlag) {
            clearTextDisplay()
        }
        clearFlag = false
        val oldNumber = text_display.text.toString()
        when (it.id) {
            btn_dot.id -> {
                if (!oldNumber.contains(".")) {
                    text_display.append(".")
                }
            }
            btn_sign.id -> {
                if (oldNumber.first() == '-') {
                    val newNumber = oldNumber.drop(1)
                    text_display.text = newNumber
                } else if (oldNumber != "0" && oldNumber.isNotEmpty()) {
                    text_display.text = "-$oldNumber"
                }
            }
            else -> {
                if (oldNumber == "0" || oldNumber.isEmpty()) {
                    text_display.text = it.text
                } else {
                    text_display.append(it.text)
                }
            }
        }
    }

    private val operatorListener = View.OnClickListener {
        it as Button
        when (it.id) {
            btn_divide.id -> {
                operation = "/"
            }
            btn_multiplication.id -> {
                operation = "*"
            }
            btn_minus.id -> {
                operation = "-"
            }
            btn_add.id -> {
                operation = "+"
            }
        }
        firstNumber = text_display.text.toString().toDouble()
        secondNumber = null
        clearFlag = true
    }

    private fun clearTextDisplay() {
        text_display.text = "0"
    }

    private fun execute(){
        if (operation != "") {
            val second : Double = if (secondNumber == null){
                text_display.text.toString().toDouble()
            } else {
                secondNumber as Double
            }
            var result: Double? = null
            when (operation) {
                "/" -> {
                    result = firstNumber / second
                }
                "*" -> {
                    result = firstNumber * second
                }
                "-" -> {
                    result = firstNumber - second
                }
                "+" -> {
                    result = firstNumber + second
                }
            }
            text_display.text = result.toString()
            secondNumber = second
            if (result != null) {
                firstNumber = result
            }
            clearFlag = true
        }
    }
}

