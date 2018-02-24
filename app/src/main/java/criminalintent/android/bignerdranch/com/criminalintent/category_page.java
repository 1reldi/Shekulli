package criminalintent.android.bignerdranch.com.criminalintent;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by GERMAN on 24-Nov-16.
 */

public class category_page implements Serializable {

    private String murl_post;
    private String mtitle_post;
    private String mdate_post;
    private String mauthor_post;
    private String mno_comments_post;
    private String mphoto_url_post;
    private String mhour_post;
    private String mCat_Name;

    public category_page(){

    }

    public String getMurl_post() {
        return murl_post;
    }

    public void setMurl_post(String murl_post) {
        this.murl_post = murl_post;
    }

    public String getMtitle_post() {
        return mtitle_post;
    }

    public void setMtitle_post(String mtitle_post) {
        this.mtitle_post = mtitle_post;
    }

    public String getMdate_post() {
        return mdate_post;
    }

    public void setMdate_post(String date_post) {

        mhour_post = date_post.substring(16);
        mdate_post = date_post;
        Log.i("oooooooooooo", mdate_post);
    }

    public String getMauthor_post() {
        return mauthor_post;
    }

    public void setMauthor_post(String mauthor_post) {
        this.mauthor_post = mauthor_post;
    }

    public String getMphoto_url_post() {
        return mphoto_url_post;
    }

    public void setMphoto_url_post(String mphoto_url_post) {
        this.mphoto_url_post = mphoto_url_post;
    }

    public String getMno_comments_post() {
        return mno_comments_post;
    }

    public void setMno_comments_post(int mno_comments_post) {
        this.mno_comments_post = "Comments: " + String.valueOf(mno_comments_post);
    }

    public String getMhour_post() {
        return mhour_post;
    }


    public String getCat_Name() {
        return mCat_Name.substring(0,1).toUpperCase() + mCat_Name.substring(1).toLowerCase();
    }

    public void setCat_Name(String cat_Name) {
        mCat_Name = cat_Name;
    }


}
