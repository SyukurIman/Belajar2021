package com.example.belajar2021.MenyimpanDta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.belajar2021.R
import com.example.belajar2021.SaveOpen.SaveData.FileModel
import com.example.belajar2021.SaveOpen.SaveData.UserPreference
import com.example.belajar2021.databinding.ActivityMenyimpanDataBinding

class Menyimpan_Data : AppCompatActivity(),View.OnClickListener {
    private lateinit var mUserPreference: UserPreference

    private var isPreferenceEmpty = false
    private lateinit var fileModel: FileModel

    private lateinit var binding: ActivityMenyimpanDataBinding

    companion object{
        private const val REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenyimpanDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "My User Preference"

        mUserPreference = UserPreference(this)
        showExitingPreference()

        binding.btnSave.setOnClickListener(this)
    }

    private fun showExitingPreference() {
        fileModel = mUserPreference.getUser()
        populateView(fileModel)
        checkForm(fileModel)
    }

    private fun checkForm(fileModel: FileModel) {
        when{
            fileModel.name.toString().isNotEmpty() ->{
                binding.btnSave.text = getString(R.string.change)
                isPreferenceEmpty = false
            }
            else ->{
                binding.btnSave.text = getString(R.string.save)
                isPreferenceEmpty = true
            }
        }
    }

    private fun populateView(fileModel: FileModel) {
        binding.tvName.text = if (fileModel.name.toString().isEmpty()) "Tidak Ada" else fileModel.name
        binding.tvEmail.text = if (fileModel.email.toString().isEmpty()) "Tidak Ada" else fileModel.email
        binding.tvPhone.text = if (fileModel.phoneNumber.toString().isEmpty()) "Tidak Ada" else fileModel.phoneNumber
        binding.tvAge.text = if (fileModel.age.toString().isEmpty()) "Tidak Ada" else fileModel.age.toString()
        binding.tvIsLoveMu.text = if (fileModel.isLove) "Ya" else "Tidak"
    }


    override fun onClick(v: View) {
        if (v.id == R.id.btn_save){
            val intent = Intent(this@Menyimpan_Data, FromUserPreferenceActivity::class.java)
            when{
                isPreferenceEmpty -> {
                    intent.putExtra(
                        FromUserPreferenceActivity.EXTRA_TYPE_FROM,
                        FromUserPreferenceActivity.TYPE_ADD
                    )
                    intent.putExtra("USER", fileModel)
                }
                else ->{
                    intent.putExtra(
                        FromUserPreferenceActivity.EXTRA_TYPE_FROM,
                        FromUserPreferenceActivity.TYPE_EDIT
                    )
                    intent.putExtra("USER", fileModel)
                }
            }
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

}