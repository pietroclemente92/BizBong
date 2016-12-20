package gamesoftitalia.bizbong.gifanimator;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.SurfaceView;

/**
 * Created by GameSoftItalia on 26/10/2016.
 */

public class GifSurfaceView extends SurfaceView {

    public GifSurfaceView(Context context) {
        super(context);
        this.setZOrderOnTop(true); // necessary
        this.getHolder().setFormat(PixelFormat.TRANSLUCENT);
    }
}
