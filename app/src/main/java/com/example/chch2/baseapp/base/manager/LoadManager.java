package com.example.chch2.baseapp.base.manager;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;


/**
 * 显示Dialog的加载窗口
 * <p>
 * Created by Administratori on 2016/9/2.
 */

public class LoadManager {
    public static ProgressDialog mDialog;

    //private static ProgressDialog mProgressDialog;
    //显示加载窗口
    public static void showLoad(final Context context, String message) {
        /**
         * AlertDialog
         *
         */
//        mDialog = new AlertDialog.Builder(context).create();
//        //mDialog.setCanceledOnTouchOutside(false);//单独的dialog点击空白不消失（点击返回键可以消失）
//        mDialog.setCancelable(false);//activity生成的dialog点击空白不消失 （点击返回键不能消失）
//        mDialog.show();
//        // 注意此处要放在show之后 否则会报异常
//        mDialog.setContentView(R.layout.loading_process_dialog);
        /**
         * ProgressDialog
         *
         */
        if (mDialog == null) {
            mDialog = ProgressDialog.show(context, null, message + "... ", true);
        }
//        mDialog= new ProgressDialog(context);
//        mDialog.setCancelable(true);
//        mDialog.setIndeterminate(true);
//        mDialog.setCancelable(false);
//        mDialog.setMessage(message+"... ");
//        mDialog.show();
    }

    public static void showLoadNoMsg(final Context context){
        /**
         * ProgressDialog
         */
        mDialog = ProgressDialog.show(context, null,"", true);
        mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {

            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub
                // Cancel task.
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dialog.dismiss();
                }
                return false;
            }
        });
    }

    //关闭加载窗口
    public static void dismissLoad() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    private static long f = 0;

    //显示加载窗口
    public static void showLoad(final Activity context, String message) {
        /**
         * AlertDialog
         *
         */
//        mDialog = new AlertDialog.Builder(context).create();
//        //mDialog.setCanceledOnTouchOutside(false);//单独的dialog点击空白不消失（点击返回键可以消失）
//        mDialog.setCancelable(false);//activity生成的dialog点击空白不消失 （点击返回键不能消失）
//        mDialog.show();
//        // 注意此处要放在show之后 否则会报异常
//        mDialog.setContentView(R.layout.loading_process_dialog);
        /**
         * ProgressDialog
         */
        mDialog = ProgressDialog.show(context, null, message + "... ", true);
        mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {

            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub
                // Cancel task.
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dialog.dismiss();
                }
                return false;
            }
        });
//        mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface arg0) {
//                long time = System.currentTimeMillis();
//                if (time - f >= 2000) {
//                    f = time;
//                    return;
//                } else {
//                    f=0;
//                    context.finish();
//                    //overridePendingTransition(R.anim.hyperspace_in, R.anim.hyperspace_out);
//                }
//            }
//        });
//        mDialog= new ProgressDialog(context);
//        mDialog.setCancelable(true);
//        mDialog.setIndeterminate(true);
//        mDialog.setCancelable(false);
//        mDialog.setMessage(message+"... ");
//        mDialog.show();
    }
}
