package com.DevRAT.lessa.UI.Adapter

import android.content.Context
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.DevRAT.lessa.Database.Entities.Senas
import com.DevRAT.lessa.R
import com.DevRAT.lessa.UI.Activities.SenaActivity
import com.sovize.ultracop.controlers.network.Glider
import kotlinx.android.synthetic.main.activity_sena.*

class SenasPageAdapter(private val context: Context, private var senaList: List<Senas>?, private  val index: Int) : PagerAdapter() {
    private val inflater: LayoutInflater


    init {
        inflater = LayoutInflater.from(context)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return senaList!!.size
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(R.layout.fragment_pager, view, false)!!
        var gifSena = imageLayout.findViewById(R.id.gifSenaPager) as ImageView
        Glider.load(senaList!![position].seña!!,gifSena)
        var textSena = imageLayout.findViewById(R.id.tv_nameSenaPager) as TextView
        textSena.setText(senaList!![position].palabra)
        view.addView(imageLayout, 0)


        return imageLayout
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {}

    override fun saveState(): Parcelable? {
        return null
    }

}