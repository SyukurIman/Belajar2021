package com.example.belajar2021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.belajar2021.MenyimpanDta.Menyimpan_Data
import com.example.belajar2021.SaveOpen.open_save_activity
import com.example.belajar2021.Setting.SettingActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonSaveOpen: Button = findViewById(R.id.Save_Open)
        buttonSaveOpen.setOnClickListener(this)

        val buttonSaveData: Button = findViewById(R.id.Save_Data)
        buttonSaveData.setOnClickListener(this)

        val buttonSeting: Button = findViewById(R.id.Setting)
        buttonSeting.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id){
            R.id.Save_Open -> {
                val intentOpen = Intent(this@MainActivity, open_save_activity::class.java)
                startActivity(intentOpen)
            }
            R.id.Save_Data ->{
                val intentData = Intent(this@MainActivity, Menyimpan_Data::class.java)
                startActivity(intentData)
            }
            R.id.Setting ->{
                val intentSetting = Intent(this@MainActivity, SettingActivity::class.java)
                startActivity(intentSetting)
            }
        }
    }

}