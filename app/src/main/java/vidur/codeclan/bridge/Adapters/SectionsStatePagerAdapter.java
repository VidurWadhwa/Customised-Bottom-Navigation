package vidur.codeclan.bridge.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by vidur on 7/26/2017.
 */

public class SectionsStatePagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<>();

    private HashMap<Fragment, Integer> fragmentIntegerHashMap = new HashMap<>();

    private HashMap<String, Integer> fragmentNumbers = new HashMap<>();

    private HashMap<Integer, String> fragmentNames = new HashMap<>();

    public SectionsStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(Fragment fragment, String name){
        fragmentList.add(fragment);
        fragmentIntegerHashMap.put(fragment, (getCount()-1));
        fragmentNumbers.put(name, (getCount()-1));
        fragmentNames.put((getCount() - 1), name);

    }
}
