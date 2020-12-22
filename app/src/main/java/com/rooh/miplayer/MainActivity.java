package com.rooh.miplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import com.rooh.adplayer.main.AdsControllerCallback;
import com.rooh.adplayer.main.VideoFragment;

public class MainActivity extends AppCompatActivity {

    VideoFragment videoFragment ;
    ViewGroup container ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.video_example_container) ;

        videoFragment = new VideoFragment();
        videoFragment.loopAd = false ;
        videoFragment.isVideoVolumeControlEnabled = false ;
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

            }
        };
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
                container.setVisibility(View.GONE);
            }
        };

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                videoFragment.initiateAd("https://pubads.g.doubleclick.net/gampad/ads?iu=/21792359936/Bestsongs.pk&description_url=[placeholder]&tfcd=0&npa=0&sz=640x480&gdfp_req=1&output=vast&unviewed_position_start=1&env=vp&impl=s&correlator=");
            }
        }, 500);

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
//        container.setVisibility(View.VISIBLE);

    }
}