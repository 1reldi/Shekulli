package criminalintent.android.bignerdranch.com.criminalintent;

/**
 * Created by reldi on 17-07-16.
 */

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

public class FragmentSliderPager extends android.support.v4.app.Fragment implements View.OnClickListener {

    String url_basic="";
    private ArrayList mItemsT1 = new ArrayList<>();
    private ArrayList mItemsT2 = new ArrayList<>();
    private ArrayList mItemsT3 = new ArrayList<>();
    private ArrayList mItemsT4 = new ArrayList<>();
    private ArrayList mcategories = new ArrayList();
    private ArrayList murl_categories = new ArrayList();
    private ArrayList headItems = new ArrayList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_header, container, false);

        TextView display_text = (TextView) v.findViewById(R.id.home_header_textView_Title);
        display_text.setText(getArguments().getString("text"));

        ImageView mainImage = (ImageView) v.findViewById(R.id.home_header_photo);
        Picasso.with(getActivity())
                    .load(getArguments().getString("img"))
                    .fit()
                    .centerCrop()
                    .into(mainImage);

        v.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                url_basic = getArguments().getString("url");
                mItemsT1 = getArguments().getParcelableArrayList("mItemsT1");
                mItemsT2 = getArguments().getParcelableArrayList("mItemsT2");
                mItemsT3 = getArguments().getParcelableArrayList("mItemsT3");
                mItemsT4 = getArguments().getParcelableArrayList("mItemsT4");
                mcategories = getArguments().getParcelableArrayList("mcategories");
                murl_categories = getArguments().getParcelableArrayList("murl_categories");

                new FetchPostBasic().execute();

            }
        });
        //mainImage.setBackgroundResource(getArguments().getInt("img"));

        return v;
    }

    @Override
    public void onClick(View v) {
        // implements your things
        Log.i("ggg", "1===================================================: " + String.valueOf(mItemsT1.size()));
        new FetchPostBasic().execute();
    }

    private class FetchPostBasic extends AsyncTask<Void,Void,List<String>> {
        @Override
        //It sets the type of value returned by doInBackground(…) as well as the type of onPostExecute(…)’s input parameter.
        protected List<String> doInBackground(Void... params) { //get data from a website and log it.
            return new criminalintent.android.bignerdranch.com.criminalintent.PostFetchr().fetchBasics(url_basic);

        }

        @Override
        protected void onPostExecute(List<String> items) {
            Log.i("ggg", "1===================================================: " + String.valueOf(mItemsT1.size()));
            Log.i("ggg", "1===================================================: " + String.valueOf(mItemsT2.size()));
            Log.i("ggg", "1===================================================: " + String.valueOf(mItemsT3.size()));
            Log.i("ggg", "1===================================================: " + String.valueOf(mItemsT4.size()));
            Log.i("ggg", "1===================================================: " + String.valueOf(mcategories.size()));
            Log.i("ggg", "1===================================================: " + String.valueOf(murl_categories.size()));
            Intent intent = PostActivity.newIntent(getActivity(), url_basic ,items.get(0),items.get(1), items.get(2), mItemsT1, mItemsT2, mItemsT3, mItemsT4, mcategories, murl_categories);
            startActivity(intent);
        }
    }

    public static FragmentSliderPager newInstance(String text, String image, String url, ArrayList arr1, ArrayList arr2, ArrayList arr3, ArrayList arr4, ArrayList arr5, ArrayList arr6) {

        FragmentSliderPager f = new FragmentSliderPager();
        Bundle b = new Bundle();
        b.putString("text", text);
        b.putString("img", image);
        b.putString("url", url);
        b.putParcelableArrayList("mItemsT1", arr1);
        b.putParcelableArrayList("mItemsT2", arr2);
        b.putParcelableArrayList("mItemsT3", arr3);
        b.putParcelableArrayList("mItemsT4", arr4);
        b.putParcelableArrayList("mcategories", arr5);
        b.putParcelableArrayList("murl_categories", arr6);


        f.setArguments(b);

        return f;
    }


}