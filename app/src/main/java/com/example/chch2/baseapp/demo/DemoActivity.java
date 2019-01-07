package com.example.chch2.baseapp.demo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.chch2.baseapp.R;
import com.example.chch2.baseapp.base.manager.TitleManager;
import com.example.chch2.baseapp.base.presenter.BasePresenter;
import com.example.chch2.baseapp.base.view.BaseActivity;

import butterknife.BindView;

public class DemoActivity extends BaseActivity {
    @BindView(R.id.im)
    ImageView mImageView;
    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        TitleManager.showDefaultTitle(this,"demo");
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //rotateOnYCoordinate();
                setAlipayDialog();
            }
        });

    }

    @Override
    public int getContentId() {
        return R.layout.activity_demo;
    }

    // 以X轴为轴心旋转
    private void rotateOnYCoordinate(ImageView imageView) {
        float centerX = imageView.getWidth() / 2.0f;
        float centerY = imageView.getHeight() / 2.0f;
        float centerZ = 0f;

        Rotate3dAnimation rotate3dAnimationX = new Rotate3dAnimation(0, 360, centerX, centerY, centerZ, Rotate3dAnimation.ROTATE_Y_AXIS, true);
        rotate3dAnimationX.setDuration(600);
        //以下两行代码是为了让动画重复播放
        rotate3dAnimationX.setRepeatMode(Animation.RESTART);
        rotate3dAnimationX.setRepeatCount(Animation.INFINITE);
        imageView.startAnimation(rotate3dAnimationX);
    }

    //2018.12.10添加---提示用户确认提现到支付宝提示框
    private void setAlipayDialog(){
        /*final LayoutInflater inflater = LayoutInflater.from(mContext);
        View layout = inflater.inflate(R.layout.lay_redpacket, null);

        //ll_redbg.setBackground(new BitmapDrawable(getImageToChange(((BitmapDrawable) getResources().getDrawable(R.mipmap.redpacketbg)).getBitmap())));
        final ImageView im_packet = layout.findViewById(R.id.im_packet);
        im_packet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotateOnYCoordinate(im_packet);
            }
        });

        final AlertDialog builder = new AlertDialog.Builder(mContext).create();
        builder.setView(layout);
        builder.getWindow().setDimAmount(0);//消除dialog外侧阴影
        builder.show();*/


        final Dialog mCameraDialog = new Dialog(mContext, R.style.BottomDialog);
        final View root =  FrameLayout.inflate(mContext,
                R.layout.lay_redpacket, null);
        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        //lp.width = (int)mContext.getResources().getDisplayMetrics().widthPixels; // 宽度
        lp.width = (int) ViewGroup.LayoutParams.WRAP_CONTENT; // 宽度
        root.measure(0, 0);
        //lp.height = root.getMeasuredHeight();
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);

        mCameraDialog.show();
    }


    //把白色转换成透明
    public static Bitmap getImageToChange(Bitmap mBitmap) {
        Bitmap createBitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        if (mBitmap != null) {
            int mWidth = mBitmap.getWidth();
            int mHeight = mBitmap.getHeight();
            for (int i = 0; i < mHeight; i++) {
                for (int j = 0; j < mWidth; j++) {
                    int color = mBitmap.getPixel(j, i);
                    int g = Color.green(color);
                    int r = Color.red(color);
                    int b = Color.blue(color);
                    int a = Color.alpha(color);
                    if(g>=250&&r>=250&&b>=250){
                        a = 0;
                    }
                    color = Color.argb(a, r, g, b);
                    createBitmap.setPixel(j, i, color);
                }
            }
        }
        return createBitmap;
    }
}
