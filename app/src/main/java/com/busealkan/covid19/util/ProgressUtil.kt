package com.busealkan.covid19

import android.app.ProgressDialog
import android.content.Context

object ProgressUtil {
    fun progressDialogCreate(
        context: Context?,
        message: CharSequence?
    ): ProgressDialog? {
        val progressDialog = ProgressDialog(context)
        progressDialog.setMessage(message)
        progressDialog.show()
        return progressDialog
    }

    fun progressDialogClose(progressDialog: ProgressDialog?) {
        if (progressDialog != null && progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }
}