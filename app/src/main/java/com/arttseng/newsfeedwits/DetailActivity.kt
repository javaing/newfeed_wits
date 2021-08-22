package com.arttseng.newsfeedwits

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.arttseng.newsfeedwits.data.NewsBean

class DetailActivity: AppCompatActivity() {
    private var newsBean: NewsBean? = null

    private lateinit var tvDatetime: TextView
    private lateinit var tvTitle: TextView
    private lateinit var tvSubtitle: TextView
    private lateinit var tvAuthor: TextView
    private lateinit var tvDesc: TextView

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

    private fun initView() {
        newsBean = intent.getParcelableExtra("NEWSBEAN")

        tvDatetime = findViewById(R.id.tv_datetime)
        tvTitle = findViewById(R.id.tv_title)
        tvSubtitle = findViewById(R.id.tv_subtitle)
        tvAuthor = findViewById(R.id.tv_author)
        tvDesc = findViewById(R.id.tv_desc)
    }

    private fun initVM() {
        newsBean?.let {
            tvDatetime.text = it.date +" "+ it.time
            tvTitle.text = it.title
            tvSubtitle.text = it.subtitle
            tvAuthor.text = it.author
            tvDesc.text = it.descriptive
        }
    }
}