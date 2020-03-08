package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DefaultSurveyActivity extends AppCompatActivity {
    Intent AtoB;
    RadioGroup ques;
    String Jstr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_one);
        AtoB = new Intent(this, ReportActivity.class);
        Jstr = "{";
    }

    //next1 to finish are all used for loading the next question
    public void next1(View view) {
        ques = (RadioGroup) findViewById(R.id.question01);
        for (int i = 0; i < ques.getChildCount(); i++) {
            RadioButton rb = (RadioButton) ques.getChildAt(i);
            if (rb.isChecked()) {
                AtoB.putExtra("ans01", rb.getText());

                //设置Jstr的值
                Jstr += "\"question 01\":\"" + rb.getText().toString() + "\",";
                setContentView(R.layout.question_two);
                break;
            }
        }
    }

    public void next2(View view) {
        ques = (RadioGroup) findViewById(R.id.question02);
        for (int i = 0; i < ques.getChildCount(); i++) {
            RadioButton rb = (RadioButton) ques.getChildAt(i);
            if (rb.isChecked()) {
                AtoB.putExtra("ans02", rb.getText());

                //设置Jstr的值
                Jstr += "\"question 02\":\"" + rb.getText().toString() + "\",";
                setContentView(R.layout.question_three);
                break;
            }
        }
    }

    public void next3(View view) {
        ques = (RadioGroup) findViewById(R.id.question03);
        for (int i = 0; i < ques.getChildCount(); i++) {
            RadioButton rb = (RadioButton) ques.getChildAt(i);
            if (rb.isChecked()) {
                AtoB.putExtra("ans03", rb.getText());
                setContentView(R.layout.question_four);

                //设置Jstr的值
                Jstr += "\"question 03\":\"" + rb.getText().toString() + "\",";
                break;
            }
        }
    }

    public void next4(View view) {
        ques = (RadioGroup) findViewById(R.id.question04);
        String str = "";
        int count = 0;
        for (int i = 0; i < ques.getChildCount(); i++) {
            CheckBox cb = (CheckBox) ques.getChildAt(i);
            if (cb.isChecked()) {
                count++;
                str += cb.getText() + ";";
            }
        }
        AtoB.putExtra("ans04", str);

        //设置Jstr的值
        Jstr += "\"question 04\": \"" + str + "\",";

        if (count != 0)
            setContentView(R.layout.question_five);
    }

    public void next5(View view) {
        ques = (RadioGroup) findViewById(R.id.question05);
        String str = "";
        int count = 0;
        for (int i = 0; i < ques.getChildCount(); i++) {
            CheckBox cb = (CheckBox) ques.getChildAt(i);
            if (cb.isChecked()) {
                count++;
                str += cb.getText() + ";";
            }
        }
        AtoB.putExtra("ans05", str);

        //设置Jstr的值
        Jstr += "\"question 05\": \"" + str + "\",";
        if (count != 0)
            setContentView(R.layout.question_six);
    }

    public void next6(View view) {
        EditText et = (EditText) findViewById(R.id.question06);
        String str = et.getText().toString();
        if (str.length() != 0) {
            AtoB.putExtra("ans06", str);

            //设置Jstr的值
            Jstr += "\"question 06\": \"" + str + "\",";

            setContentView(R.layout.question_seven);
        } else
            Toast.makeText(this, "please enter your answer", Toast.LENGTH_SHORT).show();
    }

    public void next7(View view) {
        ques = (RadioGroup) findViewById(R.id.question07);
        for (int i = 0; i < ques.getChildCount(); i++) {
            RadioButton rb = (RadioButton) ques.getChildAt(i);
            if (rb.isChecked()) {
                AtoB.putExtra("ans07", rb.getText());

                //设置Jstr的值
                Jstr += "\"question 07\":\"" + rb.getText().toString() + "\",";
                setContentView(R.layout.question_eight);
                break;
            }
        }
    }

    public void next8(View view) {
        ques = (RadioGroup) findViewById(R.id.question08);
        for (int i = 0; i < ques.getChildCount(); i++) {
            RadioButton rb = (RadioButton) ques.getChildAt(i);
            if (rb.isChecked()) {
                AtoB.putExtra("ans08", rb.getText());

                //设置Jstr的值
                Jstr += "\"question 08\":\"" + rb.getText().toString() + "\",";
                setContentView(R.layout.question_nine);
                break;
            }
        }
    }

    public void next9(View view) {
        ques = (RadioGroup) findViewById(R.id.question09);
        for (int i = 0; i < ques.getChildCount(); i++) {
            RadioButton rb = (RadioButton) ques.getChildAt(i);
            if (rb.isChecked()) {
                AtoB.putExtra("ans09", rb.getText());

                //设置Jstr的值
                Jstr += "\"question 09\":\"" + rb.getText().toString() + "\",";
                setContentView(R.layout.question_ten);
                break;
            }
        }
    }

    public void next10(View view) {
        ques = (RadioGroup) findViewById(R.id.question10);
        for (int i = 0; i < ques.getChildCount(); i++) {
            RadioButton rb = (RadioButton) ques.getChildAt(i);
            if (rb.isChecked()) {
                AtoB.putExtra("ans10", rb.getText());

                //设置Jstr的值
                Jstr += "\"question 10\":\"" + rb.getText().toString() + "\",";
                setContentView(R.layout.question_eleven);
                break;
            }
        }
    }

    public void next11(View view) {
        ques = (RadioGroup) findViewById(R.id.question11);
        for (int i = 0; i < ques.getChildCount(); i++) {
            RadioButton rb = (RadioButton) ques.getChildAt(i);
            if (rb.isChecked()) {
                AtoB.putExtra("ans11", rb.getText());

                //设置Jstr的值
                Jstr += "\"question 11\":\"" + rb.getText().toString() + "\",";
                setContentView(R.layout.question_twelve);
                break;
            }
        }
    }

    public void finish(View view) {
        ques = (RadioGroup) findViewById(R.id.question12);
        for (int i = 0; i < ques.getChildCount(); i++) {
            RadioButton rb = (RadioButton) ques.getChildAt(i);
            if (rb.isChecked()) {
                AtoB.putExtra("ans12", rb.getText());

                //设置Jstr的值
                Jstr += "\"question 12\":\"" + rb.getText().toString() + "\"}";
                setContentView(R.layout.finish_survey);
                break;
            }
        }
    }
    //next1 to finish are all used for loading the next question


    //save the answers in file
    public void report(View view) {
        System.out.println(Jstr);
        try {
            saveFile(Jstr);
            saveFiletoAPP(Jstr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        startActivity(AtoB);

    }

    //save file into app folder
    public void saveFiletoAPP(String msg) throws IOException {
        File appfile=getFilesDir();
        File saveData=new File(appfile,"saveData.json");
        int count=0;
        while(saveData.exists()){
            count++;
            saveData=new File(appfile,"saveData"+count+".json");
        }
        try {
            FileOutputStream fo=new FileOutputStream(saveData);
            fo.write(msg.getBytes());
            fo.flush();
            fo.close();
            Toast.makeText(this, "Data has been saved in"+saveData.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    //save file into shared directory
    public void saveFile(String msg) throws IOException {
        File sdFile = Environment.getExternalStorageDirectory();
        File saveAnswer = new File(sdFile, "saveAnswer.json");
        if (saveAnswer.exists()) {
            System.out.println("saveAnswer exists");
            byte[] buffer = new byte[32 * 1024];
            FileInputStream fis = new FileInputStream(saveAnswer);
            int len = 0;
            StringBuffer sb = new StringBuffer("");
            while ((len = fis.read(buffer)) > 0) {
                sb.append(new String(buffer, 0, len));
            }
            fis.close();
            String temp = sb.toString();
            int length = temp.length();
            temp = temp.substring(0, length - 1);
            temp += ",";
            temp += msg + "]";
            msg=temp;
        } else {
            System.out.println("saveAnswer doesn't exists");
            msg = "["+msg+"]";
        }
        try {
            FileOutputStream fout = new FileOutputStream(saveAnswer);
            fout.write(msg.getBytes());
            fout.flush();
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
