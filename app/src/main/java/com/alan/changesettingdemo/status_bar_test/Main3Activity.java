package com.alan.changesettingdemo.status_bar_test;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.alan.changesettingdemo.R;

/**
 * Created by Alan
 * Date: 2020/3/6
 */
public class Main3Activity extends AppCompatActivity {

    private BatteryView mBatteryView;
    private SeekBar mSeekBar;
    private Fragment mFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
//        layoutParams.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().setAttributes(layoutParams);
//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


//        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
//        layoutParams.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
//        getWindow().setAttributes(layoutParams);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        setContentView(R.layout.activity3_main);
        initView();
    }


    private void initView() {
        mBatteryView = findViewById(R.id.battery);
        mSeekBar = findViewById(R.id.sb);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mBatteryView.setBatteryPower(progress);
                seekBar.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }


}
