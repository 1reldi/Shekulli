package criminalintent.android.bignerdranch.com.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by User on 11/14/2016.
 */

public class PageAdapter extends FragmentStatePagerAdapter {

    private int mNumOfTabs;
    private boolean mis_internet;
    private ArrayList frg_data1 = new ArrayList();
    private ArrayList frg_data2 = new ArrayList();
    private ArrayList frg_data3 = new ArrayList();
    private ArrayList frg_data4 = new ArrayList();
    private ArrayList mcategories = new ArrayList();
    private ArrayList murl_categories = new ArrayList();
    private ArrayList data_Head = new ArrayList();

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


    public PageAdapter(FragmentManager fm, int NumOfTabs ,boolean internet) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        mis_internet = internet;
    }

    public PageAdapter(FragmentManager fm, int NumOfTabs ,ArrayList datas1 ,ArrayList datas2 ,ArrayList datas3 ,ArrayList datas4 ,ArrayList categories ,ArrayList url_categories, ArrayList headItems) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.frg_data1 = datas1;
        this.frg_data2 = datas2;
        this.frg_data3 = datas3;
        this.frg_data4 = datas4;
        this.mcategories = categories;
        this.murl_categories = url_categories;
        this.data_Head = headItems;
        Log.i("ggg", "????????????????????????????????????????????????: " + String.valueOf(frg_data1.size()));
        Log.i("ggg", "????????????????????????????????????????????????: " + String.valueOf(frg_data2.size()));
        Log.i("ggg", "????????????????????????????????????????????????: " + String.valueOf(frg_data3.size()));
        Log.i("ggg", "????????????????????????????????????????????????: " + String.valueOf(frg_data4.size()));
        Log.i("ggg", "????????????????????????????????????????????????: " + String.valueOf(data_Head.size()));
    }



    @Override
    public Fragment getItem(int position) {

        Log.i("ggg", "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq: " + String.valueOf(position));

        Bundle bundle1 = new Bundle();
        bundle1.putParcelableArrayList(EXTRA_FG_ID1, frg_data1);
        bundle1.putParcelableArrayList(EXTRA_FG_ID2, frg_data2);
        bundle1.putParcelableArrayList(EXTRA_FG_ID3, frg_data3);
        bundle1.putParcelableArrayList(EXTRA_FG_ID4, frg_data4);
        bundle1.putParcelableArrayList(EXTRA_CATEGORY_ID, mcategories);
        bundle1.putParcelableArrayList(EXTRA_CATEGORY_URL_ID, murl_categories);
        bundle1.putParcelableArrayList(EXTRA_HEAD, data_Head);


        switch (position) {
            case 0:

                //fragment.setArguments(bundle);
                FragmentActivity1 frg1 = new FragmentActivity1();
                frg1.setArguments(bundle1);
                return frg1;
            case 1:
                //fragment.setArguments(bundle);
                FragmentActivity2 frg2 = new FragmentActivity2();
                frg2.setArguments(bundle1);
                return frg2;
            case 2:
                //fragment.setArguments(bundle);
                FragmentActivity3 frg3 = new FragmentActivity3();
                frg3.setArguments(bundle1);
                return frg3;
            case 3:
                //fragment.setArguments(bundle);
                FragmentActivity4 frg4 = new FragmentActivity4();
                frg4.setArguments(bundle1);
                return frg4;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
