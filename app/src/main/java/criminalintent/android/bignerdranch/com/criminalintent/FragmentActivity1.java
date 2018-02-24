package criminalintent.android.bignerdranch.com.criminalintent;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by GERMAN on 12-Oct-16.
 */

public class FragmentActivity1 extends VisibleFragment {
    private List<home_page> mItems = new ArrayList<>();
    private List<category_page> mADSItems = new ArrayList<>();
    private List<category_page> mHeadItems = new ArrayList<>();
    private List<category_page> mOtherItems = new ArrayList<>();

    private RecyclerView mPhotoRecyclerView;
    private int index = 0;
    ArrayList mHeadList;
    String title;
    String url_phot0;
    String url_main_post;
    String url_ads_photo;
    String url_ads;
    String url_basic="";

    private SwipeRefreshLayout mSwipeRefresh;
    private LinearLayout connectedLayout;
    private RelativeLayout disconnectedLayout;
    private Button mReloadButton;

    private ArrayList mItemsT1 = new ArrayList<>();
    private ArrayList mItemsT2 = new ArrayList<>();
    private ArrayList mItemsT3 = new ArrayList<>();
    private ArrayList mItemsT4 = new ArrayList<>();
    private ArrayList mcategories = new ArrayList();
    private ArrayList murl_categories = new ArrayList();
    private ArrayList headItems = new ArrayList();

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
    private static final String EXTRA_HEAD =
            "com.bignerdranch.android.criminalintent.head";

    private class PhotoHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private TextView mCommentTextView;
        private ImageView ads2;
        private ImageView mHeadImage;
        private String url_page;
        private String url_photo;
        TextView display_text;
        ImageView mball;
        ViewPager pager;


        public PhotoHolder1(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            pager = (ViewPager)itemView.findViewById(R.id.sliderPager);
            //mTitleTextView = (TextView) itemView;

            mball = (ImageView)
                    itemView.findViewById(R.id.sliderBalls);

            ads2 = (ImageView)
                    itemView.findViewById(R.id.ads2);


//            mainImage = (ImageView)
//                    itemView.findViewById(R.id.home_header_photo);
//
//            display_text = (TextView)
//                    itemView.findViewById(R.id.home_header_textView_Title);


        }

        /*public void bindGalleryItem(home_page item) {
            mTitleTextView.setText(item.getTitle());
        }*/

        public void bindGalleryItem(home_page item) {

            //Context context = context.getApplicationContext();


            Log.i("ggg", "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy: " + index);


            RelativeLayout.LayoutParams params = ((RelativeLayout.LayoutParams) ads2.getLayoutParams());
            ads2.setLayoutParams(params);

            Picasso.with(getActivity())
                    .load(mADSItems.get(3).getMphoto_url_post())
                    .fit()
                    .into(ads2);

            ads2.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mADSItems.get(3).getMurl_post()));//url_page));
                    startActivity(browserIntent);
                }
            });

//            Picasso.with(getActivity())
//                    .load(new File("//home/reldi/Desktop/OLD/C/V1.test.she - 23/app/src/main/res/mipmap-hdpi/logomadheshekulli.png"))
//                    .fit()
//                    .into(mballs);

            pager.setAdapter(new MySliderAdapter(getFragmentManager()));
            pager.setCurrentItem(1, true);
            pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    Log.i("ggg", "position: " + String.valueOf(position));

                    switch (position) {
                        case 0:
                            mball.setImageResource(R.mipmap.ball7);
                            break;
                        case 1:
                            mball.setImageResource(R.mipmap.ball1);
                            break;
                        case 2:
                            mball.setImageResource(R.mipmap.ball2);
                            break;
                        case 3:
                            mball.setImageResource(R.mipmap.ball3);
                            break;
                        case 4:
                            mball.setImageResource(R.mipmap.ball4);
                            break;
                        case 5:
                            mball.setImageResource(R.mipmap.ball5);
                            break;
                        case 6:
                            mball.setImageResource(R.mipmap.ball6);
                            break;
                        case 7:
                            mball.setImageResource(R.mipmap.ball7);
                            break;
                        case 8:
                            mball.setImageResource(R.mipmap.ball1);
                            break;

                        default:
                            mball.setImageResource(R.mipmap.ball1);
                    }
                    // skip fake page (first), go to last page
                    if (position == 0) {
                        pager.setCurrentItem(7, true);
                    }

                    if (position == 2) {
                        mball.setImageResource(R.mipmap.ball2);
                    }

                    // skip fake page (last), go to first page
                    if (position == 8) {
                        pager.setCurrentItem(1, true); //notice how this jumps to position 1, and not position 0. Position 0 is the fake page!
                    }

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

