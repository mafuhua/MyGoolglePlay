package com.hellomc.mygoogle;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.hellomc.mygoogle.activity.BaseActivity;
import com.hellomc.mygoogle.activity.DetailActivity;
import com.hellomc.mygoogle.fragment.BaseFragment;
import com.hellomc.mygoogle.fragment.FragmentFactory;
import com.hellomc.mygoogle.holder.MenuHolder;
import com.hellomc.mygoogle.utils.UIUtils;

public class MainActivity extends BaseActivity implements SearchView.OnQueryTextListener, ActionBar.TabListener {
    ViewPager viewPager;
    ActionBar supportActionBar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_tab_strip);
        pagerTabStrip.setTabIndicatorColor(getResources().getColor(R.color.indicatorcolor));
        pagerTabStrip.setBackgroundColor(Color.WHITE);
        pagerTabStrip.setTextColor(Color.BLACK);

    }

    @Override
    public void initData() {
        viewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                BaseFragment fragment = (BaseFragment) FragmentFactory.getInstance().creatFragment(position);
                fragment.loadAndView();
            }
        });
        FrameLayout menuFl = (FrameLayout) findViewById(R.id.menu_fl);
        MenuHolder menuHolder = new MenuHolder();
        menuFl.addView(menuHolder.getContenView());
        menuHolder.setActivity(this);
    }


    @Override
    public void initActionBar() {
        supportActionBar = getSupportActionBar();

        supportActionBar.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.drawable.ic_drawer_am,
                R.string.drawer_open,
                R.string.drawer_close
        ) {
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                Toast.makeText(MainActivity.this, "关闭菜单", Toast.LENGTH_SHORT).show();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Toast.makeText(MainActivity.this, "开启菜单", Toast.LENGTH_SHORT).show();
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        //把开关显示到ActionBar的home菜单位置
        mDrawerToggle.syncState();
    }


    public void toDetail(View view) {
        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.home:
                Toast.makeText(this, "home", Toast.LENGTH_SHORT).show();
                mDrawerToggle.onOptionsItemSelected(item);
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Toast.makeText(this, "确定搜索内容" + query, Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Toast.makeText(this, "搜索内容" + newText, Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        // Toast.makeText(this, "tab.getText():" + tab.getText(), Toast.LENGTH_SHORT).show();
        //  viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    class MainAdapter extends FragmentStatePagerAdapter {
        public MainAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return UIUtils.getStringArray()[position];
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentFactory.getInstance().creatFragment(position);
        }

        @Override
        public int getCount() {
            return UIUtils.getStringArray().length;
        }
    }

}
