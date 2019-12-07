package com.hing.newsapplication

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hing.newsapplication.enums.MainScreen
import com.hing.newsapplication.enums.getMainScreenForMenuItem
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
    HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var viewPager: ViewPager
    private lateinit var navView: BottomNavigationView
    private lateinit var mainPagerAdapter: MainPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.view_pager)
        navView = findViewById(R.id.nav_view)
        mainPagerAdapter = MainPagerAdapter(supportFragmentManager)

        mainPagerAdapter.setItems(arrayListOf(MainScreen.TOPHEADLINENEWS, MainScreen.CUSTOMNEWS, MainScreen.PROFILE))

        val defaultScreen = MainScreen.TOPHEADLINENEWS
        scrollToScreen(defaultScreen)
        selectBottomNavigationViewMenuItem(defaultScreen.menuItemId)
        supportActionBar?.setTitle(defaultScreen.titleStringId)

        navView.setOnNavigationItemSelectedListener(this)

        viewPager.adapter = mainPagerAdapter
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                val selectedScreen = mainPagerAdapter.getItems()[position]
                selectBottomNavigationViewMenuItem(selectedScreen.menuItemId)
                supportActionBar?.setTitle(selectedScreen.titleStringId)
            }
        })
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        getMainScreenForMenuItem(menuItem.itemId)?.let {
            scrollToScreen(it)
            supportActionBar?.setTitle(it.titleStringId)
            return true
        }
        return false
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        if (::dispatchingAndroidInjector.isInitialized) {
            return dispatchingAndroidInjector
        }
        return AndroidInjector { }
    }

    private fun scrollToScreen(mainScreen: MainScreen) {
        val screenPosition = mainPagerAdapter.getItems().indexOf(mainScreen)
        if (screenPosition != viewPager.currentItem) {
            viewPager.currentItem = screenPosition
        }
    }

    private fun selectBottomNavigationViewMenuItem(@IdRes menuItemId: Int) {
        navView.setOnNavigationItemSelectedListener(null)
        navView.selectedItemId = menuItemId
        navView.setOnNavigationItemSelectedListener(this)
    }
}
