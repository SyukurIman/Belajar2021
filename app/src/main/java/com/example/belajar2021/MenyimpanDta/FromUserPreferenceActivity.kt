package com.example.belajar2021.MenyimpanDta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.belajar2021.R
import com.example.belajar2021.SaveOpen.SaveData.FileModel
import com.example.belajar2021.SaveOpen.SaveData.UserPreference
import com.example.belajar2021.databinding.ActivityFromUserPreferenceBinding

class FromUserPreferenceActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var  fileModel: FileModel
    private lateinit var binding: ActivityFromUserPreferenceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFromUserPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener(this)

        fileModel = intent.getParcelableExtra<FileModel>("USER") as FileModel
        val formType = intent.getIntExtra(EXTRA_TYPE_FROM, 0)

        var actionBarTitle = ""
        var btnTitle = ""

        when(formType){
            TYPE_ADD ->{
                actionBarTitle = "Tambah Baru"
                btnTitle = "Simpan"
            }
            TYPE_EDIT -> {
                actionBarTitle = "Ubah"
                btnTitle = "Update"
                shoPreferenceInForm()
            }
        }

        supportActionBar?.title = actionBarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnSave.text = btnTitle
    }

    private fun shoPreferenceInForm() {
        binding.edtName.setText(fileModel.name)
        binding.edtEmail.setText(fileModel.email)
        binding.edtAge.setText(fileModel.age.toString())
        binding.edtPhone.setText(fileModel.phoneNumber)
        if (fileModel.isLove){
            binding.rbYes.isChecked = true
        }else{
            binding.rbNo.isChecked = true
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_save){
            val name = binding.edtName.text.toString().trim()
            val email = binding.edtEmail.text.toString().trim()
            val age = binding.edtAge.text.toString().trim()
            val phoneNo = binding.edtPhone.text.toString().trim()
            val isLoveMu = binding.rgLoveMu.checkedRadioButtonId == R.id.rb_yes

            if (name.isEmpty()){
                binding.edtName.error = FIELD_REQUIRED
                return
            }
            if (email.isEmpty()){
                binding.edtEmail.error = FIELD_REQUIRED
                return
            }
            if (!isValidEmail(email)){
                binding.edtEmail.error = FIELD_IS_NOT_VALID
                return
            }
            if (age.isEmpty()){
                binding.edtAge.error = FIELD_REQUIRED
                return
            }
            if (phoneNo.isEmpty()){
                binding.edtPhone.error = FIELD_REQUIRED
                return
            }
            if (!TextUtils.isDigitsOnly(phoneNo)){
                binding.edtPhone.error = FIELD_DIGIT_ONLY
            }

            saveUser(name, email, age, phoneNo, isLoveMu)
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_RESULT, fileModel)
            setResult(RESULT_CODE, resultIntent)
            finish()
        }
    }

    private fun saveUser(name: String, email: String, age: String, phoneNo: String, loveMu: Boolean) {
        val userPreference = UserPreference(this)

        fileModel.name = name
        fileModel.email = email
        fileModel.age = Integer.parseInt(age)
        fileModel.phoneNumber = phoneNo
        fileModel.isLove = loveMu

        userPreference.setUser(fileModel)
        Toast.makeText(this, "Data Tersimpan", Toast.LENGTH_LONG).show()
    }

    private fun isValidEmail(email: CharSequence): Boolean{
        return  android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    companion object{
        const val EXTRA_TYPE_FROM = "extra_type_form"
        const val EXTRA_RESULT = "extra_result"
        const val RESULT_CODE = 101

        const val TYPE_ADD = 1
        const val TYPE_EDIT = 2

        private const val FIELD_REQUIRED = "Field tidak boleh kosong"
        private const val FIELD_DIGIT_ONLY = "Hanya boleh terisi numerik"
        private const val FIELD_IS_NOT_VALID = "Email tidak valid"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}