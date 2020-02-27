package com.alan.changesettingdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private Button mSystemButton,mAlarmButton,mMusicButton,mRingButton;
    private SeekBar mSystemSeekBar,mAlarmSeekBar,mMusicSeekBar,mRingSeekBar;
    private Ringtone mSystemRingtone,mAlarmRingtone,mRingtone;
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    private int mSystemCurrentVolume,mAlarmCurrentVolume,mMusicCurrentVolume,mRingCurrentVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initSeekBar();
        playSystem();
        playAlarm();
        playRing();
        playMusic();
        initOperation();
    }

    private void initView() {
        mSystemButton = findViewById(R.id.bt_play_system);
        mAlarmButton = findViewById(R.id.bt_play_alarm);
        mMusicButton = findViewById(R.id.bt_play_music);
        mRingButton = findViewById(R.id.bt_play_ring);
        mSystemSeekBar = findViewById(R.id.skb_system);
        mAlarmSeekBar = findViewById(R.id.skb_alarm);
        mMusicSeekBar = findViewById(R.id.skb_music);
        mRingSeekBar = findViewById(R.id.skb_ring);

    }

    private void initSeekBar() {
        mAudioManager = (AudioManager) getSystemService(Service.AUDIO_SERVICE);

        mSystemCurrentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
        mSystemSeekBar.setMax(mAudioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM));
        mSystemSeekBar.setProgress(mSystemCurrentVolume);

        mAlarmCurrentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_ALARM);
        mAlarmSeekBar.setMax(mAudioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM));
        mAlarmSeekBar.setProgress(mAlarmCurrentVolume);

        mMusicCurrentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        mMusicSeekBar.setMax(mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        mMusicSeekBar.setProgress(mMusicCurrentVolume);

        mRingCurrentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_RING);
        mRingSeekBar.setMax(mAudioManager.getStreamMaxVolume(AudioManager.STREAM_RING));
        mRingSeekBar.setProgress(mRingCurrentVolume);
    }


    private void initOperation() {
        mSystemButton.setOnClickListener(v ->{
            if (mSystemRingtone.isPlaying()){
                mSystemRingtone.stop();
            }else {
                mSystemRingtone.play();
            }
        });
        mAlarmButton.setOnClickListener(v ->{
            if (mAlarmRingtone.isPlaying()){
                mAlarmRingtone.stop();
            }else {
                mAlarmRingtone.play();
            }
        });
        mMusicButton.setOnClickListener(v ->{
            if (mMediaPlayer.isPlaying()){
                mMediaPlayer.stop();
            }else {
                mMediaPlayer.start();
            }
        });
        mRingButton.setOnClickListener(v ->{
            if (mRingtone.isPlaying()){
                mRingtone.stop();
            }else {
                mRingtone.play();
            }
        });

        mSystemSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAudioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, progress, 0);
                mSystemCurrentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
                seekBar.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mAlarmSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAudioManager.setStreamVolume(AudioManager.STREAM_ALARM, progress, 0);
                mAlarmCurrentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_ALARM);
                seekBar.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mMusicSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                mMusicCurrentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                seekBar.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mRingSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAudioManager.setStreamVolume(AudioManager.STREAM_RING, progress, 0);
                mRingCurrentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_RING);
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

    private void playRing(){
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        mRingtone = RingtoneManager.getRingtone(getApplicationContext(), uri);
    }

    private void playSystem(){
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mSystemRingtone = RingtoneManager.getRingtone(getApplicationContext(), uri);
    }

    private void playMusic(){
        mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);
    }

    private void playAlarm(){
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        mAlarmRingtone = RingtoneManager.getRingtone(getApplicationContext(), uri);
    }


    /**
     * 通过按键调整音量大小
     */
    private void adjustSystemVolume(){
        AudioManager audioManager = (AudioManager) getSystemService(Service.AUDIO_SERVICE);
        //参数三AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI表示在调整媒体音量的时候会发出声音，并且弹出音量调整对话框
        audioManager.adjustStreamVolume(AudioManager.STREAM_SYSTEM,
                AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI);


    }

    /**
     * 通过进度设置音量大小
     */
    private void setStreamVolume(int progress){
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, progress, 0);
    }


}
