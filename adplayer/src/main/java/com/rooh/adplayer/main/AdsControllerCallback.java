package com.rooh.adplayer.main;

public interface AdsControllerCallback {

    void onAdsBuffering () ;

    void onAdsLoaded () ;

    void onAdsStarted () ;

    void onVideoContentPauseRequested () ;

    void onVideoContentResumeReqeusted () ;

    void onAdsPaused () ;

    void onAdsResumed () ;

    void onAdsCompleted () ;


}
