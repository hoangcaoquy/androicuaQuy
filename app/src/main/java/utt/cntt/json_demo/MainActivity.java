package utt.cntt.json_demo;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    private TextView textName, textAddress, textCourses;
    private ImageView imageFlag;
    private JSONObject jsonData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textName = findViewById(R.id.textName);
        textAddress = findViewById(R.id.textAddress);
        textCourses = findViewById(R.id.textCourses);
//        imageFlag = findViewById(R.id.imageFlagvn);
//        imageFlag = findViewById(R.id.imageFlagen);
//        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_flag_vn);
//        BitmapShader shader = new BitmapShader(originalBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        Paint paint = new Paint();
//        paint.setShader(shader);
//        Bitmap outputBitmap = Bitmap.createBitmap(originalBitmap.getWidth(), originalBitmap.getHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(outputBitmap);
//        canvas.drawCircle(originalBitmap.getWidth() / 2, originalBitmap.getHeight() / 2, originalBitmap.getWidth() / 2, paint);
//        imageFlag.setImageBitmap(outputBitmap);
        // Load JSON data from file
        try {
            String jsonFileContent = loadJSONFromAsset();
            jsonData = new JSONObject(jsonFileContent);
        } catch (JSONException e) {
            Log.e("MainActivity", "Đã xảy ra lỗi khi phân tích cú pháp JSON", e);
        }

        // Hiển thị dữ liệu mặc định tiếng Việt
        displayLanguageData("vn");
    }

    public void switchToEnglish(View view) {
        displayLanguageData("en");
    }

    public void switchToVietnamese(View view) {
        displayLanguageData("vn");
    }

    private void displayLanguageData(String language) {
        try {
            JSONObject languageData = jsonData.getJSONObject("language").getJSONObject(language);
            textName.setText(languageData.getString("name"));
            textAddress.setText(languageData.getString("address"));
            textCourses.setText(languageData.getString("course1") + "\n"
                    + languageData.getString("course2") + "\n"
                    + languageData.getString("course3"));

//            if (language.equals("en")) {
//                imageFlag.setImageResource(R.drawable.ic_flag_en);
//            } else {
//                imageFlag.setImageResource(R.drawable.ic_flag_vn);
//            }
        } catch (JSONException e) {
            Log.e("MainActivity", "Đã xảy ra lỗi khi hiển thị dữ liệu ngôn ngữ", e);
        }
    }

    private String loadJSONFromAsset() {
        String json = null;
        try {
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open("data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            Log.e("MainActivity", "Đã xảy ra lỗi khi tải JSON từ asset", ex);
        }
        return json;
    }
}

