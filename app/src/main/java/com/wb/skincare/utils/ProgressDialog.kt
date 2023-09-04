package com.wb.skincare.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.ProgressBar
import com.wb.skincare.R

class ProgressDialog (context: Context?) : Dialog(context!!) {

    private lateinit var imgvLogo : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_dialog)

        //imgvLogo = findViewById(R.id.imgv_anim)
        setCancelable(true)
        setCanceledOnTouchOutside(false)
        window!!.setDimAmount(0.0f)
        window!!.setBackgroundDrawableResource(android.R.color.transparent);
//        Glide.with(context)
//            .load(R.drawable.ic_golper_boi_ani) // or url
//            .into(imgvLogo)

    }
}