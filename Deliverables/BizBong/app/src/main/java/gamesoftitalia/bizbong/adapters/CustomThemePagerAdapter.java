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

public class CustomThemePagerAdapter extends PagerAdapter {

    private int[] img;
    private String[] percentuali;
    private Context context;
    private LayoutInflater layoutInflater;

    public CustomThemePagerAdapter(Context context, int[] img, String[] percentuali) {
        this.context = context;
        this.img = img;
        this.percentuali = percentuali;
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
        View view = layoutInflater.inflate(R.layout.theme_banner_page_view, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imgBanner);
        TextView textView = (TextView) view.findViewById(R.id.textBanner);
        imageView.setImageResource(img[position]);
        textView.setText(percentuali[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.invalidate();
    }
}
