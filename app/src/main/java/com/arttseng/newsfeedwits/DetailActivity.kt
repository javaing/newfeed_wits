package com.arttseng.newsfeedwits

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.arttseng.newsfeedwits.ui.NewsViewModel

class DetailActivity: AppCompatActivity() {
    val vm = NewsViewModel()
    var askId=0L

    private lateinit var tv_datetime: TextView
    private lateinit var tv_title: TextView
    private lateinit var tv_author: TextView
    private lateinit var tv_desc: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initView()
        initVM()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun initView() {
        askId = intent.getLongExtra("NEWSID",0)

        tv_datetime = findViewById(R.id.tv_datetime)
        tv_title = findViewById(R.id.tv_title)
        tv_author = findViewById(R.id.tv_author)
        tv_desc = findViewById(R.id.tv_desc)
    }

    fun initVM() {
        vm.getNewsItem(askId)
        vm.newsItem.observe(this, {
            tv_datetime.text = it.date +" "+ it.time
            tv_title.text = it.title
            tv_author.text = it.author
            tv_desc.text = it.descriptive
        })
    }
}