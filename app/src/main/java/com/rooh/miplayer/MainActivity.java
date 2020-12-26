package com.rooh.miplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.rooh.adplayer.main.AdsControllerCallback;
import com.rooh.adplayer.main.VideoFragment;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    VideoFragment videoFragment ;
    boolean completed = false ;
    ViewGroup container ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.video_example_container) ;
        freshSetupAd();

    }

    void freshSetupAd () {
        container.setVisibility(View.VISIBLE);
        videoFragment = new VideoFragment();
        videoFragment.freshAd = true ;
        videoFragment.adTag = "https://pubads.g.doubleclick.net/gampad/ads?iu=/21792359936/11223344&description_url=[placeholder]&tfcd=0&npa=0&sz=640x480&gdfp_req=1&output=vast&unviewed_position_start=1&env=vp&impl=s&correlator=" ;
        videoFragment.loopAd = false ;
        videoFragment.isVideoVolumeControlEnabled = true ;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.video_example_container, videoFragment)
                .addToBackStack(null)
                .commit();
        videoFragment.adsControllerCallback = new AdsControllerCallback() {
            @Override
            public void onAdsBuffering() {

            }

            @Override
            public void onAdsLoaded() {

            }

            @Override
            public void onAdsStarted() {

            }

            @Override
            public void onVideoContentPauseRequested() {

            }

            @Override
            public void onVideoContentResumeReqeusted() {

            }

            @Override
            public void onAdsPaused() {

            }

            @Override
            public void onAdsResumed() {

            }

            @Override
            public void onAdsCompleted() {
                try {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .remove( videoFragment )
                            .commit();
                }catch (Exception e) {
                    Log.d("", e.getMessage()) ;
                }

                container.setVisibility(View.GONE);
                completed = true ;

            }
        };
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (completed) {
            freshSetupAd();
            completed = false ;
        } else {
            videoFragment.initiateAd("https://pubads.g.doubleclick.net/gampad/ads?iu=/21792359936/11223344&description_url=[placeholder]&tfcd=0&npa=0&sz=640x480&gdfp_req=1&output=vast&unviewed_position_start=1&env=vp&impl=s&correlator="); // https://pubads.g.doubleclick.net/gampad/ads?iu=/21792359936/11223344&description_url=[placeholder]&tfcd=0&npa=0&sz=640x480&gdfp_req=1&output=vast&unviewed_position_start=1&env=vp&impl=s&correlator=
        }

    }



}