package criminalintent.android.bignerdranch.com.criminalintent;

/**
 * Created by GERMAN on 01-Nov-16.
 */

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import java.util.ArrayList;
import java.util.UUID;

import static criminalintent.android.bignerdranch.com.criminalintent.R.string.app_name;


public class PostActivity extends AppCompatActivity {



    private Menu menu;
    private String category_title;
    private String categoty_url;
    private String share_url;
    private ArrayList mcategories = new ArrayList();
    private ArrayList murl_categories = new ArrayList();

    private static final String EXTRA_POST_ID =
            "com.bignerdranch.android.criminalintent.post_id";
    private static final String EXTRA_POST_CAT_ID =
            "com.bignerdranch.android.criminalintent.post_cat_id";
    private static final String EXTRA_POST_URL_CAT_ID =
            "com.bignerdranch.android.criminalintent.post_cat_url_id";
    private static final String EXTRA_POST_SHARE=
            "com.bignerdranch.android.criminalintent.post_share";
    public static final String TAG = "Activity Post Url: ";

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

    private ArrayList Data_Fragment1 = new ArrayList();
    private ArrayList Data_Fragment2 = new ArrayList();
    private ArrayList Data_Fragment3 = new ArrayList();
    private ArrayList Data_Fragment4 = new ArrayList();

