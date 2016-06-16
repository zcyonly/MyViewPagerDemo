package myapp.myviewpagerdemo;

import android.content.Context;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import myapp.myviewpagerdemo.FragMent.FargMent1;
import myapp.myviewpagerdemo.FragMent.FargMent2;
import myapp.myviewpagerdemo.FragMent.FargMent3;


public class MainActivity extends ActionBarActivity {
    private ViewPager viewPager;
    private PagerTabStrip pagerTabStrip;
    private List<Fragment> list;//页面列表
    private List<String> titlelist;//标题列表
    private ImageView img_bottom_line;//移动的动画
    int currIndex = 0;// 当前页卡号
    int bmpW;// 图片宽度
    int offset;// 动画偏移量
    private TextView text_one;
    private TextView text_two;
    private TextView text_three;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context= this;
        initImageView();
        initTextView();
        initView();
    }

    private void initView() {
        viewPager= (ViewPager) findViewById(R.id.viewpager);
        pagerTabStrip= (PagerTabStrip) findViewById(R.id.pagertab);
        pagerTabStrip.setTabIndicatorColor(getResources().getColor(android.R.color.holo_green_dark));
        pagerTabStrip.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
        list=new ArrayList<Fragment>();
        list.add(new FargMent1());
        list.add(new FargMent2());
        list.add(new FargMent3());
        titlelist=new ArrayList<>();
        titlelist.add("this is one");
        titlelist.add("this is two");
        titlelist.add("this is three");
        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());

    }
    public void initImageView() {

        img_bottom_line = (ImageView) findViewById(R.id.img_bottom_line);

        bmpW = img_bottom_line.getLayoutParams().width;
        // BitmapFactory.decodeResource(getResources(), id);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        System.out.println("------------" + screenW);

        offset = (screenW / 3 - bmpW) / 2;

        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        img_bottom_line.setImageMatrix(matrix);// 设置动画图片初始位置
    }
    public class MyViewPagerAdapter extends FragmentPagerAdapter{

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return list.get(i);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titlelist.get(position);
        }
    }


    class MyOnClick implements View.OnClickListener {
        int index = 0;

        MyOnClick(int i) {
            this.index = i;
        }

        @Override
        public void onClick(View v) {

            viewPager.setCurrentItem(index);
        }

    }
    public void initTextView() {
        text_one = (TextView) findViewById(R.id.text_one);
        text_two = (TextView) findViewById(R.id.text_two);
        text_three = (TextView) findViewById(R.id.text_three);

        text_one.setOnClickListener(new MyOnClick(0));
        text_two.setOnClickListener(new MyOnClick(1));
        text_three.setOnClickListener(new MyOnClick(2));

    }
    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        int one = offset * 2 + bmpW;// yeka1-->yeka2 pianyiliang
        int two = offset * 2;// yeka2-->yeka3 pianyiliang

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int arg0) {
            Animation animation = new TranslateAnimation(one * currIndex, one
                    * arg0, 0, 0);
            currIndex = arg0;
            animation.setFillAfter(true);// True:图片停留在动画结束的位置
            animation.setDuration(300);
            img_bottom_line.startAnimation(animation);
            Utils.Toastshow(context,"您选择了" + viewPager.getCurrentItem() + "yeka");
        }

    }
}
