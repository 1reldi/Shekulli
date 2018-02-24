package criminalintent.android.bignerdranch.com.criminalintent;

/**
 * Created by GERMAN on 01-Nov-16.
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

public class PostFetchr extends post_page{
    private static final String TAG = "PostFetchr";
    List<Comment_Stucture> Comments = new ArrayList<>();

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
    public List<post_page> fetchItems(String UrlOfPost) {

        List<post_page> items = new ArrayList<>();
        try {
            String url = Uri.parse(UrlOfPost)
                    .buildUpon()
                    .build().toString();
            String jsonString = getUrlString(url);
            Log.i(TAG, "Received JSON of Post: " + jsonString);
            JSONObject jsonBody = new JSONObject(jsonString); //The JSONObject constructor parses the JSON string you passed it, resulting in an object hierarchy that maps to the original JSON text.
            parseItems(items, jsonBody); // kjo metod shton ne liste objekte me ane te te dhenave te json
        } catch (JSONException je){
            Log.e(TAG, "Failed to parse JSON", je);

            post_page item = new post_page();
            item.setTitle("Projekti politik, Kokëdhima shpall lëvizjen brenda PS-së");
            item.setContent( "Politikani dhe botuesi Koço Kokëdhima, i ftuar në emisionin “Studio e Hapur” e Eni Vasilit, njoftoi mbrëmë nisjen e një lëvizjeje politike brenda Partisë Socialiste për ta demokratizuar, reformuar dhe fuqizuar atë. “Unë kam për të zhvilluar një lëvizje brenda Partisë Socialiste, që synon reformimin e PS-së dhe të arrihet që çdo anëtar të votojë”- tha ai. Intervista e Kokëdhimës nisi me jehonën në Shqipëri të fitores së kandidatit republikan në SHBA, Donald Trump, tashmë president i zgjedhur dhe leksioni që dha triumfi i tij, i vetëm përballë establishmetit polilitik dhe duke mbështetur qytetarët e papërfaqësuar. Fitorja e Trumpit  Kokëdhima shpjegoi disa nga arsyet se pse ka besuar në fitoren e Donald Trump si president i Shteteve të Bashkuara të Amerikës. “E kam ndjekur presidentin e zgjedhur Donald Trump shumë vite më përpara dhe kam qenë i sigurt për vlerat e jashtëzakonshme të këtij njeriu.");
            item.setDate("16 Nëntor 2016, 07:52");
            item.setAuthor("Shekulli");
            item.setPhoto_Main_Url("http://www.shekulli.com.al//timthumb.php?src=http://www.shekulli.com.al/admin/wp-content/uploads/2016/11/kokedhima.png&amp;h=400&amp;w=640&amp;zc=1&a=t");

            items.add(item);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        }
        return items;
    }

    //method that pulls out information for each photo
    private void parseItems(List<post_page> items, JSONObject jsonBody)
            throws IOException, JSONException {

        //This code uses convenience methods such as getJSONObject(String name) and getJSONArray(String name) to navigate the JSONObject hierarchy
        JSONObject postJsonObject = jsonBody.getJSONArray("lajmi").getJSONObject(0);//.getJSONObject("data");

        //JSONObject comentJsonObject = postJsonObject.getJSONObject("author");

        post_page item = new post_page(); //modeli post_page.java

//=------------------------------------------------------------------------------------------------------------------------------------

        //JSONObject AuthorJsonObject = postJsonObject.getJSONObject("author");
        //JSONArray CommentJsonArray = postJsonObject.getJSONArray("comments");

//=------------------------------------------------------------------------------------------------------------------------------------
/*
        List<Comment_Stucture> Comments = new ArrayList<>();
        for (int i = 0; i < CommentJsonArray.length(); i++) {
            JSONObject CommentJsonObject = CommentJsonArray.getJSONObject(i); //objekt i ri

            Comment_Stucture comms = new Comment_Stucture();
            comms.setCommentName(CommentJsonObject.getString("name"));
            comms.setCommentDate(CommentJsonObject.getString("date"));
            comms.setCommentContent(CommentJsonObject.getString("content"));

            Comments.add(comms);
        }*/


