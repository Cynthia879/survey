package mg.studio.android.survey;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Intent AtoB;
    RadioGroup ques;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        AtoB = new Intent(MainActivity.this, ReportActivity.class);
    }
    public void start(View view) {
        CheckBox ac = (CheckBox) findViewById(R.id.accept);
        if (ac.isChecked())
            setContentView(R.layout.question_one);
        else {
            AlertDialog accept = new AlertDialog.Builder(this)
                    .setMessage("Please confirm that you accept the requests before answering questions")
                    .setPositiveButton("OK", null)
                    .create();
            accept.show();
        }

    }

    public void next1(View view) {
        ques = (RadioGroup) findViewById(R.id.question01);
        for (int i = 0; i < ques.getChildCount(); i++) {
            RadioButton rb = (RadioButton) ques.getChildAt(i);
            if (rb.isChecked()) {
                AtoB.putExtra("ans01", rb.getText());
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
                break;
            }
        }
    }

    public void next4(View view) {
        ques = (RadioGroup) findViewById(R.id.question04);
        String str = "";
        int count=0;
        for (int i = 0; i < ques.getChildCount(); i++) {
            CheckBox cb = (CheckBox) ques.getChildAt(i);
            if (cb.isChecked()) {
                count++;
                str += cb.getText() + ";";
            }
        }
        AtoB.putExtra("ans04", str);
        if(count!=0)
            setContentView(R.layout.question_five);
    }

    public void next5(View view) {
        ques = (RadioGroup) findViewById(R.id.question05);
        String str = "";
        int count=0;
        for (int i = 0; i < ques.getChildCount(); i++) {
            CheckBox cb = (CheckBox) ques.getChildAt(i);
            if (cb.isChecked()) {
                count++;
                str += cb.getText() + ";";
            }
        }
        AtoB.putExtra("ans05", str);
        if(count!=0)
            setContentView(R.layout.question_six);
    }

    public void next6(View view) {
        EditText et = (EditText) findViewById(R.id.question06);
        String str = et.getText().toString();
        if (str.length() != 0) {
            AtoB.putExtra("ans06", str);
            setContentView(R.layout.question_seven);
        } else
            Toast.makeText(MainActivity.this, "please enter your answer", Toast.LENGTH_SHORT).show();
    }

    public void next7(View view) {
        ques = (RadioGroup) findViewById(R.id.question07);
        for (int i = 0; i < ques.getChildCount(); i++) {
            RadioButton rb = (RadioButton) ques.getChildAt(i);
            if (rb.isChecked()) {
                AtoB.putExtra("ans07", rb.getText());
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
                setContentView(R.layout.finish_survey);
                break;
            }
        }
    }

    public void report(View view) {
        startActivity(AtoB);
    }
}
