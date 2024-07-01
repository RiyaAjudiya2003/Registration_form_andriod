package com.example.ass4

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity2 : AppCompatActivity() {
    val sharedpreferences = "loginshared"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var em = findViewById<EditText>(R.id.e5)
        var pwd = findViewById<EditText>(R.id.e6)
        var btn3 = findViewById<Button>(R.id.b3)

        var sharedpreferences = getSharedPreferences(sharedpreferences, Context.MODE_PRIVATE)
        val db=openOrCreateDatabase("studentdb",Context.MODE_PRIVATE,null)

        fun msg(title: String, msg:String){
            val b = AlertDialog.Builder(this)
            b.setCancelable(true)
            b.setTitle(title)
            b.setMessage(msg)
            b.show()
        }
        fun clearText(){
            em.setText("")
            pwd.setText("")
            em.requestFocus()
        }
        fun isvalid(email: String,  password: String):Boolean
        {
            if (email.isEmpty())
            {
                msg("Error","Enter email")
            }
            else if (password.isEmpty())
            {
                msg("error","enter password")
            }
            return true
        }
        val email = sharedpreferences.getString("EMAIL_KEY", null)
       val  password = sharedpreferences.getString("PASSWORD_KEY", null)
        btn3.setOnClickListener(){
            val lemail= em.text.toString()
            val lpassword = pwd.text.toString()
            if(isvalid(lemail,lpassword))
            {
                val cursor = db.rawQuery("SELECT * FROM student WHERE email='$lemail' AND pw='$lpassword'",null)
                val editor = sharedpreferences.edit()
                editor.putString("EMAIL_KEY", em.text.toString())
                editor.putString("PASSWORD_KEY", pwd.text.toString())
                editor.apply()
                if (cursor.moveToNext())
                {
                    //Toast.makeText(this,"sucess",Toast.LENGTH_LONG).show()
                     val intent=Intent(this,MainActivity3::class.java)
                    startActivity(intent)
                    finish()
                }
                else
                {
                    Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
                }
            }

        }

    }
}