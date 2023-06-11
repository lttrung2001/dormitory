package com.lttrung.dormitory.ui.bases

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.lttrung.dormitory.ui.dialogs.ConfirmDialog
import com.lttrung.dormitory.utils.Common
import javax.inject.Inject

open class BaseActivity : AppCompatActivity() {
    open val confirmDialog by lazy {
        ConfirmDialog(this@BaseActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        Common.currentActivity = this
    }

    override fun onStart() {
        super.onStart()
        Common.currentActivity = this
    }

    override fun onResume() {
        super.onResume()
        Common.currentActivity = this
    }

    override fun onStop() {
        super.onStop()
        Common.currentActivity = null
    }

    override fun onDestroy() {
        super.onDestroy()
        Common.currentActivity = null
    }
}