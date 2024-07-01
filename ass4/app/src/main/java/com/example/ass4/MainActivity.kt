package com.example.ass4

import android.content.Context
import android.content.Intent
import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var fnm = findViewById<EditText>(R.id.e1)
        var lnm = findViewById<EditText>(R.id.e2)
        var email = findViewById<EditText>(R.id.e3)
        var pw = findViewById<EditText>(R.id.e4)
        var btn1 = findViewById<Button>(R.id.b1)
        var btn2 = findViewById<Button>(R.id.b2)

        fun msg(title: String, msg:String){
            val b = AlertDialog.Builder(this)
            b.setCancelable(true)
            b.setTitle(title)
            b.setMessage(msg)
            b.show()
        }
        fun clearText(){
            fnm.setText("")
            lnm.setText("")
            email.setText("")
            pw.setText("")
            fnm.requestFocus()
        }

        var db = openOrCreateDatabase("studentdb", Context.MODE_PRIVATE,null)
        db.execSQL("create table IF NOT EXISTS student(fnm VARCHAR,lnm VARCHAR,email VARCHAR,pw VARCHAR);")

        btn1.setOnClickListener(View.OnClickListener {
            if(fnm.text.toString().trim().length.equals(0)||
                lnm.text.toString().trim().length.equals(0)||
                email.text.toString().trim().length.equals(0)||
                pw.text.toString().trim().length.equals(0))
            {
                msg("Error","Please, fill up the all values");
            }
            else{
                db.execSQL("INSERT INTO student VALUES('"+ fnm.text+"','"+ lnm.text+"','"+ email.text.toString()+"','"+ pw.text.toString()+"');")
                msg("Success","Record inserted...");
                clearText();
            }
        })

        btn2.setOnClickListener(){
            intent=Intent(this,MainActivity2::class.java)
            //intent.putExtra("em",email)
            //intent.putExtra("pass",pw)
            startActivity(intent)
        }

    }
}

private fun Intent.putExtra(s: String, email: EditText?) {

}



