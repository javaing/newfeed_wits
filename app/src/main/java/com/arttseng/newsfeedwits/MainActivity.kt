package com.arttseng.newsfeedwits

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.arttseng.newsfeedwits.ui.NewsViewModel
import com.arttseng.newsfeedwits.data.NewsBean

class MainActivity : AppCompatActivity() {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var sp_category: Spinner
    private lateinit var sp_provider: Spinner
    private lateinit var recycler: RecyclerView
    private lateinit var iv_setting: ImageView
    private val vm = NewsViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.hide()

        initView()
        initVM()
    }

    private fun initView() {
        swipeRefreshLayout = findViewById(R.id.swipe)
        sp_category = findViewById(R.id.sp_category)
        sp_provider = findViewById(R.id.sp_provider)
        recycler = findViewById(R.id.recycler)
        iv_setting= findViewById(R.id.iv_setting)

        swipeRefreshLayout.setOnRefreshListener {
            refreshNews()
            Handler().postDelayed(Runnable {
                swipeRefreshLayout.isRefreshing = false
            }, 1000)
        }

        sp_category.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                refreshNews()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        sp_provider.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                refreshNews()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        iv_setting.setOnClickListener {

        }


        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler.layoutManager = layoutManager
        recycler.adapter = DataAdapter(listOf()) {
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("NEWSID", it.id)
            }
            startActivity(intent)
        }
    }

    private fun refreshNews() {
        vm.getNewsBy(sp_provider.selectedItemPosition, sp_category.selectedItemPosition)
    }

    private fun initVM() {
        vm.getNewsProviders()
        vm.getNewsCategories()
        vm.getNews()

        vm.providerBean.observe(this, { list ->
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list.map { it.name })
            sp_provider.adapter = adapter
        })

        vm.categoryBean.observe(this, { list->
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list.map { it.name })
            sp_category.adapter = adapter
        })

        vm.newsBean.observe(this, {
            if(it.isEmpty()) {
                Toast.makeText(this, "Just No news", Toast.LENGTH_SHORT).show()
            }
            (recycler.adapter as DataAdapter).update(it)
        })
    }

}