package com.alan.changesettingdemo.light_test;

import android.os.Bundle;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alan.changesettingdemo.R;

/**
 * Created by Alan
 * Date: 2020/3/25
 */
public class LightActivity extends AppCompatActivity {

    private SeekBar mSeekBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
        initView();
    }

    private void initView() {
        mSeekBar = findViewById(R.id.sb);
        mSeekBar.setMax(255);
        mSeekBar.setProgress(getSystemLight());
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setSystemLight(progress);
                int currentLight = getSystemLight();
                seekBar.setProgress(currentLight);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    /**
     * 系统亮度
     * @return 0~255
     */
    private int getSystemLight(){
        int systemLight = 0;
        try {
            systemLight = Settings.System.getInt(getContentResolver(), Settings
                    .System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return systemLight;
    }

    private void setSystemLight(int light){
        Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, light);

//        Window window = getWindow();
//        WindowManager.LayoutParams layoutParams = window.getAttributes();
//        float floatLight = light / 255f;
//        layoutParams.screenBrightness = floatLight;
//        window.setAttributes(layoutParams);
    }


}
