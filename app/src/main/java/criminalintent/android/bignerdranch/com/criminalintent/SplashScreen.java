package criminalintent.android.bignerdranch.com.criminalintent;

/**
 * Created by GERMAN on 21-Nov-16.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SplashScreen extends AppCompatActivity {

    private ArrayList mcategories = new ArrayList();
    private ArrayList murl_categories = new ArrayList();
    public ArrayList mItems1 = new ArrayList();
    public ArrayList mItems2 = new ArrayList();
    public ArrayList mItems3 = new ArrayList();
    public ArrayList mItems4 = new ArrayList();
    public ArrayList Fragment_Data = new ArrayList();
    private ArrayList mHeadItems = new ArrayList<>();

    private RelativeLayout disconnectedLayout;
    private RelativeLayout connectedLayout;
    private Button mReloadButton;
    private ProgressBar spinner;

    public SharedPreferences appPreferences;
    boolean isAppInstalled = false;

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1000;
//


    //---------------------------------------------------------------------------------------------------------------



    //AsyncTask creates a background thread for you and runs the code in the doInBackground(…) method on that thread.
    private class FetchItemsTask1 extends AsyncTask<Void,Void,List<home_page>> {

        @Override
        //It sets the type of value returned by doInBackground(…) as well as the type of onPostExecute(…)’s input parameter.
        protected List<home_page> doInBackground(Void... params) { //get data from a website and log it.
            return new criminalintent.android.bignerdranch.com.criminalintent.FlickrFetchr().fetchItems();

        }

        @Override
        protected void onPostExecute(List<home_page> items) {

//                RequestQueue queue = Volley.newRequestQueue(this);
//                String url ="http://long-frog-7245.getsandbox.com/";
//
//                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                // Display the first 500 characters of the response string.
//                                Log.i("Response is: ", "");
//                            }
//                        }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.i("That didn't work! ", "");
//                    }
//                });
//            // Add the request to the RequestQueue.
//                queue.add(stringRequest);

            if (items.size()>0) {
                for (int i = 0; i < items.size(); i++) {

                    mItems1.add(items.get(i));
                }
            }
            else {
                Log.i("Splash", "uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu" + " nothing");
            }

            Log.i("Splash","!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + String.valueOf(mItems1.size()));
            Fragment_Data.add(items);
            //mItems1 = items;

        }
    }

    //---------------------------------------------------------------------------------------------------------------

    //AsyncTask creates a background thread for you and runs the code in the doInBackground(…) method on that thread.
    private class FetchItemsTask2 extends AsyncTask<Void,Void,List<category_page>> {

        @Override
        //It sets the type of value returned by doInBackground(…) as well as the type of onPostExecute(…)’s input parameter.
        protected List<category_page> doInBackground(Void... params) { //get data from a website and log it.
            return new criminalintent.android.bignerdranch.com.criminalintent.CategoryFetchr().fetchItems("http://www.shekulli.com.al/api/efundit.php");

        }

        @Override
        protected void onPostExecute(List<category_page> items) {

            if (items.size()>0) {
                for (int i = 0; i < items.size(); i++) {

                    mItems2.add(items.get(i));
                }
            }
            else {
                Log.i("Splash", "uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu" + " nothing");
            }

            Log.i("Splash","!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + String.valueOf(mItems2.size()));
            Fragment_Data.add(items);
            //mItems2 = items;


        }
    }

    //---------------------------------------------------------------------------------------------------------------



    //AsyncTask creates a background thread for you and runs the code in the doInBackground(…) method on that thread.
    private class FetchItemsTask3 extends AsyncTask<Void,Void,List<category_page>> {

        @Override
        //It sets the type of value returned by doInBackground(…) as well as the type of onPostExecute(…)’s input parameter.
        protected List<category_page> doInBackground(Void... params) { //get data from a website and log it.
            return new criminalintent.android.bignerdranch.com.criminalintent.CategoryFetchr().fetchItems("http://www.shekulli.com.al/api/search.php?keywords=video");

        }

        @Override
        protected void onPostExecute(List<category_page> items) {

            if (items.size()>0) {
                for (int i = 0; i < items.size(); i++) {

                    mItems3.add(items.get(i));
                }
            }
            else {
                Log.i("Splash", "uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu" + " nothing");
            }

            Log.i("Splash","!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + String.valueOf(mItems3.size()));
            Fragment_Data.add(items);
            //mItems3 = items;
        }
    }


    //---------------------------------------------------------------------------------------------------------------


    //AsyncTask creates a background thread for you and runs the code in the doInBackground(…) method on that thread.
    private class FetchItemsTask4 extends AsyncTask<Void,Void,List<category_page>> {

        @Override
        //It sets the type of value returned by doInBackground(…) as well as the type of onPostExecute(…)’s input parameter.
        protected List<category_page> doInBackground(Void... params) { //get data from a website and log it.
            return new criminalintent.android.bignerdranch.com.criminalintent.CategoryFetchr().fetchItems("http://www.shekulli.com.al/api/kategori.php?n=soft");//http://www.shekulli.com.al/api/topview.php

        }

        @Override
        protected void onPostExecute(List<category_page> items) {

            if (items.size()>0) {
                for (int i = 0; i < items.size(); i++) {

                    mItems4.add(items.get(i));
                }
            }
            else {
                Log.i("Splash", "uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu" + " nothing");
            }

            Log.i("Splash","!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + String.valueOf(mItems4.size()));
            Fragment_Data.add(items);
            //mItems4 = items;

        }
    }


    //---------------------------------------------------------------------------------------------------------------



    //AsyncTask creates a background thread for you and runs the code in the doInBackground(…) method on that thread.
    private class FetchHeaderTask extends AsyncTask<Void,Void,List<category_page>> {
        Context context;
        @Override
        //It sets the type of value returned by doInBackground(…) as well as the type of onPostExecute(…)’s input parameter.
        protected List<category_page> doInBackground(Void... params) { //get data from a website and log it.

            Log.i("Result", "Received title: " + "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
            return new criminalintent.android.bignerdranch.com.criminalintent.FlickrFetchr().fetchHead();

        }


        @Override
        protected void onPostExecute(List<category_page> head_list) {
            Log.i("Result", "Received head size: " + String.valueOf(head_list.size()));
            for (int i = 0;i < head_list.size();i++) {
                mHeadItems.add(head_list.get(i));
            }
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = sharedPrefs.edit();
            Gson gson = new Gson();

            String json = gson.toJson(mHeadItems);

            editor.putString("Head", json);
            editor.commit();
        }
    }






    //---------------------------------------------------------------------------------------------------------------


    //AsyncTask creates a background thread for you and runs the code in the doInBackground(…) method on that thread.
    public class FetchCategoryTask extends AsyncTask<Void,Void,ArrayList> {
        @Override
        //It sets the type of value returned by doInBackground(…) as well as the type of onPostExecute(…)’s input parameter.
        protected ArrayList doInBackground(Void... params) { //get data from a website and log it.

            Log.i("Splash", "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            return new criminalintent.android.bignerdranch.com.criminalintent.FlickrFetchr().fetchCategories();

        }


        @Override
        protected void onPostExecute(ArrayList category_list) {

            if (category_list.size()>0) {
                for (int i = 0; i < category_list.size(); i++) {

                    mcategories.add(category_list.get(i));
                }
            }
            else {
                Log.i("Splash", "uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu" + " nothing");
            }


            //mcategories = category_list;

        }
    }

    public class FetchIsPaid extends AsyncTask<Void,Void,String> {
        @Override
        //It sets the type of value returned by doInBackground(…) as well as the type of onPostExecute(…)’s input parameter.
        protected String doInBackground(Void... params) { //get data from a website and log it.

            Log.i("Splash", "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            return new criminalintent.android.bignerdranch.com.criminalintent.FlickrFetchr().isPaid();

        }


        @Override
        protected void onPostExecute(String isPaid) {

            Log.i("Splash", "uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu isPaid " + isPaid);
            if (isPaid == "true") {
                Log.i("Splash", "yayyyyyyyyyyyyyyyyyyyyyyyyyyyy isPaid " + isPaid);
                new FetchItemsTask1().execute();
                new FetchItemsTask2().execute();
                new FetchItemsTask3().execute();
                new FetchItemsTask4().execute();

                new FetchCategoryTask().execute();
                new FetchUrlCategoryTask().execute();
            } else {
                disconnectedLayout.setVisibility(View.VISIBLE);
                connectedLayout.setVisibility(View.GONE);


                mReloadButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new Handler().postDelayed(new Runnable() {


                            @Override
                            public void run() {
                                // This method will be executed once the timer is over
                                // Start your app main activity


                                Intent i = new Intent(SplashScreen.this, SplashScreen.class);
                                startActivity(i);
                                // close this activity
                                finish();
                            }
                        }, 50);
                    }
                });
            }

        }
    }

    //---------------------------------------------------------------------------------------------------------------


    //---------------------------------------------------------------------------------------------------------------


    //AsyncTask creates a background thread for you and runs the code in the doInBackground(…) method on that thread.
    public class FetchUrlCategoryTask extends AsyncTask<Void,Void,ArrayList> {
        @Override
        //It sets the type of value returned by doInBackground(…) as well as the type of onPostExecute(…)’s input parameter.
        protected ArrayList doInBackground(Void... params) { //get data from a website and log it.

            Log.i("Splash", "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            return new criminalintent.android.bignerdranch.com.criminalintent.FlickrFetchr().FetchUrlCategories();

        }


        @Override
        protected void onPostExecute(ArrayList category_url_list) {

            if (category_url_list.size()>0) {
                for (int i = 0; i < category_url_list.size(); i++) {
                    murl_categories.add(category_url_list.get(i));
                }
            }
            else {
                Log.i("Splash", "uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu" + " nothing");
            }

            Log.i("Splash","^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^mcategories " + String.valueOf(mcategories.size()));
            Log.i("Splash","^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^murl_categories " + String.valueOf(murl_categories.size()));
            Log.i("Splash","^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^Fragment_Data " + String.valueOf(Fragment_Data.size()));
            Log.i("Splash","^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^mItems1 " + String.valueOf(mItems1.size()));
            Log.i("Splash","^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^mItems2 " + String.valueOf(mItems2.size()));
            Log.i("Splash","^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^mItems3 " + String.valueOf(mItems3.size()));
            Log.i("Splash","^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^mItems4 " + String.valueOf(mItems4.size()));


            //ArrayList t1 = new ArrayList();
            //t1 = frg_data.subList(0,3);

            //Log.i("Splash","^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ " + String.valueOf(Fragment_Data.get(0).get(0)));

            if(mcategories.size()==0 || murl_categories.size()==0 || mItems1.size()==0 || mItems2.size()==0 || mItems3.size()==0 || mItems4.size()==0){
                disconnectedLayout.setVisibility(View.VISIBLE);
                connectedLayout.setVisibility(View.GONE);

                mReloadButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new Handler().postDelayed(new Runnable() {


                            @Override
                            public void run() {
                                // This method will be executed once the timer is over
                                // Start your app main activity


                                Intent i = new Intent(SplashScreen.this, SplashScreen.class);
                                startActivity(i);
                                // close this activity
                                finish();
                            }
                        }, 50);
                    }
                });

            }

            else {


                new Handler().postDelayed(new Runnable() {

            /*
              Showing splash screen with a timer. This will be useful when you
              want to show case your app logo / company
             */

                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        // Start your app main activity
                        Intent intent = MainActivity.newIntent(SplashScreen.this, mcategories, murl_categories, mItems1, mItems2, mItems3, mItems4, mHeadItems);

                        startActivity(intent);
                        // close this activity
                        finish();
                    }
                }, SPLASH_TIME_OUT);
                //Intent i = new Intent(SplashScreen.this, MainActivity.class);

            }


            //mcategories = category_list;

        }
    }

    //---------------------------------------------------------------------------------------------------------------

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void addShortcutIcon() {
        appPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        isAppInstalled = appPreferences.getBoolean("isAppInstalled", false);
        boolean shortcutReinstall = false;

        String currentLanguage = Locale.getDefault().getDisplayLanguage();
        String previousSetLanguage = appPreferences.getString("phoneLanguage", Locale.getDefault().getDisplayLanguage());

        if (!previousSetLanguage.equals(currentLanguage)) {
            shortcutReinstall = true;
        }

        if(!isAppInstalled || shortcutReinstall){

            Intent HomeScreenShortCut= new Intent(getApplicationContext(),
                    SplashScreen.class);

            HomeScreenShortCut.setAction(Intent.ACTION_MAIN);
            HomeScreenShortCut.putExtra("duplicate", false);

            if(shortcutReinstall) {
                Intent removeIntent = new Intent();
                removeIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, HomeScreenShortCut);
                String prevAppName = appPreferences.getString("appName", getString(R.string.app_name));
                removeIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, prevAppName);
                removeIntent.setAction("com.android.launcher.action.UNINSTALL_SHORTCUT");
                getApplicationContext().sendBroadcast(removeIntent);
            }

            Intent addIntent = new Intent();
            addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, HomeScreenShortCut);
            addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, getString(R.string.app_name));
            addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                    Intent.ShortcutIconResource.fromContext(getApplicationContext(),
                            R.mipmap.logoportokallishekulli));
            addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            getApplicationContext().sendBroadcast(addIntent);


            //Make preference true
            SharedPreferences.Editor editor = appPreferences.edit();
            editor.putBoolean("isAppInstalled", true);
            editor.putString("phoneLanguage", currentLanguage);
            editor.putString("appName", getString(R.string.app_name));
            editor.commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        appPreferences = getSharedPreferences("myprefs", Context.MODE_PRIVATE);
        isAppInstalled = appPreferences.getBoolean("isAppInstalled",false);
        Log.i("onCreate", "*************************************************************************************************************** " + String.valueOf(isAppInstalled));
        if(!isAppInstalled){
            addShortcutIcon();
        }

        Log.i("onCreate", "*************************************************************************************************************** ");


        connectedLayout = (RelativeLayout)findViewById(R.id.internet_splash);
        disconnectedLayout = (RelativeLayout)findViewById(R.id.no_internet_splash);
        mReloadButton = (Button)findViewById(R.id.spash_reload_button);
        spinner=(ProgressBar)findViewById(R.id.progressBar);

        if (isNetworkAvailable()){

            spinner.setVisibility(View.VISIBLE);
            //new FetchIsPaid().execute();
            new FetchItemsTask1().execute();
            new FetchItemsTask2().execute();
            new FetchItemsTask3().execute();
            new FetchItemsTask4().execute();
            new FetchHeaderTask().execute();

            new FetchCategoryTask().execute();
            new FetchUrlCategoryTask().execute();

        }
        else {

            disconnectedLayout.setVisibility(View.VISIBLE);
            connectedLayout.setVisibility(View.GONE);


            mReloadButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Handler().postDelayed(new Runnable() {


                        @Override
                        public void run() {
                            // This method will be executed once the timer is over
                            // Start your app main activity


                            Intent i = new Intent(SplashScreen.this, SplashScreen.class);
                            startActivity(i);
                            // close this activity
                            finish();
                        }
                    }, 50);
                }
            });
            /*
            new Handler().postDelayed(new Runnable() {


                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity


                    //Intent i = new Intent(SplashScreen.this, No_Net.class);
                    //startActivity(i);
                    // close this activity
                    finish();
                }
            }, 3000);*/

        }


        Log.i("onCreate", "*************************************************************************************************************** ");



    }

}
