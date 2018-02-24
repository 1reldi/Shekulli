package criminalintent.android.bignerdranch.com.criminalintent;

/**
 * Created by GERMAN on 27-Oct-16.
 */

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class FlickrFetchr {
    private static final String TAG = "FlickrFetchr";
    private static final String API_KEY = "a13c577fca1376fbefcbc80327a6a127";
    ArrayList local_categories = new ArrayList();
    ArrayList categories_go = new ArrayList();
    private String temp_cat;

    //method fetches raw data from a URL and returns it as an array of bytes.
    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);//This code creates a URL object from a string
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();//create a connection object pointed at the URL ,cast it to HttpURLConnection
        try {                                                                   // This gives you HTTP-specific interfaces for working with request methods, response codes, streaming methods, and more.
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream(); //connect to your endpoint
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() +
                        ": with " +
                        urlSpec);
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    //method converts the result from getUrlBytes(String) to a String.
    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    //-------------------------------------------------------------------------------------------------------------------------------------


    public List<category_page> fetchHead() {
        List<category_page> head = new ArrayList<>();

        try {
            String url = Uri.parse("http://www.shekulli.com.al/api/kryesore.php")
                    .buildUpon()
                    .build().toString();
            String jsonString = getUrlString(url);
            Log.i(TAG, "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmReceived JSON: " + jsonString);
            JSONObject jsonHead = new JSONObject(jsonString); //The JSONObject constructor parses the JSON string you passed it, resulting in an object hierarchy that maps to the original JSON text.
            //parseItems(items, jsonBody); // kjo metod shton ne liste objekte me ane te te dhenave te json
            JSONArray headArray = jsonHead.getJSONArray("posts");

            for (int i = 0; i < headArray.length()-1; i++) {
                JSONObject headJsonObject = headArray.getJSONObject(i); //objekt i ri
                category_page component = new category_page();

                component.setMurl_post(headJsonObject.getString("url"));
                component.setMtitle_post(headJsonObject.getString("title"));
                component.setMphoto_url_post(headJsonObject.getString("src"));

                Log.i(TAG, "Received url of Url Head: " + String.valueOf(i) + headJsonObject.getString("url"));
                Log.i(TAG, "Received url of Title Head: " + String.valueOf(i) + headJsonObject.getString("title"));
                Log.i(TAG, "Received url of Photo Head: " + String.valueOf(i) + headJsonObject.getString("src"));

                head.add(component);
            }
        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSONmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm", je);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch itemsmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm", ioe);
        }

        return head;
    }

    public String isPaid() {
        String is = "";
        try {
            String url = Uri.parse("http://long-frog-7245.getsandbox.com/")
                    .buildUpon()
                    .build().toString();
            String jsonString = getUrlString(url);
            Log.i(TAG, "http://long-frog-7245.getsandbox.com/Received JSON: " + jsonString);
            JSONObject jsonHead = new JSONObject(jsonString); //The JSONObject constructor parses the JSON string you passed it, resulting in an object hierarchy that maps to the original JSON text.
            //parseItems(items, jsonBody); // kjo metod shton ne liste objekte me ane te te dhenave te json

            //--------------------------------------------------------------------------------------------------


            //--------------------------------------------------------------------------------------------------
            Log.i(TAG, "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb : " + "para");

            //JSONObject Head = jsonHead.getJSONObject("data");
            is = jsonHead.getString("working");
            Log.i(TAG, "is paid : " + is);
        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSON isPaid", je);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items isPaid", ioe);
        }

        /*String title = "Tramp president i SHBA-ve, 5 ndryshimet që prekin gjithë botën";
        String url = "http://www.shekulli.com.al/timthumb.php?src=http://www.shekulli.com.al/admin/wp-content/uploads/2016/11/Donald-Tramp-620x348.jpg&h=360&w=550&zc=1&a=t";
        head.add(title);
        head.add(url);*/


        return is;
    }


    //-------------------------------------------------------------------------------------------------------------------------------------


    public ArrayList fetchCategories() {
        ArrayList categories = new ArrayList();
        categories.add("Kreu");
        categories_go.add("Kreu");

        try {
            String url = Uri.parse("http://www.shekulli.com.al/api/kategori.php")
                    .buildUpon()
                    .build().toString();
            String jsonString = getUrlString(url);
            Log.i(TAG, "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkReceived JSON: " + jsonString);
            JSONObject jsonCategory = new JSONObject(jsonString); //The JSONObject constructor parses the JSON string you passed it, resulting in an object hierarchy that maps to the original JSON text.
            //parseItems(items, jsonBody); // kjo metod shton ne liste objekte me ane te te dhenave te json

            //--------------------------------------------------------------------------------------------------
            Iterator<String> iter = jsonCategory.keys();

            while (iter.hasNext()) {
                String key = iter.next();
                categories.add(key);
                categories_go.add(key);
            }

            //--------------------------------------------------------------------------------------------------

        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSONllllllllllllllllllllllllllllllllllllllllllllllllll", je);

        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch itemsmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm", ioe);
        }

        /*String title = "Tramp president i SHBA-ve, 5 ndryshimet që prekin gjithë botën";
        String url = "http://www.shekulli.com.al/timthumb.php?src=http://www.shekulli.com.al/admin/wp-content/uploads/2016/11/Donald-Tramp-620x348.jpg&h=360&w=550&zc=1&a=t";
        head.add(title);
        head.add(url);*/


        return categories;
    }


    //-------------------------------------------------------------------------------------------------------------------------------------


    //-------------------------------------------------------------------------------------------------------------------------------------


    public ArrayList FetchUrlCategories() {
        ArrayList utl_categories = new ArrayList();
        utl_categories.add("http://www.shekulli.com.al/api/kategori.php");

        try {
            String url = Uri.parse("http://www.shekulli.com.al/api/kategori.php")
                    .buildUpon()
                    .build().toString();
            String jsonString = getUrlString(url);
            Log.i(TAG, "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkReceived JSON: " + jsonString);
            JSONObject jsonCategory = new JSONObject(jsonString); //The JSONObject constructor parses the JSON string you passed it, resulting in an object hierarchy that maps to the original JSON text.
            //parseItems(items, jsonBody); // kjo metod shton ne liste objekte me ane te te dhenave te json

            //--------------------------------------------------------------------------------------------------
            Iterator<String> iter = jsonCategory.keys();


            while (iter.hasNext()) {
                String key = iter.next();
                utl_categories.add(jsonCategory.get(key));


            }

            //--------------------------------------------------------------------------------------------------

        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSONllllllllllllllllllllllllllllllllllllllllllllllllll", je);

        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch itemsmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm", ioe);
        }

        /*String title = "Tramp president i SHBA-ve, 5 ndryshimet që prekin gjithë botën";
        String url = "http://www.shekulli.com.al/timthumb.php?src=http://www.shekulli.com.al/admin/wp-content/uploads/2016/11/Donald-Tramp-620x348.jpg&h=360&w=550&zc=1&a=t";
        head.add(title);
        head.add(url);*/


        return utl_categories;
    }

    //-------------------------------------------------------------------------------------------------------------------------------------
    public List<category_page> fetchADS(String UrlOfCategory) {

        List<category_page> items = new ArrayList<>();
        try {
            Log.i(TAG, "Received URL of ADS: " + "http" + UrlOfCategory.substring(4));
            String url = Uri.parse(UrlOfCategory)
                    .buildUpon()
                    .build().toString();
            Log.i(TAG,url);
            String jsonString = getUrlString(url);
            Log.i(TAG, "Received JSON of Ads: " + jsonString);
            JSONObject jsonBody = new JSONObject(jsonString); //The JSONObject constructor parses the JSON string you passed it, resulting in an object hierarchy that maps to the original JSON text.

            JSONArray jsonArray_Ads = jsonBody.getJSONArray("ads");
            for (int i = 0; i < 6; i++) {

                JSONObject AdJsonObject = jsonArray_Ads.getJSONObject(i); //objekt i ri
                category_page ads_component = new category_page();

                ads_component.setMurl_post(AdJsonObject.getString("url"));
                ads_component.setMphoto_url_post(AdJsonObject.getString("img_url"));
                ads_component.setMtitle_post( AdJsonObject.getString("zone"));

                Log.i(TAG, "Received url of Ads: " + AdJsonObject.getString("url"));
                Log.i(TAG, "Received photo of Ads: " + AdJsonObject.getString("img_url"));
                Log.i(TAG, "Received zone of Ads: " + AdJsonObject.getString("zone"));
                items.add(ads_component);
            }


        } catch (JSONException je){
            Log.e(TAG, "Failed to parse JSON00000000000000000000000000000000Video", je);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items00000000000000000000000000000000Video", ioe);
        }
        return items;
    }







    //-------------------------------------------------------------------------------------------------------------------------------------


    //method that builds an appropriate request URL and fetches its contents
    public List<home_page> fetchItems() {
        local_categories.add("http://www.shekulli.com.al/api/efundit.php");
        local_categories.add("http://www.shekulli.com.al/api/kategori.php?n=politike");
        local_categories.add("http://www.shekulli.com.al/api/kategori.php?n=kronike");
        local_categories.add("http://www.shekulli.com.al/api/kategori.php?n=kulture");
        local_categories.add("http://www.shekulli.com.al/api/kategori.php?n=bote");
        local_categories.add("http://www.shekulli.com.al/api/kategori.php?n=sport");
        List<home_page> items = new ArrayList<>();

        home_page component_title_cat2 = new home_page();
        component_title_cat2.setTitle("name");
        items.add(component_title_cat2);

        if (local_categories.size() > 0) {
            for (int i = 0; i < local_categories.size(); i++) {
                Log.i(TAG, "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmReceived url_cat: " + String.valueOf(local_categories.get(i)));
            }
        } else {
            Log.i(TAG, "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmReceived url_cat: " + "asgje");
        }

        for (int i = 0; i < 6; i++) {
            temp_cat = String.valueOf(local_categories.get(i));
            try {
                String url = Uri.parse("http" + temp_cat.substring(4))
                        .buildUpon()
                        .build().toString();
                String jsonString = getUrlString(url);
                Log.i(TAG, "Received JSON: " + jsonString);
                JSONObject jsonBody = new JSONObject(jsonString); //The JSONObject constructor parses the JSON string you passed it, resulting in an object hierarchy that maps to the original JSON text.
                parseItems(items, jsonBody, temp_cat); // kjo metod shton ne liste objekte me ane te te dhenave te json
            } catch (JSONException je) {
                Log.e(TAG, "Failed to parse JSON", je);
            } catch (IOException ioe) {
                Log.e(TAG, "Failed to fetch items", ioe);
            }

        }


        return items;
    }


    //method that pulls out information for each photo
    private void parseItems(List<home_page> items, JSONObject jsonBody, String catUrl)
            throws IOException, JSONException {

        String title_category;
        if (jsonBody.getString("name").equalsIgnoreCase("efundit")) {
            title_category = "E fundit";
        }
        else {
            title_category = jsonBody.getString("name");
        }


        home_page component_title_cat = new home_page();
        component_title_cat.setTitle(title_category);
        items.add(component_title_cat);

        //This code uses convenience methods such as getJSONObject(String name) and getJSONArray(String name) to navigate the JSONObject hierarchy
        JSONArray postsJsonArray = jsonBody.getJSONArray("posts");


        //krijohen dhe me von shtohen ne liste objektet e krijuara ne baze te te dhenave te json
        for (int i = 0; i < 3; i++) { //per cdo element ne photoJsonArray
            JSONObject CatJsonObject = postsJsonArray.getJSONObject(i); //objekt i ri


            home_page component = new home_page();

            component.setUrl(CatJsonObject.getString("url"));
            component.setTitle(CatJsonObject.getString("title"));
            component.setDate(CatJsonObject.getString("date"));
            component.setPhotoUrl(CatJsonObject.getString("photo_url"));
            if (CatJsonObject.getString("photo_url").equalsIgnoreCase("")) {
                Log.i(TAG, "String " + "bosh");
                component.setPhotoUrl("https://www.w3schools.com/css/img_fjords.jpg");
            } else {
                Log.i(TAG, "String Jobosh" + CatJsonObject.getString("photo_url"));
            }
            component.setCat_Title(title_category);
            component.setSuggUrl(catUrl);

            if (CatJsonObject.getString("comments") == "") {
                component.setNo_comments(0);
            } else {
                component.setNo_comments(0);
            }

            items.add(component); //shtohet objekti i ri ne liste

        }

        home_page component_go_cat = new home_page();
        component_go_cat.setTitle(title_category);
        component_go_cat.setUrl(temp_cat);

        categories_go.clear();
        fetchCategories();

        component_go_cat.setMcategories(categories_go);
        items.add(component_go_cat);

    }
}
