package gustav_dev.a2_2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class MainActivity extends Activity {
    private String text1, text2, text3, text4;

    private EditText ed1, ed2,ed3, ed4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            // könnte man kleiner schrieben
            text1 = savedInstanceState.getString("text1");
            text2 = savedInstanceState.getString("text2");
            text3 = savedInstanceState.getString("text3");
            text4 = savedInstanceState.getString("text4");

            ed1 = (EditText) findViewById(R.id.mult_text_1);
            ed2 = (EditText) findViewById(R.id.mult_text_2);
            ed3 = (EditText) findViewById(R.id.mult_text_3);
            ed4 = (EditText) findViewById(R.id.mult_text_4);

            ed1.setText(text1);
            ed2.setText(text2);
            ed3.setText(text3);
            ed4.setText(text4);
        }



    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("text1",text1);
        savedInstanceState.putString("text2",text2);
        savedInstanceState.putString("text3",text3);
        savedInstanceState.putString("text4",text4);

        Log.v("DEMO","---> onSaveInstanceState() <--- ");
    }

    /*
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        text1 = savedInstanceState.getString("text1");
        text2 = savedInstanceState.getString("text2");
        text3 = savedInstanceState.getString("text3");
        text4 = savedInstanceState.getString("text4");

        Log.v("DEMO","---> onRestoreInstanceState() <--- ");
    }
    */



}