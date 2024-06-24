package com.id.masel.healtime.view.detail

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.id.masel.healtime.data.model.ListStory
import com.id.masel.healtime.databinding.ActivityDetailBinding
import com.id.masel.healtime.util.ViewStateCallback

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity(), ViewStateCallback<ListStory> {

    private lateinit var detailBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)



        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            elevation = 0f
        }
    }



    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onSuccess(data: ListStory) {
        Log.e(TAG, "onSuccess Detail: $data")

    }

    override fun onLoading() {
        Log.e(TAG, "onLoading Detail")
    }

    override fun onError(error: String?) {
        Log.e(TAG, "onFailed Detail: $error")
    }
}