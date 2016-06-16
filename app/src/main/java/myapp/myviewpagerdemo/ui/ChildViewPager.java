package myapp.myviewpagerdemo.ui;

import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import myapp.myviewpagerdemo.Utils;

/**
 * Created by Administrator on 2016/3/3.
 */
public class ChildViewPager extends ViewPager {

    private int dss;

    public ChildViewPager(Context context) {
        super(context);
    }


    public ChildViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 触摸时按下的点 *
     */
    PointF downP = new PointF();
    /**
     * 触摸时当前的点 *
     */
    PointF curP = new PointF();
    OnSingleTouchListener onSingleTouchListener;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        // 当拦截触摸事件到达此位置的时候，返回true，
        // 说明将onTouch拦截在此控件，进而执行此控件的onTouchEvent
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        // 每次进行onTouch事件都记录当前的按下的坐标
        curP.x = arg0.getX();
        curP.y = arg0.getY();

        if (arg0.getAction() == MotionEvent.ACTION_DOWN) {
            // 记录按下时候的坐标
            // 切记不可用 downP = curP ，这样在改变curP的时候，downP也会改变
            downP.x = arg0.getX();
            downP.y = arg0.getY();
            // 此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰
            getParent().requestDisallowInterceptTouchEvent(true);
        }

        if (arg0.getAction() == MotionEvent.ACTION_MOVE) {
            // 此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰
            if ((getCurrentItem() == 0 && (arg0.getX() - downP.x) > 0) ||
                    (getCurrentItem() == getChildCount() && (arg0.getX() - downP.x) < 0)) {
                //第一页左滑和最后一页右滑,用来分发传递给父控件的点击事件
                //true为自己处理，false为交给父控件处理
                dss++;
                Utils.showUtLog("asdf", "jinlaile"+ dss);
                getParent().requestDisallowInterceptTouchEvent(false);
            } else {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
        }

        if (arg0.getAction() == MotionEvent.ACTION_UP) {
            // 在up时判断是否按下和松手的坐标为一个点
            // 如果是一个点，将执行点击事件，这是我自己写的点击事件，而不是onclick
            if (downP.x == curP.x && downP.y == curP.y) {
                onSingleTouch();
                return true;
            }
        }

        return super.onTouchEvent(arg0);
    }

    /**
     * 单击
     */
    public void onSingleTouch() {
        if (onSingleTouchListener != null) {

            onSingleTouchListener.onSingleTouch();
        }
    }

    /**
     * 创建点击事件接口
     */
    public interface OnSingleTouchListener {
        public void onSingleTouch();
    }

    public void setOnSingleTouchListener(OnSingleTouchListener onSingleTouchListener) {
        this.onSingleTouchListener = onSingleTouchListener;
    }
}