//=------------------------------------------------------------------------------------------------------------------------------------


        try {

            item.setTitle(String.valueOf(postJsonObject.get("title")));
            item.setContent(postJsonObject.getString("content"));
            item.setDate(postJsonObject.getString("date"));
            item.setAuthor(postJsonObject.getString("author"));
            item.setPhoto_Main_Url(postJsonObject.getString("photo_url"));
            item.setCategory_Url(postJsonObject.getString("category_url"));

            Log.i(TAG, "Received VERSION of Post: " + postJsonObject.get("versioni"));




        } catch (JSONException je) {

            item.setTitle("Projekti politik, Kokëdhima shpall lëvizjen brenda PS-së");
            item.setContent( "Politikani dhe botuesi Koço Kokëdhima, i ftuar në emisionin “Studio e Hapur” e Eni Vasilit, njoftoi mbrëmë nisjen e një lëvizjeje politike brenda Partisë Socialiste për ta demokratizuar, reformuar dhe fuqizuar atë. “Unë kam për të zhvilluar një lëvizje brenda Partisë Socialiste, që synon reformimin e PS-së dhe të arrihet që çdo anëtar të votojë”- tha ai. Intervista e Kokëdhimës nisi me jehonën në Shqipëri të fitores së kandidatit republikan në SHBA, Donald Trump, tashmë president i zgjedhur dhe leksioni që dha triumfi i tij, i vetëm përballë establishmetit polilitik dhe duke mbështetur qytetarët e papërfaqësuar. Fitorja e Trumpit  Kokëdhima shpjegoi disa nga arsyet se pse ka besuar në fitoren e Donald Trump si president i Shteteve të Bashkuara të Amerikës. “E kam ndjekur presidentin e zgjedhur Donald Trump shumë vite më përpara dhe kam qenë i sigurt për vlerat e jashtëzakonshme të këtij njeriu.");
            item.setDate("16 Nëntor 2016, 07:52");
            item.setAuthor("Shekulli");
            item.setPhoto_Main_Url("http://www.shekulli.com.al//timthumb.php?src=http://www.shekulli.com.al/admin/wp-content/uploads/2016/11/kokedhima.png&amp;h=400&amp;w=640&amp;zc=1&a=t");

        }


        //item.setCommentsAll(Comments);
        //item.setAuthor(AuthorJsonObject.getString("name"));

//=------------------------------------------------------------------------------------------------------------------------------------

        items.add(item); //shtohet objekti i ri ne liste

//=------------------------------------------------------------------------------------------------------------------------------------

        try {

            if (jsonBody.getJSONArray("fotogaleri") != null) {//String.valueOf(postJsonObject.get("versioni")).equalsIgnoreCase("5")){

                JSONArray Photo_Gallery = jsonBody.getJSONArray("fotogaleri").getJSONObject(0).getJSONArray("src");
                post_page item2 = new post_page();

                ArrayList Url_Photos = new ArrayList();

                for (int i = 0; i < Photo_Gallery.length() - 1; i++) {
                    Url_Photos.add(Photo_Gallery.get(i));
                }


                //Url_Photos.add("http://www.shekulli.com.al/admin/wp-content/uploads/2016/11/3A831D0900000578-0-image-a-26_1479466028241.jpg");
                //Url_Photos.add("http://www.shekulli.com.al/admin/wp-content/uploads/2016/11/3A831CB300000578-3949286-image-m-34_1479469387757.jpg");
                //Url_Photos.add("http://www.shekulli.com.al/admin/wp-content/uploads/2016/11/3A831CBD00000578-0-image-a-23_1479466006448.jpg");

                item2.setPhotos(Url_Photos);

                items.add(item2);
            }
        }
        catch (JSONException ji){
            Log.i(TAG, "Error404");
        }


