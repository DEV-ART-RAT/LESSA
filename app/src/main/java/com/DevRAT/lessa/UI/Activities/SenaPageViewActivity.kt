package com.DevRAT.lessa.UI.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.DevRAT.lessa.Database.Entities.Senas
import com.DevRAT.lessa.R
import com.DevRAT.lessa.UI.Adapter.SenasPageAdapter
import com.DevRAT.lessa.UI.Transformer.*


class SenaPageViewActivity : AppCompatActivity() {
    companion object {
        var senaList: List<Senas>? = null
        var index = 1
        private var mPager: ViewPager? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = resources.configuration.orientation
        super.onCreate(savedInstanceState)
        //getResources().getConfiguration().orientation
        setContentView(R.layout.activity_sena_page_view)
        init()
    }


    private fun init() {
        mPager = findViewById(R.id.view_pager) as ViewPager
        mPager?.setPageTransformer(true, Transicion5())
        mPager?.adapter = SenasPageAdapter(this@SenaPageViewActivity, senaList, index)
        mPager?.setCurrentItem(index)


    }
}



