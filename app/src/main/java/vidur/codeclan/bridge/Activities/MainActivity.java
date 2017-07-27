package vidur.codeclan.bridge.Activities;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nostra13.universalimageloader.core.ImageLoader;

import vidur.codeclan.bridge.Adapters.HomeFragmentPagerAdapter;
import vidur.codeclan.bridge.Fragments.CameraFragment;
import vidur.codeclan.bridge.Fragments.HomeFragment;
import vidur.codeclan.bridge.Fragments.MessagesFragment;
import vidur.codeclan.bridge.R;
import vidur.codeclan.bridge.Utils.UniversalImageLoader;
import vidur.codeclan.bridge.Utils.bottomNavViewHelper;

public class MainActivity extends AppCompatActivity {

    private static final int number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConfigLoader();
        setUpNav();
        setUpTabs();
        

    }

//    Function to add fragments to the page viwer will be
//    using the fragmentpager

    private void setUpTabs(){

        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new CameraFragment());
        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new MessagesFragment());

        ViewPager viewPager = (ViewPager)findViewById(R.id.content);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        //To set the icons to the tabs
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);
        tabLayout.getTabAt(1).setIcon(R.drawable.insta_logo);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_arrow);

    }


    private void setUpNav() {
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        bottomNavViewHelper.setUpNavView(bottomNavigationViewEx);
        bottomNavViewHelper.enableNav(MainActivity.this, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(number);
        menuItem.setChecked(true);

    }

    public void ConfigLoader() {

        UniversalImageLoader universalImageLoader = new UniversalImageLoader(MainActivity.this);
        ImageLoader.getInstance().init(universalImageLoader.getInstance());

    }


}
