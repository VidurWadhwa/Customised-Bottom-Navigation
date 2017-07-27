package vidur.codeclan.bridge.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import vidur.codeclan.bridge.Adapters.SectionsStatePagerAdapter;
import vidur.codeclan.bridge.Fragments.EditProfileFragment;
import vidur.codeclan.bridge.Fragments.SignOutFragment;
import vidur.codeclan.bridge.R;
import vidur.codeclan.bridge.Utils.bottomNavViewHelper;

public class AccountSettingsActivity extends AppCompatActivity {

    private static final int number = 3;
    private SectionsStatePagerAdapter mAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());

    private ViewPager vpager;
    private RelativeLayout rLayout;
    ImageView back;
    private Context c = AccountSettingsActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountsettings);
        vpager = (ViewPager)findViewById(R.id.content);
        rLayout = (RelativeLayout) findViewById(R.id.Relative1) ;
        back = (ImageView) findViewById(R.id.backArrow);
        setUpFragments();
        setUpNav();
        setUpList();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(c, ResultActivity.class));
            }
        });
        setUpFragments();

    }

    private void setUpFragments() {

        mAdapter.addFragment(new EditProfileFragment(), "Edit Profile");
        mAdapter.addFragment(new SignOutFragment(), "Sign Out");

    }

    private void setUpList() {

        ListView listView = (ListView) findViewById(R.id.settingsListView);

        ArrayList<String> options = new ArrayList<>();

        options.add("Edit Profile");
        options.add("Sign Out");

        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(c,android.R.layout.simple_list_item_1, options);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setUpViewPager(position);
            }
        });

    }

    private void setUpViewPager(Integer fragmentNo) {

        rLayout.setVisibility(View.GONE);
        vpager.setAdapter(mAdapter);
        vpager.setCurrentItem(fragmentNo);

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
