package com.hing.newsapplication.enums

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.hing.newsapplication.R
import com.hing.newsapplication.ui.customnews.CustomNewsFragment
import com.hing.newsapplication.ui.profile.ProfileFragment
import com.hing.newsapplication.ui.topheadlinenews.TopHeadlineFragment

/**
 * Created by HingTang on 2019-12-07.
 */
enum class MainScreen(
    @IdRes val menuItemId: Int,
    @StringRes val titleStringId: Int,
    val fragment: Fragment
) {
    TOPHEADLINENEWS(
        R.id.navigation_top_headline_news,
        R.string.title_top_headline_news,
        TopHeadlineFragment()
    ),
    CUSTOMNEWS(
        R.id.navigation_custom_news,
        R.string.title_custom_news,
        CustomNewsFragment()
    ),
    PROFILE(
        R.id.navigation_profile,
        R.string.title_profile,
        ProfileFragment()
    )
}

fun getMainScreenForMenuItem(menuItemId: Int): MainScreen? {
    for (mainScreen in MainScreen.values()) {
        if (mainScreen.menuItemId == menuItemId) {
            return mainScreen
        }
    }
    return null
}
