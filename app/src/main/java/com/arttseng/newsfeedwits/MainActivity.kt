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
import com.arttseng.newsfeedwits.ui.DialogAdapter
import com.arttseng.newsfeedwits.ui.NewsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var sp_category: Spinner
    private lateinit var sp_provider: Spinner
    private lateinit var recycler: RecyclerView
    private lateinit var iv_setting: ImageView
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
        settingDialog = SettingDialog(this)

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
            settingDialog.show()
        }


        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler.layoutManager = layoutManager
        recycler.adapter = DataAdapter(listOf()) {
            it.isRead = true
            recycler.adapter?.notifyDataSetChanged()
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("NEWSID", it.id)
            }
            startActivity(intent)
        }
    }

    private fun refreshProvider(list: List<ProviderBean>) {
        val filter = list.filter { it.isSubscrib }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, filter.map { it.name })
        sp_provider.adapter = adapter
    }

    private fun getProviderIds(selectPos:Int):List<Int> {
        if(selectPos!=0)
            return listOf(selectPos)
        val aa = vm.providerBean.value?.filter { it.isSubscrib }
        aa?.let {
            return (1 until aa.size).map {
                aa[it].id.toInt()
            }
        }
        return listOf(selectPos)
    }

    private fun refreshNews() {
        vm.getNewsBy(getProviderIds(sp_provider.selectedItemPosition), sp_category.selectedItemPosition)
    }

    private fun initVM() {
        vm.getNewsProviders()
        vm.getNewsCategories()
        vm.getNews()

        vm.providerBean.observe(this, { list ->
            Toast.makeText(this, "fresh provider", Toast.LENGTH_SHORT).show()

            refreshProvider(list)

            dialogAdapter = DialogAdapter()
            dialogAdapter.setDataCallBack(object : DialogAdapter.GetDataCallBack {
                override fun getDataChange(data: List<ProviderBean>) {
                    //Toast.makeText(this@MainActivity, data.toString(), Toast.LENGTH_SHORT).show()
                    refreshProvider(data)
                }
            })
            dialogAdapter.setData(list)
        })

        vm.categoryBean.observe(this, { list->
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list.map { it.name })
            sp_category.adapter = adapter
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


            val recycler_provider = findViewById<RecyclerView>(R.id.recycler_provider)
            val tvOK = findViewById<TextView>(R.id.tv_ok)
            val tvCancel = findViewById<TextView>(R.id.tv_cancel)

            tvOK.setOnClickListener{
                dismiss()
            }
            tvCancel.setOnClickListener { dismiss() }


            val layoutManager = LinearLayoutManager(context)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            recycler_provider.layoutManager = layoutManager


            recycler_provider.adapter = dialogAdapter
        }
    }

}