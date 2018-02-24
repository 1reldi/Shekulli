package criminalintent.android.bignerdranch.com.criminalintent;

/**
 * Created by GERMAN on 01-Nov-16.
 */

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
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


import java.util.UUID;

import static criminalintent.android.bignerdranch.com.criminalintent.R.string.app_name;


public class PostActivityNotification extends AppCompatActivity {



    private Menu menu;
    private String category_title;

    private static final String EXTRA_POST_ID =
            "com.bignerdranch.android.criminalintent.post_id";
    private static final String EXTRA_POST_CAT_ID =
            "com.bignerdranch.android.criminalintent.post_cat_id";
    public static final String TAG = "Activity Post Url: ";

    public static Intent newIntent(Context packageContext, String url, String cat_title) {
        Intent intent = new Intent(packageContext, PostActivityNotification.class);
        intent.putExtra(EXTRA_POST_ID, url);
        intent.putExtra(EXTRA_POST_CAT_ID, cat_title);
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


        FragmentManager fm = getSupportFragmentManager(); //The FragmentManager is responsible for managing your fragments and adding their views to the activity’s view hierarchy

        String post_url = (String) getIntent()
                .getSerializableExtra(EXTRA_POST_ID);
        Log.i(TAG, "Received URL: " + post_url);

        category_title = (String) getIntent()
                .getSerializableExtra(EXTRA_POST_CAT_ID);
        Log.i(TAG, "Received URL: " + post_url);

        Bundle bundle1 = new Bundle();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);//ask the FragmentManager for the fragment with a container view ID of R.id.fragment_container
        if (fragment == null) {
            fragment = new PostFragment().newInstance(post_url, "http://www.shekulli.com.al/api/efundit.php", category_title, post_url, bundle1);
            fm.beginTransaction() //Create a new fragment transaction, include one add operation in it, and then commit it
                    .add(R.id.fragment_container, fragment) //This code creates and commits a fragment  transaction.
                    .commit();                              //Fragment transactions are used to add, remove, attach, detach, or replace fragments in the fragment list.
        }

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
                new Handler().postDelayed(new Runnable() {


                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        // Start your app main activity


                        Intent i = new Intent(PostActivityNotification.this, SplashScreen.class);
                        startActivity(i);
                        // close this activity
                        finish();
                    }
                }, 10);
                return(true);
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