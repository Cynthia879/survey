package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ChooseActivity extends AppCompatActivity {
    Intent CtoD;
    TextView SquesNUM;
    TextView MquesNUM;
    TextView EquesNUM;
    TextView Squs;
    TextView Mqus;
    TextView Equs;
    RadioGroup Soption;
    RadioGroup Moption;
    EditText Eoption;
    String style;
    String saveAnswer;
    int count;
    int current;
    JSONArray JArr;
    JSONObject Jo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        CtoD=new Intent(this,DefaultSurveyActivity.class);
        style="";
        saveAnswer="{";
    }

    //begin the questions in json file
    public void StartFileSurvey(View view) throws JSONException {
        EditText jsonfile=(EditText)findViewById(R.id.jsonfilename);
        String Jstr= null;
        try {
            Jstr = readFile(jsonfile.getText().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(Jstr.length()!=0){
            System.out.println("从JSON文件读取出的数据为"+Jstr);
            try {
                JSONObject Jsurvey=new JSONObject(Jstr);
                Jstr=Jsurvey.getString("survey");
                Jsurvey=new JSONObject(Jstr);
                Jstr=Jsurvey.getString("questions");
                System.out.println("question中的内容有"+Jstr);
                JArr = new JSONArray(Jstr);
                count=JArr.length();
                System.out.println("question中的个数有"+count+"个");
                current=0;
                Jo=(JSONObject) JArr.get(0);
                System.out.println("第一个json对象"+Jo.getString("type"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(Jo.getString("type").equals("single")){
                ShowSingle();
            }
            else if(Jo.getString("type").equals("multiple")){
                ShowMultiple();
            }
            else if(Jo.getString("type").equals("edit")){
                ShowEdit();
            }
        }
    }

    //begin the default survey
    public void StartDefaultSurvey(View view){
        startActivity(CtoD);
    }

    //When the problem is a radio problem, the page for the radio problem is displayed
    private void ShowSingle() throws JSONException {
        style="single";
        int quesnum=current+1;
        String ques=Jo.getString("question");
        String option=Jo.getString("options");
        JSONArray Joption=new JSONArray(option);
        setContentView(R.layout.single_question);
        SquesNUM=(TextView)findViewById(R.id.SquesionNum);
        Squs=(TextView)findViewById(R.id.Squesiontext);
        Soption=(RadioGroup)findViewById(R.id.SOption);
        Soption.removeAllViewsInLayout(); //清空原有选项
        SquesNUM.setText("Question "+quesnum);
        Squs.setText(ques);
        for(int i=0;i<Joption.length();i++){
            RadioButton rb=new RadioButton(this);
            Soption.addView(rb);
            rb.setText(Joption.getJSONObject(i).getString(""+(i+1)));
        }
    }

    //When the problem is a multiple-select problem, the page for the multiple-select problem is displayed
    private void ShowMultiple() throws JSONException {
        style="multiple";
        int quesnum=current+1;
        String ques=Jo.getString("question");
        String option=Jo.getString("options");
        JSONArray Joption=new JSONArray(option);
        setContentView(R.layout.multiple_question);
        MquesNUM=(TextView)findViewById(R.id.MquesionNum);
        Mqus=(TextView)findViewById(R.id.Mquesiontext);
        Moption=(RadioGroup)findViewById(R.id.MOption);
        Moption.removeAllViewsInLayout(); //清空原有选项
        MquesNUM.setText("Question "+quesnum);
        Mqus.setText(ques);
        for(int i=0;i<Joption.length();i++){
            System.out.println("当前选项为"+Joption.getJSONObject(i).getString(""+(i+1)));
            CheckBox cb=new CheckBox(this);
            Moption.addView(cb);
            cb.setText(Joption.getJSONObject(i).getString(""+(i+1)));
        }
    }

    //When the problem is editable, the page for the editable problem is displayed
    private void ShowEdit() throws JSONException {
        style="edit";
        int quesnum=current+1;
        String ques=Jo.getString("question");
        System.out.println("当前可编辑问题的题目是"+ques);
        setContentView(R.layout.edit_question);
        EquesNUM=(TextView)findViewById(R.id.EquesionNum);
        Equs=(TextView)findViewById(R.id.Equesiontext);
        Eoption=(EditText) findViewById(R.id.EOption);
        EquesNUM.setText("Question "+quesnum);
        Equs.setText(ques);
    }

    //Displays the page for the next question
    public void nextque(View view) throws JSONException {
        Boolean flag=false;
        if(style.equals("single")){
            flag=saveSingleAnswer();
        }else if(style.equals("multiple")){
            flag=saveMultipleAnswer();
        }else if(style.equals("edit")){
            flag=saveEditAnswer();
        }

        if(flag){//答案已保存
            if(current+1<count){//还未到最后一个问题
                current++;
                saveAnswer+=",";
                Jo=(JSONObject) JArr.get(current);
                if(Jo.getString("type").equals("single")){
                    ShowSingle();
                }
                else if(Jo.getString("type").equals("multiple")){
                    ShowMultiple();
                }
                else if(Jo.getString("type").equals("edit")){
                    ShowEdit();
                }
            }else{
                saveAnswer+="}";
                System.out.println("保存在saveAnswer中的数据为"+saveAnswer);
                setContentView(R.layout.finish_jsonsurvey);
            }
        }else{
            Toast.makeText(this, "please answer the question", Toast.LENGTH_SHORT).show();
        }
    }

    //Save the answer to the radio question
    private Boolean saveSingleAnswer() {
        for (int i = 0; i < Soption.getChildCount(); i++) {
            RadioButton rb = (RadioButton) Soption.getChildAt(i);
            if (rb.isChecked()) {
                //设置saveAnswer的值
                saveAnswer += "\"question "+(current+1)+"\":\"" + rb.getText().toString() + "\"";
                return true;
            }
        }
        return false;
    }

    //Save the answer to the multiple-choice question
    private Boolean saveMultipleAnswer() {
        String str = "";
        int count = 0;
        for (int i = 0; i < Moption.getChildCount(); i++) {
            CheckBox cb = (CheckBox) Moption.getChildAt(i);
            if (cb.isChecked()) {
                count++;
                str += cb.getText() + ";";
            }
        }
        //设置saveAnswer的值
        saveAnswer += "\"question "+(current+1)+"\":\"" + str + "\"";
        if (count != 0)
        {return true;}else{
            return false;
        }
    }
    //Save the answer to the editable question
    private Boolean saveEditAnswer() {
        String str = Eoption.getText().toString();
        if (str.length() != 0) {
            //设置saveAnswer的值
            saveAnswer += "\"question "+(current+1)+"\":\"" + str + "\"";
            return true;
        } else{
        return false;}
    }


    //End the questionnaire
    public void FinishJS(View view) throws IOException {
        saveResultFile(saveAnswer);
    }

    //Save the results to a file
    public void saveResultFile(String msg) throws IOException {
        File sdFile = Environment.getExternalStorageDirectory();
        File result = new File(sdFile, "result.json");
        int i=0;
        while (result.exists()) {
            i++;
            result=new File(sdFile,"result"+i+".json");
        }
        try {
            FileOutputStream fout = new FileOutputStream(result);
            fout.write(msg.getBytes());
            fout.flush();
            fout.close();
            Toast.makeText(this, "Result has been saved in"+result.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Reads the contents of the json file specified by the user
    public String readFile(String jsonfilename) throws IOException {
        String result="";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sdFile = Environment.getExternalStorageDirectory();
            File file = new File(sdFile, jsonfilename+".json");
            if(!file.exists()){
                Toast.makeText(this, "The file doesn't exist", Toast.LENGTH_SHORT).show();
            }else{
                byte[] buffer = new byte[32 * 1024];
                FileInputStream fis = new FileInputStream(file);
                int len = 0;
                StringBuffer sb = new StringBuffer("");
                while ((len = fis.read(buffer)) > 0) {
                    sb.append(new String(buffer, 0, len));
                }
                fis.close();
                result=sb.toString();
            }}
            return result;

    }
}
