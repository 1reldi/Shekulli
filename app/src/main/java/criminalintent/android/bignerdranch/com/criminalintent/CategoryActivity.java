package criminalintent.android.bignerdranch.com.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

import static criminalintent.android.bignerdranch.com.criminalintent.R.mipmap.logoportokallishekulli;

public class CategoryActivity extends AppCompatActivity {

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle = "Ska ardh gje";
    private String mcategory_url;
    private String mTempCat;
    private String Url_Search;


    private ArrayList mcategories = new ArrayList();
    private ArrayList murl_categories = new ArrayList();

    private static final String EXTRA_POST_ID =
            "com.bignerdranch.android.criminalintent.post_id";
    private static final String EXTRA_CATEGORY_ID =
            "com.bignerdranch.android.criminalintent.category_id";
    private static final String EXTRA_CATEGORY_URL_ID =
            "com.bignerdranch.android.criminalintent.category_url_id";
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
    public static final String TAG = "Category Activity Post Url: ";


    private ArrayList Data_Fragment1 = new ArrayList();
    private ArrayList Data_Fragment2 = new ArrayList();
    private ArrayList Data_Fragment3 = new ArrayList();
    private ArrayList Data_Fragment4 = new ArrayList();

    Bundle bundle1 = new Bundle();

    public static Intent newIntent(Context packageContext, String url ,ArrayList cat ,ArrayList cat_url ,String name ,ArrayList dt_fg1 ,ArrayList dt_fg2 ,ArrayList dt_fg3 ,ArrayList dt_fg4) {

        Intent intent = new Intent(packageContext, CategoryActivity.class);
        intent.putExtra(EXTRA_POST_ID, url);
        intent.putExtra(EXTRA_CATEGORY_ID, cat);
        intent.putExtra(EXTRA_CATEGORY_URL_ID, cat_url);
        intent.putExtra(EXTRA_NAME_ID, name);
        intent.putExtra(EXTRA_FG_ID1, dt_fg1);
        intent.putExtra(EXTRA_FG_ID2, dt_fg2);
        intent.putExtra(EXTRA_FG_ID3, dt_fg3);
        intent.putExtra(EXTRA_FG_ID4, dt_fg4);
        return intent;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) CategoryActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



