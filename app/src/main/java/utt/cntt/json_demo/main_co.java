package utt.cntt.json_demo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class main_co extends AppCompatActivity {
    ImageView buttonVietnamese,buttonEnglish;
    private TextView textName, textAddress, textCourses;
    public String enName, enAddress, enCourse1, enCourse2, enCourse3;
    public String name, address, course1, course2, course3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        readJsonObject();
    }

    private void readJsonObject() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.40.20.171/JSON/lang.json";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject language = response.getJSONObject("language");
                            JSONObject vn = language.getJSONObject("vn");
                            JSONObject en = language.getJSONObject("en");

                             name = vn.getString("name");
                             address = vn.getString("address");
                             course1 = vn.getString("course1");
                             course2 = vn.getString("course2");
                             course3 = vn.getString("course3");

                            enName = en.getString("name");
                            enAddress = en.getString("address");
                            enCourse1 = en.getString("course1");
                            enCourse2 = en.getString("course2");
                            enCourse3 = en.getString("course3");
                            Log.d("AA",enName);


                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("AAAA", error.toString());
                    }
                });
        queue.add(request);
    }

    public void switchToVietnamese(View view) {
        displayInformation();
    }
    public void switchToEnglish(View view) {
        displayInformationEN();
    }

    private void displayInformation() {
        textName.setText(name);
        textAddress.setText(address);
        textCourses.setText(course1 + ", " + course2 + ", " + course3);
    }

    private void displayInformationEN() {
        textName.setText(enName);
        textAddress.setText(enAddress);
        textCourses.setText(enCourse1 + ", " + enCourse2 + ", " + enCourse3);
    }

    private void anhxa() {
        textName = findViewById(R.id.textName);
        textAddress = findViewById(R.id.textAddress);
        textCourses = findViewById(R.id.textCourses);
        buttonVietnamese = findViewById(R.id.buttonVietnamese);
        buttonEnglish = findViewById(R.id.buttonEnglish);
    }
}