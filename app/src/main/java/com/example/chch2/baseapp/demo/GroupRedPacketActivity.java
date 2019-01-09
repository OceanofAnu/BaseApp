package com.example.chch2.baseapp.demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.chch2.baseapp.R;
import com.example.chch2.baseapp.base.manager.TitleManager;
import com.example.chch2.baseapp.base.presenter.BasePresenter;
import com.example.chch2.baseapp.base.view.BaseActivity;
import com.example.chch2.baseapp.demo.adapter.RedPacketUserAdapter;
import com.example.chch2.baseapp.demo.bean.RedPacketUser;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GroupRedPacketActivity extends BaseActivity {
    @BindView(R.id.rl_redpacket)
    ListView rl_redpacket;

    RedPacketUserAdapter redPacketUserAdapter;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        TitleManager.showRedTitle(this, "99路红包", null, -1, null, 0);
        List<RedPacketUser> list = new ArrayList<>();
        list.add(new RedPacketUser("1","周磊","520"));
        list.add(new RedPacketUser("2","小孙","1314"));
        list.add(new RedPacketUser("3","小乐","250"));

        list.add(new RedPacketUser("1","周磊","520"));
        list.add(new RedPacketUser("2","小孙","1314"));
        list.add(new RedPacketUser("3","小乐","250"));

        list.add(new RedPacketUser("1","周磊","520"));
        list.add(new RedPacketUser("2","小孙","1314"));
        list.add(new RedPacketUser("3","小乐","250"));

        list.add(new RedPacketUser("1","周磊","520"));
        list.add(new RedPacketUser("2","小孙","1314"));
        list.add(new RedPacketUser("3","小乐","250"));
        redPacketUserAdapter = new RedPacketUserAdapter(list ,mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.lay_header,null,true);
        rl_redpacket.addHeaderView(view);
        rl_redpacket.setAdapter(redPacketUserAdapter);
    }

    @Override
    public int getContentId() {
        return R.layout.activity_group_redpacket;
    }
}
