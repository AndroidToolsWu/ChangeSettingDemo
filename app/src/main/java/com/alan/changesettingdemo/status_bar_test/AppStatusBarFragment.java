package com.alan.changesettingdemo.status_bar_test;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alan.changesettingdemo.R;
import com.alan.changesettingdemo.core.BaseFragment;
import com.alan.changesettingdemo.databinding.FragmentAppStatusBarBinding;

/**
 * Created by Alan
 * Date: 2020/3/9
 */
@SuppressWarnings("ConstantConditions")
public class AppStatusBarFragment extends Fragment {

    private View mView;
    private TextView mTvTime, mTvBattery;
    private ImageView mIvSignal;
    private BatteryView mBatteryView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_app_status_bar, container, true);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StatusBarManager.init(getContext());
        initView();
    }

    private void initView() {
        mTvTime = mView.findViewById(R.id.tv_time);
        mTvBattery = mView.findViewById(R.id.tv_battery);
        mIvSignal = mView.findViewById(R.id.iv_signal);
        mBatteryView = mView.findViewById(R.id.battery_view);

        mTvTime.setText(StatusBarManager.getInstance().getTimeFormat());
        mTvBattery.setText(StatusBarManager.getInstance().getBatteryInfo() + "%");
        mBatteryView.setBatteryPower(StatusBarManager.getInstance().getBatteryInfo());

        int imgId = getContext().getResources().getIdentifier("ic_status_signal" + StatusBarManager
                .getInstance().getSignalLevel(),"drawable", getContext().getPackageName());
        mIvSignal.setImageDrawable(getContext().getDrawable(imgId));

    }


}
