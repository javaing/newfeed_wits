package com.arttseng.newsfeedwits

import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.arttseng.newsfeedwits.data.ProviderBean
import com.arttseng.newsfeedwits.ui.DataAdapter
import com.arttseng.newsfeedwits.ui.DialogAdapter
import com.arttseng.newsfeedwits.ui.NewsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var spCategory: Spinner
    private lateinit var spProvider: Spinner
    private lateinit var recycler: RecyclerView
    private lateinit var ivSetting: ImageView
    private val vm = NewsViewModel()
    private lateinit var settingDialog : SettingDialog
    private lateinit var dialogAdapter : DialogAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        initView()
        initVM()
    }

    private fun initView() {
        swipeRefreshLayout = findViewById(R.id.swipe)
        spCategory = findViewById(R.id.sp_category)
        spProvider = findViewById(R.id.sp_provider)
        recycler = findViewById(R.id.recycler)
        ivSetting= findViewById(R.id.iv_setting)

        swipeRefreshLayout.setOnRefreshListener {
            refreshNews()
            Handler().postDelayed(Runnable {
                swipeRefreshLayout.isRefreshing = false
            }, 1000)
        }

        spCategory.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                refreshNews()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        spProvider.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                refreshNews()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        settingDialog = SettingDialog(this)
        ivSetting.setOnClickListener {
            settingDialog.show()
        }

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler.layoutManager = layoutManager
        recycler.adapter = DataAdapter(listOf()) {
            it.isRead = true
            recycler.adapter?.notifyDataSetChanged()
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("NEWSBEAN", it)
            }
            startActivity(intent)
        }
    }

    private fun refreshProvider(list: List<ProviderBean>) {
        val filter = list.filter { it.isSubscribe }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, filter.map { it.name })
        spProvider.adapter = adapter
    }

    private fun allOrOneProviders(selectPos:Int):List<Int> {
        if(selectPos!=0)
            return listOf(selectPos)
        val aa = vm.providerBean.value?.filter { it.isSubscribe }
        aa?.let {
            return (1 until aa.size).map {
                aa[it].id.toInt()
            }
        }
        return listOf(selectPos)
    }

    private fun refreshNews() {
        vm.getNewsBy(allOrOneProviders(spProvider.selectedItemPosition), spCategory.selectedItemPosition)
    }

    private fun initVM() {
        vm.getNewsProviders()
        vm.getNewsCategories()
        vm.getNews()

        vm.providerBean.observe(this, { list ->
            refreshProvider(list)

            dialogAdapter = DialogAdapter()
            dialogAdapter.setDataCallBack(object : DialogAdapter.GetDataCallBack {
                override fun getDataChange(data: List<ProviderBean>) {
                    refreshProvider(data)
                }
            })
            dialogAdapter.setData(list)
        })

        vm.categoryBean.observe(this, { list->
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list.map { it.name })
            spCategory.adapter = adapter
        })

        vm.filterNews.observe(this, {
            if(it.isEmpty()) {
                Toast.makeText(this, "Just No news", Toast.LENGTH_SHORT).show()
            }
            (recycler.adapter as DataAdapter).update(it)
        })
    }


    inner class SettingDialog(context: Context) : Dialog(context) {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.dialog_setting_providers)
            setCancelable(false)

            val recyclerProvider = findViewById<RecyclerView>(R.id.recycler_provider)
            val tvOK = findViewById<TextView>(R.id.tv_ok)

            tvOK.setOnClickListener{
                dismiss()
            }

            val layoutManager = LinearLayoutManager(context)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            recyclerProvider.layoutManager = layoutManager
            recyclerProvider.adapter = dialogAdapter
        }
    }

}