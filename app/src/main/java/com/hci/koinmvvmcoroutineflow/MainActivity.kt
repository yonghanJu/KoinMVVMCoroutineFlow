package com.hci.koinmvvmcoroutineflow

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hci.koinmvvmcoroutineflow.ui.list.LoremPicsumListActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this, LoremPicsumListActivity::class.java))
    }
}