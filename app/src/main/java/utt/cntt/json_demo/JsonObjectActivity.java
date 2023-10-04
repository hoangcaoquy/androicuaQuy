package utt.cntt.json_demo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonObjectActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsonobject);
        new ReadJsonObject().execute("http://localhost/json/thongtin.json");
    }
    class ReadJsonObject extends AsyncTask<String , Void, String>{
        StringBuilder stringBuilder = new StringBuilder();

        protected String doInBackground(String... strings){
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader br = new BufferedReader(inputStreamReader);
                String line= "";
                while ((line = br.readLine()) != null){
                    stringBuilder.append(line);
                }
            }catch (Exception e){
                Log.d("error: ", e.getMessage());
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            //Toast.makeText(JsonObjectActivity.this, ""+s, Toast.LENGTH_SHORT).show();
            try {
                JSONObject jsonObject = new JSONObject(s);
                String hoten = jsonObject.getString("hoten");
                String ngaysinh = jsonObject.getString("ngaysinh");
                String quequan = jsonObject.getString("quequan");
                String sdt = jsonObject.getString("sdt");
                String email = jsonObject.getString("email");
                Toast.makeText(JsonObjectActivity.this, ""+hoten, Toast.LENGTH_SHORT).show();
                Toast.makeText(JsonObjectActivity.this, ""+ngaysinh, Toast.LENGTH_SHORT).show();
                Toast.makeText(JsonObjectActivity.this, ""+quequan, Toast.LENGTH_SHORT).show();
                Toast.makeText(JsonObjectActivity.this, ""+sdt, Toast.LENGTH_SHORT).show();
                Toast.makeText(JsonObjectActivity.this, ""+email, Toast.LENGTH_SHORT).show();


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}