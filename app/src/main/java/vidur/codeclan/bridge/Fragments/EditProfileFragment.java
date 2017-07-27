package vidur.codeclan.bridge.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import vidur.codeclan.bridge.R;
import vidur.codeclan.bridge.Utils.UniversalImageLoader;


public class EditProfileFragment extends Fragment {

    ImageView mProfileImage;
    ImageView getBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        mProfileImage = (ImageView)v.findViewById(R.id.profile_editable_image);
        getBack = (ImageView) v.findViewById(R.id.backArrow);
        getBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To finish this fragment
                getActivity().finish();
            }
        });

//        ConfigLoader();

//        String URL = "https://pbs.twimg.com/profile_images/606585229034135553/2NqZJYQI.png";
//        UniversalImageLoader.setImage(URL, mProfileImage, null, "");
        return v;


    }




}
