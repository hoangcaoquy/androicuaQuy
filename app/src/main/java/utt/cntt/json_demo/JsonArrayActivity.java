package utt.cntt.json_demo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonArrayActivity extends AppCompatActivity {
    public class JsonObjectActivity extends AppCompatActivity {
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_jsonarray);
            new ReadJsonObject().execute("");
        }

        class ReadJsonObject extends AsyncTask<String, Void, String> {
            StringBuilder stringBuilder = new StringBuilder();

            protected String doInBackground(String... strings) {
                try {
                    URL url = new URL(strings[0]);
                    InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                    BufferedReader br = new BufferedReader(inputStreamReader);
                    String line = "";
                    while ((line = br.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    //  Log.d("error: ", e.getMessage());
                    e.printStackTrace();
                }
                return stringBuilder.toString();
            }

            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("danhsach");
                    for(int i=0; i < jsonArray.length(); i++ ){
                        JSONObject objectKhoaHoc = jsonArray.getJSONObject(i);
                        String khoaHoc = objectKhoaHoc.getString("khoaHoc");
                        Toast.makeText(JsonArrayActivity.this, ""+khoaHoc, Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

