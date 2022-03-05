package com.busealkan.covid19.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import com.busealkan.covid19.Constants
import com.busealkan.covid19.R
import com.busealkan.covid19.databinding.ActivitySplashBinding
import com.busealkan.covid19.ui.base.BaseBindingActivity
import com.busealkan.covid19.ui.statistics.statistics.StatisticsActivity
import com.busealkan.covid19.util.AlertUtil
import com.busealkan.covid19.util.NetworkUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseBindingActivity<ActivitySplashBinding>() {

    var countDownTimer : CountDownTimer?=null

    override fun getContentViewLayoutResId(): Int {
        return R.layout.activity_splash
    }

    override fun populateUI(savedInstanceState: Bundle?) {
        init()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    fun init() {
        mBinding?.apply {


        }

        timer()
    }

    private fun timer() {
        var timerMillisInFuture = Constants.TIMER_MILLIS_IN_FUTURE
        val timerInterval = Constants.TIMER_INTERVAL

        countDownTimer = object :
            CountDownTimer(timerMillisInFuture.toLong(), timerInterval.toLong()) {
            override fun onTick(millisUntilFinished:Long) {
                //binding.txtSure.text = millisUntilFinished.toString()
            }
            override fun onFinish() {
                networkController()
            }
        }
        countDownTimer?.start()
    }

    private fun networkController() {
        if (NetworkUtil.isThereInternet(applicationContext)) {
            mainActivity()
        } else {
            internetAlert()
        }
    }

    private fun internetAlert() {
        AlertUtil.alertDialogShow(this@SplashActivity, R.style.AlertDialogTheme, resources.getDrawable(R.drawable.interneticon), resources.getString(R.string.alertTitle), resources.getString(R.string.alertMessage), resources.getString(R.string.alertNegativeButton), resources.getString(R.string.alertPositiveButton), AlertUtil.AlertDialogSelected.INTERNET)
    }

    private fun mainActivity() {
        val listIntent = Intent(this@SplashActivity, StatisticsActivity::class.java)
        startActivity(listIntent)
        finish()
    }
}