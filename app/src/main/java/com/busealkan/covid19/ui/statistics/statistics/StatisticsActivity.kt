package com.busealkan.covid19.ui.statistics.statistics

import android.os.Bundle
import android.view.Menu
import android.widget.*
import androidx.appcompat.widget.SearchView
import android.app.SearchManager
import android.content.Intent
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.busealkan.covid19.Constants
import com.busealkan.covid19.ObjectUtil
import com.busealkan.covid19.R
import com.busealkan.covid19.StatisticsItemClickListener
import com.busealkan.covid19.data.model.StatisticsResponse
import com.busealkan.covid19.databinding.ActivityStatisticsBinding
import com.busealkan.covid19.ui.base.BaseBindingActivity
import com.busealkan.covid19.ui.statistics.StatisticsViewModel
import com.busealkan.covid19.ui.statistics.detail.DetailActivity
import com.busealkan.covid19.util.AlertUtil
import com.busealkan.covid19.util.toLowerCaseTr
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class StatisticsActivity : BaseBindingActivity<ActivityStatisticsBinding>() {

    private val mViewModel: StatisticsViewModel by viewModels()
    private lateinit var statisticsAdapter: StatisticsAdapter
    private var statisticsList:List<StatisticsResponse>?= null
    private var searchView: SearchView? = null

    override fun getContentViewLayoutResId(): Int {
        return R.layout.activity_statistics
    }

    override fun populateUI(savedInstanceState: Bundle?) {
        init()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView!!.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView!!.maxWidth = Int.MAX_VALUE

        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                countrySearch(query)
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_search) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (!searchView!!.isIconified) {
            searchView!!.isIconified = true
            return
        }
        else{
            exitAlert()
            return
        }
        super.onBackPressed()
    }

    fun init() {
        mBinding?.apply {
            swipeRefresh.setColorSchemeResources(R.color.swipeColor1,R.color.swipeColor2,R.color.swipeColor3,R.color.swipeColor4)
            swipeRefresh.setOnRefreshListener {
                initObservers()
                swipeRefresh.setRefreshing(false)
            }
        }

        initObservers()
        mViewModel.getStatisticsList()

    }

    private fun countrySearch(countrySearch: String) {
        countrySearch?.let {
            statisticsList?.let{

                var countryListFilter = it.filter {
                    it.country?.toLowerCaseTr()!!.contains(countrySearch.toLowerCaseTr())
                }
                statisticsAdapter.setData(countryListFilter)
                statisticsAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun initObservers() {
        mViewModel.apply {

            allStatisticsLiveData.observe(this@StatisticsActivity, Observer {
                statisticsList = it.sortedBy { it.country }
                initRecycleView(statisticsList)
                //countrySorted()

            })

            errorMessageLiveData.observe(this@StatisticsActivity, Observer {
                Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
            })

            isLoading.observe(this@StatisticsActivity, Observer {

                //Handle loading
            })

        }

    }

    private fun initRecycleView(countryList: List<StatisticsResponse>?) {
        mBinding?.apply {
              statisticsAdapter = StatisticsAdapter(countryList!!, object : StatisticsItemClickListener {
                 override fun onItemClick(statisticsItem: StatisticsResponse) {
                     openStatisticsPage(statisticsItem)
                 }
             })

            rcvCountries.adapter = statisticsAdapter
            rcvCountries.setLayoutManager(LinearLayoutManager(applicationContext))

        }
    }

    private fun openStatisticsPage(clickedCountry: StatisticsResponse) {
        val countryIntent = Intent(applicationContext, DetailActivity::class.java)
        val clickedCountryString: String = ObjectUtil.objectToString(clickedCountry)
        countryIntent.putExtra(Constants.MOVED_COUNTRY_NAME, clickedCountryString)
        startActivity(countryIntent)
    }


    private fun exitAlert() {
        AlertUtil.alertDialogShow(
                this@StatisticsActivity,
                R.style.AlertDialogTheme,
                resources.getDrawable(R.drawable.exiticon),
                resources.getString(R.string.alertExitTitle),
                resources.getString(R.string.alertExitMessage),
                resources.getString(R.string.alertExitNegativeButton),
                resources.getString(R.string.alertExitPositiveButton),
                AlertUtil.AlertDialogSelected.EXIT
        )
    }

    }