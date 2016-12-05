package com.example.android_tfw_retrofit2_mvp.activity.main.main_navigation_drawer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_tfw_retrofit2_mvp.R;
import com.example.android_tfw_retrofit2_mvp.activity.main.frgments.HuiYIFragment;
import com.example.android_tfw_retrofit2_mvp.activity.main.frgments.TestFragment2;
import com.example.android_tfw_retrofit2_mvp.activity.main.frgments.TestFragment3;
import com.example.android_tfw_retrofit2_mvp.activity.main.frgments.TestFragment4;
import com.example.android_tfw_retrofit2_mvp.activity.main.frgments.dummy.DummyContent;
import com.example.android_tfw_retrofit2_mvp.activity.settings.ScrollingActivity;
import com.example.android_tfw_retrofit2_mvp.activity.settings.SettingsActivity;
import com.example.android_tfw_retrofit2_mvp.adapter.ViewPagerAdapter;
import com.example.android_tfw_retrofit2_mvp.base.BaseActivity;
import com.example.android_tfw_retrofit2_mvp.listener.OnFragmentInteractionListener;
import com.example.android_tfw_retrofit2_mvp.listener.OnListFragmentInteractionListener;
import com.example.android_tfw_retrofit2_mvp.utils.CustomToast;
import com.example.android_tfw_retrofit2_mvp.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 李均 on 2016/11/16.
 * Mainactivity 作为程序得主窗口主要功能时控制各个frgment的切换/
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,OnListFragmentInteractionListener,OnFragmentInteractionListener {

    FragmentManager fragmentManager;
    ArrayList<Fragment> fragments;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.id_tvbtn_huiyiguan)
    TextView idTvbtnHuiyiguan;
    @BindView(R.id.id_tvbtn_renwuta)
    TextView idTvbtnRenwuta;
    @BindView(R.id.id_tvbtn_guozijian)
    TextView idTvbtnGuozijian;
    @BindView(R.id.id_tvbtn_danganguan)
    TextView idTvbtnDanganguan;
    @BindView(R.id.id_ll_mian_bottom)
    LinearLayout idLlMianBottom;
    @BindView(R.id.id_btn_fab)
    FloatingActionButton idBtnFab;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.content_main)
    RelativeLayout contentMain;
    @BindView(R.id.id_btn_setting)
    Button btnSetting;
    @BindView(R.id.id_btn_exit)
    Button btnExit;
    @BindView(R.id.nav_view)
    MyNavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;  //侧滑抽屉
    private int currIndex;//当前页编号

    @NonNull
    private HuiYIFragment huiyigaunFragment;
    @NonNull
    private TestFragment2 testFragment2;
    @NonNull
    private TestFragment3 testFragment3;
    @NonNull
    private TestFragment4 testFragment4;

    @NonNull
    private ArrayList<TextView> textViews;