    Bundle bundle1 = new Bundle();

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) PostActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static Intent newIntent(Context packageContext, String url, String cat_title, String cat_url, String share, ArrayList dt_fg1 ,ArrayList dt_fg2 ,ArrayList dt_fg3 ,ArrayList dt_fg4, ArrayList categories, ArrayList url_categories) {
        Intent intent = new Intent(packageContext, PostActivity.class);
        intent.putExtra(EXTRA_POST_ID, url);
        intent.putExtra(EXTRA_POST_CAT_ID, cat_title);
        intent.putExtra(EXTRA_POST_URL_CAT_ID, cat_url);
        intent.putExtra(EXTRA_POST_SHARE, share);
        intent.putExtra(EXTRA_FG_ID1, dt_fg1);
        intent.putExtra(EXTRA_FG_ID2, dt_fg2);
        intent.putExtra(EXTRA_FG_ID3, dt_fg3);
        intent.putExtra(EXTRA_FG_ID4, dt_fg4);
        intent.putExtra(EXTRA_CATEGORY_ID, categories);
        intent.putExtra(EXTRA_CATEGORY_URL_ID, url_categories);
        return intent;
    }

    private void updateMenuTitles() {
        MenuItem bedMenuItem = menu.findItem(R.menu.post_menu);
        bedMenuItem.setTitle(Html.fromHtml("<font color='#fe9a2e'>" + category_title + "</font>"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_dynamic);


        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


//        //check for this value. If it exists, assign it to mCurrentIndex.
//        if (savedInstanceState != null) {
//            Data_Fragment1 = savedInstanceState.getParcelableArrayList(EXTRA_FG_ID1);
//            Data_Fragment2 = savedInstanceState.getParcelableArrayList(EXTRA_FG_ID2);
//            Data_Fragment3 = savedInstanceState.getParcelableArrayList(EXTRA_FG_ID3);
//            Data_Fragment4 = savedInstanceState.getParcelableArrayList(EXTRA_FG_ID4);
//            mcategories = savedInstanceState.getParcelableArrayList(EXTRA_CATEGORY_ID);
//            murl_categories = savedInstanceState.getParcelableArrayList(EXTRA_CATEGORY_URL_ID);
//
//            bundle1.putParcelableArrayList(EXTRA_FG_ID1, Data_Fragment1);
//            bundle1.putParcelableArrayList(EXTRA_FG_ID2, Data_Fragment2);
//            bundle1.putParcelableArrayList(EXTRA_FG_ID3, Data_Fragment3);
//            bundle1.putParcelableArrayList(EXTRA_FG_ID4, Data_Fragment4);
//            bundle1.putParcelableArrayList(EXTRA_CATEGORY_ID, mcategories);
//            bundle1.putParcelableArrayList(EXTRA_CATEGORY_URL_ID, murl_categories);
//
//            Log.i("onCreatePost", "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%" + String.valueOf(mcategories.size()));
//            Log.i("onCreatePost", "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% " + String.valueOf(murl_categories.size()));
//
//        }
//        else {

            Data_Fragment1 = (ArrayList) getIntent()
                    .getSerializableExtra(EXTRA_FG_ID1);
            Data_Fragment2 = (ArrayList) getIntent()
                    .getSerializableExtra(EXTRA_FG_ID2);
            Data_Fragment3 = (ArrayList) getIntent()
                    .getSerializableExtra(EXTRA_FG_ID3);
            Data_Fragment4 = (ArrayList) getIntent()
                    .getSerializableExtra(EXTRA_FG_ID4);
            mcategories = (ArrayList) getIntent()
                    .getSerializableExtra(EXTRA_CATEGORY_ID);
            murl_categories = (ArrayList) getIntent()
                    .getSerializableExtra(EXTRA_CATEGORY_URL_ID);

            bundle1.putParcelableArrayList(EXTRA_FG_ID1, Data_Fragment1);
            bundle1.putParcelableArrayList(EXTRA_FG_ID2, Data_Fragment2);
            bundle1.putParcelableArrayList(EXTRA_FG_ID3, Data_Fragment3);
            bundle1.putParcelableArrayList(EXTRA_FG_ID4, Data_Fragment4);
            bundle1.putParcelableArrayList(EXTRA_CATEGORY_ID, mcategories);
            bundle1.putParcelableArrayList(EXTRA_CATEGORY_URL_ID, murl_categories);

            Log.i("onCreatePost", "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%" + String.valueOf(mcategories.size()));
            Log.i("onCreatePost", "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% " + String.valueOf(murl_categories.size()));
     //   }

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


        FragmentManager fm = getSupportFragmentManager(); //The FragmentManager is responsible for managing your fragments and adding their views to the activity’s view hierarchy

        String post_url = (String) getIntent()
                .getSerializableExtra(EXTRA_POST_ID);
        Log.i(TAG, "Received URL: " + post_url);

        category_title = (String) getIntent()
                .getSerializableExtra(EXTRA_POST_CAT_ID);
        Log.i(TAG, "Received URL: " + post_url);

        categoty_url = (String) getIntent()
                .getSerializableExtra(EXTRA_POST_URL_CAT_ID);
        Log.i(TAG, "Received URL: " + categoty_url);

        share_url = (String) getIntent()
                .getSerializableExtra(EXTRA_POST_SHARE);


        Fragment fragment = fm.findFragmentById(R.id.fragment_container);//ask the FragmentManager for the fragment with a container view ID of R.id.fragment_container
        if (fragment == null) {
            fragment = new PostFragment().newInstance(post_url, categoty_url, category_title, share_url, bundle1);//share_url
            fm.beginTransaction() //Create a new fragment transaction, include one add operation in it, and then commit it
                    .add(R.id.fragment_container, fragment) //This code creates and commits a fragment  transaction.
                    .commit();                              //Fragment transactions are used to add, remove, attach, detach, or replace fragments in the fragment list.
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
//        Log.i("g", "onSaveInstanceState");
//        savedInstanceState.putParcelableArrayList(EXTRA_FG_ID1, Data_Fragment1);
//        savedInstanceState.putParcelableArrayList(EXTRA_FG_ID2, Data_Fragment2);
//        savedInstanceState.putParcelableArrayList(EXTRA_FG_ID3, Data_Fragment3);
//        savedInstanceState.putParcelableArrayList(EXTRA_FG_ID4, Data_Fragment4);
//        savedInstanceState.putParcelableArrayList(EXTRA_CATEGORY_ID, mcategories);
//        savedInstanceState.putParcelableArrayList(EXTRA_CATEGORY_URL_ID, murl_categories);
    }




    @Override
    public boolean onPrepareOptionsMenu(Menu menu){


        MenuItem bedMenuItem = menu.findItem(R.menu.post_menu);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#fe9a2e'>" + category_title + "</font>"));
        //getSupportActionBar().setIcon(R.mipmap.logo);

        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.post_menu, menu);
        this.menu = menu;
        //MenuItem bedMenuItem = menu.findItem(R.menu.post_menu);
        //bedMenuItem.setTitle(Html.fromHtml("<font color='#fe9a2e'>HELLO WORLD</font>"));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       // item.setTitle(Html.fromHtml("<font color='#fe9a2e'>HELLO WORLD</font>"));

        switch(item.getItemId()) {
            case R.id.reset:
                //add the function to perform here
                if (category_title.equalsIgnoreCase("Lajm i Fundit")){
                    new Handler().postDelayed(new Runnable() {


                        @Override
                        public void run() {
                            // This method will be executed once the timer is over
                            // Start your app main activity


                            Intent i = new Intent(PostActivity.this, SplashScreen.class);
                            startActivity(i);
                            // close this activity
                            finish();
                        }
                    }, 10);
                }
                else {
                    this.finish();
                }
                return(true);
            case R.id.share:
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

                // Add data to the intent, the receiving app will decide
                // what to do with it.
                share.putExtra(Intent.EXTRA_TEXT, share_url);

                startActivity(Intent.createChooser(share, "Shperndani linkun e lajmit"));
                return(true);

            case R.id.logo:
                if (category_title.equalsIgnoreCase("Lajm i Fundit")){
                    new Handler().postDelayed(new Runnable() {


                        @Override
                        public void run() {
                            // This method will be executed once the timer is over
                            // Start your app main activity


                            Intent i = new Intent(PostActivity.this, SplashScreen.class);
                            startActivity(i);
                            // close this activity
                            finish();
                        }
                    }, 10);
                }
                else {
                    if (isNetworkAvailable()) {
                        Intent intent = MainActivity.newIntent(PostActivity.this, mcategories, murl_categories, Data_Fragment1, Data_Fragment2, Data_Fragment3, Data_Fragment4, Data_Fragment4);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                    else {
                        //disconnectedLayout.setVisibility(View.VISIBLE);
                        //connectedLayout.setVisibility(View.GONE);
                        Intent i = new Intent(PostActivity.this, No_Net.class);
                        startActivity(i);
                    }
                }
    }
        return(super.onOptionsItemSelected(item));
    }

}















/*
public class PostActivity extends SingleFragmentActivity {

    private static final String EXTRA_POST_ID =
            "com.bignerdranch.android.criminalintent.post_id";
    public static final String TAG = "Activity Post Url: ";

    public static Intent newIntent(Context packageContext, String url) {
        Intent intent = new Intent(packageContext, PostActivity.class);
        intent.putExtra(EXTRA_POST_ID, url);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        String post_url = (String) getIntent()
                .getSerializableExtra(EXTRA_POST_ID);
        Log.i(TAG, "Received URL: " + post_url);
        return PostFragment.newInstance(post_url);
    }
}
*/