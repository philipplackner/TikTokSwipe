package com.androiddevs.tiktokswipe

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val verticalViewPager = VerticalViewPager(this)

        rootview.addView(verticalViewPager)

        val images = listOf(
            Uri.parse("android.resource://$packageName/${R.drawable.kermit7}"),
            Uri.parse("android.resource://$packageName/${R.drawable.kermit8}"),
            Uri.parse("android.resource://$packageName/${R.drawable.kermit1}"),
            Uri.parse("android.resource://$packageName/${R.drawable.kermit4}"),
            Uri.parse("android.resource://$packageName/${R.drawable.kermit5}"),
            Uri.parse("android.resource://$packageName/${R.drawable.kermit6}")
        )
        val adapter = ImagePagerAdapter(this, images)
        verticalViewPager.adapter = adapter
    }
}
