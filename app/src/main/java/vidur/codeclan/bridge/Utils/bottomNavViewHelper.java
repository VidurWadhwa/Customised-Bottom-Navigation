package vidur.codeclan.bridge.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import vidur.codeclan.bridge.Activities.AcademicsActivity;
import vidur.codeclan.bridge.Activities.AwardsActivity;
import vidur.codeclan.bridge.Activities.MainActivity;
import vidur.codeclan.bridge.Activities.NoticeActivity;
import vidur.codeclan.bridge.Activities.ResultActivity;
import vidur.codeclan.bridge.R;

/**
 * Created by vidur on 7/25/2017.
 */

public class bottomNavViewHelper {

    private static final String TAG = "bottomNavViewHelper";

    public static void setUpNavView(BottomNavigationViewEx bnav){
        Log.d(TAG, "setUpNavView: Setting up");
        bnav.enableAnimation(false);
        bnav.enableItemShiftingMode(false);
        bnav.enableShiftingMode(false);
        bnav.setTextVisibility(false);
    }

    public static void enableNav(final Context c, BottomNavigationViewEx bottomNavigationViewEx){

        bottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){

                    case R.id.ic_home:
                        Intent intent1 = new Intent(c, MainActivity.class);
                        c.startActivity(intent1);
                        break;

                    case R.id.ic_academics:
                        Intent intent2 = new Intent(c, AcademicsActivity.class);
                        c.startActivity(intent2);
                        break;

                    case R.id.ic_notices:
                        Intent intent3 = new Intent(c, NoticeActivity.class);
                        c.startActivity(intent3);
                        break;

                    case R.id.ic_awards:
                        Intent intent4 = new Intent(c, AwardsActivity.class);
                        c.startActivity(intent4);
                        break;

                    case R.id.ic_result:
                        Intent intent5 = new Intent(c, ResultActivity.class);
                        c.startActivity(intent5);
                        break;


                }
                return false;
            }
        });

    }

}
