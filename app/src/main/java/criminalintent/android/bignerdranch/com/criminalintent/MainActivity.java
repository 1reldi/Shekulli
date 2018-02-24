package criminalintent.android.bignerdranch.com.criminalintent;
//            Intent i=  PostActivity.newIntent(PollService.this, items.get(0).getMurl_post(), "Lajm i Fundit");


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.content.res.Configuration;
import android.view.MenuItem;
import com.onesignal.OSNotificationPayload;
import com.onesignal.NotificationExtenderService;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
    private ArrayList mcategories = new ArrayList();
    private ArrayList murl_categories = new ArrayList();
    private SwipeRefreshLayout mMainRefresh;
    private String mTempCat;
    private String Url_Search;
    private boolean closed = true;

    private ArrayList Data_Fragment1 = new ArrayList();
    private ArrayList Data_Fragment2 = new ArrayList();
    private ArrayList Data_Fragment3 = new ArrayList();
    private ArrayList Data_Fragment4 = new ArrayList();
    private ArrayList Data_Head = new ArrayList();

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
    public static final String TAG = "Category Activity Post Url: ";

    public static Intent newIntent(Context packageContext, ArrayList cat , ArrayList cat_url , ArrayList dt_fg1 , ArrayList dt_fg2 , ArrayList dt_fg3 , ArrayList dt_fg4, ArrayList headItems) {

        Intent intent = new Intent(packageContext, MainActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID, cat);
        intent.putExtra(EXTRA_CATEGORY_URL_ID, cat_url);
        intent.putExtra(EXTRA_FG_ID1, dt_fg1);
        intent.putExtra(EXTRA_FG_ID2, dt_fg2);
        intent.putExtra(EXTRA_FG_ID3, dt_fg3);
        intent.putExtra(EXTRA_FG_ID4, dt_fg4);
        intent.putExtra(EXTRA_HEAD, headItems);
        return intent;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) MainActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.fragment_activity_menu,menu);

        final MenuItem searchItem = menu.findItem(R.id.menu_item_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(Color.parseColor("#fe9a2e"));

        MenuItem toggleItem = menu.findItem(R.id.menu_item_toggle_polling);
        if(!QueryPreferences.areNofificationsOn(this)){
            toggleItem.setTitle("Stop Notifications"); // Stop Notifications
        } else {
            toggleItem.setTitle("Start Notifications"); // Start Notifications
        }
        //toggleItem.setIcon(R.drawable.abc_ic_action_name);


        if(searchView != null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    // pasi shtyp enter text ekzekutohet kjo
                    // bej dicka
                    //Toast.makeText(MainActivity.this,"fjala e vendosur: "+query,Toast.LENGTH_SHORT).show();
                    if (isNetworkAvailable()) {
                        Url_Search = "http://www.shekulli.com.al/api/search.php?keywords=" + query;
                        Intent intent = CategoryActivity.newIntent(MainActivity.this, Url_Search, mcategories, murl_categories, query, Data_Fragment1, Data_Fragment2, Data_Fragment3, Data_Fragment4);
                        startActivity(intent);
                    }
                    else {
                        //disconnectedLayout.setVisibility(View.VISIBLE);
                        //connectedLayout.setVisibility(View.GONE);
                        Intent i = new Intent(MainActivity.this, No_Net.class);
                        startActivity(i);
                    }
                    //pastro pamjen , hiq keyboard pas input
                    //searchView.setDrawingCacheBackgroundColor(Color.parseColor("#000000"));
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


 /*   private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    } */

    //---------------------------------------------------------------------------------------------------------------


    //AsyncTask creates a background thread for you and runs the code in the doInBackground(…) method on that thread.
    public class FetchCategoryTask extends AsyncTask<Void,Void,ArrayList> {
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
    public class FetchUrlCategoryTask extends AsyncTask<Void,Void,ArrayList> {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //new FetchCategoryTask().execute();
        //new FetchUrlCategoryTask().execute();

        Log.i("onCreate", "------------------------------------------------------------------------------------------------------------------------ ");

        setContentView(R.layout.home_dynamic);

       /* Intent i = new Intent(this, RegistrationService.class);
        startService(i); */


//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


        //check for this value. If it exists, assign it to mCurrentIndex.
//        if (savedInstanceState != null) {
//            mcategories = savedInstanceState.getStringArrayList(EXTRA_CATEGORY_ID);
//            murl_categories = savedInstanceState.getStringArrayList(EXTRA_CATEGORY_URL_ID);
//            Data_Fragment1 = savedInstanceState.getParcelableArrayList(EXTRA_FG_ID1);
//            Data_Fragment2 = savedInstanceState.getParcelableArrayList(EXTRA_FG_ID2);
//            Data_Fragment3 = savedInstanceState.getParcelableArrayList(EXTRA_FG_ID3);
//            Data_Fragment4 = savedInstanceState.getParcelableArrayList(EXTRA_FG_ID4);
//            Data_Head = savedInstanceState.getParcelableArrayList(EXTRA_HEAD);
//
//            Log.i("onCreate", "`123######################################################### " + String.valueOf(Data_Fragment1.size()));
//            Log.i("onCreate", "`123######################################################### " + String.valueOf(Data_Fragment2.size()));
//            Log.i("onCreate", "`123######################################################### " + String.valueOf(Data_Fragment3.size()));
//            Log.i("onCreate", "`123######################################################### " + String.valueOf(Data_Fragment4.size()));
//            Log.i("onCreateData_Head", "`123######################################################### " + String.valueOf(Data_Head.size()));
//        }
//        else {
            mcategories = (ArrayList) getIntent()
                    .getSerializableExtra(EXTRA_CATEGORY_ID);
            murl_categories = (ArrayList) getIntent()
                    .getSerializableExtra(EXTRA_CATEGORY_URL_ID);
            Data_Fragment1 = (ArrayList) getIntent()
                    .getSerializableExtra(EXTRA_FG_ID1);
            Data_Fragment2 = (ArrayList) getIntent()
                    .getSerializableExtra(EXTRA_FG_ID2);
            Data_Fragment3 = (ArrayList) getIntent()
                    .getSerializableExtra(EXTRA_FG_ID3);
            Data_Fragment4 = (ArrayList) getIntent()
                    .getSerializableExtra(EXTRA_FG_ID4);
            Data_Head = (ArrayList) getIntent()
                    .getSerializableExtra(EXTRA_HEAD);

            Log.i("onCreate", "######################################################### " + String.valueOf(Data_Fragment1.size()));
            Log.i("onCreate", "######################################################### " + String.valueOf(Data_Fragment2.size()));
            Log.i("onCreate", "######################################################### " + String.valueOf(Data_Fragment3.size()));
            Log.i("onCreate", "######################################################### " + String.valueOf(Data_Fragment4.size()));
            Log.i("onCreateData_Head", "######################################################### " + String.valueOf(Data_Head.size()));

        //}





//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        ActionBar action = getSupportActionBar();




        action.setDisplayHomeAsUpEnabled(true);
        action.setHomeButtonEnabled(true);
        action.setDisplayShowHomeEnabled(true);

        action.setTitle("");
        action.setIcon(R.mipmap.logo);

        action.setBackgroundDrawable(new ColorDrawable(0xFFFFFFFF));



        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
                //Toast.makeText(MainActivity.this, String.valueOf(mcategories.get(position)), Toast.LENGTH_SHORT).show();
                if (position==0){
                    Toast.makeText(MainActivity.this, "Aty jemi ;)", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (isNetworkAvailable()) {
                        Intent intent = CategoryActivity.newIntent(MainActivity.this, String.valueOf(murl_categories.get(position)), mcategories, murl_categories, String.valueOf(mcategories.get(position)), Data_Fragment1, Data_Fragment2, Data_Fragment3, Data_Fragment4);
                        startActivity(intent);
                    }
                    else {
                        //disconnectedLayout.setVisibility(View.VISIBLE);
                        //connectedLayout.setVisibility(View.GONE);
                        Intent i = new Intent(MainActivity.this, No_Net.class);
                        startActivity(i);
                    }
                }
            }
        });



        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.home_view_pager);
        final PageAdapter viewPagerAdapter = new PageAdapter(getSupportFragmentManager()
                ,tabLayout.getTabCount(), Data_Fragment1 , Data_Fragment2 , Data_Fragment3 , Data_Fragment4, mcategories, murl_categories, Data_Head);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setOffscreenPageLimit(4);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //sipas pozicionit bej dicka te ndryshme ne adapter (PageAdapter)
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        //      FragmentActivity1 frg1=new FragmentActivity1();//create the fragment instance for the top fragment
        //head_fragment frg=new head_fragment();//create the fragment instance for the middle fragment
        //VideoViewActivity1 frg2=new VideoViewActivity1();//create the fragment instance for the bottom fragment

        //       FragmentManager manager=getSupportFragmentManager();//create an instance of fragment manager

        //     FragmentTransaction transaction=manager.beginTransaction();//create an instance of Fragment-transaction

        //transaction.add(R.id.My_Container_1_ID, frg, "Frag_Top_tag");
        //      transaction.add(R.id.My_Container_2_ID, frg1, "Frag_Middle_tag");
        //transaction.add(R.id.My_Container_3_ID, frg2, "Frag_Bottom_tag");


        //      transaction.commit();


    }





    /*You can override onSaveInstanceState(…) to save additional data to the bundle and then read that
 data back in onCreate(…). This is how you are going to save the value of mCurrentIndex across
 rotation.*/
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
//        super.onSaveInstanceState(savedInstanceState);
//        Log.i("g", "onSaveInstanceState");
//        savedInstanceState.putStringArrayList(EXTRA_CATEGORY_ID, mcategories);  //override onSaveInstanceState(…) to write the value of mCurrentIndex to the bundle with the constant as its key
//        savedInstanceState.putStringArrayList(EXTRA_CATEGORY_URL_ID, murl_categories);
//        savedInstanceState.putParcelableArrayList(EXTRA_FG_ID1, Data_Fragment1);
//        savedInstanceState.putParcelableArrayList(EXTRA_FG_ID2, Data_Fragment2);
//        savedInstanceState.putParcelableArrayList(EXTRA_FG_ID3, Data_Fragment3);
//        savedInstanceState.putParcelableArrayList(EXTRA_FG_ID4, Data_Fragment4);
//        savedInstanceState.putParcelableArrayList(EXTRA_HEAD, Data_Head);
    }






    private void addDrawerItems() {

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
                if (mcategories.size()==1 || murl_categories.size()==1){
                    mcategories.clear();
                    murl_categories.clear();
                    new FetchCategoryTask().execute();
                    new FetchUrlCategoryTask().execute();
                }
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
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

        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            //Toast.makeText(this, "drawer", Toast.LENGTH_SHORT).show();
            return true;
        }

        switch (item.getItemId()) {
            case R.id.menu_item_toggle_polling:
                boolean notifications = !QueryPreferences.areNofificationsOn(this);
                QueryPreferences.setNotificationsOn(this, notifications);
                this.invalidateOptionsMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}