package com.wb.skincare.utils

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CommonMethods (  private val context: Context) {

    fun isOnline(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        if (!(activeNetwork != null && activeNetwork.isConnectedOrConnecting)) {
            Toast.makeText(context, "আপনি অফলাইনে আছেন", Toast.LENGTH_LONG).show()
        }
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    fun showMatDialog(
        title: String?,
        message: String?
    ) {
        MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("পুনরায় আবার চেষ্টা করুন") { dialogInterface, i -> }
            .show()
    }
}