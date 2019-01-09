package com.example.chch2.baseapp.base.manager;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chch2.baseapp.R;


/**
 * Created by gyg on 2016/8/29.
 */
public class TitleManager {
    /**
     *
     * @param from
     * @param titleText
     * @param leftText
     * @param leftImg
     * @param rightText
     * @param rightImg
     * @param bgColor
     * @param txtColor
     */
    public static void showTitle(final Activity from, Object titleText, Object leftText, int leftImg, Object rightText, int rightImg, int bgColor, int txtColor){
        RelativeLayout titleLayout= (RelativeLayout) from.findViewById(R.id.top);
        TextView tvLeft = (TextView) from.findViewById(R.id.txt_left);
        ImageView imgLeft= (ImageView) from.findViewById(R.id.img_left);
        TextView tvTitle = (TextView) from.findViewById(R.id.txt_title);
        TextView tvRight = (TextView) from.findViewById(R.id.txt_right);
        ImageView imgRight = (ImageView) from.findViewById(R.id.img_right);
        //设置标题栏可见
        titleLayout.setVisibility(View.VISIBLE);
        titleLayout.setBackgroundColor(bgColor);
        //开始都设置不可见
        tvLeft.setVisibility(View.GONE);
        imgLeft.setVisibility(View.GONE);
        tvTitle.setVisibility(View.GONE);
        tvRight.setVisibility(View.GONE);
        imgRight.setVisibility(View.GONE);
        tvLeft.setTextColor(txtColor);
        tvTitle.setTextColor(txtColor);
        tvRight.setTextColor(txtColor);
        //左侧文字显示
        if (leftText!=null){
            if (leftText instanceof Integer){
                int temp=(Integer)leftText;
                if (temp>0){
                    tvLeft.setText(temp);
                    tvLeft.setVisibility(View.VISIBLE);
                }
            }else if (leftText instanceof String){
                tvLeft.setText((String)leftText);
                tvLeft.setVisibility(View.VISIBLE);
            }
        }
        //中间文本显示
        if (titleText!=null){
            if (titleText instanceof Integer){
                int temp=(Integer)titleText;
                if (temp>0){
                    tvTitle.setText(temp);
                    tvTitle.setVisibility(View.VISIBLE);
                }
            }else if (titleText instanceof String){
                tvTitle.setText((String)titleText);
                tvTitle.setVisibility(View.VISIBLE);
            }
        }
        //右侧文本显示
        if (rightText!=null){
            if (rightText instanceof Integer){
                int temp=(Integer)rightText;
                if (temp>0){
                    tvRight.setText(temp);
                    tvRight.setVisibility(View.VISIBLE);
                }
            }else if (titleText instanceof String){
                tvRight.setText((String)rightText);
                tvRight.setVisibility(View.VISIBLE);
            }
        }

        //左侧图标显示
        if (leftImg==-1){//返回按钮
            imgLeft.setVisibility(View.VISIBLE);
            imgLeft.setImageResource(R.mipmap.ic_back);
            imgLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    from.finish();
                }
            });
        }else if (leftImg>0){
            imgLeft.setVisibility(View.VISIBLE);
            imgLeft.setImageResource(leftImg);
        }

        //右侧图标显示
        if (rightImg>0){
            imgRight.setVisibility(View.VISIBLE);
            imgRight.setImageResource(rightImg);
        }
    }

    /**
     *
     * @param from
     * @param titleText
     * @param leftText
     * @param leftImg
     * @param rightText
     * @param rightImg
     */
    //蓝色标题栏 字体白色
    public static void showBlueTitle(Activity from, Object titleText, Object leftText, int leftImg, Object rightText, int rightImg){

        //2018.9.18---修改界面 这里的蓝色是Toptitle
        showTitle(from,titleText,leftText,leftImg,rightText,rightImg,0xff498bf6,0xffffffff);
    }

    //红色标题栏 字体白色
    public static void showRedTitle(Activity from, Object titleText, Object leftText, int leftImg, Object rightText, int rightImg){
        showTitle(from,titleText,leftText,leftImg,rightText,rightImg,0xffF6594E,0xffffffff);
    }


    /**
     *
     * @param from
     * @param titleText
     */
    //返回标签 背景蓝色 字体白色
    public static void showDefaultTitle(Activity from, Object titleText){
        showBlueTitle(from,titleText,null,-1,null,0);
    }

    //可以修改右边的
    public static void showDefaultTitleRight(Activity from, Object titleText, Object rightText){
        showBlueTitle(from,titleText,null,-1,rightText,0);
    }
    //可以修改右边的图标
    public static void showDefaultTitleRightImg(Activity from, Object titleText, int rightText){
        showBlueTitle(from,titleText,null,-1,null,rightText);
    }
}
