package criminalintent.android.bignerdranch.com.criminalintent;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by GERMAN on 12-Oct-16.
 */

public class FragmentActivity3 extends VisibleFragment {
    private List<category_page> mItems = new ArrayList<>();
    private ArrayList mItemsT = new ArrayList<>();

    private RecyclerView mPhotoRecyclerView;
    private int index = 0;
    String title;
    private SwipeRefreshLayout mSwipeRefresh;
    private LinearLayout connectedLayout;
    private RelativeLayout disconnectedLayout;
    private Button mReloadButton;
    String url_basic="";



    private ArrayList mItemsT1 = new ArrayList<>();
    private ArrayList mItemsT2 = new ArrayList<>();
    private ArrayList mItemsT3 = new ArrayList<>();
    private ArrayList mItemsT4 = new ArrayList<>();
    private ArrayList mcategories = new ArrayList();
    private ArrayList murl_categories = new ArrayList();

    private static final String EXTRA_CATEGORY_URL_ID =
            "com.bignerdranch.android.criminalintent.category_url_id";
    private static final String EXTRA_CATEGORY_ID =
            "com.bignerdranch.android.criminalintent.category_id";
    private static final String EXTRA_FG_ID1 =
            "com.bignerdranch.android.criminalintent.fg_id1";
    private static final String EXTRA_FG_ID2 =
            "com.bignerdranch.android.criminalintent.fg_id2";
    private static final String EXTRA_FG_ID3 =
            "com.bignerdranch.android.criminalintent.fg_id3";
    private static final String EXTRA_FG_ID4 =
            "com.bignerdranch.android.criminalintent.fg_id4";


    private class PhotoHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private TextView mCommentTextView;
        private ImageView mImage;
        private ImageView mHeadImage;
        private String url_page;
        private String url_photo;
        TextView display_text;
        ImageView mainImage;


        public PhotoHolder3(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //mTitleTextView = (TextView) itemView;


            mainImage = (ImageView)
                    itemView.findViewById(R.id.video_header_photo);

            display_text = (TextView)
                    itemView.findViewById(R.id.video_header_textView_Title);


        }


        public void bindGalleryItem(category_page item) {

            //Context context = context.getApplicationContext();


            Log.i("ggg", "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy: " + index);
            url_page = item.getMurl_post();

            Picasso.with(getActivity())
                    .load(item.getMphoto_url_post())
                    .fit()
                    .centerCrop()
                    .into(mainImage);
            display_text.setText(item.getMtitle_post());

            //mImage.setImageDrawable(R.id.imageView);
            //mTitleTextView.setText(item.getNo_comments());
        }

