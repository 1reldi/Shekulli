package criminalintent.android.bignerdranch.com.criminalintent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import it.sephiroth.android.library.picasso.Picasso;

/*
 * Created by GERMAN on 01-Nov-16.
 */

public class VideoTFragment extends Fragment {
    private static final String ARG_POST_ID = "PostUrl";
    public static final String TAG = "Fragment Post Url: ";
    private String urlPost;
    private VideoView mWebVideo;
    private ProgressDialog pDialog;
    private int W = 0;
    private int H;
    private View vv;

    private RecyclerView mPostRecyclerView;
    private List<post_page> mItems = new ArrayList<>();

    private VideoEnabledWebView webView;
    private VideoEnabledWebChromeClient webChromeClient;

    public static VideoTFragment newInstance(String post_url) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_POST_ID, post_url);
        VideoTFragment fragment = new VideoTFragment();
        fragment.setArguments(args);
        return fragment;
    }


    //---------------------------------------------------------------------------------------------------------------

    @Override
    public void onCreate(Bundle savedInstanceState) { //Fragment.onCreate(Bundle) is a public method whereas Activity.onCreate(Bundle) is protected
        super.onCreate(savedInstanceState);  //Fragment.onCreate(…) and other Fragment lifecycle methods must be public because they will be called by whatever activity is hosting the fragment.
        String Post_Url= (String) getArguments().getSerializable(ARG_POST_ID);
        Log.i(TAG, "Received URL: " + Post_Url);
        urlPost = Post_Url;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) { //This method is where you inflate the layout for the fragment’s view and return the inflated View to the hosting activity.
        View v = inflater.inflate(R.layout.videoview_post, container, false);
        Bundle args = new Bundle();

       /* // Save the web view
        webView = (VideoEnabledWebView)v.findViewById(R.id.webView);

        // Initialize the VideoEnabledWebChromeClient and set event handlers
        View nonVideoLayout = v.findViewById(R.id.nonVideoLayout); // Your own view, read class comments
        ViewGroup videoLayout = (ViewGroup)v.findViewById(R.id.videoLayout); // Your own view, read class comments
        //noinspection all
        View loadingView = getLayoutInflater(args).inflate(R.layout.view_loading_video, null); // Your own view, read class comments
        webChromeClient = new VideoEnabledWebChromeClient(nonVideoLayout, videoLayout, loadingView, webView) // See all available constructors...
        {
            // Subscribe to standard events, such as onProgressChanged()...
            @Override
            public void onProgressChanged(WebView view, int progress)
            {
                // Your code...
            }
        };
        webChromeClient.setOnToggledFullscreen(new VideoEnabledWebChromeClient.ToggledFullscreenCallback()
        {
            @Override
            public void toggledFullscreen(boolean fullscreen)
            {
                // Your code to handle the full-screen change, for example showing and hiding the title bar. Example:
                if (fullscreen)
                {
                    WindowManager.LayoutParams attrs = getActivity().getWindow().getAttributes();
                    attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
                    attrs.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                    getActivity().getWindow().setAttributes(attrs);
                    if (android.os.Build.VERSION.SDK_INT >= 14)
                    {
                        //noinspection all
                        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
                    }
                }
                else
                {
                    WindowManager.LayoutParams attrs = getActivity().getWindow().getAttributes();
                    attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
                    attrs.flags &= ~WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                    getActivity().getWindow().setAttributes(attrs);
                    if (android.os.Build.VERSION.SDK_INT >= 14)
                    {
                        //noinspection all
                        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                    }
                }

            }
        });
        webView.setWebChromeClient(webChromeClient);
        // Call private class InsideWebViewClient
        webView.setWebViewClient(new InsideWebViewClient());

        // Navigate anywhere you want, but consider that this classes have only been tested on YouTube's mobile site
        webView.loadUrl(urlPost);








        /*mWebVideo = (VideoView)
                v.findViewById(R.id.webview);

        pDialog = new ProgressDialog(getActivity());
        // Set progressbar title
        pDialog.setTitle("");
        // Set progressbar message
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        // Show progressbar
        pDialog.show();

        try {
            // Start the MediaController
            MediaController mediacontroller = new MediaController(
                    getActivity());
            mediacontroller.setAnchorView(mWebVideo);
            // Get the URL from String VideoURL
            Uri video = Uri.parse(urlPost);
            mWebVideo.setMediaController(mediacontroller);
            mWebVideo.setVideoURI(video);
            mWebVideo.setZOrderOnTop(true);

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        mWebVideo.requestFocus();
        mWebVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                mWebVideo.start();
            }
        });*/

        return v;
    }

    private class InsideWebViewClient extends WebViewClient {
        @Override
        // Force links to be opened inside WebView and not in Default Browser
        // Thanks http://stackoverflow.com/a/33681975/1815624
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    // When you change direction of phone, this method will be called.
    // It store the state of video (Current position)
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // Store current position.
        //savedInstanceState.putInt("CurrentPosition", mWebVideo.getCurrentPosition());
        //mWebVideo.pause();
    }


}