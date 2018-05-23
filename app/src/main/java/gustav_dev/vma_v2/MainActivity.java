package gustav_dev.vma_v2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // Variablen für TextViews(Number)
    private int sum=0,rad=0,fuss=0;
    private EditText et_sum;
    private EditText et_rad;
    private EditText et_fuss;

    // Variablen für Dialog in Reset Funktion
    static final int JA_NEIN_VIELLEICHT_DIALOG_ID = 1;  // Codes zur Übergabe an showDialog(), die verschiedene Dialoge identifizieren (siehe unten)
    static final int AUSWAHL_DIALOG_ID = 2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //final Button switchButton1_2 = (Button) findViewById(R.id.switchActivity1_2);
        final Button btn_reset = (Button) findViewById(R.id.btn_reset);
        final Button btn_rad = (Button) findViewById(R.id.btn_rad);
        final Button btn_fuss = (Button) findViewById(R.id.btn_fuss);

        //switchButton1_2.setOnClickListener(new SwitchButtonListener1_2());
        btn_reset.setOnClickListener(new Listener_btn_reset());
        btn_rad.setOnClickListener(new Listener_btn_rad());
        btn_fuss.setOnClickListener(new Listener_btn_fuss());




        et_sum = (EditText) findViewById(R.id.et_sum);
        et_sum.setText(""+sum);

        et_rad = (EditText) findViewById(R.id.et_rad);
        et_rad.setText(""+rad);

        et_fuss = (EditText) findViewById(R.id.et_fuss);
        et_fuss.setText(""+fuss);

    }


    class Listener_btn_reset implements View.OnClickListener {   // Listener des Buttons
        public void onClick(View v) {
            showDialog(1);
        }
    }

    class Listener_btn_rad implements View.OnClickListener {   // Listener des Buttons
        public void onClick(View v) {
            Log.v("MainActivity_Log","---> Button Radfahrer Pressed <--- ");
            //Intent myIntent = new Intent(v.getContext(), MainActivity.class); // Durch Übergabe dieses Intent-Objekts an startActivity():
            //myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            sum++;
            rad++;

            et_rad.setText(""+rad);
            et_sum.setText(""+sum);

        }
    }


    class Listener_btn_fuss implements View.OnClickListener {   // Listener des Buttons
        public void onClick(View v) {
            Log.v("MainActivity_Log","---> Button Fussgaenger Pressed <--- ");
            //Intent myIntent = new Intent(v.getContext(), MainActivity.class); // Durch Übergabe dieses Intent-Objekts an startActivity():
            //myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            sum++;
            fuss++;

            et_fuss.setText(""+fuss);
            et_sum.setText(""+sum);
        }
    }



    @Override
    protected Dialog onCreateDialog(int id, Bundle args) {

        AlertDialog.Builder dialogBuilder;  // DialogBuilder zum Aufbau des Dialogs
        AlertDialog dialog;  // Variable für den aufzubauenden Dialog


        dialogBuilder = new AlertDialog.Builder(this);  // DialogBuilder zum Aufbau des Dialogs ('this' referenziert die Activity)
        dialogBuilder.setMessage("Sollen die Zähler wirklich zurückgestezt werden ?");
        dialogBuilder.setCancelable(false);  // false = Benutzer kann den Dialog nicht mit dem Back-Button schließen
        // Im Folgenden: Definition der Buttons mit ihren Beschriftungen und Listenern.
        // Es können maximal drei Buttons vereinbart werden (Positive/Negative/Neutral).
        dialogBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Log.v("MainActivity_Log","---> Button Reset Pressed <--- ");
                //Intent myIntent = new Intent(v.getContext(), MainActivity.class); // Durch Übergabe dieses Intent-Objekts an startActivity():
                //myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


                sum=0;
                rad=0;
                fuss=0;

                et_rad.setText(""+rad);
                et_sum.setText(""+sum);
                et_fuss.setText(""+fuss);

                zeigeToast("Zähler zurückgesetzt");
            }
        });
        dialogBuilder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                zeigeToast("Zähler NICHT zurückgesetzt");
            }
        });

        dialog = dialogBuilder.create();  // Erzeugung des Dialogs
        return dialog;
    }


        void zeigeToast(String text) {
            Toast myToast = Toast.makeText(this, text, Toast.LENGTH_LONG);
            myToast.setGravity(Gravity.CENTER, 0, 0);
            View toastView = myToast.getView();
            toastView.setBackgroundColor(Color.BLACK);
            TextView toastMessage = (TextView) toastView.findViewById(android.R.id.message);
            toastMessage.setTextSize(24);
            toastMessage.setBackgroundColor(Color.BLACK);
            toastMessage.setTextColor(Color.WHITE);
            myToast.show();
        }

}
