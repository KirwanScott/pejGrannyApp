package com.example.pejgrannyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var txtRUsername: EditText = findViewById(R.id.txtRUsername)
        var txtRPassword: EditText = findViewById(R.id.txtRPassword)
        var txtConPassword: EditText = findViewById(R.id.txtConfirmPassword)
        var btnRegister: Button = findViewById(R.id.btnRegister)
        var btnrlogin: Button = findViewById(R.id.btnrlogin)

        btnRegister.setOnClickListener(){
            var Username = txtRUsername.text.toString()
            var Password = txtRPassword.text.toString()
            var ConPassword = txtConPassword.text.toString()

            if(Password == ConPassword){
                val log = classLogin()
                log.AddOldUsers()
                Log.d("AddNewUser","Pressed the reg button")
                log.AddNewUser(Username, Password)

                val intent = Intent(this,Login::class.java)
                startActivity(intent)
            }
            else
            {
                Toast.makeText(this,"Passwords do not match",Toast.LENGTH_SHORT).show()
            }
        }

        btnrlogin.setOnClickListener(){
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }
    }
}