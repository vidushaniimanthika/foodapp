package com.example.food

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val login = findViewById<Button>(R.id.login)
        val sign = findViewById<Button>(R.id.sign)

        login.setOnClickListener {

            val intent = Intent(this,Llogin::class.java)
            startActivity(intent)
            Toast.makeText(applicationContext,"Move Login Page" , Toast.LENGTH_SHORT)
                .show()
        }



        sign.setOnClickListener {
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)

            Toast.makeText(applicationContext,"Move Sign Page" , Toast.LENGTH_SHORT)
                .show()



        }

    }
}
