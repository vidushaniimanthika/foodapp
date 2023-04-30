package com.example.food

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Toast
import com.example.food.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUp : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var user: FirebaseAuth
    private lateinit var db:FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)


        user = FirebaseAuth.getInstance()

        binding.loginp.setOnClickListener {
            val intent=Intent(this,Llogin::class.java)
            startActivity(intent)
        }

        binding.sign.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            val confirmpass = binding.cpassword.text.toString()
            val checkBox=findViewById<CheckBox>(R.id.checkBox)

            if (email.isNotEmpty() && password.isNotEmpty() && confirmpass.isNotEmpty()){
                if (password == (confirmpass)){
                    user.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                      if (it.isSuccessful){
                          val intent=Intent(this,Llogin::class.java)
                          startActivity(intent)

                      }else{
                          Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT)
                              .show()
                      }
                    }

                }else{
                    Toast.makeText(this,"Password not matchning",Toast.LENGTH_SHORT)
                        .show()
                }
            }else{
                Toast.makeText(this,"Empty Fiels are not required", Toast.LENGTH_SHORT)
                    .show()
            }
            if (checkBox.isChecked){
                Toast.makeText(applicationContext,"Accepted", Toast.LENGTH_SHORT)
                    .show()
            }else{
                checkBox.text = "Please Accept this!"
                checkBox.setTextColor(Color.parseColor("#FF0000"))
            }
        }

    }
}


