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
            Glider.load("https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63",gifImageView)
            textView2.text = sena?.palabra
        }

    }
}
