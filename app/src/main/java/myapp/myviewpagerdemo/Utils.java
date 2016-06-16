package myapp.myviewpagerdemo;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    private static Toast toast;

    public static void Toastshow(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }
    public static String getTimeStr(long time) {
        Date date = new Date(time);
        SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdfs.format(date);
    }

    public static String getCurrentTime(){
        return getTimeStr(System.currentTimeMillis());
    }
    public static void showUtLog(String tag, String msg) {
        if (!TextUtils.isEmpty(msg)) {

            if (TextUtils.isEmpty(tag)) {
                tag = "日志";
            }
            Log.i(tag, msg);

        }
    }


    /**
     * 获取手机的缓存目录
     * @param context
     * @return
     */
    public String getDiskCacheDir(Context context) {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }
}