//    @Override
//    public void onRegisterCloseListener() {
//        registerCloseListener(this);
//    }
//
//    @Override
//    public void onUnRegisterCloseListener() {
//        unRegisterCloseListener(this);
//    }


    @Override
    protected void onResume() {
        super.onResume();
//        registerCloseListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unRegisterCloseListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // App Logo
        toolbar.setLogo(R.mipmap.ic_launcher);
        // Title
//        toolbar.setTitle("My Title");
        // Sub Title
        toolbar.setSubtitle("Sub title");
        setSupportActionBar(toolbar);

        // toolbar 设置监听
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                int id = item.getItemId();
//
//                //noinspection SimplifiableIfStatement
//                switch (id) {
//                    case R.id.action_settings:
//                        Toast.makeText(MainActivity.this, "Toolbar,settings", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.action_share:
//                        Toast.makeText(MainActivity.this, "Toolbar,share", Toast.LENGTH_SHORT).show();
//                        break;
//
//                }
//                return false;
//            }
//        });


        idBtnFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
        MyNavigationView navigationView = (MyNavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            }
        });
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.START);
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });
//          View view = LayoutInflater.from(this).inflate(R.layout.nav_header_main, null, false);
//        ImageView imageView = (ImageView) navigationView.findViewById(R.id.imageView);
//
//         imageView.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 startActivity(new Intent(MainActivity.this, ScrollingActivity.class));
//                 drawer.closeDrawer(GravityCompat.START);
//             }
//         });


        // TODO: 2016/11/22
        initBottomBtn();
        initFragment();
        initViewPager();

    }

    /**
     * 初始化底部按钮，设置监听事件
     */
    private void initBottomBtn() {
        idTvbtnHuiyiguan.setOnClickListener(new BottomBtnListener(0));
        idTvbtnRenwuta.setOnClickListener(new BottomBtnListener(1));
        idTvbtnGuozijian.setOnClickListener(new BottomBtnListener(2));
        idTvbtnDanganguan.setOnClickListener(new BottomBtnListener(3));
        textViews = new ArrayList<>();
        textViews.add(idTvbtnHuiyiguan);
        textViews.add(idTvbtnRenwuta);
        textViews.add(idTvbtnGuozijian);
        textViews.add(idTvbtnDanganguan);
        textViews.get(0).setBackgroundColor(getResources().getColor(R.color.darkorange));
    }

    /**
    * 初始化ViewPager
    */
    public void initViewPager(){
        //给ViewPager设置适配器
        viewPager.setAdapter(new ViewPagerAdapter(fragmentManager, fragments));
        viewPager.setCurrentItem(0);//设置当前显示标签页为第一页
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());//页面变化时的监听器
    }

    private void initFragment() {
        fragmentManager = getSupportFragmentManager();
        fragments = new ArrayList<Fragment>();
        huiyigaunFragment = HuiYIFragment.newInstance(2);
        testFragment2 = TestFragment2.newInstance("this is second fragment","2");
        testFragment3 = TestFragment3.newInstance("this is third fragment","3");
        testFragment4 = TestFragment4.newInstance("this is fourth fragment","4");
        fragments.add(huiyigaunFragment);
        fragments.add(testFragment2);
        fragments.add(testFragment3);
        fragments.add(testFragment4);
    }
    public class BottomBtnListener implements View.OnClickListener {

        private int index=0;

        public BottomBtnListener(int i) {
            index =i;
        }
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            viewPager.setCurrentItem(index);
        }
    }
    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
//        private int one = offset *2 +bmpW;//两个相邻页面的偏移量

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onPageSelected(int arg0) {
            // TODO Auto-generated method stub
//            Animation animation = new TranslateAnimation(currIndex*one,arg0*one,0,0);//平移动画
//            currIndex = arg0;
//            animation.setFillAfter(true);//动画终止时停留在最后一帧，不然会回到没有执行前的状态
//            animation.setDuration(200);//动画持续时间0.2秒
//            image.startAnimation(animation);//是用ImageView来显示动画的
            currIndex = arg0;
            for (int i = 0; i <textViews.size() ; i++) {
                if(i!=currIndex){
                    textViews.get(i).setBackgroundColor(getResources().getColor(R.color.royalblue));
                }else{
                    textViews.get(i).setBackgroundColor(getResources().getColor(R.color.darkorange));
                }
            }
        }
    }















    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                CustomToast.show(this, "settings", Toast.LENGTH_SHORT);
                startActivity(new Intent(MainActivity.this, ScrollingActivity.class));
                break;
            case R.id.action_about:
                CustomToast.show(this, "about", Toast.LENGTH_SHORT);
                break;
            case R.id.action_math:
                CustomToast.show(this, "math", Toast.LENGTH_SHORT);
                break;
            case R.id.action_english:
                CustomToast.show(this, "english", Toast.LENGTH_SHORT);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String str = "";
        if (id == R.id.nav_camera) {
            // Handle the camera action
            str = "nav_camera";
        } else if (id == R.id.nav_gallery) {
            str = "nav_gallery";
        } else if (id == R.id.nav_slideshow) {
            str = "nav_slideshow";
        } else if (id == R.id.nav_manage) {
            str = "nav_manage";
        } else if (id == R.id.nav_share) {
            str = "nav_share";
        } else if (id == R.id.nav_send) {
            str = "nav_send";
        }
        CustomToast.show(this, str, Toast.LENGTH_SHORT);

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            cancelLoading();
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
