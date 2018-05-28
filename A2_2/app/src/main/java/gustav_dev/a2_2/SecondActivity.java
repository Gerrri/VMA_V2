package gustav_dev.a2_2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class SecondActivity extends Activity {
    Intent temp;
    ArrayList<String> data = new ArrayList<String>();
    ArrayAdapter<String> darray;
    boolean already_started =false; //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ListView listView1 = (ListView) findViewById(R.id.ListView1);

        darray = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
        listView1.setAdapter(darray);


        //Debug
        Button popupbutton = (Button) findViewById(R.id.popupbutton);
        popupbutton.setOnClickListener(new MyOnClickListener()); // Setzen des Button-Listeners (siehe unten)

        data.add("54.88");
        data.add("859.36");
        data.add("7.58");
        data.add("99.99");
        darray.notifyDataSetChanged();


    }

    /**
     * Ruft den Eingabe Dialog nach dem Start der Activity auf.
     * @param hasFocus
     */
    public void onWindowFocusChanged (boolean hasFocus){
        if(already_started == false) {
            openDialog(findViewById(android.R.id.content));
            already_started = true;
        }
    }

    /**
     * Ruft den Eingabe Dialog beim Click auf den Button auf
     */
    class MyOnClickListener implements View.OnClickListener {

        public void onClick(View v) {
            openDialog(v);
        }
    }

    /**
     * Öffnet den Dialog
     * @param v benötigt der Aktuellen View
     */
    public void openDialog(View v) {

        Log.v("DEMO","---> openDialog(View v) <--- ");

        // Erzeugung des PopupWindows (siehe Klassendefinition unten)
        final MyPopupWindow pw = new MyPopupWindow(SecondActivity.this);

        // Festlegung eines Listeners, der ausgeführt wird, wenn das PopupWindows schließt
        pw.setOnDismissListener(new PopupWindow.OnDismissListener() {  // ordnet ihm einen "OnDismissListener" zu, der bei Schlie�en des PopupWindows aktiv wird

            public void onDismiss() {
                // Beschaffung der Benutzereingabe aus dem PopupWindow
                String eingegebenerName = pw.eingabefeld.getText().toString();
                // Anzeige eines Toasts mit der Eingabe
                // Minimalanzeige:
                //  Toast.makeText(PopupWindowDemo.this, "Hallo " + eingegebenerName, Toast.LENGTH_LONG).show();
                // alternativ: Toast-Anzeige mit individualisiertem Layout



               /*
                Toast myToast = Toast.makeText(SecondActivity.this, "Eintrag hinzugefügt: " + eingegebenerName, Toast.LENGTH_LONG);
                myToast.setGravity(Gravity.CENTER, 0, 0);
                View myToastView = myToast.getView();
                myToastView.setBackgroundColor(Color.BLACK);
                TextView myToastMessage = (TextView) myToastView.findViewById(android.R.id.message);
                myToastMessage.setTextSize(24);
                myToastMessage.setBackgroundColor(Color.BLACK);
                myToastMessage.setTextColor(Color.WHITE);
                myToast.show();
                */
            }
        });

         pw.showAtLocation(pw.layout, Gravity.CENTER, 0, 0); // Anzeige des PopupWindows

        //pw.showAtLocation(SecondActivity.this,Gravity.CENTER,0,0);

        // pw.update(0,0,320,380);  Hier: Angabe der Höhe und Breite in absoluten Pixeln (px)

        // Alternativ: Umrechnung in dichteunabhängige Pixel (dp)

        int breite = (int ) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 320, getResources().getDisplayMetrics());
        int hoehe = (int )TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 380, getResources().getDisplayMetrics());
        pw.update(0,0,breite,hoehe);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        Log.v("DEMO","##### Activity Second: onCreateOptionsMenu() #####");
        MenuInflater mi = new MenuInflater(this);
        mi.inflate(R.menu.menu1, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Log.v("DEMO","##### Activity Second: "+item.getTitle()+" #####");
        if (item.getItemId()==R.id.menuItemNotizen) {
            temp = new Intent(this, MainActivity.class);
            //temp.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(temp);
        }

        return true;
    }

    class MyPopupWindow extends PopupWindow {

        EditText eingabefeld;  // Textfeld zur Eingabe eines Strings
        Button okButton,abbrechenButton;       // Bestätigungsbutton
        LinearLayout layout;   // Layout des PopupWindows

        MyPopupWindow(Activity aktuelleActivity) {
            super(aktuelleActivity);
            // Beschaffung des Layouts des PopupWindows aus der zugeordneten XML-Datei
            LayoutInflater inflater = (LayoutInflater) aktuelleActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = (LinearLayout) inflater.inflate(R.layout.popupmenu,null,false);
            // Komponenten des Layouts
            eingabefeld = (EditText) layout.findViewById(R.id.eingabefeld);

            //OK BUTTON
            okButton = (Button) layout.findViewById(R.id.okButton);
            okButton.setOnClickListener(new ButtonListener());

            //Abbrechen Button
            abbrechenButton = (Button) layout.findViewById(R.id.abbrechen_button);
            abbrechenButton.setOnClickListener(new ButtonListenerabbrechen());

            // Anzeige des Layouts im PopupWindow
            setContentView(layout);
            setFocusable(true);  // damit das Popup Window Eingaben entgegegennehmen kann
        }

        // Listener für den Bestätigungsbutton im PopupWindow:
        // schließt das PopupWindow

        private class ButtonListener implements View.OnClickListener {
            public void onClick(View v) {
                CharSequence itemText = ((TextView)v).getText();


                data.add(0,eingabefeld.getText().toString());
                darray.notifyDataSetChanged();
                dismiss();
            }
        }

        private class ButtonListenerabbrechen implements View.OnClickListener {
            public void onClick(View v) {
                dismiss();
            }
        }



    }


    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        String save[] = new String[data.size()];

        for(int i=0; i<data.size(); i++){
            save[i] = data.get(i);
        }

        savedInstanceState.putStringArray("data", save);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String restore[] = new String[savedInstanceState.getStringArray("data").length];

        //data = savedInstanceState.getStringArray("data");
        data.clear();
        restore = savedInstanceState.getStringArray("data");

        for(int i=0;i<savedInstanceState.getStringArray("data").length;i++){
            data.add(restore[i]);
        }

        darray.notifyDataSetChanged();
    }




}
