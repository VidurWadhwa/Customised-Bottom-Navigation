package vidur.codeclan.bridge.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import vidur.codeclan.bridge.R;
import vidur.codeclan.bridge.Utils.bottomNavViewHelper;

public class ResultActivity extends AppCompatActivity {

    private static final String TAG = "ResultActivity";
    private static final int number = 4;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Log.d(TAG, "onCreate: Result");
        progressBar = (ProgressBar)findViewById(R.id.profileProgressBar);
        progressBar.setVisibility(View.INVISIBLE);
        setUpNav();
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

    private void setUpNav() {
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        bottomNavViewHelper.setUpNavView(bottomNavigationViewEx);
        bottomNavViewHelper.enableNav(ResultActivity.this, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(number);
        menuItem.setChecked(true);

    }




  }

