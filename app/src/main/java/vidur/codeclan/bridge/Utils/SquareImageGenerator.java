package vidur.codeclan.bridge.Utils;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by vidur on 7/27/2017.
 */

public class SquareImageGenerator extends AppCompatImageView{

    /*
        This is a class to override the imageview class and then
        set the height and width of the class to be equal
     */

    public SquareImageGenerator(Context context) {
        super(context);
    }

    public SquareImageGenerator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageGenerator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
