package com.alan.changesettingdemo.switch_btn;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.alan.changesettingdemo.R;

/**
 * Created by Alan
 * Date: 2020/3/24
 */
public class SwitchActivity extends AppCompatActivity {

    private ImageView mIvSwitch;
    private boolean isCheck = false;
    private SwitchCompat mSwitch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);

//        mIvSwitch = findViewById(R.id.iv_switch);
//        mIvSwitch.setOnClickListener( v->{
//            mIvSwitch.setSelected(isCheck = !isCheck);
//        });

        mSwitch = findViewById(R.id.switch_setting);


    }
}
