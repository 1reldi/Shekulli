package criminalintent.android.bignerdranch.com.criminalintent;

/**
 * Created by GERMAN on 05-Nov-16.
 */

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;
import android.support.v4.app.Fragment;

public class VideoViewActivity1 extends  Fragment{
/*
    View view;
    // Declare variables
    ProgressDialog pDialog;
    VideoView videoview;

    // Insert your Video URL
    String VideoURL = "http://edge-europe.abingmedia.com/3/streaming/1108805.mp4";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.videoview_post, container,false);

        videoview = (VideoView)
                view.findViewById(R.id.VideoViewPost);
        // Execute StreamVideo AsyncTask

        /* Create a progressbar
        pDialog = new ProgressDialog(getActivity());
        // Set progressbar title
        pDialog.setTitle("Android Video Streaming Tutorial");
        // Set progressbar message
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        // Show progressbar
        pDialog.show();*/
/*
        try {
            // Start the MediaController
            MediaController mediacontroller = new MediaController(
                    getActivity());
            mediacontroller.setAnchorView(videoview);
            // Get the URL from String VideoURL
            Uri video = Uri.parse(VideoURL);
            videoview.setMediaController(mediacontroller);
            videoview.setVideoURI(video);

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        videoview.requestFocus();
        videoview.setOnPreparedListener(new OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                //pDialog.dismiss();
                videoview.pause();
            }
        });

        return view;

    }*/

}
