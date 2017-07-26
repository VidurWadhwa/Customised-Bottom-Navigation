package vidur.codeclan.bridge.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vidur on 7/25/2017.
 */

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {

    //Since this is private cannot be accessed from another class hence addFragment Method
    private List<Fragment> fragList = new ArrayList<>();

    public HomeFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragList.get(position);
    }


    @Override
    public int getCount() {
        return fragList.size();
    }

    public void addFragment(Fragment fragment){
        fragList.add(fragment);
    }
}