//=------------------------------------------------------------------------------------------------------------------------------------

        try {

            if (jsonBody.getJSONArray("video") != null) {
                JSONArray Video_Gallery = jsonBody.getJSONArray("video").getJSONObject(0).getJSONArray("src");
                post_page item3 = new post_page();

                for (int i = 0; i < Video_Gallery.length() - 1; i++) {
                    item3.setVideo(String.valueOf(Video_Gallery.get(i)));
                    items.add(item3);
                }
            }
        }
        catch (JSONException jo){
            Log.i(TAG, "Error404");
        }


        //post_page item3 = new post_page();
        //String Url_Videos = "https://www.facebook.com/plugins/video.php?href=https%3A%2F%2Fwww.facebook.com%2Fkocokokedhima%2Fvideos%2F1264341026970200%2F&amp;show_text=0&amp;width=560";
        //item3.setVideo(Url_Videos);
        //items.add(item3);

//=------------------------------------------------------------------------------------------------------------------------------------

        post_page item4 = new post_page();
        items.add(item4);

        post_page item5 = new post_page();
        item5.setSuug(1);

        items.add(item5);
        items.add(item5);
        items.add(item5);
        items.add(item5);
        items.add(item5);
        items.add(item5);
        items.add(item5);
        items.add(item5);
        items.add(item5);
        items.add(item5);

    }

    public List<category_page> fetchADS(String UrlOfCategory) {

        List<category_page> items = new ArrayList<>();
        try {
            Log.i(TAG, "Received URL of ADS: " + "https" + UrlOfCategory.substring(4));
            String url = Uri.parse("http" + UrlOfCategory.substring(4))
                    .buildUpon()
                    .build().toString();
            Log.i(TAG,url);
            String jsonString = getUrlString(url);
            Log.i(TAG, "Received JSON of Ads: " + jsonString);
            JSONObject jsonBody = new JSONObject(jsonString); //The JSONObject constructor parses the JSON string you passed it, resulting in an object hierarchy that maps to the original JSON text.

            JSONArray jsonArray_Ads = jsonBody.getJSONArray("ads");
            for (int i = 0; i < jsonArray_Ads.length()-1; i++) {

                JSONObject AdJsonObject = jsonArray_Ads.getJSONObject(i); //objekt i ri
                if (String.valueOf(AdJsonObject.getString("zone")).equalsIgnoreCase("G1")  || String.valueOf(AdJsonObject.getString("zone")).equalsIgnoreCase("G2")) {
                    category_page ads_component = new category_page();

                    ads_component.setMurl_post(AdJsonObject.getString("url"));
                    ads_component.setMphoto_url_post(AdJsonObject.getString("img_url"));

                    Log.i(TAG, "Received url of Ads: " + AdJsonObject.getString("url"));
                    Log.i(TAG, "Received photo of Ads: " + AdJsonObject.getString("img_url"));
                    items.add(ads_component);
                }
            }


        } catch (JSONException je){
            Log.e(TAG, "Failed to parse JSON00000000000000000000000000000000Video", je);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items00000000000000000000000000000000Video", ioe);
        }
        return items;
    }

    public List<String> fetchBasics(String urlPost) {
        List<String> items = new ArrayList<>();
        try {
            Log.i(TAG, "Received URL of Basic: " + "https" + urlPost.substring(4));
            String url = Uri.parse("http" + urlPost.substring(4))
                    .buildUpon()
                    .build().toString();
            Log.i(TAG,url);
            String jsonString = getUrlString(url);
            Log.i(TAG, "Received JSON of Basic: " + jsonString);
            JSONObject jsonBody = new JSONObject(jsonString); //The JSONObject constructor parses the JSON string you passed it, resulting in an object hierarchy that maps to the original JSON text.

            JSONObject postJsonObject = jsonBody.getJSONArray("lajmi").getJSONObject(0);

            items.add(String.valueOf(postJsonObject.get("category")));
            items.add(String.valueOf(postJsonObject.get("category_url")));
            items.add(String.valueOf(postJsonObject.get("share")));


        } catch (JSONException je){
            Log.e(TAG, "Failed to parse JSON00000000000000000000000000000000Basic", je);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items00000000000000000000000000000000Basic", ioe);
        }
        return items;
    }



}
