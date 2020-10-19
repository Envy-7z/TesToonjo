package com.tif.testoonjo.activity.add

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.tif.testoonjo.R
import io.reactivex.Observable

import com.tif.testoonjo.local.room.ContactDatabase
import com.tif.testoonjo.local.room.entity.ContactEntity
import com.vincent.filepicker.Constant
import com.vincent.filepicker.Constant.MAX_NUMBER
import com.vincent.filepicker.Constant.REQUEST_CODE_PICK_IMAGE
import com.vincent.filepicker.activity.ImagePickActivity
import com.vincent.filepicker.filter.entity.ImageFile
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_add_activitys.*
import kotlinx.android.synthetic.main.toolbar.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import pub.devrel.easypermissions.EasyPermissions
import java.io.File

class AddActivitys : AppCompatActivity() {


    private var contactDb:ContactDatabase? = null

    val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_activitys)
        tv_toolbar_title.text = "       Contact"
        contactDb = ContactDatabase.getInstance(this)
        ivGambar.setOnClickListener {

            // check permission untuk android M dan ke atas.
            // saat permission disetujui oleh user maka jalan script untuk intent ke pick image.
            if (EasyPermissions.hasPermissions(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                val i = Intent(this, ImagePickActivity::class.java)
                i.putExtra(MAX_NUMBER, 1)
                startActivityForResult(i, REQUEST_CODE_PICK_IMAGE)
            } else {
                // tampilkan permission request saat belum mendapat permission dari user
                EasyPermissions.requestPermissions(
                    this,
                    "This application need your permission to access photo gallery.",
                    991,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
            }
        }

        btnSimpan.setOnClickListener {
        insertToDb(ContactEntity("",etEmail.text.toString(),spinner.selectedItem.toString(),etFirst.text.toString(),
        etLast.text.toString()))

            startActivity(Intent(this@AddActivitys , com.tif.testoonjo.MainActivity::class.java))

            applicationContext.toast("Data berhasil dimasukkan")

        }



    }

    // override method onActivityResult untuk handling data dari pickImageActivity.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null) {


            // membuat variable yang menampung path dari picked image.
            val pickedImg = data.getParcelableArrayListExtra<ImageFile>(Constant.RESULT_PICK_IMAGE)?.get(0)?.path

            // membuat request body yang berisi file dari picked image.
            val requestBody = RequestBody.create("multipart".toMediaTypeOrNull(), File(pickedImg))


            // mempilkan image yang akan diupload dengan glide ke imgUpload.
            Glide.with(this).load(pickedImg).into(ivGambar)

        }

    }
    fun insertToDb(contact:ContactEntity){
        compositeDisposable.add(Observable.fromCallable{contactDb?.contactDao()?.insert(contact)}
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe())
    }

    override fun onDestroy() {
        super.onDestroy()
        ContactDatabase.destroyInstance()
        compositeDisposable.dispose()
    }

    fun Context.toast(message:CharSequence){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

