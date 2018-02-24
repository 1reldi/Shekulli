package criminalintent.android.bignerdranch.com.criminalintent;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by GERMAN on 23-Nov-16.
 */

public class CategoryFragment extends Fragment {
    private static final String ARG_CATEGORY_ID = "CategoryUrl";
    public static final String TAG = "Fragment Category Url: ";

    private RecyclerView mCategoryRecyclerView;
    private SwipeRefreshLayout mCatRefresh;

    private List<category_page> mPostItems = new ArrayList<>();
    private List<category_page> mAdsItems = new ArrayList<>();

    private String urlCategory;
    private String Url_Search;
    String url_basic="";

    private static final String EXTRA_FG_ID1 =
            "com.bignerdranch.android.criminalintent.fg_id1";
    private static final String EXTRA_FG_ID2 =
            "com.bignerdranch.android.criminalintent.fg_id2";
    private static final String EXTRA_FG_ID3 =
            "com.bignerdranch.android.criminalintent.fg_id3";
    private static final String EXTRA_FG_ID4 =
            "com.bignerdranch.android.criminalintent.fg_id4";
    private static final String EXTRA_NAME_ID =
            "com.bignerdranch.android.criminalintent.name_id";
    private static final String EXTRA_CATEGORY_URL_ID =
            "com.bignerdranch.android.criminalintent.category_url_id";
    private static final String EXTRA_CATEGORY_ID =
            "com.bignerdranch.android.criminalintent.category_id";


    private ArrayList mcategories = new ArrayList();
    private ArrayList murl_categories = new ArrayList();
    private ArrayList Data_Fragment1 = new ArrayList();
    private ArrayList Data_Fragment2 = new ArrayList();
    private ArrayList Data_Fragment3 = new ArrayList();
    private ArrayList Data_Fragment4 = new ArrayList();