    //---------------------------------------------------------------------------------------------------------------




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.fragment_activity_menu,menu);

        final MenuItem searchItem = menu.findItem(R.id.menu_item_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(Color.parseColor("#fe9a2e"));

        MenuItem toggleItem = menu.findItem(R.id.menu_item_toggle_polling);
        //toggleItem.setIcon(R.drawable.abc_ic_action_name);
        if(!QueryPreferences.areNofificationsOn(this)){
            toggleItem.setTitle("Stop Notifications"); // Stop Notifications
        } else {
            toggleItem.setTitle("Start Notifications"); // Start Notifications
        }

        if(searchView != null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    // pasi shtyp enter text ekzekutohet kjo
                    // bej dicka
                    //Toast.makeText(MainActivity.this,"fjala e vendosur: "+query,Toast.LENGTH_SHORT).show();
                    if (isNetworkAvailable()) {
                        Url_Search = "http://www.shekulli.com.al/api/search.php?keywords=" + query;
                        Intent intent = CategoryActivity.newIntent(CategoryActivity.this, Url_Search, mcategories, murl_categories, query, Data_Fragment1, Data_Fragment2, Data_Fragment3, Data_Fragment4);
                        startActivity(intent);
                    }
                    else {
                        //disconnectedLayout.setVisibility(View.VISIBLE);
                        //connectedLayout.setVisibility(View.GONE);
                        Intent i = new Intent(CategoryActivity.this, No_Net.class);
                        startActivity(i);
                    }
                    //pastro pamjen , hiq keyboard pas input
                    searchView.setQuery("",false);
                    searchView.clearFocus();
                    searchView.setIconified(true);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }
        return true;
    }






    //AsyncTask creates a background thread for you and runs the code in the doInBackground(…) method on that thread.
    private class FetchCategoryTask1 extends AsyncTask<Void,Void,ArrayList> {
        @Override
        //It sets the type of value returned by doInBackground(…) as well as the type of onPostExecute(…)’s input parameter.
        protected ArrayList doInBackground(Void... params) { //get data from a website and log it.

            Log.i("Result", "Received title: " + "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
            return new criminalintent.android.bignerdranch.com.criminalintent.FlickrFetchr().fetchCategories();

        }


        @Override
        protected void onPostExecute(ArrayList category_list) {

            if (category_list.size()>0) {
                for (int i = 0; i < category_list.size(); i++) {

                    mcategories.add(category_list.get(i));
                    Log.i("onPostExecute", "uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu " + mcategories.get(i));
                }
            }
            else {
                Log.i("onPostExecute", "uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu" + " nothing");
            }


            //mcategories = category_list;

        }
    }

    //---------------------------------------------------------------------------------------------------------------


    //---------------------------------------------------------------------------------------------------------------


    //AsyncTask creates a background thread for you and runs the code in the doInBackground(…) method on that thread.
    private class FetchUrlCategoryTask1 extends AsyncTask<Void,Void,ArrayList> {
        @Override
        //It sets the type of value returned by doInBackground(…) as well as the type of onPostExecute(…)’s input parameter.
        protected ArrayList doInBackground(Void... params) { //get data from a website and log it.

            Log.i("Result", "Received title: " + "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
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
                Log.i("onPostExecute", "uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu" + " nothing");
            }


            //mcategories = category_list;

        }
    }

    //---------------------------------------------------------------------------------------------------------------

    //---------------------------------------------------------------------------------------------------------------

    //---------------------------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //new FetchCategoryTask1().execute();
        //new FetchUrlCategoryTask1().execute();


        Log.i("onCreate", "------------------------------------------------------------------------------------------------------------------------ ");
        setContentView(R.layout.activity_category);




//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


//        //check for this value. If it exists, assign it to mCurrentIndex.
//        if (savedInstanceState != null) {
//            Data_Fragment1 = savedInstanceState.getParcelableArrayList(EXTRA_FG_ID1);
//            Data_Fragment2 = savedInstanceState.getParcelableArrayList(EXTRA_FG_ID2);
//            Data_Fragment3 = savedInstanceState.getParcelableArrayList(EXTRA_FG_ID3);
//            Data_Fragment4 = savedInstanceState.getParcelableArrayList(EXTRA_FG_ID4);
//            //mcategories = savedInstanceState.getParcelableArrayList(EXTRA_CATEGORY_ID);
//            //murl_categories = savedInstanceState.getParcelableArrayList(EXTRA_CATEGORY_URL_ID);
//
//            bundle1.putParcelableArrayList(EXTRA_FG_ID1, Data_Fragment1);
//            bundle1.putParcelableArrayList(EXTRA_FG_ID2, Data_Fragment2);
//            bundle1.putParcelableArrayList(EXTRA_FG_ID3, Data_Fragment3);
//            bundle1.putParcelableArrayList(EXTRA_FG_ID4, Data_Fragment4);
//            //bundle1.putParcelableArrayList(EXTRA_CATEGORY_ID, mcategories);
//            //bundle1.putParcelableArrayList(EXTRA_CATEGORY_URL_ID, murl_categories);
//
//            Log.i("onCreate", "`123######################################################### " + String.valueOf(Data_Fragment1.size()));
//            Log.i("onCreate", "`123######################################################### " + String.valueOf(Data_Fragment2.size()));
//            Log.i("onCreate", "`123######################################################### " + String.valueOf(Data_Fragment3.size()));
//            Log.i("onCreate", "`123######################################################### " + String.valueOf(Data_Fragment4.size()));
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
            //mcategories = (ArrayList) getIntent()
            //        .getSerializableExtra(EXTRA_CATEGORY_ID);
            //murl_categories = (ArrayList) getIntent()
            //       .getSerializableExtra(EXTRA_CATEGORY_URL_ID);

            bundle1.putParcelableArrayList(EXTRA_FG_ID1, Data_Fragment1);
            bundle1.putParcelableArrayList(EXTRA_FG_ID2, Data_Fragment2);
            bundle1.putParcelableArrayList(EXTRA_FG_ID3, Data_Fragment3);
            bundle1.putParcelableArrayList(EXTRA_FG_ID4, Data_Fragment4);
            //bundle1.putParcelableArrayList(EXTRA_CATEGORY_ID, mcategories);
            //bundle1.putParcelableArrayList(EXTRA_CATEGORY_URL_ID, murl_categories);
            Log.i("onCreate", "######################################################### " + String.valueOf(Data_Fragment1.size()));
            Log.i("onCreate", "######################################################### " + String.valueOf(Data_Fragment2.size()));
            Log.i("onCreate", "######################################################### " + String.valueOf(Data_Fragment3.size()));
            Log.i("onCreate", "######################################################### " + String.valueOf(Data_Fragment4.size()));
      //  }

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++











        mcategory_url = (String) getIntent()
                .getSerializableExtra(EXTRA_POST_ID);
        Log.i(TAG, "Received CategoryURL: " + mcategory_url);

        mActivityTitle = (String) getIntent()
                .getSerializableExtra(EXTRA_NAME_ID);
        Log.i(TAG, "Received NameCategory: " + mActivityTitle);

        ArrayList cat2 = (ArrayList) getIntent()
                .getSerializableExtra(EXTRA_CATEGORY_ID);
        if (cat2.size()>0) {
            for (int i = 0; i < cat2.size(); i++) {
                Log.i("onCreate", "uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu " + cat2.get(i));
                mcategories.add(cat2.get(i));
            }
        }
        else {
            Log.i("onCreate", "uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu" + " nothing");
        }
        bundle1.putParcelableArrayList(EXTRA_CATEGORY_ID, mcategories);

        ArrayList cat3 = (ArrayList) getIntent()
                .getSerializableExtra(EXTRA_CATEGORY_URL_ID);
        if (cat3.size()>0) {
            for (int i = 0; i < cat3.size(); i++) {
                Log.i("onCreate", "uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu " + cat3.get(i));
                murl_categories.add(cat3.get(i));
            }
        }
        else {
            Log.i("onCreate", "uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu" + " nothing");
        }
        bundle1.putParcelableArrayList(EXTRA_CATEGORY_URL_ID, murl_categories);





        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);


        addDrawerItems();
        setupDrawer();

        ActionBar action = getSupportActionBar();

        //action.setTitle(mActivityTitle);Html.fromHtml("<font color='#ff0000'>ActionBarTitle </font>")
        action.setTitle(Html.fromHtml("<font color='#fe9a2e'>" + mActivityTitle + "</font>"));

        action.setBackgroundDrawable(new ColorDrawable(0xFFFFFFFF));

        //abTitle.setTextColor(0xFFFFFFFF);

        action.setDisplayHomeAsUpEnabled(true);
        action.setHomeButtonEnabled(true);


        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(CategoryActivity.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
                //Toast.makeText(MainActivity.this, String.valueOf(mcategories.get(position)), Toast.LENGTH_SHORT).show();
                if (position==0){
                    ///
                    //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    if (isNetworkAvailable()) {
                        Intent intent = MainActivity.newIntent(CategoryActivity.this, mcategories, murl_categories, Data_Fragment1, Data_Fragment2, Data_Fragment3, Data_Fragment4, Data_Fragment4);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                    else {
                        //disconnectedLayout.setVisibility(View.VISIBLE);
                        //connectedLayout.setVisibility(View.GONE);
                        Intent i = new Intent(CategoryActivity.this, No_Net.class);
                        startActivity(i);
                    }

                }
                else {
                    if (isNetworkAvailable()) {
                        Log.i("onCreate", "cccccccccccccccccccccccccccccccccccccccccc " + murl_categories.get(position));
                        Intent intent = CategoryActivity.newIntent(CategoryActivity.this, String.valueOf(murl_categories.get(position)), mcategories, murl_categories, String.valueOf(mcategories.get(position)), Data_Fragment1, Data_Fragment2, Data_Fragment3, Data_Fragment4);
                        startActivity(intent);
                    }
                    else {
                        //disconnectedLayout.setVisibility(View.VISIBLE);
                        //connectedLayout.setVisibility(View.GONE);
                        Intent i = new Intent(CategoryActivity.this, No_Net.class);
                        startActivity(i);
                    }
                }
            }
        });

        FragmentManager fm = getSupportFragmentManager();

        Fragment fragment = fm.findFragmentById(R.id.category_activity);//ask the FragmentManager for the fragment with a container view ID of R.id.fragment_container
        if (fragment == null) {
            fragment = new CategoryFragment().newInstance(mcategory_url, bundle1);
            fm.beginTransaction() //Create a new fragment transaction, include one add operation in it, and then commit it
                    .add(R.id.My_Container_3_ID, fragment) //This code creates and commits a fragment  transaction.
                    .commit();                              //Fragment transactions are used to add, remove, attach, detach, or replace fragments in the fragment list.
        }
    }

    /*You can override onSaveInstanceState(…) to save additional data to the bundle and then read that
 data back in onCreate(…). This is how you are going to save the value of mCurrentIndex across
 rotation.*/
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
//        super.onSaveInstanceState(savedInstanceState);
//        Log.i("g", "onSaveInstanceState");
//        savedInstanceState.putParcelableArrayList(EXTRA_FG_ID1, Data_Fragment1);
//        savedInstanceState.putParcelableArrayList(EXTRA_FG_ID2, Data_Fragment2);
//        savedInstanceState.putParcelableArrayList(EXTRA_FG_ID3, Data_Fragment3);
//        savedInstanceState.putParcelableArrayList(EXTRA_FG_ID4, Data_Fragment4);
    }


    private void addDrawerItems() {



        if (mcategories.size()>0) {
            for (int i = 0; i < mcategories.size(); i++) {
                Log.i("addDrawerItems", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv" + mcategories.get(i));
            }
        }
        else {
            Log.i("addDrawerItems", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv" + " nothing");
        }


        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mcategories);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, String.valueOf(mcategories.get(position)), Toast.LENGTH_SHORT).show();
                //Log.i("onPostExecute", "ooooooooooooooooooooooooooooooooooooooo " + String.valueOf(mcategories.get(position)));
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(Html.fromHtml("<font color='#fe9a2e'>" + mActivityTitle + "</font>"));
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(Html.fromHtml("<font color='#fe9a2e'>" + mActivityTitle + "</font>"));
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }



    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/
        switch (item.getItemId()) {
            case R.id.menu_item_toggle_polling:
                boolean notifications = !QueryPreferences.areNofificationsOn(this);
                QueryPreferences.setNotificationsOn(this, notifications);
                this.invalidateOptionsMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

        // Activate the navigation drawer toggle
    }
}