//            Picasso.with(getActivity())
//                    .load(url_phot0)
//                    .fit()
//                    .centerCrop()
//                    .into(mainImage);
//            display_text.setText(title);

            //mImage.setImageDrawable(R.id.imageView);
            //mTitleTextView.setText(item.getNo_comments());
        }

        @Override
        public void onClick(View pager) {
            Log.i("ggg", "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy: " + index);
            // url_page not defined
            if (isNetworkAvailable()) {
                url_basic=url_main_post;
                new FetchPostBasic().execute();
                //Intent intent = PostActivity.newIntent(getActivity(), url_main_post ,"Shekulli", "http://www.shekulli.com.al/api/topview.php", "https://www.google.al/", mItemsT1, mItemsT2, mItemsT3, mItemsT4, mcategories, murl_categories);
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

    private class PhotoHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private TextView mCommentTextView;
        private ImageView mImage;
        private ImageView mHeadImage;
        private String url_page;
        private String url_photo;
        private String cat_to_go;
        private String suggUrl;
        TextView display_text;
        ImageView mainImage;


        public PhotoHolder2(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //mTitleTextView = (TextView) itemView;


            mImage = (ImageView)
                    itemView.findViewById(R.id.fragment_photo_gallery_image_view);

            mTitleTextView = (TextView)
                    itemView.findViewById(R.id.textView_Title);

            /*Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),  "Franklin-Gothic-Heavy_18503.ttf.ttf");
            mTitleTextView.setTypeface(custom_font);*/

            mDateTextView = (TextView)
                    itemView.findViewById(R.id.textDate);



        }

        /*public void bindGalleryItem(home_page item) {
            mTitleTextView.setText(item.getTitle());
        }*/

        public void bindGalleryItem(home_page item) {

            //Context context = context.getApplicationContext();


            mTitleTextView.setText(item.getTitle());
            mDateTextView.setText(item.getDate());
            url_page = item.getUrl();
            url_photo = item.getPhotoUrl();
            cat_to_go = item.getCat_Title();
            suggUrl = item.getSuggUrl();

            Picasso.with(getActivity())
                    .load(url_photo)
                    .fit()
                    .centerCrop()
                    .into(mImage);


            //mImage.setImageDrawable(R.id.imageView);
            //mTitleTextView.setText(item.getNo_comments());
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

            display_text = (TextView)
                    itemView.findViewById(R.id.news_category_name);
            mHeadImage = (ImageView)
                    itemView.findViewById(R.id.home_advertisment);


        }

        /*public void bindGalleryItem(home_page item) {
            mTitleTextView.setText(item.getTitle());
        }*/

        public void bindGalleryItem(home_page item, int position) {

            //Context context = context.getApplicationContext();

            if (mADSItems.size() != 0){
                url_ads_photo = mADSItems.get(position/6).getMphoto_url_post();
                url_ads = mADSItems.get(position/6).getMurl_post();
                Log.i("ggg", "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy: " + mADSItems.get(position/6).getMtitle_post());
            }
            Picasso.with(getActivity())
                    .load(url_ads_photo)//mADSItems.get(position/6).getMphoto_url_post())
                    .fit()
                    .into(mHeadImage);
            //url_page = mADSItems.get(position/6).getMurl_post();
            mHeadImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url_ads));//url_page));
                    startActivity(browserIntent);
                    // Does nothing yet, but soon!
                }
            });

            Log.i("ggg", "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy: " + index);
            //Log.i("ggg", "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy: " + mADSItems.get(position/6).getMtitle_post());

            display_text.setText(item.getTitle().toUpperCase());


            //mImage.setImageDrawable(R.id.imageView);
            //mTitleTextView.setText(item.getNo_comments());
        }

        @Override
        public void onClick(View v) {
            // url_page not defined
            //Intent intent = PostActivity.newIntent(getActivity(), url_main_post);
            //startActivity(intent);
        }
    }


    private class PhotoHolder4 extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private TextView mCommentTextView;
        private ImageView mImage;
        private ImageView mHeadImage;
        private String url_cat;
        private String name_cat;
        private ArrayList cat_go;
        TextView go_display_text;
        ImageView mainImage;


        public PhotoHolder4(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //mTitleTextView = (TextView) itemView;

            go_display_text = (TextView)
                    itemView.findViewById(R.id.go_category_name);


        }

        /*public void bindGalleryItem(home_page item) {
            mTitleTextView.setText(item.getTitle());
        }*/

        public void bindGalleryItem(home_page item) {

            //Context context = context.getApplicationContext();

            url_cat = item.getUrl();

            cat_go = item.getMcategories();

            name_cat = String.valueOf(item.getTitle().toUpperCase());



            Log.i("ggg", "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy: " + index);

            go_display_text.setText("Per me shume ne kategorine: " + name_cat);

            //name_cat = name_cat.substring(0,1).toUpperCase() + name_cat.substring(1).toLowerCase()

            //mImage.setImageDrawable(R.id.imageView);
            //mTitleTextView.setText(item.getNo_comments());
        }

        @Override
        public void onClick(View v) {
            // url_page not defined
            if (isNetworkAvailable()) {
                Intent intent = CategoryActivity.newIntent(getActivity(), url_cat, cat_go, murl_categories, name_cat.substring(0,1).toUpperCase() + name_cat.substring(1).toLowerCase(), mItemsT1, mItemsT2, mItemsT3, mItemsT4);
                startActivity(intent);
            }
            else {
                //disconnectedLayout.setVisibility(View.VISIBLE);
                //connectedLayout.setVisibility(View.GONE);
                Intent i = new Intent(getActivity(), No_Net.class);
                startActivity(i);
            }
        }
    }


    private class PhotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<home_page> mHome_pages;
        private final int HEADLINE = 0;
        private final int ARTICLE = 1;
        private final int CAT_TITLE = 2;
        private final int GO_CAT = 3;

        public PhotoAdapter(List<home_page> galleryItems) {
            mHome_pages = galleryItems;

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
                            .inflate(R.layout.slider_home, viewGroup, false);
                    holder = new PhotoHolder1(view1);
                    break;


                case ARTICLE:
                    View view2 = layoutInflater
                            .inflate(R.layout.list_news, viewGroup, false);
                    holder = new PhotoHolder2(view2);
                    break;

                case CAT_TITLE:
                    View view3 = layoutInflater
                            .inflate(R.layout.home_news_title, viewGroup, false);
                    holder = new PhotoHolder3(view3);
                    break;

                case GO_CAT:
                    View view4 = layoutInflater
                            .inflate(R.layout.home_news_category, viewGroup, false);
                    holder = new PhotoHolder4(view4);
                    break;


                default:
                    View view = layoutInflater
                            .inflate(R.layout.list_news, viewGroup, false);
                    holder = new PhotoHolder2(view);
                    break;

            }

            return holder;
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder photoHolder, int position) {

            if (position < 100){


                home_page galleryItem = mHome_pages.get(position);
                switch(photoHolder.getItemViewType()){
                    case HEADLINE:
                        PhotoHolder1 holder1 = (PhotoHolder1) photoHolder;
                        holder1.bindGalleryItem(galleryItem);
                        break;
                    case CAT_TITLE:
                        PhotoHolder3 holder3 = (PhotoHolder3) photoHolder;
                        holder3.bindGalleryItem(galleryItem, position);
                        break;
                    case GO_CAT:
                        PhotoHolder4 holder4 = (PhotoHolder4) photoHolder;
                        holder4.bindGalleryItem(galleryItem);
                        break;
                    case ARTICLE:
                        PhotoHolder2 holder2 = (PhotoHolder2) photoHolder;
                        holder2.bindGalleryItem(galleryItem);
                        break;
                }



            }


      /*      if (position<30) {
                home_page galleryItem = mHome_pages.get(position);
                Log.i("ggg", "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz: " + String.valueOf(position));
                photoHolder.bindGalleryItem(galleryItem);
            } */

        }


        @Override
        public int getItemCount() {
            return mHome_pages.size();
        }

        @Override
        public int getItemViewType(int position) {
            if(position == 0){
                return HEADLINE;
            }
            else if ((position-1==0) || ((position-1)%5==0)){
                return CAT_TITLE;
            }
            else if (position%5==0){
                return GO_CAT;
            }

            else {
                return ARTICLE;
            }
        }
    }

    private class MySliderAdapter extends FragmentPagerAdapter {

        public MySliderAdapter(FragmentManager fm) {
            super(fm);


        }

        @Override
        public android.support.v4.app.Fragment getItem(int pos) {
            switch (pos) {
                case 0:
                    return FragmentSliderPager.newInstance(mHeadItems.get(6).getMtitle_post(), mHeadItems.get(6).getMphoto_url_post(), mHeadItems.get(6).getMurl_post(), mItemsT1, mItemsT2, mItemsT3, mItemsT4, mcategories, murl_categories);
                case 1:
                    return FragmentSliderPager.newInstance(mHeadItems.get(0).getMtitle_post(), mHeadItems.get(0).getMphoto_url_post(), mHeadItems.get(0).getMurl_post(), mItemsT1, mItemsT2, mItemsT3, mItemsT4, mcategories, murl_categories);
                case 2:
                    return FragmentSliderPager.newInstance(mHeadItems.get(1).getMtitle_post(), mHeadItems.get(1).getMphoto_url_post(), mHeadItems.get(1).getMurl_post(), mItemsT1, mItemsT2, mItemsT3, mItemsT4, mcategories, murl_categories);
                case 3:
                    return FragmentSliderPager.newInstance(mHeadItems.get(2).getMtitle_post(), mHeadItems.get(2).getMphoto_url_post(), mHeadItems.get(2).getMurl_post(), mItemsT1, mItemsT2, mItemsT3, mItemsT4, mcategories, murl_categories);
                case 4:
                    return FragmentSliderPager.newInstance(mHeadItems.get(3).getMtitle_post(), mHeadItems.get(3).getMphoto_url_post(), mHeadItems.get(3).getMurl_post(), mItemsT1, mItemsT2, mItemsT3, mItemsT4, mcategories, murl_categories);
                case 5:
                    return FragmentSliderPager.newInstance(mHeadItems.get(4).getMtitle_post(), mHeadItems.get(4).getMphoto_url_post(), mHeadItems.get(4).getMurl_post(), mItemsT1, mItemsT2, mItemsT3, mItemsT4, mcategories, murl_categories);
                case 6:
                    return FragmentSliderPager.newInstance(mHeadItems.get(5).getMtitle_post(), mHeadItems.get(5).getMphoto_url_post(), mHeadItems.get(5).getMurl_post(), mItemsT1, mItemsT2, mItemsT3, mItemsT4, mcategories, murl_categories);
                case 7:
                    return FragmentSliderPager.newInstance(mHeadItems.get(6).getMtitle_post(), mHeadItems.get(6).getMphoto_url_post(), mHeadItems.get(6).getMurl_post(), mItemsT1, mItemsT2, mItemsT3, mItemsT4, mcategories, murl_categories);
                case 8:
                    return FragmentSliderPager.newInstance(mHeadItems.get(0).getMtitle_post(), mHeadItems.get(0).getMphoto_url_post(), mHeadItems.get(0).getMurl_post(), mItemsT1, mItemsT2, mItemsT3, mItemsT4, mcategories, murl_categories);
                default:
                    return FragmentSliderPager.newInstance("hello def", "http://www.readersdigest.ca/wp-content/uploads/2011/01/4-ways-cheer-up-depressed-cat.jpg",  mHeadItems.get(0).getMurl_post(), mItemsT1, mItemsT2, mItemsT3, mItemsT4, mcategories, murl_categories);
            }
        }

        @Override
        public int getCount() {
            return 9;
        }
    }

    //---------------------------------------------------------------------------------------------------------------



    //AsyncTask creates a background thread for you and runs the code in the doInBackground(…) method on that thread.
    private class FetchItemsTask extends AsyncTask<Void,Void,List<home_page>> {

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
        protected List<home_page> doInBackground(Void... params) { //get data from a website and log it.
            return new criminalintent.android.bignerdranch.com.criminalintent.FlickrFetchr().fetchItems();

        }

        @Override
        protected void onPostExecute(List<home_page> items) {

            if (dialog.isShowing()) {
                dialog.dismiss();
            }

            mItems = items;
            mPhotoRecyclerView.getAdapter().notifyDataSetChanged();
            if (mSwipeRefresh.isRefreshing()){
                mSwipeRefresh.setRefreshing(false);
            }


        }
    }

    //AsyncTask creates a background thread for you and runs the code in the doInBackground(…) method on that thread.
    private class FetchHeaderTask extends AsyncTask<Void,Void,List<category_page>> {
        @Override
        //It sets the type of value returned by doInBackground(…) as well as the type of onPostExecute(…)’s input parameter.
        protected List<category_page> doInBackground(Void... params) { //get data from a website and log it.

            Log.i("Result", "Received title: " + "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
            return new criminalintent.android.bignerdranch.com.criminalintent.FlickrFetchr().fetchHead();

        }


        @Override
        protected void onPostExecute(List<category_page> head_list) {

            for (int i = 0;i < head_list.size() - 1;i++) {
                mHeadItems.set(i, head_list.get(i));
                Log.i("head", "Received title: " + i + mHeadItems.get(i).getMtitle_post());
            }

            mPhotoRecyclerView.getAdapter().notifyDataSetChanged();
            if (mSwipeRefresh.isRefreshing()){
                mSwipeRefresh.setRefreshing(false);
            }
        }
    }

    //AsyncTask creates a background thread for you and runs the code in the doInBackground(…) method on that thread.
    private class FetchADSTask extends AsyncTask<Void,Void,List<category_page>> {

        @Override
        //It sets the type of value returned by doInBackground(…) as well as the type of onPostExecute(…)’s input parameter.
        protected List<category_page> doInBackground(Void... params) { //get data from a website and log it.
            return new criminalintent.android.bignerdranch.com.criminalintent.FlickrFetchr().fetchADS("http://www.shekulli.com.al/api/ads.php");

        }

        @Override
        protected void onPostExecute(List<category_page> items) {

            mADSItems = items;
            url_ads_photo = mADSItems.get(0).getMphoto_url_post();
            url_ads = mADSItems.get(0).getMphoto_url_post();

            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
            SharedPreferences.Editor editor = sharedPrefs.edit();
            Gson gson = new Gson();

            String json = gson.toJson(mADSItems);

            editor.putString("Ads", json);
            editor.commit();

            for (int i = 0; i < mADSItems.size(); i++) {
                Log.i("1", "Received ADs Url: " + mADSItems.get(i).getMphoto_url_post());
            }
            setupAdapter();
            //Log.i("1", "Received ADs Url: " + mADSItems.get(0).getMurl_post());
            //Log.i("1", "Received Ads photo: " + mADSItems.get(0).getMphoto_url_post());


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
        super.onCreate(savedInstanceState);//Fragment.onCreate(…) and other Fragment lifecycle methods must be public because they will be called by whatever activity is hosting the fragment.

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        Gson gson = new Gson();
        String json = sharedPrefs.getString("Head", null);
        Type type = new TypeToken<ArrayList<category_page>>() {}.getType();
        ArrayList<category_page> arrayList = gson.fromJson(json, type);
        Log.i("gggGSON", "1===================================================: " + String.valueOf(arrayList.size()));
        headItems = arrayList;
        mHeadItems  = headItems;
        Log.i("gggGSON", "1===================================================: " + String.valueOf(mHeadItems.size()));

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mItemsT1 = bundle.getParcelableArrayList(EXTRA_FG_ID1);
            mItemsT2 = bundle.getParcelableArrayList(EXTRA_FG_ID2);
            mItemsT3 = bundle.getParcelableArrayList(EXTRA_FG_ID3);
            mItemsT4 = bundle.getParcelableArrayList(EXTRA_FG_ID4);
            mcategories = bundle.getParcelableArrayList(EXTRA_CATEGORY_ID);
            murl_categories = bundle.getParcelableArrayList(EXTRA_CATEGORY_URL_ID);
            //headItems = bundle.getParcelableArrayList(EXTRA_HEAD);

//            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
//            Gson gson = new Gson();
//            String json = sharedPrefs.getString("Head", null);
//            Type type = new TypeToken<ArrayList<category_page>>() {}.getType();
//            ArrayList<category_page> arrayList = gson.fromJson(json, type);
//            Log.i("gggGSON", "1===================================================: " + String.valueOf(arrayList.size()));
//            headItems = arrayList;
//            mHeadItems  = headItems;

            Log.i("ggg", "1===================================================: " + String.valueOf(mItemsT1.size()));
            Log.i("ggg", "1===================================================: " + String.valueOf(mItemsT2.size()));
            Log.i("ggg", "1===================================================: " + String.valueOf(mItemsT3.size()));
            Log.i("ggg", "1===================================================: " + String.valueOf(mItemsT4.size()));
            //mHeadItems  = headItems;
            mItems = mItemsT1;
            mOtherItems = mItemsT4;
        }

        if (isNetworkAvailable()){
            new FetchADSTask().execute();
            //new FetchItemsTask().execute();
        }

        //      setHasOptionsMenu(true);



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

        //setupAdapter(); //You callsetupAdapter() in onCreateView(…) so that every time a new RecyclerView is created, it is reconfigured with an appropriate adapter.
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
            //new FetchHeaderTask().execute();
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



