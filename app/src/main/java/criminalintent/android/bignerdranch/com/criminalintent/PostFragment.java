package criminalintent.android.bignerdranch.com.criminalintent;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import it.sephiroth.android.library.picasso.Picasso;

/*
 * Created by GERMAN on 01-Nov-16.
 */

public class PostFragment extends VisibleFragment {
    private static final String ARG_POST_ID = "PostUrl";
    private static final String ARG_CAT_ID = "CATUrl";
    private static final String ARG_CAT_NAME_ID = "CATName";
    public static final String TAG = "Fragment Post Url: ";


    private String urlPost;
    private String urlCategory;
    private String nameCategory;
    private String shareUrl;
    private int start_suggest;
    private ImageView mWebVideo = null;
    private boolean are_videos = true;
    private ProgressDialog dialog;
    String url_basic="";

    private RecyclerView mPostRecyclerView;
    private List<post_page> mItems = new ArrayList<>();
    private List<category_page> mSuggItems = new ArrayList<>();
    private List<category_page> mADSItems = new ArrayList<>();

    private static final String EXTRA_FG_ID1 =
            "com.bignerdranch.android.criminalintent.fg_id1";
    private static final String EXTRA_FG_ID2 =
            "com.bignerdranch.android.criminalintent.fg_id2";
    private static final String EXTRA_FG_ID3 =
            "com.bignerdranch.android.criminalintent.fg_id3";
    private static final String EXTRA_FG_ID4 =
            "com.bignerdranch.android.criminalintent.fg_id4";
    private static final String EXTRA_CATEGORY_URL_ID =
            "com.bignerdranch.android.criminalintent.category_url_id";
    private static final String EXTRA_CATEGORY_ID =
            "com.bignerdranch.android.criminalintent.category_id";
    private static final String EXTRA_SHARE_URL =
            "com.bignerdranch.android.criminalintent.share_url";


    private ArrayList mcategories = new ArrayList();
    private ArrayList murl_categories = new ArrayList();
    private ArrayList Data_Fragment1 = new ArrayList();
    private ArrayList Data_Fragment2 = new ArrayList();
    private ArrayList Data_Fragment3 = new ArrayList();
    private ArrayList Data_Fragment4 = new ArrayList();


