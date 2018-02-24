package criminalintent.android.bignerdranch.com.criminalintent;

/**
 * Created by GERMAN on 24-Nov-16.
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

public class CategoryFetchr{
    private static final String TAG = "CategoryFetchr";


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

    //method that builds an appropriate request URL and fetches its contents
    public List<category_page> fetchItems(String UrlOfCategory) {

        List<category_page> items = new ArrayList<>();
        try {
            Log.i(TAG, "Received URL of Category: " + "https" + UrlOfCategory.substring(4));
            String url = Uri.parse("http" + UrlOfCategory.substring(4))
                    .buildUpon()
                    .build().toString();
            String jsonString = getUrlString(url);
            Log.i(TAG, "Received JSON of Category: " + jsonString);
            JSONObject jsonBody = new JSONObject(jsonString); //The JSONObject constructor parses the JSON string you passed it, resulting in an object hierarchy that maps to the original JSON text.
            parsItems(items, jsonBody); // kjo metod shton ne liste objekte me ane te te dhenave te json
        } catch (JSONException je){
            Log.e(TAG, "Failed to parse JSON", je);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
            category_page component = new category_page();

            component.setMurl_post("www.google.com");
            component.setMtitle_post("Test");
            component.setMdate_post("10/10/2010");
            component.setMphoto_url_post("https://www.w3schools.com/css/img_fjords.jpg");
            component.setCat_Name("Bote");

            component.setMno_comments_post(0);

            items.add(component); //shtohet objekti i ri ne liste
            return items;
        }

        return items;
    }

    //method that pulls out information for each photo
    private void parsItems(List<category_page> items, JSONObject jsonBody)
            throws IOException, JSONException {

        //This code uses convenience methods such as getJSONObject(String name) and getJSONArray(String name) to navigate the JSONObject hierarchy
        JSONArray postsJsonArray = jsonBody.getJSONArray("posts");

        String titleview = jsonBody.getString("name");


        //krijohen dhe me von shtohen ne liste objektet e krijuara ne baze te te dhenave te json
        for (int i = 0; i < postsJsonArray.length()-1; i++) { //per cdo element ne photoJsonArray
            JSONObject CatJsonObject = postsJsonArray.getJSONObject(i); //objekt i ri


            category_page component = new category_page();

            component.setMurl_post(CatJsonObject.getString("url"));
            component.setMtitle_post(CatJsonObject.getString("title"));
            component.setMdate_post(CatJsonObject.getString("date"));
            component.setMphoto_url_post(CatJsonObject.getString("photo_url"));
            if (CatJsonObject.getString("photo_url").equalsIgnoreCase("")) {
                Log.i(TAG, "String " + "bosh");
                component.setMphoto_url_post("https://www.w3schools.com/css/img_fjords.jpg");
            } else {
                Log.i(TAG, "String Jobosh" + CatJsonObject.getString("photo_url"));
            }
            component.setCat_Name(titleview);

            if (CatJsonObject.getString("comments") == ""){
                component.setMno_comments_post(0);
            }
            else {
                component.setMno_comments_post(0);
            }

            items.add(component); //shtohet objekti i ri ne liste

        }
    }

    //--- VideoGallery --//

    public List<category_page> fetchVideos(String UrlOfCategory) {

        List<category_page> items = new ArrayList<>();
        try {
            Log.i(TAG, "Received URL of Category: " + UrlOfCategory);
            String url = Uri.parse(UrlOfCategory)
                    .buildUpon()
                    .build().toString();
            Log.i(TAG,url);
            String jsonString = getUrlString(url);
            Log.i(TAG, "Received JSON of Category: " + jsonString);
            JSONObject jsonBody = new JSONObject(jsonString); //The JSONObject constructor parses the JSON string you passed it, resulting in an object hierarchy that maps to the original JSON text.



            Iterator<String> iter = jsonBody.keys();

            Log.i(TAG,iter.toString());


            while (iter.hasNext()) {
                String key = iter.next();
                Log.i(TAG,"this key is: " + key);

                //utl_categories.add(jsonCategory.get(key));
                JSONObject VideoJsonObject = jsonBody.getJSONObject(key);
                //Log.i(TAG,"this vidObject is: " + VideoJsonObject);
                String id = String.valueOf(VideoJsonObject.get("ID"));
                Log.i(TAG,"this key is: " + String.valueOf(VideoJsonObject.get("mainImage")));
                Log.i(TAG, "this id is: " + id);

                category_page video_component = new category_page();
                video_component.setMtitle_post(String.valueOf(VideoJsonObject.get("title")));
                video_component.setMurl_post("http://edge-streaming.abingmedia.com/" + id + ".mp4?3");
                video_component.setMphoto_url_post("http://abingmedia.com/" + String.valueOf(VideoJsonObject.get("mainImage")));

                Log.i(TAG, "this title is: " + String.valueOf(VideoJsonObject.get("title")));

                Log.i(TAG, "                                                                 ");
                Log.i(TAG, "                                                                 ");
                Log.i(TAG, "                                                                 ");
                Log.i(TAG, "                                                                 ");
                Log.i(TAG, "                                                                 ");

                items.add(video_component);
            }







        } catch (JSONException je){
            Log.e(TAG, "Failed to parse JSON00000000000000000000000000000000Video", je);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items00000000000000000000000000000000Video", ioe);
        }
        return items;
    }

}
