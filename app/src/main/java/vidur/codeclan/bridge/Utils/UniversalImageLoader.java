package vidur.codeclan.bridge.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import vidur.codeclan.bridge.R;

/**
 * Created by vidur on 7/26/2017.
 */

public class UniversalImageLoader {

    private static final int defaultImage = R.drawable.ic_arrow;

    private Context c;

    public UniversalImageLoader(Context c) {
        this.c = c;
    }

    public ImageLoaderConfiguration getInstance() {
        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
                                                    .showImageOnLoading(defaultImage)
                                                    .showImageForEmptyUri(defaultImage)
                                                    .showImageOnFail(defaultImage)
                                                    .cacheOnDisk(true).cacheInMemory(true).resetViewBeforeLoading(true)
                                                    .imageScaleType(ImageScaleType.EXACTLY).displayer(new FadeInBitmapDisplayer(300))
                                                    .build();

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(c)
                .defaultDisplayImageOptions(displayImageOptions)
                .memoryCache(new WeakMemoryCache())
                .diskCacheSize(100 * 1024 * 1024).build();

        return configuration;
    }

//    Can only used set if the images are static and are not setup for
//    a list view or grid view

    public static void setImage(String ImageUrl, ImageView iv_put, final ProgressBar mProgressBar, String append) {
        //append is to get the various types of files like from the web or drawables
        //https://github.com/VidurWadhwa/Android-Universal-Image-Loader

        ImageLoader imageLoader = ImageLoader.getInstance();

        imageLoader.displayImage(append + ImageUrl, iv_put, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

                if(mProgressBar != null){
                    mProgressBar.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                if(mProgressBar != null){
                    mProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                if(mProgressBar != null){
                    mProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                if(mProgressBar != null){
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });



    }
}
