package com.busealkan.covid19.ui.statistics.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.app.NavUtils
import com.busealkan.covid19.data.model.StatisticsResponse
import com.busealkan.covid19.databinding.ActivityStatisticsBinding
import com.busealkan.covid19.ui.base.BaseBindingActivity
import dagger.hilt.android.AndroidEntryPoint
import com.busealkan.covid19.*
import com.busealkan.covid19.databinding.ActivityDetailBinding
import com.busealkan.covid19.ui.statistics.history.HistoryActivity
import com.busealkan.covid19.ui.statistics.statistics.StatisticsActivity
import com.busealkan.covid19.util.formatDateTimeCovid


@AndroidEntryPoint
class DetailActivity : BaseBindingActivity<ActivityDetailBinding>() {

    private lateinit var countryModel:StatisticsResponse

    override fun getContentViewLayoutResId(): Int {
        return R.layout.activity_detail
    }

    override fun populateUI(savedInstanceState: Bundle?) {
        init()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                val sharingIntent = Intent(Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"

                val shareBody =

                        resources.getString(R.string.todayCases)+countryModel.cases?.new+"\n"+
                        resources.getString(R.string.todayDeath)+countryModel.deaths?.new+"\n"+
                        resources.getString(R.string.critical)+countryModel.cases?.critical+"\n"+
                        resources.getString(R.string.totalTest)+countryModel.tests?.total+"\n"+
                        resources.getString(R.string.totalCases)+countryModel.cases?.total+"\n"+
                        resources.getString(R.string.totalDeaths)+countryModel.deaths?.total+"\n"+
                        resources.getString(R.string.totalRecovered)+countryModel.cases?.recovered




                val shareSubject = countryModel.country+ resources.getString(R.string.statistics)+resources.getString(R.string.date)+countryModel.time?.formatDateTimeCovid()

                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject)
                startActivity(Intent.createChooser(sharingIntent, resources.getString(R.string.shareUsing)))
            }
            R.id.home -> {
                val backButton = Intent(applicationContext, StatisticsActivity::class.java)
                NavUtils.navigateUpTo(this, backButton)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun init() {
        val movedCountryString:String? = intent.getStringExtra(Constants.MOVED_COUNTRY_NAME)
        countryModel = ObjectUtil.jsonStringToObject(movedCountryString.toString())
        //mViewModel.getCountryStatistics(countryModel.country)


        mBinding?.apply {
            txtCountryName.text = countryModel.country
            txtTodayCasesValue.text = countryModel.cases?.new
            txtTodayDeathValue.text = countryModel.deaths?.new
            txtCriticalValue.text = countryModel.cases?.critical
            txtTotalTestValue.text = countryModel.tests?.total
            txtTotalCasesValue.text = countryModel.cases?.total
            txtTotalDeathsValue.text = countryModel.deaths?.total
            txtTotalRecoveredValue.text = countryModel.cases?.recovered
            txtDate.text = countryModel.time?.formatDateTimeCovid()

            btnHistory.setOnClickListener {
                val historyIntent = Intent(applicationContext, HistoryActivity::class.java)
                historyIntent.putExtra(Constants.MOVED_COUNTRY_NAME, countryModel.country)
                startActivity(historyIntent)
                finish()
            }

            getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
            getSupportActionBar()?.setTitle(resources.getString(R.string.detailLabel))



        }




    }
}