    public static CategoryFragment newInstance(String cat_url, Bundle datas) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CATEGORY_ID, cat_url);
        args.putParcelableArrayList(EXTRA_FG_ID1, datas.getParcelableArrayList(EXTRA_FG_ID1));
        args.putParcelableArrayList(EXTRA_FG_ID2, datas.getParcelableArrayList(EXTRA_FG_ID2));
        args.putParcelableArrayList(EXTRA_FG_ID3, datas.getParcelableArrayList(EXTRA_FG_ID3));
        args.putParcelableArrayList(EXTRA_FG_ID4, datas.getParcelableArrayList(EXTRA_FG_ID4));
        args.putParcelableArrayList(EXTRA_CATEGORY_ID, datas.getParcelableArrayList(EXTRA_CATEGORY_ID));
        args.putParcelableArrayList(EXTRA_CATEGORY_URL_ID, datas.getParcelableArrayList(EXTRA_CATEGORY_URL_ID));

        CategoryFragment fragment = new CategoryFragment();
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

    private class PostHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        private String url_main_post;
        TextView display_text;
        ImageView mainImage;
        private String cat_name;



        public PostHolder1(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mainImage = (ImageView)
                    itemView.findViewById(R.id.home_header_photo);

            display_text = (TextView)
                    itemView.findViewById(R.id.home_header_textView_Title);


        }


        public void bindGalleryItem(category_page item) {


            cat_name = item.getCat_Name();
            Picasso.with(getActivity())
                    .load(item.getMphoto_url_post())
                    .fit()
                    .centerCrop()
                    .into(mainImage);
            display_text.setText(item.getMtitle_post());
            url_main_post = item.getMurl_post();

        }

        @Override
        public void onClick(View v) {
            // url_page not defined
            if (isNetworkAvailable()) {
                url_basic=url_main_post;
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

    private class PostHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private TextView mCommentTextView;
        private ImageView mImage;
        private ImageView mHeadImage;
        private String url_page;
        private String url_photo;
        TextView display_text;
        ImageView mainImage;
        private String cat_name;



        public PostHolder2(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);



            mImage = (ImageView)
                    itemView.findViewById(R.id.fragment_photo_gallery_image_view);
            mTitleTextView = (TextView)
                    itemView.findViewById(R.id.textView_Title);

            mDateTextView = (TextView)
                    itemView.findViewById(R.id.textDate);



        }


        public void bindGalleryItem(category_page item) {


            mTitleTextView.setText(item.getMtitle_post());
            mDateTextView.setText(item.getMdate_post());
            url_page = item.getMurl_post();
            url_photo = item.getMphoto_url_post();
            cat_name = item.getCat_Name();

            Picasso.with(getActivity())
                    .load(url_photo)
                    .fit()
                    .centerCrop()
                    .into(mImage);

        }

        @Override
        public void onClick(View v) {
            //url_page defined
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

    private class PostHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImage;


        public PostHolder3(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mImage = (ImageView)
                    itemView.findViewById(R.id.adImage);

        }


        public void bindGalleryItem(category_page item) {

            Picasso.with(getActivity())
                    .load(mAdsItems.get(0).getMphoto_url_post())
                    .fit()
                    .centerCrop()
                    .into(mImage);

            mImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mAdsItems.get(0).getMurl_post()));//url_page));
                    startActivity(browserIntent);
                    // Does nothing yet, but soon!
                }
            });

        }

        @Override
        public void onClick(View v) {

        }
    }

    private class NoResultHolder extends RecyclerView.ViewHolder {

        ImageView no_res;



        public NoResultHolder(View itemView) {
            super(itemView);


            no_res = (ImageView)
                    itemView.findViewById(R.id.no_res_photo);


        }


        public void bindPostItem() {

            Picasso.with(getActivity())
                    .load("http://unbxd.com/blog/wp-content/uploads/2014/02/No-results-found.jpg")
                    .fit()
                    .into(no_res);


        }

    }

    private class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<category_page> mCategory_pages;
        private final int HEADLINE = 0;
        private final int ARTICLE = 1;
        private final int ADS = 2;
        private final int RESULTS = 7;

        public PostAdapter(List<category_page> galleryItems) {
            mCategory_pages = galleryItems;

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            //TextView textView = new TextView(getActivity());
            //return new PhotoHolder(textView);
            RecyclerView.ViewHolder holder = null;
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            switch(viewType){
                case HEADLINE:
                    View view1 = layoutInflater
                            .inflate(R.layout.home_header, viewGroup, false);
                    holder = new PostHolder1(view1);
                    break;


                case ARTICLE:
                    View view2 = layoutInflater
                            .inflate(R.layout.list_news, viewGroup, false);
                    holder = new PostHolder2(view2);
                    break;

                case ADS:
                    View view3 = layoutInflater
                            .inflate(R.layout.ad_container, viewGroup, false);
                    holder = new PostHolder3(view3);
                    break;


                default:
                    View view6 = layoutInflater
                            .inflate(R.layout.no_results, viewGroup, false);
                    holder = new NoResultHolder(view6);
                    break;

            }

            return holder;
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder postHolder, int position) {

            Log.i(TAG, "Sizeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee: " + String.valueOf(mCategory_pages.size()));
            if (mCategory_pages.size() == 0){
                // max 30 lajme
                NoResultHolder holder7 = (NoResultHolder) postHolder;
                holder7.bindPostItem();
            }
            else {
                category_page galleryItem = mCategory_pages.get(position);
                switch(postHolder.getItemViewType()){
                    case HEADLINE:
                        PostHolder1 holder1 = (PostHolder1) postHolder;
                        holder1.bindGalleryItem(galleryItem);
                        break;
                    case ARTICLE:
                        PostHolder2 holder2 = (PostHolder2) postHolder;
                        holder2.bindGalleryItem(galleryItem);
                        break;
                    case ADS:
                        PostHolder3 holder3 = (PostHolder3) postHolder;
                        holder3.bindGalleryItem(galleryItem);
                        break;
                    case RESULTS:
                        NoResultHolder holder7 = (NoResultHolder) postHolder;
                        holder7.bindPostItem();
                        break;
                }
            }

        }


        @Override
        public int getItemCount() {
            return mCategory_pages.size();
        }

        @Override
        public int getItemViewType(int position) {
            if (mCategory_pages.size() == 0){
                return RESULTS;
            }

            if(position == 0){
                return HEADLINE;
            } else if(position == 1){
                return ADS;
            }
            else {
                return ARTICLE;
            }
        }
    }

    //---------------------------------------------------------------------------------------------------------------

    //AsyncTask creates a background thread for you and runs the code in the doInBackground(…) method on that thread.
    private class FetchPostTask extends AsyncTask<Void, Void, List<category_page>> {

        private ProgressDialog dialog = new ProgressDialog(getActivity());

        /** progress dialog to show user that the backup is processing. */
        /** application context. */
        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Ju lutem prisni!");
            this.dialog.show();
        }

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

            mPostItems = items;
            Log.i(TAG,"Size: " + String.valueOf(mPostItems.size()));
            setupAdapter();
            mCategoryRecyclerView.getAdapter().notifyDataSetChanged();
            if (mCatRefresh.isRefreshing()){
                mCatRefresh.setRefreshing(false);
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
            Intent intent = PostActivity.newIntent(getActivity(), url_basic ,items.get(0),items.get(1), items.get(2), Data_Fragment1, Data_Fragment2, Data_Fragment3, Data_Fragment4, mcategories, murl_categories);
            startActivity(intent);
        }
    }


    //---------------------------------------------------------------------------------------------------------------

    @Override
    public void onCreate(Bundle savedInstanceState) { //Fragment.onCreate(Bundle) is a public method whereas Activity.onCreate(Bundle) is protected
        super.onCreate(savedInstanceState);  //Fragment.onCreate(…) and other Fragment lifecycle methods must be public because they will be called by whatever activity is hosting the fragment.
        Log.i(TAG,"################################################################################################33");

        Bundle bundle = this.getArguments();

        String Cat_Url = (String) getArguments().getSerializable(ARG_CATEGORY_ID);
        Data_Fragment1 = bundle.getParcelableArrayList(EXTRA_FG_ID1);
        Data_Fragment2 = bundle.getParcelableArrayList(EXTRA_FG_ID2);
        Data_Fragment3 = bundle.getParcelableArrayList(EXTRA_FG_ID3);
        Data_Fragment4 = bundle.getParcelableArrayList(EXTRA_FG_ID4);
        mcategories = bundle.getParcelableArrayList(EXTRA_CATEGORY_ID);
        murl_categories = bundle.getParcelableArrayList(EXTRA_CATEGORY_URL_ID);

        Log.i("888", "][][][][][][][][][][][][][][][][][][][][][][][][][][][: " + String.valueOf(mcategories.size()));
        Log.i("888", "][][][][][][][][][][][][][][][][][][][][][][][][][][][: " + String.valueOf(murl_categories.size()));

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        Gson gson = new Gson();
        String json = sharedPrefs.getString("Ads", null);
        Type type = new TypeToken<ArrayList<category_page>>() {}.getType();
        ArrayList<category_page> arrayList = gson.fromJson(json, type);
        Log.i("gggGSON", "1===================================================: " + String.valueOf(arrayList.size()));
        mAdsItems = arrayList;

        Log.i(TAG, "Received URL: " + Cat_Url);
        urlCategory = Cat_Url;

        setHasOptionsMenu(true);
        new FetchPostTask().execute();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) { //This method is where you inflate the layout for the fragment’s view and return the inflated View to the hosting activity.
        View v = inflater.inflate(R.layout.fragment_category, container, false);

        mCategoryRecyclerView = (RecyclerView) v
                .findViewById(R.id.fragment_category_recycler_view);
        mCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        setupAdapter();

        mCatRefresh = (SwipeRefreshLayout) v.findViewById(R.id.swipe_category_refresh);
        mCatRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isNetworkAvailable()) {
                    new FetchPostTask().execute();
                }
                else {
                    //disconnectedLayout.setVisibility(View.VISIBLE);
                    //connectedLayout.setVisibility(View.GONE);
                    mCatRefresh.setRefreshing(false);
                    Intent i = new Intent(getActivity(), No_Net.class);
                    startActivity(i);
                }
            }
        });

        return v;
    }


    private void setupAdapter() {
        if (isAdded()) { //This confirms that the fragment has been attached to an activity, and in turn that getActivity() will not be null
            mCategoryRecyclerView.setAdapter(new PostAdapter(mPostItems));
        }

        /*
        However, now that you are using an AsyncTask you are triggering some callbacks from a background
        thread. Thus you cannot assume that the fragment is attached to an activity. You must check to make
        sure that your fragment is still attached. If it is not, then operations that rely on that activity (like
        creating your PostAdapter, which in turn creates a TextView using the hosting activity as the context)
        will fail. This is why, in your code above, you check that isAdded() is true before setting the adapter.
         */
    }
}
