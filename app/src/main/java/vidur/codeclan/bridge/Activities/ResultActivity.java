package vidur.codeclan.bridge.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import vidur.codeclan.bridge.R;
import vidur.codeclan.bridge.Utils.GridImageAdapter;
import vidur.codeclan.bridge.Utils.UniversalImageLoader;
import vidur.codeclan.bridge.Utils.bottomNavViewHelper;

public class ResultActivity extends AppCompatActivity {

    private static final String TAG = "ResultActivity";
    private static final int number = 4;
    private ProgressBar progressBar;
    ImageView iv_profile;
    int NUM_GRID_COLUMNS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Log.d(TAG, "onCreate: Result");
        iv_profile = (ImageView) findViewById(R.id.profile_image);
        progressBar = (ProgressBar)findViewById(R.id.profileProgressBar);
        progressBar.setVisibility(View.INVISIBLE);
        setUpNav();
        tempGridViewSetup();
        setUpProfileImage();
        setUpToolbar();
    }

    //Setting up the toolBar

    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.top_profile_bar);
        setSupportActionBar(toolbar);
        ImageView settings = (ImageView)findViewById(R.id.proileMenu);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResultActivity.this, AccountSettingsActivity.class);
                startActivity(i);
            }
        });

    }
    //Set up Nav Bar
    private void setUpNav() {
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        bottomNavViewHelper.setUpNavView(bottomNavigationViewEx);
        bottomNavViewHelper.enableNav(ResultActivity.this, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(number);
        menuItem.setChecked(true);

    }

    //Set up Profile Image

    private void setUpProfileImage() {

        Log.d(TAG, "setUpProfileImage: setting up profile photo");
        UniversalImageLoader.setImage("https://pbs.twimg.com/profile_images/606585229034135553/2NqZJYQI.png", iv_profile, progressBar, "");

    }


    private void setUpImageGridView(ArrayList<String> mImageUrl) {

        GridView imageGrid = (GridView)findViewById(R.id.imageGrid);
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int imageWidth = screenWidth/NUM_GRID_COLUMNS;
        imageGrid.setColumnWidth(imageWidth);
        GridImageAdapter adapter = new GridImageAdapter(ResultActivity.this, R.layout.grid_indivisual, "", mImageUrl);
        imageGrid.setAdapter(adapter);


    }
    //Images will give error override the imageView class and then make them square and center cropped..........
    private void tempGridViewSetup() {
        ArrayList<String> mImageUrl = new ArrayList<String>();
        mImageUrl.add("http://i.imgur.com/mN7U3dK.jpg");
        mImageUrl.add("https://lh4.ggpht.com/mJDgTDUOtIyHcrb69WM0cpaxFwCNW6f0VQ2ExA7dMKpMDrZ0A6ta64OCX3H-NMdRd20=w300");
        mImageUrl.add("https://www.donationcoder.com/Software/Mouser/urlsnooper/screenshots/Screenshot_main.gif");
        mImageUrl.add("http://urlprofiler.com/wp-content/themes/urlprofiler/images/_majestic.png");
        mImageUrl.add("https://resources.whatwg.org/logo-url.svg");
        mImageUrl.add("http://www.openvalley-web.com/wp-content/uploads/2012/08/seo-url-structure.jpg");
        mImageUrl.add("http://complexspiral.com/events/archive/2004/uiuc/keynote/my-url-is.png");
        mImageUrl.add("https://www.rankwatch.com/learning/sites/default/files/sectionimg/S2_0.png");
        mImageUrl.add("https://lh4.ggpht.com/mJDgTDUOtIyHcrb69WM0cpaxFwCNW6f0VQ2ExA7dMKpMDrZ0A6ta64OCX3H-NMdRd20=w300");
        mImageUrl.add("https://lh4.ggpht.com/mJDgTDUOtIyHcrb69WM0cpaxFwCNW6f0VQ2ExA7dMKpMDrZ0A6ta64OCX3H-NMdRd20=w300");
        mImageUrl.add("https://lh4.ggpht.com/mJDgTDUOtIyHcrb69WM0cpaxFwCNW6f0VQ2ExA7dMKpMDrZ0A6ta64OCX3H-NMdRd20=w300");
        setUpImageGridView(mImageUrl);
    }



  }

