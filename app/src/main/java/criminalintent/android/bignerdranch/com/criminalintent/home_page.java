package criminalintent.android.bignerdranch.com.criminalintent;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.lang.String;

/**
 * Created by GERMAN on 12-Oct-16.
 */

public class home_page implements Serializable {

    //private int mId;
    private String mTitle;
    private String mCat_Title;
    private String mUrl;
    private String mDate;
    private String  mNo_comments;
    private String mPhotoUrl;
    private String SuggUrl;
    private ArrayList mcategories;

    public home_page() {

    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
        //mDate = mDate.subSequence(0,14).toString();
        Log.i("ggg", date);
    }

    public String getNo_comments() {
        return mNo_comments;
    }

    public void setNo_comments(int no_comments) {
        mNo_comments = "Comments: " + String.valueOf(no_comments);
    }
    public String getPhotoUrl() {
        return mPhotoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        mPhotoUrl = photoUrl;
    }

    public ArrayList getMcategories() {
        return mcategories;
    }

    public void setMcategories(ArrayList mcategories) {
        this.mcategories = mcategories;
    }

    public String getCat_Title() {
        //mCat_Title="Shekulli";
        return mCat_Title.substring(0,1).toUpperCase() + mCat_Title.substring(1).toLowerCase();
    }

    public void setCat_Title(String cat_Title) {
        mCat_Title = cat_Title;
    }

    public String getSuggUrl() {
        return SuggUrl;
    }

    public void setSuggUrl(String suggUrl) {
        SuggUrl = suggUrl;
    }


}
