package ir.nima.balootchallenge.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import ir.nima.balootchallenge.R
import ir.nima.balootchallenge.databinding.FragmentMainPageBinding
import ir.nima.balootchallenge.ui.adapter.ViewPagerAdapter

@AndroidEntryPoint
class MainPageFragment : BaseFragment(R.layout.fragment_main_page) {
    private lateinit var _binding : FragmentMainPageBinding
    private val binding get() = _binding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentMainPageBinding.bind(view)
        addTabs()
        setViewPager()
    }
    private fun addTabs() {
        val tabIcons = intArrayOf(
            R.drawable.ic_baseline_article_24,
            R.drawable.ic_baseline_person_24,
        )
        binding.apply {
            tabLayout.addTab(
                tabLayout.newTab().setIcon(tabIcons[0])
                    .setText(getString(R.string.all_news))
            )
            tabLayout.addTab(
                tabLayout.newTab().setIcon(tabIcons[1])
                    .setText(getString(R.string.profile))
            )
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    viewPager.setCurrentItem(tab.position)
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {}
                override fun onTabReselected(tab: TabLayout.Tab) {}
            })

        }
    }

    private fun setViewPager() {
        val fragments: MutableList<Fragment> = ArrayList()
        fragments.add(NewsFragment())
        fragments.add(ProfileFragment())
        val viewPagerAdapter = ViewPagerAdapter(
            childFragmentManager,
            lifecycle, fragments, this
        )
        binding.viewPager.adapter = viewPagerAdapter
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> binding.tabLayout.getTabAt(0)?.select()
                    1 -> binding.tabLayout.getTabAt(1)?.select()
                }
            }
        })
    }


}