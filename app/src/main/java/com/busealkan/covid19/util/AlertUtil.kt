package com.busealkan.covid19.util

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.Drawable
import android.provider.Settings


object AlertUtil {
    fun alertDialogShow(activity: Activity, style: Int, icon: Drawable?, title: CharSequence?, message: CharSequence?, negativeButton: CharSequence?, positiveButton: CharSequence?,alertSelected: AlertDialogSelected) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity, style)
        builder.setIcon(icon)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setNegativeButton(negativeButton,
            DialogInterface.OnClickListener { dialog, which ->
                if (alertSelected.equals(AlertDialogSelected.INTERNET)) {
                    activity.finish()
                } else {
                    activity.finish()
                }
            })
        builder.setPositiveButton(positiveButton,
            DialogInterface.OnClickListener { dialog, which ->
                if (alertSelected.equals(AlertDialogSelected.INTERNET)) {
                    dialog.dismiss()
                    activity.startActivity(Intent(Settings.ACTION_SETTINGS))
                    activity.finish()
                } else {
                    dialog.dismiss()
                }
            })
        builder.show()
    }




    enum class AlertDialogSelected {
        INTERNET,
        EXIT
    }
}




