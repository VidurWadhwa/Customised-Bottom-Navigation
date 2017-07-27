package vidur.codeclan.bridge.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import vidur.codeclan.bridge.R;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by vidur on 7/27/2017.
 */

public class GridImageAdapter extends ArrayAdapter<String> {

    Context context;
    LayoutInflater layoutInflater;
    int layoutResource;
    String append;
    ArrayList<String> mUrlResource;

    public GridImageAdapter(Context context,
                            int layoutResource,
                            String append,
                            ArrayList<String> mUrlResource) {
        super(context, layoutResource, mUrlResource);
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layoutResource = layoutResource;
        this.append = append;
        this.mUrlResource = mUrlResource;
    }

    public static class ViewHolder{
        SquareImageGenerator profileImage;
        ProgressBar progressBar;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        if(convertView==null) {
            convertView = layoutInflater.inflate(layoutResource, parent, false);
            holder = new ViewHolder();
            holder.profileImage = (SquareImageGenerator) convertView.findViewById(R.id.GridImageView);
            holder.progressBar = (ProgressBar)convertView.findViewById(R.id.profileImageProgressBar);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        String imageUrl = getItem(position);
        ImageLoader imageLoader  =ImageLoader.getInstance();
        imageLoader.displayImage(append + imageUrl, holder.profileImage, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                if(holder.progressBar!=null){
                    holder.progressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                if(holder.progressBar!=null){
                    holder.progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                if(holder.progressBar!=null){
                    holder.progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                if(holder.progressBar!=null){
                    holder.progressBar.setVisibility(View.GONE);
                }
            }
        });
        return convertView;

    }
}
