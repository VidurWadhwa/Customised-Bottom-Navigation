package vidur.codeclan.bridge.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

    private static final String TAG = "MainActivity";

    private static final int number = 0;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        ConfigLoader();
        setUpFirebaseAuth();
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

    /*
    ------------------------------------------------------------ firebase stuff
     */


    private void checkCurrentStatus(FirebaseUser user) {

        Log.d(TAG, "checkCurrentStatus: Checking user");
        if(user == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }

    }

    private void setUpFirebaseAuth() {


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                checkCurrentStatus(user);

                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }

            }
        };

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        /*
        -------------------------------Always check the users state when this activity starts
         */
        //checkCurrentStatus(mAuth.getCurrentUser());

    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


}
