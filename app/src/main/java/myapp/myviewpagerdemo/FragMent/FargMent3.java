package myapp.myviewpagerdemo.FragMent;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import myapp.myviewpagerdemo.Base.MyBaseFragment;
import myapp.myviewpagerdemo.R;
import myapp.myviewpagerdemo.Utils;

/**
 * Created by Administrator on 2016/3/2.
 */
public class FargMent3 extends MyBaseFragment {
    private Context context;
    private View tv;

    @Override
    public View initView(LayoutInflater inflater) {

        View view =inflater.inflate(R.layout.activity_3, null);
        tv=view.findViewById(R.id.tv_three);
        return view;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        context=getActivity();
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.Toastshow(context,"threePager");
            }
        });



    }
}
