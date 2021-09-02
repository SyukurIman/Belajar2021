package com.example.belajar2021.Setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.belajar2021.R

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        supportFragmentManager.beginTransaction().add(R.id.setting_holder, MyPreferenceFragment()).commit()
    }
}