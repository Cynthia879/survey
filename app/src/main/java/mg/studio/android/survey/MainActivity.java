package mg.studio.android.survey;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    Intent AtoB;
    Intent AtoC;
    RadioGroup ques;
    String Jstr;
    private static String[] PERMISSION = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static int PERMISSION_CODE = 1;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        AtoB = new Intent(MainActivity.this, ReportActivity.class);
        AtoC = new Intent(MainActivity.this, ChooseActivity.class);
        Jstr = "{";
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSION, PERMISSION_CODE);
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                Log.i("MainActivity", "permission to apply：" + permissions[i] + "result：" + grantResults[i]);
            }
        }
    }

    //Users can enter the questionnaire survey after confirming the terms
    //Save the sample file to the sdcard
    public void start(View view) {
        CheckBox ac = (CheckBox) findViewById(R.id.accept);
        String str="{\n" +
                "\t\"survey\": {\n" +
                "\t\t\"id\": \"12344134\",\n" +
                "\t\t\"len\": \"2\",\n" +
                "\t\t\"questions\": [{\n" +
                "\t\t\t\t\"type\": \"single\",\n" +
                "\t\t\t\t\"question\": \"How well do the professors teach at this university?\",\n" +
                "\t\t\t\t\"options\": [{\n" +
                "\t\t\t\t\t\"1\": \"Extremely well\"\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"2\": \"Very well\"\n" +
                "\t\t\t\t}]\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"type\": \"single\",\n" +
                "\t\t\t\t\"question\": \"How effective is the teaching outside yur major at the univesrity?\",\n" +
                "\t\t\t\t\"options\": [{\n" +
                "\t\t\t\t\t\"1\": \"Extremetly effective\"\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"2\": \"Very effective\"\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"3\": \"Somewhat effective\"\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"4\": \"Not so effective\"\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"5\": \"Not at all effective\"\n" +
                "\t\t\t\t}]\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"type\": \"edit\",\n" +
                "\t\t\t\t\"question\": \"How well do the professors teach at this university?\",\n" +
                "\t\t\t\t\"options\": \"\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"type\": \"multiple\",\n" +
                "\t\t\t\t\"question\": \"How effective is the teaching outside yur major at the univesrity?\",\n" +
                "\t\t\t\t\"options\": [{\n" +
                "\t\t\t\t\t\"1\": \"Extremetly effective\"\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"2\": \"Very effective\"\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"3\": \"Somewhat effective\"\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"4\": \"Not so effective\"\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"5\": \"Not at all effective\"\n" +
                "\t\t\t\t}]\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"type\": \"single\",\n" +
                "\t\t\t\t\"question\": \"How effective is the teaching outside yur major at the univesrity?\",\n" +
                "\t\t\t\t\"options\": [{\n" +
                "\t\t\t\t\t\"1\": \"Extremetly effective\"\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"2\": \"Very effective\"\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"3\": \"Somewhat effective\"\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"4\": \"Not so effective\"\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"5\": \"Not at all effective\"\n" +
                "\t\t\t\t}]\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"type\": \"single\",\n" +
                "\t\t\t\t\"question\": \"How effective is the teaching outside yur major at the univesrity?\",\n" +
                "\t\t\t\t\"options\": [{\n" +
                "\t\t\t\t\t\"1\": \"Extremetly effective\"\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"2\": \"Very effective\"\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"3\": \"Somewhat effective\"\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"4\": \"Not so effective\"\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"5\": \"Not at all effective\"\n" +
                "\t\t\t\t}]\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"type\": \"multiple\",\n" +
                "\t\t\t\t\"question\": \"How effective is the teaching outside yur major at the univesrity?\",\n" +
                "\t\t\t\t\"options\": [{\n" +
                "\t\t\t\t\t\"1\": \"Extremetly effective\"\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"2\": \"Very effective\"\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"3\": \"Somewhat effective\"\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"4\": \"Not so effective\"\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"5\": \"Not at all effective\"\n" +
                "\t\t\t\t}]\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t}\n" +
                "}";
        try {
            savequesFile(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (ac.isChecked()){
            startActivity(AtoC);
        }
        else {
            AlertDialog accept = new AlertDialog.Builder(this)
                    .setMessage("Please confirm that you accept the requests before answering questions")
                    .setPositiveButton("OK", null)
                    .create();
            accept.show();
        }
    }

    //save file in the root directory of sdcard
    public void savequesFile(String msg) throws IOException {
        File sdFile = Environment.getExternalStorageDirectory();
        File sample = new File(sdFile, "sample.json");
        if (sample.exists()) {
            System.out.println("sample exists");

        } else {
            System.out.println("sample doesn't exists");
        }
        try {
            FileOutputStream fout = new FileOutputStream(sample);
            fout.write(msg.getBytes());
            fout.flush();
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