        @Override
        public void onClick(View v) {
            // url_page not defined
            if (isNetworkAvailable()) {
                url_basic=url_page;
                new FetchPostBasic().execute();
                //Intent intent = VideoTActivity.newIntent(getActivity(), url_page);
                //startActivity(intent);
            }
            else {
                //disconnectedLayout.setVisibility(View.VISIBLE);
                //connectedLayout.setVisibility(View.GONE);
                Intent i = new Intent(getActivity(), No_Net.class);
                startActivity(i);

            }
        }
    }


    private class PhotoAdapter extends RecyclerView.Adapter<PhotoHolder3> {
        private List<category_page> mHome_pages;
        public PhotoAdapter(List<category_page> galleryItems) {
            mHome_pages = galleryItems;
        }
        @Override
        public PhotoHolder3 onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            //TextView textView = new TextView(getActivity());
            //return new PhotoHolder(textView);
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());



            View view = layoutInflater
                    .inflate(R.layout.video_header, viewGroup, false);
            return new PhotoHolder3(view);
        }
        @Override
        public void onBindViewHolder(PhotoHolder3 photoHolder, int position) {
            category_page galleryItem = mHome_pages.get(position);
            photoHolder.bindGalleryItem(galleryItem);
        }
        @Override
        public int getItemCount() {
            return mHome_pages.size();
        }
    }
    //---------------------------------------------------------------------------------------------------------------



    //AsyncTask creates a background thread for you and runs the code in the doInBackground(…) method on that thread.
    private class FetchItemsTask extends AsyncTask<Void,Void,List<category_page>> {

        @Override
        //It sets the type of value returned by doInBackground(…) as well as the type of onPostExecute(…)’s input parameter.
        protected List<category_page> doInBackground(Void... params) { //get data from a website and log it.
            return new criminalintent.android.bignerdranch.com.criminalintent.CategoryFetchr().fetchVideos("https://shekulli.com.al/api/abing.php");

        }

        @Override
        protected void onPostExecute(List<category_page> items) {

            mItems = items;
            setupAdapter();
            mPhotoRecyclerView.getAdapter().notifyDataSetChanged();
            if (mSwipeRefresh.isRefreshing()){
                mSwipeRefresh.setRefreshing(false);
            }

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
            Intent intent = PostActivity.newIntent(getActivity(), url_basic ,items.get(0),items.get(1), items.get(2), mItemsT1, mItemsT2, mItemsT3, mItemsT4, mcategories, murl_categories);
            startActivity(intent);
        }
    }


    //---------------------------------------------------------------------------------------------------------------

    @Override
    public void onCreate(Bundle savedInstanceState) { //Fragment.onCreate(Bundle) is a public method whereas Activity.onCreate(Bundle) is protected
        super.onCreate(savedInstanceState);  //Fragment.onCreate(…) and other Fragment lifecycle methods must be public because they will be called by whatever activity is hosting the fragment.

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mItemsT = bundle.getParcelableArrayList(EXTRA_FG_ID3);
            Log.i("ggg", "3===================================================: " + String.valueOf(mItemsT.size()));
            mItems = mItemsT;

            mItemsT1 = bundle.getParcelableArrayList(EXTRA_FG_ID1);
            mItemsT2 = bundle.getParcelableArrayList(EXTRA_FG_ID2);
            mItemsT3 = bundle.getParcelableArrayList(EXTRA_FG_ID3);
            mItemsT4 = bundle.getParcelableArrayList(EXTRA_FG_ID4);
            mcategories = bundle.getParcelableArrayList(EXTRA_CATEGORY_ID);
            murl_categories = bundle.getParcelableArrayList(EXTRA_CATEGORY_URL_ID);

        }


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_photo_gallery, container, false);

        connectedLayout = (LinearLayout) v.findViewById(R.id.connection_avaiable_layout);
        disconnectedLayout = (RelativeLayout) v.findViewById(R.id.no_internet_layout);


        mPhotoRecyclerView = (RecyclerView) v
                .findViewById(R.id.fragment_photo_gallery_recycler_view);
        mPhotoRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        setupAdapter(); //You callsetupAdapter() in onCreateView(…) so that every time a new RecyclerView is created, it is reconfigured with an appropriate adapter.
        //ou also want to call it every time your set of model objects changes.
        refreshViews(isNetworkAvailable());

        mSwipeRefresh = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

        mReloadButton = (Button) v.findViewById(R.id.reload_button);
        mReloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });


        return v;
    }


    private void setupAdapter() {
        if (isAdded()) { //This confirms that the fragment has been attached to an activity, and in turn that getActivity() will not be null
            mPhotoRecyclerView.setAdapter(new PhotoAdapter(mItems));}

        /*
        However, now that you are using an AsyncTask you are triggering some callbacks from a background
        thread. Thus you cannot assume that the fragment is attached to an activity. You must check to make
        sure that your fragment is still attached. If it is not, then operations that rely on that activity (like
        creating your PhotoAdapter, which in turn creates a TextView using the hosting activity as the context)
        will fail. This is why, in your code above, you check that isAdded() is true before setting the adapter.
         */
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void refreshViews(boolean networkAvaiable){
        if(networkAvaiable){
            connectedLayout.setVisibility(View.VISIBLE);
            disconnectedLayout.setVisibility(View.GONE);
        } else {
            //disconnectedLayout.setVisibility(View.VISIBLE);
            //connectedLayout.setVisibility(View.GONE);
            Intent i = new Intent(getActivity(), No_Net.class);
            startActivity(i);

        }
    }

    private void refresh(){
        refreshViews(isNetworkAvailable());
        if(isNetworkAvailable()){
            new Handler().postDelayed(new Runnable() {


                @Override
                public void run() {
                    mSwipeRefresh.setRefreshing(false);
                }
            }, 1000);
        } else {
            mSwipeRefresh.setRefreshing(false);
        }
    }


}

/* video activity

    private class PhotoHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private TextView mCommentTextView;
        private ImageView mImage;
        private ImageView mHeadImage;
        private String url_page;
        private String url_photo;
        TextView display_text;
        ImageView mainImage;


        public PhotoHolder3(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //mTitleTextView = (TextView) itemView;


            mainImage = (ImageView)
                    itemView.findViewById(R.id.video_header_photo);

            display_text = (TextView)
                    itemView.findViewById(R.id.video_header_textView_Title);


        }
*/


    /*public void bindGalleryItem(home_page item) {

        //Context context = context.getApplicationContext();


        Log.i("ggg", "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy: " + index);

        Picasso.with(getActivity())
                .load(item.getPhotoUrl())
                .fit()
                .centerCrop()
                .into(mainImage);
        display_text.setText(item.getTitle());

        //mImage.setImageDrawable(R.id.imageView);
        //mTitleTextView.setText(item.getNo_comments());
    }

    @Override
    public void onClick(View v) {
        // url_page not defined
        Intent intent = PostActivity.newIntent(getActivity(), url_main_post);
        startActivity(intent);
    }
}*/


