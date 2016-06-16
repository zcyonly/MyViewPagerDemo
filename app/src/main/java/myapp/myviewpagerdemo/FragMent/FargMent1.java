package myapp.myviewpagerdemo.FragMent;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import myapp.myviewpagerdemo.Base.MyBaseFragment;
import myapp.myviewpagerdemo.R;
import myapp.myviewpagerdemo.Utils;
import myapp.myviewpagerdemo.ui.ChildViewPager;

/**
 * Created by Administrator on 2016/3/2.
 */
public class FargMent1 extends MyBaseFragment {
    private Context context;
    private ChildViewPager vp1;
    private View tv;
    private ArrayList<ImageView> images = new ArrayList<ImageView>();
    private int[] ims = {R.drawable.thing_lun_one, R.drawable.thing_lun_two, R.drawable.thing_lun_three};


    @Override
    public View initView(LayoutInflater inflater) {

        View view = inflater.inflate(R.layout.activity_1, null);
        tv = view.findViewById(R.id.tv_one);
        vp1 = (ChildViewPager) view.findViewById(R.id.one_pager);

        return view;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        context = getActivity();
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.Toastshow(context, "onePager");
            }
        });
        for (int i = 0; i < 3; i++) {
            ImageView im = new ImageView(context);
            im.setImageResource(ims[i]);
            images.add(im);
        }

        vp1.setAdapter(new MyViewPagerAdapter());
    }

    public class MyViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager) container).addView(images.get(position));
            return images.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewGroup) container).removeView(images.get(position));
        }
    }
}
