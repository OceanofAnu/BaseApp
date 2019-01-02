package com.example.chch2.baseapp.base.util;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

public class ToastUtils {

    /**
     * 系统toast
     */
	private static Toast toast;
	private static Handler h= new Handler();
	private static Runnable run = new Runnable() {
		@Override
		public void run() {
			if(toast != null){
				toast.cancel();
				toast = null;
			}
		}
	};
    public static void showShort(Context context, String s){
        show(context, s, Toast.LENGTH_SHORT);
    }
    public static void showLong(Context context, String s){
    	show(context, s, Toast.LENGTH_LONG);
    }

    private static void show(Context context, String s, int x){
    	if(toast != null){
    		//h.postDelayed(run, 0);
    		toast.cancel();
    		toast = null;
    	}
    	toast = Toast.makeText(context, s, x);
    	toast.show();
    	h.postDelayed(run, x== Toast.LENGTH_SHORT?1500:3000);
    }


}
