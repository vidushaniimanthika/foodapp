package com.example.food

import android.content.Intent
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.widget.Toast
    import com.example.food.databinding.ActivityLloginBinding
import com.google.firebase.auth.FirebaseAuth

    class Llogin : AppCompatActivity() {

        private lateinit var binding: ActivityLloginBinding
        private lateinit var user:FirebaseAuth


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding= ActivityLloginBinding.inflate(layoutInflater)
            setContentView(binding.root)

            user=FirebaseAuth.getInstance()

            binding.signp.setOnClickListener {
                val intent= Intent(this,SignUp::class.java)
                startActivity(intent)
            }



            binding.login.setOnClickListener {
                val email = binding.emailL.text.toString()
                val password = binding.passwordL.text.toString()


                if (email.isNotEmpty() && password.isNotEmpty()){

                    user.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                        if (it.isSuccessful){
                            val intent=Intent(this, FoodItemList::class.java)
                            startActivity(intent)

                        }else{
                            Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }else{
                    Toast.makeText(this,"Empty Fiels are not required", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            }

        }




