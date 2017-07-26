package vidur.codeclan.bridge.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import vidur.codeclan.bridge.R;
import vidur.codeclan.bridge.Utils.bottomNavViewHelper;

public class AccountSettingsActivity extends AppCompatActivity {

    private static final int number = 4;
    ImageView back;
    private Context c = AccountSettingsActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountsettings);
        back = (ImageView) findViewById(R.id.backArrow);
        setUpNav();
        setUpList();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(c, ResultActivity.class));
            }
        });

    }

    private void setUpList() {

        ListView listView = (ListView) findViewById(R.id.settingsListView);

        ArrayList<String> options = new ArrayList<>();

        options.add("Edit Profile");
        options.add("Sign Out");

        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(c,android.R.layout.simple_list_item_1, options);
        listView.setAdapter(mAdapter);


    }

    private void setUpNav() {
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        bottomNavViewHelper.setUpNavView(bottomNavigationViewEx);
        bottomNavViewHelper.enableNav(AccountSettingsActivity.this, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(number);
        menuItem.setChecked(true);
    }
}
