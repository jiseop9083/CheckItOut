package com.example.madcampweek1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import com.example.madcampweek1.databinding.ActivityMainBinding
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout
    private val context: Context = this
    // lateinit는 var
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    val dataManager = DataManager.instance
    private val tabTextList = dataManager.getTabTextList()
    private val subjectList = dataManager.getSubjectList()
    private val tabIconList = listOf(R.drawable.profile, R.drawable.check, R.drawable.calendar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val intent = Intent(this, LoadingActivity::class.java)
        startActivity(intent)

        val i = binding.tabs.tabCount

        binding.viewPagerContainer.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.tabs, binding.viewPagerContainer) { tab, pos ->
            tab.text = tabTextList[pos]
            tab.setIcon(tabIconList[pos])
        }.attach()

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayShowTitleEnabled(false) // 기존 title 지우기
        actionBar?.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 만들기
        actionBar?.setHomeAsUpIndicator(R.drawable.check) // 뒤로가기 버튼 이미지 지정

        mDrawerLayout = findViewById(R.id.drawer_layout)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            mDrawerLayout.closeDrawers()

            val id: Int = menuItem.itemId
            val title: String = menuItem.title.toString()
            when (id) {
                R.id.subject1 -> binding.subjectText.text = subjectList[0]
                R.id.subject2 -> binding.subjectText.text = subjectList[1]
                R.id.subject3 -> binding.subjectText.text = subjectList[2]
            }

            true
        }
//        binding.subjectMenu.setOnClickListener {
//            onOptionsItemSelected(item: MenuItem)
//        }




        binding.viewPagerContainer.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            var currentState = 0
            var currentPos = 0

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                currentPos = position
                //binding.subjectText.text =subjectList[currentPos]
                binding.titleText.text = tabTextList[currentPos]
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                currentState = state

                super.onPageScrollStateChanged(state)
            }
        })


    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> { // 왼쪽 상단 버튼 눌렀을 때
                mDrawerLayout.openDrawer(GravityCompat.START)


                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}