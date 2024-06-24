package com.id.masel.healtime.view

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.id.masel.healtime.R
import com.id.masel.healtime.data.local.DiseaseEntity
import com.id.masel.healtime.data.model.DiseaseResponse
import com.id.masel.healtime.databinding.ActivityMainBinding
import com.id.masel.healtime.util.Constanta.EXTRA_TOKEN
import com.id.masel.healtime.util.ViewModelFactory
import com.id.masel.healtime.util.ViewStateCallback
import com.id.masel.healtime.view.Fragment.acc.AccFragment
import com.id.masel.healtime.view.Fragment.archieve.ArchiFragment
import com.id.masel.healtime.view.Fragment.history.HistoryFragmentFragment
import com.id.masel.healtime.view.Fragment.history.HistoryFragment
import com.id.masel.healtime.view.Fragment.home.HomepageFragment
import com.id.masel.healtime.view.login.LoginActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), ViewStateCallback<DiseaseResponse> {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var bottomNavigationView: BottomNavigationView

    private val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
    private val mainViewModel: MainViewModel by viewModels {
        factory
    }

    private lateinit var tkn: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val token = intent.getStringExtra(EXTRA_TOKEN)
        Log.e(TAG, "token: $token")
        if (token != null) {
            getAllDisease(token)
        }


        setAdapter()
        if (token != null) {
            if (token.isNotEmpty()){
            }
            tkn = token
        }
        else {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            Toast.makeText(this, getString(R.string.please_login), Toast.LENGTH_SHORT).show()
            finish()
        }


        bottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemReselectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.bottom_home -> {
                    replaceFragment(HomepageFragment())
                    true
                }
                R.id.bottom_story -> {
                replaceFragment(HistoryFragment())
                true
                }
                R.id.bottom_save -> {
                    replaceFragment(ArchiFragment())
                    true
                }
                R.id.bottom_acc -> {
                    replaceFragment(AccFragment())
                    true
                }
                else -> false
            }
        }
        replaceFragment(HomepageFragment())
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()
    }

    private fun getAllDisease(token: String) {
        mainViewModel.getStories(token).observe(this@MainActivity) {
            updateRecyclerData(it)
        }
    }

    private fun updateRecyclerData(Disease: PagingData<DiseaseEntity>) {
        val recyclerViewState = recyclerView.layoutManager?.onSaveInstanceState()
        DiseaseAdapter.submitData(lifecycle, Disease)
        recyclerView.layoutManager?.onRestoreInstanceState(recyclerViewState)
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_option, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_logout -> {
                lifecycleScope.launch {
                    mainViewModel.logout()
                }
                Toast.makeText(this, getString(R.string.logout), Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish()
                true
            }

            else -> false
        }
    }

}