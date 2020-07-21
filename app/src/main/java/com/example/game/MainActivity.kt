package com.example.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val randomNumber: Int? = (1..10).shuffled().first()
        playagainbttn.visibility = View.INVISIBLE
        var x = 3

        enterbttn.setOnClickListener {

            val guessedValue: Int = number.text.toString().toInt()


            x--

            if (x == 0) {

                Toast.makeText(this, "No More Attempts", Toast.LENGTH_SHORT).show()
                number.visibility = View.GONE
                enterbttn.visibility = View.INVISIBLE
                playagainbttn.visibility = View.VISIBLE
                textView.visibility = View.INVISIBLE
                textView2.visibility = View.INVISIBLE

                playagainbttn.setOnClickListener{
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
            else
            {
                if (guessedValue == randomNumber) {
                    val intent = Intent(this, Main2Activity::class.java)
                    startActivity(intent)
                }

                else if (guessedValue == 0){
                        val builder = AlertDialog.Builder(this)
                        //set title for alert dialog
                        builder.setTitle("Warning!!")
                        //set message for alert dialog
                        builder.setMessage("Please enter number in between 1 & 10.")
                        builder.setIcon(android.R.drawable.ic_dialog_alert)

                        //performing positive action
                        builder.setPositiveButton("Ok") { dialog, which ->
                            Toast.makeText(applicationContext, "clicked yes", Toast.LENGTH_LONG).show()
                        }

                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }
                else {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Try Again!!")
                    builder.setMessage("Try Again! You have $x more chances.")
                    builder.setPositiveButton("Continue") { dialog, which ->
                        Toast.makeText(this, "Try Again!", Toast.LENGTH_SHORT).show()
                    number.setText("")
                    }

                    val dialog: AlertDialog = builder.create()
                    dialog.show()

                }
            }
        }
        }
    }

