package com.aravi.shelftabssample.fragment

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter

import com.aravi.shelftabssample.R
import com.aravi.shelftabssample.frament.TestFragment
import io.github.kamaravichow.shelftabs.DisplayUtil
import io.github.kamaravichow.shelftabs.TabAdapter
import io.github.kamaravichow.shelftabs.TabView
import kotlinx.android.synthetic.main.fragment_tags_list.*

class TagsListFragment : Fragment() {

    lateinit var tabsTitle: MutableList<String>
    var fragments: MutableList<Fragment> = ArrayList<Fragment>()
    lateinit var adapter: TabAdapter
    lateinit var fragmentPagerAdapter: FragmentPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tags_list,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        getData()
    }

    private fun initView() {
        //个人建议使用pt适配
        (rlTitle.layoutParams as MarginLayoutParams).leftMargin = DisplayUtil.getSR(20)
        (rlTitle.layoutParams as MarginLayoutParams).topMargin = DisplayUtil.getSR(23)
        (rlTitle.layoutParams as MarginLayoutParams).bottomMargin = DisplayUtil.getSR(20)

        Log.e("xujj","SR:${DisplayUtil.getSR(64)}")
        verticalTabLayout.layoutParams.width = DisplayUtil.getSR(64)
        verticalTabLayout2.layoutParams.width = DisplayUtil.getSR(64)

        vpAllTags.offscreenPageLimit = 10
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, DisplayUtil.getSR(18).toFloat())
    }

    private fun getData() {
        tabsTitle = mutableListOf("Programming" , "JAVA" , "Android", "Kotlin", "C++", "Flutter", "Python")

        for (title in tabsTitle){
            val fragment = TestFragment()
            val bundle1 = Bundle()
            bundle1.putString("_type", title)
            fragment.arguments = bundle1
            fragments.add(fragment)
        }

        setAdapter()
    }

    private fun setAdapter() {
        fragmentPagerAdapter = object : FragmentPagerAdapter(childFragmentManager) {
            override fun getItem(i: Int): Fragment {
                return fragments[i]
            }

            override fun getCount(): Int {
                return fragments.size
            }
        }

        vpAllTags.adapter = fragmentPagerAdapter
        verticalTabLayout.setupWithViewPager(vpAllTags)
        verticalTabLayout2.setupWithViewPager(vpAllTags)
        adapter =object : TabAdapter {
            override fun getIcon(position: Int): TabView.TabIcon? {
                return null
            }

            override fun getBadge(position: Int): TabView.TabBadge? {
                return null
            }

            override fun getBackground(position: Int): Int {
                return resources.getColor(R.color.white)
            }

            override fun getTitle(position: Int): TabView.TabTitle {
                return TabView.TabTitle.Builder()
                    .setContent(tabsTitle[position])
                    .setTextSize(DisplayUtil.getSR(13))
                    .setTextColor(0xFFcbcbcc.toInt(), 0xFF4a4a4a.toInt())
                    .build()
            }

            override fun getCount(): Int {
                return tabsTitle.size
            }

        }
        verticalTabLayout.setTabAdapter(adapter)
        verticalTabLayout2.setTabAdapter(adapter)
    }

}