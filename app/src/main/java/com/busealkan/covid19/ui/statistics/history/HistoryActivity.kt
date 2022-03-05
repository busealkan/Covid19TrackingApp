package com.busealkan.covid19.ui.statistics.history

import android.content.Intent
import androidx.lifecycle.Observer
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.NavUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.busealkan.covid19.*
import com.busealkan.covid19.data.model.StatisticsResponse
import com.busealkan.covid19.databinding.ActivityHistoryBinding
import com.busealkan.covid19.ui.base.BaseBindingActivity
import com.busealkan.covid19.ui.statistics.StatisticsViewModel
import com.busealkan.covid19.ui.statistics.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class HistoryActivity : BaseBindingActivity<ActivityHistoryBinding>() {

    private val mViewModel: StatisticsViewModel by viewModels()
    val sdf = SimpleDateFormat(Constants.SIMPLE_DATE_FORMAT)


    override fun getContentViewLayoutResId(): Int {
        return R.layout.activity_history
    }

    override fun populateUI(savedInstanceState: Bundle?) {
        init()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            val backButton = Intent(applicationContext, DetailActivity::class.java)
            NavUtils.navigateUpTo(this, backButton)
            return true
        }
        return true
    }

    fun init() {

        val country:String? = intent.getStringExtra(Constants.MOVED_COUNTRY_NAME)
        val currentDate = sdf.format(Date())

        mViewModel.getStatisticalHistory(country!!,currentDate)


        mBinding?.apply {
            getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
            getSupportActionBar()?.setTitle(resources.getString(R.string.historyLabel))

        }
        initObservers()
    }


    private fun initObservers() {
        mViewModel.apply {

            allHistoryLiveData.observe(this@HistoryActivity, Observer {

                initRecycleView(it)

            })

            errorMessageLiveData.observe(this@HistoryActivity, Observer {
                Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
            })

            isLoading.observe(this@HistoryActivity, Observer {

                //Handle loading
            })

        }

    }

    private fun initRecycleView(historyList: List<StatisticsResponse>?) {
        mBinding?.apply {
            val historyAdapter = HistoryAdapter(historyList!!, object : StatisticsItemClickListener {
                override fun onItemClick(statisticsItem: StatisticsResponse) {
                    val detailIntent = Intent(applicationContext, DetailActivity::class.java)
                    val clickedCountryString: String = ObjectUtil.objectToString(statisticsItem)
                    detailIntent.putExtra(Constants.MOVED_COUNTRY_NAME, clickedCountryString)
                    startActivity(detailIntent)
                    //finish()


                }
            })

            rcvHistory.adapter = historyAdapter
            rcvHistory.setLayoutManager(LinearLayoutManager(applicationContext))

        }
    }

}