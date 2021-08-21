package com.arttseng.newsfeedwits

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DetailActivityv: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initView()
        initVM()
    }

    fun initView() {

    }

    fun initVM() {

    }
}