package com.example.aviral.openseseme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class LoggedIn extends AppCompatActivity {

    private Button accessButton;
    private ImageView imageView;
    private ImageLoader imageLoader;
    private Spinner locationSpinner;

    public static final String url = "http://192.168.43.17:3030";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        accessButton = (Button) findViewById(R.id.accessButton);
        imageView = (ImageView) findViewById(R.id.imageView);


        addSpinner();


        accessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject jsonObject = new JSONObject();

                try{
                    jsonObject.put("username", "danny");
                    jsonObject.put("location", String.valueOf(locationSpinner.getSelectedItem()));

                }catch (JSONException e){
                    e.printStackTrace();
                }

                final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url,jsonObject,

                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {


                                    String imgurl = response.getString("image");
                                    String urlimg = url + imgurl;


                                    // Create an object for subclass of AsyncTask
                                    GetXMLTask task = new GetXMLTask();
                                    // Execute the task
                                    task.execute(urlimg );
                                    new android.os.Handler().postDelayed(
                                            new Runnable() {
                                                public void run() {
                                                    GetXMLTask task = new GetXMLTask();
                                                    task.execute(url + "/door_close.png");
                                                }
                                            },
                                            8000);


                                } catch (Exception e){
                                    e.printStackTrace();
                                }


//                                Toast.makeText(getApplicationContext(), "awd", Toast.LENGTH_SHORT).show();
                                requestQueue.stop();


                            }


                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        requestQueue.stop();
                    }

                });
//                {
//
//                    @Override
//                    protected Map<String,String> getParams() {
//                        HashMap<String, String> params = new HashMap<String, String>();
//                        params.put("username", "aviral");
//                        return params;
//                    }
//
//
////
////                    @Override
////                    public Map<String, String> getHeaders() throws AuthFailureError {
////                        HashMap<String, String> headers = new HashMap<String, String>();
////                        headers.put("Content-Type", "application/json");
////                        return headers;
////                    }
//                };


                requestQueue.add(jsonObjReq);


            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    private class GetXMLTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;
            for (String url : urls) {
                map = downloadImage(url);
            }
            return map;
        }

        // Sets the Bitmap returned by doInBackground
        @Override
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }

        // Creates Bitmap from InputStream and returns it
        private Bitmap downloadImage(String url) {
            Bitmap bitmap = null;
            InputStream stream = null;
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;

            try {
                stream = getHttpConnection(url);
                bitmap = BitmapFactory.
                        decodeStream(stream, null, bmOptions);
                stream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return bitmap;
        }

        // Makes HttpURLConnection and returns InputStream
        private InputStream getHttpConnection(String urlString)
                throws IOException {
            InputStream stream = null;
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            try {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();

                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return stream;
        }
    }

    public void addSpinner(){
        locationSpinner = (Spinner) findViewById(R.id.locationSpinner);
        List<String> list = new ArrayList<String>();
        list.add("Front-Gate");
        list.add("Back-Gate");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(dataAdapter);
    }

}


