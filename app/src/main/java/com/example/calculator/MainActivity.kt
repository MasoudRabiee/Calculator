package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var dotFlag = false

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
    }


    private val digitListener = View.OnClickListener {
        it as Button
        val oldNumber = text_display.text.toString()
        when (it.id) {
            btn_dot.id -> {
                if (!dotFlag) {
                    text_display.append(".")
                    dotFlag = true
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
}

