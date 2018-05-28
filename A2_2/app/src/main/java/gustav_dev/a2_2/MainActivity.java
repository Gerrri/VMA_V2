package gustav_dev.a2_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends Activity {
    private String text1, text2, text3, text4;
    Intent temp;
    private EditText ed1, ed2,ed3, ed4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
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
        */



    }





    /*
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        ed1 = (EditText) findViewById(R.id.mult_text_1);
        ed2 = (EditText) findViewById(R.id.mult_text_2);
        ed3 = (EditText) findViewById(R.id.mult_text_3);
        ed4 = (EditText) findViewById(R.id.mult_text_4);

        text1 = ed1.getText().toString();
        text2 = ed2.getText().toString();
        text3 = ed3.getText().toString();
        text4 = ed4.getText().toString();

        savedInstanceState.putString("text1",text1);
        savedInstanceState.putString("text2",text2);
        savedInstanceState.putString("text3",text3);
        savedInstanceState.putString("text4",text4);

        Log.v("DEMO","---> onSaveInstanceState() Main<--- ");
    }


    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
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


        Log.v("DEMO","---> onRestoreInstanceState() Main<--- ");
    }
    */


    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        Log.v("DEMO","##### Activity Main: onCreateOptionsMenu() #####");
        MenuInflater mi = new MenuInflater(this);
        mi.inflate(R.menu.menu2, menu);
        return true;
    }



    public boolean onOptionsItemSelected(MenuItem item) {
        Log.v("DEMO","##### Activity Main: "+item.getTitle()+" #####");
        if (item.getItemId()==R.id.menuItemBuchfuehrung) {
            temp = new Intent(this, SecondActivity.class);
            //temp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(temp);

        }

        return true;
    }



    public void onStart() {
        super.onStart();
        Log.v("DEMO","---> onStart() Main <--- ");
    }

    public void onResume() {
        super.onResume();
        Log.v("DEMO","---> onResume() Main<--- ");
    }

    public void onPause() {
        super.onPause();
        Log.v("DEMO","---> onPause() Main <--- ");
    }

    public void onStop() {
        super.onStop();
        Log.v("DEMO","---> onStop() Main<--- ");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.v("DEMO","---> onDestroy() Main<--- ");
    }







}