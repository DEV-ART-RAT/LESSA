package com.DevRAT.lessa.UI.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.DevRAT.lessa.Database.Entities.Senas
import com.DevRAT.lessa.R
import com.sovize.ultracop.controlers.network.Glider
import kotlinx.android.synthetic.main.activity_sena.*

class SenaActivity : AppCompatActivity() {
    companion object{
        var sena : Senas? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sena).apply {
            tv_nameSena.text = sena?.palabra
            //gifSena.setImageResource()
            Glider.load(sena?.seña!!,gifSena)

        }

    }
}
