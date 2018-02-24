package criminalintent.android.bignerdranch.com.criminalintent;

/**
 * Created by GERMAN on 21-Nov-16.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class No_Internet extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) { //Fragment.onCreate(Bundle) is a public method whereas Activity.onCreate(Bundle) is protected
        super.onCreate(savedInstanceState);  //Fragment.onCreate(…) and other Fragment lifecycle methods must be public because they will be called by whatever activity is hosting the fragment.
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) { //This method is where you inflate the layout for the fragment’s view and return the inflated View to the hosting activity.
        View v = inflater.inflate(R.layout.no_internet, container, false);

        return v;
    }
}
