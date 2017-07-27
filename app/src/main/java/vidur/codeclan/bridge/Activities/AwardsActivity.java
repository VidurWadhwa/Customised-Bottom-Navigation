package vidur.codeclan.bridge.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import vidur.codeclan.bridge.R;
import vidur.codeclan.bridge.Utils.bottomNavViewHelper;

public class AwardsActivity extends AppCompatActivity {
    private static final int number = 3;
    private static final String TAG = "AwardsActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awards);
        setUpNav();
        Log.d(TAG, "onCreate: Awards");
    }
    private void setUpNav() {
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        bottomNavViewHelper.setUpNavView(bottomNavigationViewEx);
        bottomNavViewHelper.enableNav(AwardsActivity.this, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(number);
        menuItem.setChecked(true);

    }
}