    public static PostFragment newInstance(String post_url, String cat_url, String cat_name, String share_url, Bundle datas) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_POST_ID, post_url);
        args.putSerializable(ARG_CAT_ID, cat_url);
        args.putSerializable(ARG_CAT_NAME_ID, cat_name);
        args.putSerializable(EXTRA_SHARE_URL, share_url);
        args.putParcelableArrayList(EXTRA_FG_ID1, datas.getParcelableArrayList(EXTRA_FG_ID1));
        args.putParcelableArrayList(EXTRA_FG_ID2, datas.getParcelableArrayList(EXTRA_FG_ID2));
        args.putParcelableArrayList(EXTRA_FG_ID3, datas.getParcelableArrayList(EXTRA_FG_ID3));
        args.putParcelableArrayList(EXTRA_FG_ID4, datas.getParcelableArrayList(EXTRA_FG_ID4));
        args.putParcelableArrayList(EXTRA_CATEGORY_ID, datas.getParcelableArrayList(EXTRA_CATEGORY_ID));
        args.putParcelableArrayList(EXTRA_CATEGORY_URL_ID, datas.getParcelableArrayList(EXTRA_CATEGORY_URL_ID));

        PostFragment fragment = new PostFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    //---------------------------------------------------------------------------------------------------------------


    private class PostHolder extends RecyclerView.ViewHolder { //implements View.OnClickListener
        private TextView mTitlePostView;
        private TextView mDatePostView;
        private TextView mAuthorPostView;
        private TextView mContentPostView;
        private ImageView mPhotoMain;
        private ImageView mPhotoAdvertisment1;
        private ImageView mPhotoAdvertisment2;

        public PostHolder(View itemView) {
            super(itemView);

            mTitlePostView = (TextView)
                    itemView.findViewById(R.id.Post_View_Title);
            mDatePostView = (TextView)
                    itemView.findViewById(R.id.Post_Date);
            mContentPostView = (TextView)
                    itemView.findViewById(R.id.Post_Content);
            mPhotoMain = (ImageView)
                    itemView.findViewById(R.id.post_header_photo);
            mPhotoAdvertisment1 = (ImageView)
                    itemView.findViewById(R.id.post_advertisment1);
            mPhotoAdvertisment2 = (ImageView)
                    itemView.findViewById(R.id.post_advertisment2);

        }


        public void bindPostItem(post_page item) {

            mTitlePostView.setText(item.getTitle());
            mDatePostView.setText(item.getDate() + " nga " + item.getAuthor());
            mContentPostView.setText(item.getContent());
            //urlCategory = item.getCategory_Url();


            //RelativeLayout.LayoutParams params=itemView.getLayoutParams();

            Picasso.with(getActivity())
                    .load(item.getPhoto_Main_Url())
                    .fit()
                    .centerCrop()
                    .into(mPhotoMain);

            Picasso.with(getActivity())
                    .load(mADSItems.get(0).getMphoto_url_post())
                    .fit()
                    .into(mPhotoAdvertisment1);
            Picasso.with(getActivity())
                    .load(mADSItems.get(1).getMphoto_url_post())
                    .fit()
                    .into(mPhotoAdvertisment2);

            mPhotoAdvertisment1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mADSItems.get(0).getMurl_post()));
                    startActivity(browserIntent);
                    // Does nothing yet, but soon!
                }
            });

            mPhotoAdvertisment2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mADSItems.get(1).getMurl_post()));
                    startActivity(browserIntent);
                    // Does nothing yet, but soon!
                }
            });

        }

    }


    private class GalleryHolder extends RecyclerView.ViewHolder { //implements View.OnClickListener

        private ImageView mGalleryMain;
        private Button mPrevious;
        private Button mNext;
        private ArrayList mGalleryPhotos = new ArrayList();
        private TextView mStatusPhoto;
        private int mIndex = 0;


        public GalleryHolder(View itemView) {
            super(itemView);

            mGalleryMain = (ImageView)
                    itemView.findViewById(R.id.gallery_header_photo);
            mPrevious = (Button)
                    itemView.findViewById(R.id.previous_button);
            mNext = (Button)
                    itemView.findViewById(R.id.next_button);
            mStatusPhoto = (TextView)
                    itemView.findViewById(R.id.photo_status);

        }


        public void bindPostItem(post_page item) {

            //RelativeLayout.LayoutParams params=itemView.getLayoutParams();
            mGalleryPhotos = item.getPhotos();

            String status = "Foto " + String.valueOf(mIndex + 1) + " nga " + String.valueOf(mGalleryPhotos.size());
            mStatusPhoto.setText(status);

            Picasso.with(getActivity())
                    .load(String.valueOf(mGalleryPhotos.get(mIndex)))
                    .fit()
                    .into(mGalleryMain);


            mPrevious.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mIndex == 0) {
                        mIndex = mGalleryPhotos.size() - 1;
                    } else {
                        mIndex -= 1;
                    }

                    Picasso.with(getActivity())
                            .load(String.valueOf(mGalleryPhotos.get(mIndex)))
                            .fit()
                            .into(mGalleryMain);

                    String status = "Foto " + String.valueOf(mIndex + 1) + " nga " + String.valueOf(mGalleryPhotos.size());
                    mStatusPhoto.setText(status);

                    // Does nothing yet, but soon!
                }
            });

            mNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mIndex == mGalleryPhotos.size() - 1) {
                        mIndex = 0;
                    } else {
                        mIndex += 1;
                    }

                    Picasso.with(getActivity())
                            .load(String.valueOf(mGalleryPhotos.get(mIndex)))
                            .fit()
                            .into(mGalleryMain);

                    String status = "Foto " + String.valueOf(mIndex + 1) + " nga " + String.valueOf(mGalleryPhotos.size());
                    mStatusPhoto.setText(status);

                }
            });

        }

    }


    private class VideoHolder extends RecyclerView.ViewHolder { //implements View.OnClickListener

        //private VideoView mvideoview;
        private String mVideoUrl;

        private WebSettings webSettings;

        //WebView= (webview) find view byid(r.id...);
        //Webview.getsettings().setjavascriptallowed(true);
        //Webview.loadurl(url);


        public VideoHolder(View itemView) {
            super(itemView);

            mWebVideo = (ImageView)
                    itemView.findViewById(R.id.post_video);

            /*mvideoview = (VideoView)
                    itemView.findViewById(R.id.VideoViewPost);*/

        }


        public void bindPostItem(post_page item) {

            mVideoUrl = item.getVideo();
            Log.d(TAG, "mVideoUrl: " + mVideoUrl);
            Log.d(TAG, "mVideoUrl: " + mVideoUrl.indexOf("youtube"));
            Log.d(TAG, "mVideoUrl: " + mVideoUrl.indexOf("reldi"));
            Log.d(TAG, "mVideoUrl: " + mVideoUrl.contains("youtube"));
            Log.d(TAG, "mVideoUrl: " + mVideoUrl.contains("reldi"));

            mWebVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // launching facebook comments activity
                    Intent intent = VideoTActivity.newIntent(getActivity(), mVideoUrl);
                    startActivity(intent);
//                    if (mVideoUrl.contains("youtube")) {
//                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mVideoUrl));
//                        startActivity(intent);
//                    } else {
//                        Intent intent = VideoTActivity.newIntent(getActivity(), mVideoUrl);
//                        startActivity(intent);
//                    }
                }
            });

        }
    }


    private class Suggest extends RecyclerView.ViewHolder {

        TextView display_text;
        Button commentsButton;



        public Suggest(View itemView) {
            super(itemView);


            display_text = (TextView)
                    itemView.findViewById(R.id.post_suggestion_name);
            commentsButton = (Button)
                    itemView.findViewById(R.id.buttonComments);


        }


        public void bindPostItem(post_page item) {

            display_text.setText(R.string.suggestion);

            commentsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(TAG, "Received URL: " + urlPost);
                    // launching facebook comments activity
                    Intent intent = new Intent(getActivity(), FbCommentsActivity.class);

                    // passing the article url
//                    intent.putExtra("url", shareUrl);//http://shekulli.com.al/arrestohet-25-vjecari-nga-elbasani-u-kap-60-doza-kanabis/
                    intent.putExtra("url", shareUrl);//
                    startActivity(intent);
                }
            });


        }

    }

    private class Empty extends RecyclerView.ViewHolder {

        public Empty(View itemView) {
            super(itemView);
        }


        public void bindPostItem() {

        }

    }

    private class News extends RecyclerView.ViewHolder implements View.OnClickListener {
        private String url_page;
        TextView display_text;
        ImageView mainImage;


        public News(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //mTitleTextView = (TextView) itemView;


            mainImage = (ImageView)
                    itemView.findViewById(R.id.home_header_photo);

            display_text = (TextView)
                    itemView.findViewById(R.id.home_header_textView_Title);


        }

        public void bindPostItem(category_page item) {

            url_page = item.getMurl_post();

            //Log.i("ggg", "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy: " + index);

            Picasso.with(getActivity())
                    .load(item.getMphoto_url_post())
                    .fit()
                    .centerCrop()
                    .into(mainImage);
            display_text.setText(item.getMtitle_post());


        }

        @Override
        public void onClick(View v) {
            // url_page not defined
            if (isNetworkAvailable()) {
                url_basic=url_page;
                new FetchPostBasic().execute();
            }
            else {
                //disconnectedLayout.setVisibility(View.VISIBLE);
                //connectedLayout.setVisibility(View.GONE);
                Intent i = new Intent(getActivity(), No_Net.class);
                startActivity(i);

            }
        }
    }


    private class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<post_page> mPost_pages;

        private final int ARTICLE = 0;
        private final int GALLERY = 1;
        private final int VIDEO = 2;
        private final int SUGG = 3;
        private final int NEWS = 4;
        private final int EMPTY = 5;

        private boolean are_photos = true;

        private boolean are_news = true;

        public PostAdapter(List<post_page> galleryItems) {
            mPost_pages = galleryItems;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            //TextView textView = new TextView(getActivity());
            //return new PhotoHolder(textView);
            RecyclerView.ViewHolder holder = null;
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            switch (viewType) {
                case ARTICLE:
                    View view1 = layoutInflater
                            .inflate(R.layout.post_component, viewGroup, false);
                    holder = new PostHolder(view1);
                    break;


                case GALLERY:
                    View view2 = layoutInflater
                            .inflate(R.layout.post_gallery, viewGroup, false);
                    holder = new GalleryHolder(view2);
                    break;

                case VIDEO:
                    View view3 = layoutInflater
                            .inflate(R.layout.post_video, viewGroup, false);
                    holder = new VideoHolder(view3);
                    break;

                case SUGG:
                    View view4 = layoutInflater
                            .inflate(R.layout.post_suggestion, viewGroup, false);
                    holder = new Suggest(view4);
                    break;

                case NEWS:
                    View view5 = layoutInflater
                            .inflate(R.layout.home_header, viewGroup, false);
                    holder = new News(view5);
                    break;
                case EMPTY:
                    View view7 = layoutInflater
                            .inflate(R.layout.empty_view, viewGroup, false);
                    holder = new Empty(view7);
                    break;


                default:
                    View view6 = layoutInflater
                            .inflate(R.layout.videoview_post, viewGroup, false);
                    holder = new VideoHolder(view6);
                    break;

            }


            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder postholder, int position) {



            // max 30 lajme
            post_page galleryItem = mPost_pages.get(position);

            switch (postholder.getItemViewType()) {
                case ARTICLE:
                    PostHolder holder1 = (PostHolder) postholder;
                    holder1.bindPostItem(galleryItem);
                    break;
                case GALLERY:
                    GalleryHolder holder2 = (GalleryHolder) postholder;
                    holder2.bindPostItem(galleryItem);
                    break;
                case VIDEO:
                    VideoHolder holder3 = (VideoHolder) postholder;
                    holder3.bindPostItem(galleryItem);
                    break;
                case SUGG:
                    Suggest holder4 = (Suggest) postholder;
                    holder4.bindPostItem(galleryItem);
                    start_suggest = position+1;
                    break;
                case NEWS:
                    News holder5 = (News) postholder;
                    holder5.bindPostItem(mSuggItems.get(position-start_suggest));
                    break;
                case EMPTY:
                    Empty holder6 = (Empty) postholder;
                    holder6.bindPostItem();
                    break;
                default:
                    VideoHolder holder7 = (VideoHolder) postholder;
                    holder7.bindPostItem(galleryItem);
            }



            //post_page galleryItem = mPost_pages.get(position);
            //postholder.bindPostItem(galleryItem);
        }

        @Override
        public int getItemCount() {
            return mPost_pages.size();
        }

        @Override
        public int getItemViewType(int position) {

            post_page galleryItem = mPost_pages.get(position);

            if (galleryItem.getPhotos().size() == 0) {
                are_photos = false;
            } else {
                are_photos = true;
            }

            if (galleryItem.getVideo() == null) {
                are_videos = false;
            } else {
                are_videos = true;
            }


            if (galleryItem.getSuug() == 0) {
                are_news = false;
            } else {
                are_news = true;
            }

            if (position == 0) {
                return ARTICLE;
            }
            else if (position == 1 && are_photos) {
                return GALLERY;
            }
            else if (are_videos) {
                return VIDEO;
            }
            else if (are_news) {
                if (mSuggItems.size() == 0) {
                    return EMPTY;
                }
                return NEWS;
            }
            else{
                return SUGG;
            }
        }
    }


    //---------------------------------------------------------------------------------------------------------------

    //AsyncTask creates a background thread for you and runs the code in the doInBackground(…) method on that thread.
    private class FetchItemsTask extends AsyncTask<Void, Void, List<post_page>> {

//        /** progress dialog to show user that the backup is processing. */
//        /** application context. */
//        @Override
//        protected void onPreExecute() {
//
//        }

        @Override
        //It sets the type of value returned by doInBackground(…) as well as the type of onPostExecute(…)’s input parameter.
        protected List<post_page> doInBackground(Void... params) { //get data from a website and log it.
            return new criminalintent.android.bignerdranch.com.criminalintent.PostFetchr().fetchItems(urlPost);

        }


        @Override
        protected void onPostExecute(List<post_page> items) {

            mItems = items;
            setupAdapter();

        }
    }


    //AsyncTask creates a background thread for you and runs the code in the doInBackground(…) method on that thread.
    private class FetchSuggestionTask extends AsyncTask<Void,Void,List<category_page>> {

        @Override
        //It sets the type of value returned by doInBackground(…) as well as the type of onPostExecute(…)’s input parameter.
        protected List<category_page> doInBackground(Void... params) { //get data from a website and log it.
            return new criminalintent.android.bignerdranch.com.criminalintent.CategoryFetchr().fetchItems(urlCategory);

        }

        @Override
        protected void onPostExecute(List<category_page> items) {

            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            mSuggItems = items;


        }
    }

    //AsyncTask creates a background thread for you and runs the code in the doInBackground(…) method on that thread.
    private class FetchADSTask extends AsyncTask<Void,Void,List<category_page>> {

        @Override
        //It sets the type of value returned by doInBackground(…) as well as the type of onPostExecute(…)’s input parameter.
        protected List<category_page> doInBackground(Void... params) { //get data from a website and log it.
            return new criminalintent.android.bignerdranch.com.criminalintent.PostFetchr().fetchADS("http://www.shekulli.com.al/api/ads.php");

        }

        @Override
        protected void onPostExecute(List<category_page> items) {

            mADSItems = items;
            Log.i(TAG, "Received ADs Url: " + mADSItems.get(0).getMurl_post());
            Log.i(TAG, "Received Ads photo: " + mADSItems.get(0).getMphoto_url_post());


        }
    }

    private class FetchPostBasic extends AsyncTask<Void,Void,List<String>> {
        @Override
        //It sets the type of value returned by doInBackground(…) as well as the type of onPostExecute(…)’s input parameter.
        protected List<String> doInBackground(Void... params) { //get data from a website and log it.
            return new criminalintent.android.bignerdranch.com.criminalintent.PostFetchr().fetchBasics(url_basic);

        }

        @Override
        protected void onPostExecute(List<String> items) {
            Intent intent = PostActivity.newIntent(getActivity(), url_basic ,items.get(0),items.get(1), items.get(2), Data_Fragment1, Data_Fragment2, Data_Fragment3, Data_Fragment4, mcategories, murl_categories);
            startActivity(intent);
        }
    }


    //---------------------------------------------------------------------------------------------------------------

    @Override
    public void onCreate(Bundle savedInstanceState) { //Fragment.onCreate(Bundle) is a public method whereas Activity.onCreate(Bundle) is protected
        super.onCreate(savedInstanceState);  //Fragment.onCreate(…) and other Fragment lifecycle methods must be public because they will be called by whatever activity is hosting the fragment.

        setRetainInstance(true);

        Bundle bundle = this.getArguments();
        Data_Fragment1 = bundle.getParcelableArrayList(EXTRA_FG_ID1);
        Data_Fragment2 = bundle.getParcelableArrayList(EXTRA_FG_ID2);
        Data_Fragment3 = bundle.getParcelableArrayList(EXTRA_FG_ID3);
        Data_Fragment4 = bundle.getParcelableArrayList(EXTRA_FG_ID4);
        mcategories = bundle.getParcelableArrayList(EXTRA_CATEGORY_ID);
        murl_categories = bundle.getParcelableArrayList(EXTRA_CATEGORY_URL_ID);

        Log.i("888", "][][][][][][][][][][][][][][][][][][][][][][][][][][][: " + String.valueOf(mcategories.size()));
        Log.i("888", "][][][][][][][][][][][][][][][][][][][][][][][][][][][: " + String.valueOf(murl_categories.size()));

        String Post_Url = (String) getArguments().getSerializable(ARG_POST_ID);
        String Cat_URL = (String) getArguments().getSerializable(ARG_CAT_ID);
        String Cat_NAME = (String) getArguments().getSerializable(ARG_CAT_NAME_ID);
        String Share_Url = (String) getArguments().getSerializable(EXTRA_SHARE_URL);
        Log.i(TAG, "Received URL: " + Post_Url);
        Log.i(TAG, "Received CATURL: " + Cat_URL);
        Log.i(TAG, "Received SHAREURL: " + Share_Url);
//        try {
//            HttpURLConnection con = (HttpURLConnection) new URL(Share_Url).openConnection();
//            con.setInstanceFollowRedirects(false);
//            con.connect();
//            String realURL = con.getHeaderField().toString();
//            Log.i(TAG, "Received SHAREURL: " + realURL);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        urlPost = Post_Url;
        urlCategory = Cat_URL;
        nameCategory = Cat_NAME;
        shareUrl  = Share_Url;

        dialog = new ProgressDialog(getActivity());
        this.dialog.setMessage("Ju lutem prisni!");
        this.dialog.show();

        new FetchADSTask().execute();
        new FetchItemsTask().execute();


        Log.i(TAG, "alalalalalalalallalalalalalalalaallalalalalalalalla");
        new FetchSuggestionTask().execute();




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) { //This method is where you inflate the layout for the fragment’s view and return the inflated View to the hosting activity.
        View v = inflater.inflate(R.layout.fragment_view, container, false);
        mPostRecyclerView = (RecyclerView) v
                .findViewById(R.id.fragment_post_gallery_recycler_view);
        mPostRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        setupAdapter(); //You callsetupAdapter() in onCreateView(…) so that every time a new RecyclerView is created, it is reconfigured with an appropriate adapter.
        //ou also want to call it every time your set of model objects changes.

        return v;
    }

//    @Override
//    public void onPause() {
//        super.onPause();
//
//        try {
//            if (mWebVideo != null) {
//                Class.forName("android.webkit.WebView")
//                        .getMethod("onPause", (Class[]) null)
//                        .invoke(mWebVideo, (Object[]) null);
//            }
//
//        } catch(ClassNotFoundException cnfe) {
//            //
//        } catch(NoSuchMethodException nsme) {
//            //
//        } catch(InvocationTargetException ite) {
//            //
//        } catch (IllegalAccessException iae) {
//            //
//        }
//    }


    private void setupAdapter() {
        if (isAdded()) { //This confirms that the fragment has been attached to an activity, and in turn that getActivity() will not be null
            mPostRecyclerView.setAdapter(new PostAdapter(mItems));
        }
    }
}