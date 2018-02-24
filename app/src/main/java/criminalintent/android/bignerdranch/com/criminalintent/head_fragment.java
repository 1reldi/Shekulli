package criminalintent.android.bignerdranch.com.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by GERMAN on 05-Nov-16.
 */

public class head_fragment extends Fragment {

    View view;

    TextView display_text;
    ImageView mainImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        view=inflater.inflate(R.layout.home_header, container,false);
        //display_text=(TextView) view.findViewById(R.id.editText1);

        mainImage = (ImageView)
                view.findViewById(R.id.home_header_photo);

        display_text = (TextView)
                view.findViewById(R.id.home_header_textView_Title);

        Picasso.with(getActivity())
                .load("http://www.shekulli.com.al/admin/wp-content/uploads/2016/11/Koco-Kokedhima.jpg")
                .resize(440, 250)
                .into(mainImage);
        display_text.setText("Kokëdhima: Disa diplomatë, rol të pazakontë dhe jashtë Konventës së Vjenës");

        return view;
    }




}
