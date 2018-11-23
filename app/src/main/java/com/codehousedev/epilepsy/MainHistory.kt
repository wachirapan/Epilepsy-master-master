package com.codehousedev.epilepsy

import android.content.Intent
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.codehousedev.sqlite.QuerySQLite

class MainHistory : AppCompatActivity() {
    lateinit var cursor : Cursor
    lateinit var profileid : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_history)
        init()
    }
    fun init()
    {
        var txtname : TextView = findViewById(R.id.txtname)
        var txtage : TextView = findViewById(R.id.txtage)
        var txttell : TextView = findViewById(R.id.txttell)
        var txttreatment : TextView = findViewById(R.id.txttreatment)
        var txtcause : TextView = findViewById(R.id.txtcause)
        var txttype : TextView = findViewById(R.id.txttype)
        var txtstimulant : TextView = findViewById(R.id.txtstimulant)
        var txtsideeffect : TextView = findViewById(R.id.txtsideeffect)
        var txtallergyhistory : TextView = findViewById(R.id.txtallergyhistory)

        var getDB = QuerySQLite(this)
        cursor = getDB.queryprofile()
        if(cursor.equals(null)){

        }else{
            while (cursor.moveToNext()){
                profileid = cursor.getString(0)
                txtname.setText(cursor.getString(1))
                txtage.setText(cursor.getString(2))
                txttell.setText(cursor.getString(3))
                txttreatment.setText(cursor.getString(4))
                txtcause.setText(cursor.getString(5))
                txttype.setText(cursor.getString(6))
                txtstimulant.setText(cursor.getString(7))
                txtsideeffect.setText(cursor.getString(8))
                txtallergyhistory.setText(cursor.getString(9))
            }

        }
        var addprofile : Button = findViewById(R.id.addprofile)
        addprofile.setOnClickListener({
            var insertprofile = Intent(this,MainInsertHistory::class.java)
            startActivity(insertprofile)
        })
        var editprofile : Button = findViewById(R.id.editprofile)
        editprofile.setOnClickListener({
            if(profileid == ""){
                Toast.makeText(this,"คุณยังไม่ได้ไม่มีประวัติกรุณาเพิ่มประวัติก่อนค่ะ",Toast.LENGTH_LONG).show()
            }else {
                var editintent = Intent(this, MainEditProfile::class.java)
                editintent.putExtra("productid", profileid)
                startActivity(editintent)
            }
        })
    }
    override fun onBackPressed() {
        var backmain = Intent(this,MainActivity::class.java)
        startActivity(backmain)
    }
}
