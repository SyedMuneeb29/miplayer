package com.rooh.adplayer.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/** The main fragment for displaying video content. */
public class VideoFragment extends Fragment {

    private VideoPlayerController videoPlayerController;
    public Boolean isVideoVolumeControlEnabled = false ;
    private VideoItem videoItem;
    private TextView videoTitle;
    private ScrollView videoExampleLayout;
    private OnVideoFragmentViewCreatedListener viewCreatedCallback;

    public AdsControllerCallback adsControllerCallback ;


    public VideoPlayerWithAdPlayback videoPlayerWithAdPlayback ;
    public ViewGroup companionAdSlot ;
    RelativeLayout rootView ;

    public boolean loopAd = true ;

    /** Listener called when the fragment's onCreateView is fired. */
    public interface OnVideoFragmentViewCreatedListener {
        public void onVideoFragmentViewCreated();
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {

        rootView = new RelativeLayout(getContext()) ;

        RelativeLayout.LayoutParams rootLayoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT ,
                RelativeLayout.LayoutParams.MATCH_PARENT
        ) ;

        rootView.setLayoutParams(rootLayoutParams);

        videoPlayerWithAdPlayback = new VideoPlayerWithAdPlayback(getContext()) ;

        RelativeLayout.LayoutParams videoPlayerLayoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT ,
                RelativeLayout.LayoutParams.MATCH_PARENT
        ) ;

        videoPlayerWithAdPlayback.setLayoutParams(videoPlayerLayoutParams);

        rootView.addView(videoPlayerWithAdPlayback);

//
//    View rootView = inflater.inflate(R.layout.fragment_video, container, false);
//
//      if (viewCreatedCallback != null) {
//        viewCreatedCallback.onVideoFragmentViewCreated();
//      }


        return rootView;
    }

    public void initiateAd (String withAdTag ) {
        String adTag = withAdTag ;
        setupVideoScreenView(rootView , adTag);
    }

    private void setupVideoScreenView(View rootView , String adTag) {
//    VideoPlayerWithAdPlayback videoPlayerWithAdPlayback =
//            rootView.findViewById(R.id.videoPlayerWithAdPlayback);

//    videoPlayerWithAdPlayback.adUiContainer = rootView.findViewById(R.id.adUiContainer) ;

//    View playButton = rootView.findViewById(R.id.playButton);
        View playPauseToggle = rootView ; //rootView.findViewById(R.id.videoContainer);
        ViewGroup companionAdSlot = null ; //rootView.findViewById(R.id.companionAdSlot);
//    videoTitle = rootView.findViewById(R.id.video_title);
//    videoExampleLayout = rootView.findViewById(R.id.videoExampleLayout);
//    videoExampleLayout.setOverScrollMode(View.OVER_SCROLL_ALWAYS);
//    videoExampleLayout.setSmoothScrollingEnabled(true);

        // Make the dummyScrollContent height the size of the screen height.
//    DisplayMetrics displayMetrics = new DisplayMetrics();
//    getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//    ConstraintLayout constraintLayout = rootView.findViewById(R.id.constraintLayout);
//    ConstraintSet forceHeight = new ConstraintSet();
//    forceHeight.clone(constraintLayout);
//    forceHeight.constrainHeight(R.id.dummyScrollContent, displayMetrics.heightPixels);
//    forceHeight.applyTo(constraintLayout);

        final TextView logText = new TextView(getContext()) ; //rootView.findViewById(R.id.logText);


        // Provide an implementation of a logger so we can output SDK events to the UI.
        VideoPlayerController.Logger logger =
                new VideoPlayerController.Logger() {
                    @Override
                    public void log(String message) {
                        Log.i("ImaExample", message);
                        if (logText != null) {
                            logText.append(message);
                        }
                    }
                };

        videoPlayerController =
                new VideoPlayerController(
                        this.getActivity(),
                        videoPlayerWithAdPlayback,
                        playPauseToggle,
                        "en",
                        companionAdSlot,
                        logger ,
                        loopAd ,
                        isVideoVolumeControlEnabled
                );

        setupVideoControllerWith(adTag);

//     If we've already selected a video, load it now.
        loadAd() ;


    }

    public void setupVideoControllerWith(String adTag) {

        this.videoItem = new VideoItem(
                "https://storage.googleapis.com/gvabox/media/samples/stock.html" ,
                "",
                 adTag ,
                0 ,
                false

        );
        if (videoPlayerController == null) {
            return;
        }

        if ( adsControllerCallback != null ) {
            videoPlayerController.adsControllerCallback = adsControllerCallback ;
        }

        videoPlayerController.setContentVideo(videoItem.getVideoUrl());
        videoPlayerController.setAdTagUrl(videoItem.getAdTagUrl());
//    videoTitle.setText(videoItem.getTitle());



    }

    public void loadAd() {
        videoPlayerController.requestAndPlayAds(-1);
    }


    /** Shows or hides all non-video UI elements to make the video as large as possible. */
    public void makeFullscreen(boolean isFullscreen) {
        for (int i = 0; i < videoExampleLayout.getChildCount(); i++) {
            View view = videoExampleLayout.getChildAt(i);
            // If it's not the video element, hide or show it, depending on fullscreen status.
//      if (view.getId() != R.id.videoContainer) {
//        if (isFullscreen) {
//          view.setVisibility(View.GONE);
//        } else {
//          view.setVisibility(View.VISIBLE);
//        }
//      }
        }
    }

    public VideoPlayerController getVideoPlayerController() {
        return videoPlayerController;
    }
    public boolean isVmap() {
        return videoItem.getIsVmap();
    }



    // LIFE CYCLE :

    @Override
    public void onPause() {
        if (videoPlayerController != null) {
            videoPlayerController.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        if (videoPlayerController != null) {
            videoPlayerController.resume();
        }
        super.onResume();
    }

    @Override
    public void onDestroy() {
        if (videoPlayerController != null) {
            videoPlayerController.destroy();
        }
        super.onDestroy();
    }

}
