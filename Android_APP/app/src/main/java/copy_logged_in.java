//package com.example.aviral.openseseme;
//
//import android.net.Uri;
//import android.os.AsyncTask;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//
//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;
//import com.google.android.gms.common.api.GoogleApiClient;
//
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.io.Writer;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//
//public class LoggedIn extends AppCompatActivity {
//
//    private Button accessButtton;
//    private GoogleApiClient client;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_logged_in);
//
//        accessButtton = (Button) findViewById(R.id.accessButton);
//
//        accessButtton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                JSONObject jsonObject = new JSONObject();
//                try {
//                    jsonObject.put("username", "aviral");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                if(jsonObject.length() > 0){
//                    new SendDataToServer().execute(String.valueOf(jsonObject));
//                }
//
//            }
//        });
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//
//    }
//
////    class SendDataToServer extends Async<String,String,String>{
////    }
//
//
//}
//
//class SendDataToServer extends AsyncTask <String,String,String>{
//
//    @Override
//    protected String doInBackground(String... params) {
//        String JsonResponse = null;
//        String JsonDATA = params[0];
//
//        HttpURLConnection urlConnection = null;
//        BufferedReader reader = null;
//        try{
//            URL url = new URL("http://127.0.0.1:3000");
//            urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setDoOutput(true);
//
//            urlConnection.setRequestMethod("POST");
//            urlConnection.setRequestProperty("Content-Type", "application/json");
//            urlConnection.setRequestProperty("Accept", "application/json");
//
//            Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
//            writer.write(JsonDATA);
//            writer.close();
//
//            InputStream inputStream = urlConnection.getInputStream();
//
//            StringBuffer buffer = new StringBuffer();
//
//            if(inputStream == null){
//                return  null;
//            }
//
//            reader = new BufferedReader(new InputStreamReader(inputStream));
//
//            String inputLine;
//            while ((inputLine = reader.readLine()) != null){
//                buffer.append(inputLine + "\n");
//            }
//
//            if(buffer.length() == 0){
//                return null;
//            }
//
//            JsonResponse = buffer.toString();
//
//            Log.i(TAG, JsonResponse);
//            try{
//                return JsonResponse;
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//            return  null;
//
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//        finally {
//            if(urlConnection != null){
//                urlConnection.disconnect();
//            }
//            if(reader != null){
//                try{
//                    reader.close();
//                } catch (final Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }
//        return null;
//
//    }
//
//
//    @Override
//    protected void onPostExecute(String s) {
//    }
//
//}
