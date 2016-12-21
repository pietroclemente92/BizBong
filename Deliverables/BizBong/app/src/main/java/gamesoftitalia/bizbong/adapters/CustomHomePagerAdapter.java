package gamesoftitalia.bizbong.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import gamesoftitalia.bizbong.R;

/**
 * Created by Raffaella on 16/12/2016.
 */

public class CustomHomePagerAdapter extends PagerAdapter {

    private int[] img;
    private Context context;
    private LayoutInflater layoutInflater;

    public CustomHomePagerAdapter(Context context, int[] img) {
        this.context = context;

        this.img = img;
    }

    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.banner_indicator, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imgBanner);
        //TextView textView = (TextView) view.findViewById(R.id.textBanner);
        imageView.setImageResource(img[position]);
        //textView.setText("Image:" +  position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.invalidate();
    }
}
