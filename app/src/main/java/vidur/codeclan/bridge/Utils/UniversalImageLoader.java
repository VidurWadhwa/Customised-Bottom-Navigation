package vidur.codeclan.bridge.Utils;

import android.content.Context;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

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
}
