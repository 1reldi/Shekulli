package criminalintent.android.bignerdranch.com.criminalintent;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GERMAN on 01-Nov-16.
 */

public class post_page implements Serializable {

    private static final String TAG = "Post_Page";

    private String mTitle;
    private String mDate;
    private String mContent;
    private String mAuthor;
    private String mPhoto_Main_Url;
    private String mCategory;
    private String mCategory_Url;
    private ArrayList mPhotos = new ArrayList();
    private String mVideo;
    private List<Comment_Stucture> mCommentsAll = new ArrayList<>();

    private int suug = 0;


    public post_page() {

    }

    public class Comment_Stucture {

        private String CommentName;
        private String CommentDate;
        private String CommentContent;

        public String getCommentName() {
            return CommentName;
        }

        public void setCommentName(String commentName) {
            CommentName = commentName;
        }

        public String getCommentDate() {
            return CommentDate;
        }

        public void setCommentDate(String commentDate) {
            CommentDate = commentDate;
        }

        public String getCommentContent() {
            return CommentContent;
        }

        public void setCommentContent(String commentContent) {
            CommentContent = commentContent;
        }

    }




    public void setCommentsAll(List<Comment_Stucture> commentsAll) {
        mCommentsAll = commentsAll;
    }

    public List<Comment_Stucture> getComments() {
        return mCommentsAll;
    }


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDate() {
        return "- Publikuar me: " + mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
        Log.i(TAG, mAuthor);
    }

    public String getPhoto_Main_Url() {
        return mPhoto_Main_Url;
    }

    public void setPhoto_Main_Url(String photo_Main_Url) {
        mPhoto_Main_Url = photo_Main_Url;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    public ArrayList getPhotos() {
        return mPhotos;
    }

    public void setPhotos(ArrayList photos) {
        mPhotos = photos;
    }

    public String getVideo() {
        return mVideo;
    }

    public void setVideo(String video) {
        mVideo = video;
    }

    public int getSuug() {
        return suug;
    }

    public void setSuug(int suug) {
        this.suug = suug;
    }


    public String getCategory_Url() {
        return mCategory_Url;
    }

    public void setCategory_Url(String category_Url) {
        mCategory_Url = category_Url;
    }



}
