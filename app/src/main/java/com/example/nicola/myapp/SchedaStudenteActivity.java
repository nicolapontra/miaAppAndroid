package com.example.nicola.myapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.nicola.myapp.chat.BluetoothChatService;
import com.example.nicola.myapp.chat.Constants;
import com.example.nicola.myapp.chat.DeviceListActivity;
import com.example.nicola.myapp.sensori.SensoreListActivity;

import java.util.Calendar;

public class SchedaStudenteActivity extends AppCompatActivity {
    private BluetoothAdapter mBluetoothAdapter = null;
    private BluetoothChatService mChatService = null;
    private static final String TAG = "BluetoothChatFragment";
    private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
    private static final int REQUEST_CONNECT_DEVICE_INSECURE = 2;
    private static final int REQUEST_ENABLE_BT = 3;
    private String mConnectedDeviceName = null;
    private StringBuffer mOutStringBuffer;


    EditText editNome;
    EditText editCognome;
    EditText editDataNasc;
    EditText editSito;
    EditText editEsamiok;
    EditText editEsamiDaFare;
    EditText editMedia;
    EditText editCell1;
    EditText editCell2;
    EditText editCell3;
    RadioButton maschio;
    RadioButton femmina;
    RatingBar rating;
    ToggleButton modifica;
    Button cond ;
    Button connetti;

    Button changeData;
    static final int DATE_DIALOG_ID = 0;
    final Calendar c = Calendar.getInstance();
    private int mYear = c.get(Calendar.YEAR);
    private int mMonth = c.get(Calendar.MONTH);
    private int mDay = c.get(Calendar.DAY_OF_MONTH);

    //---------------------------------------------------------------------
    private static final String SCHEDA_PREF = "com.example.nicola.myapp.SCHEDASTUDENTE_PREF";

    //istanza del db
    StudenteDbHelper mDbHelper;
        //path adb
    //C:\Users\Nicola\AppData\Local\Android\Sdk\platform-tools

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheda_studente);

        editNome = (EditText) findViewById(R.id.editNome);
        editNome.setFocusable(false);
        editCognome = (EditText) findViewById(R.id.editCognome);
        editCognome.setFocusable(false);
        editDataNasc = (EditText) findViewById(R.id.editNascita);
        editDataNasc.setFocusable(false);
        editSito = (EditText) findViewById(R.id.editSito);
        editSito.setFocusable(false);
        editEsamiok = (EditText) findViewById(R.id.editEsami1);
        editEsamiok.setFocusable(false);
        editEsamiDaFare = (EditText) findViewById(R.id.editEsami2);
        editEsamiDaFare.setFocusable(false);
        editMedia = (EditText) findViewById(R.id.editMedia);
        editMedia.setFocusable(false);
        editCell1 = (EditText) findViewById(R.id.editCell1);
        editCell1.setFocusable(false);
        editCell2 = (EditText) findViewById(R.id.editCell2);
        editCell2.setFocusable(false);
        editCell3 = (EditText) findViewById(R.id.editCell3);
        editCell3.setFocusable(false);
        maschio = (RadioButton) findViewById(R.id.radiobtn1);
        maschio.setClickable(false);
        femmina = (RadioButton) findViewById(R.id.radiobtn2);
        femmina.setClickable(false);
        rating = (RatingBar) findViewById(R.id.ratingBar);
        rating.setClickable(false);

        modifica = (ToggleButton) findViewById(R.id.toggleButton);
        modifica.setOnClickListener(abilitaconferma);

        changeData = (Button) findViewById(R.id.btnChangeData);
        // add a click listener to the button
        changeData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        cond = (Button) findViewById(R.id.btnShare);
        connetti= (Button) findViewById(R.id.btnConnect);

        connetti.setOnClickListener(onConnect);
        initBT();
        //------------------------------------------------------------------------------------------
       /* SharedPreferences preferences = getSharedPreferences(SCHEDA_PREF, Context.MODE_PRIVATE);
        editNome.setText(preferences.getString("nome", ""));
        editCognome.setText(preferences.getString("cognome", ""));
        editDataNasc.setText(preferences.getString("datanascita", ""));
        editSito.setText(preferences.getString("sitoweb", ""));
        editEsamiok.setText(preferences.getString("esamisvolti", ""));
        editEsamiDaFare.setText(preferences.getString("esamidafare", ""));
        editMedia.setText(preferences.getString("media", ""));
        editCell1.setText(preferences.getString("cellulare1", ""));
        editCell2.setText(preferences.getString("cellulare2", ""));
        editCell3.setText(preferences.getString("cellulare3", ""));
        maschio.setChecked(preferences.getBoolean("maschio",false));
        femmina.setChecked(preferences.getBoolean("femmina",false));
        rating.setRating(preferences.getFloat("rating",0));*/

        //creazione istanza db
        mDbHelper = new StudenteDbHelper(this);

        //popolamento scheda

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor c = db.query(
                StudenteDbHelper.STUDENTE_TABLE_NAME,
                new String[]{StudenteDbHelper.COLONNA_NOME,StudenteDbHelper.COLONNA_COGNOME,
                StudenteDbHelper.COLONNA_NASCITA,StudenteDbHelper.COLONNA_SITOWEB,StudenteDbHelper.COLONNA_MASCHIO,
                StudenteDbHelper.COLONNA_FEMMINA,StudenteDbHelper.COLONNA_ESAMIOK,StudenteDbHelper.COLONNA_ESAMIDAFARE,
                StudenteDbHelper.COLONNA_MEDIA,StudenteDbHelper.COLONNA_RATING,StudenteDbHelper.COLONNA_NUM1,
                StudenteDbHelper.COLONNA_NUM2,StudenteDbHelper.COLONNA_NUM3},
                null,
                null,
                null,
                null,
                null
                );
        c.moveToFirst();
        while (c.moveToNext()) {
            editNome.setText(c.getString(c.getColumnIndexOrThrow(StudenteDbHelper.COLONNA_NOME)));
            editCognome.setText(c.getString(c.getColumnIndexOrThrow(StudenteDbHelper.COLONNA_COGNOME)));
            editDataNasc.setText(c.getString(c.getColumnIndexOrThrow(StudenteDbHelper.COLONNA_NASCITA)));
            editSito.setText(c.getString(c.getColumnIndexOrThrow(StudenteDbHelper.COLONNA_SITOWEB)));
            maschio.setChecked(Boolean.parseBoolean(c.getString(c.getColumnIndexOrThrow(StudenteDbHelper.COLONNA_MASCHIO))));
            femmina.setChecked(Boolean.parseBoolean(c.getString(c.getColumnIndexOrThrow(StudenteDbHelper.COLONNA_FEMMINA))));
            editEsamiok.setText(c.getString(c.getColumnIndexOrThrow(StudenteDbHelper.COLONNA_ESAMIOK)));
            editEsamiDaFare.setText(c.getString(c.getColumnIndexOrThrow(StudenteDbHelper.COLONNA_ESAMIDAFARE)));
            editMedia.setText(c.getString(c.getColumnIndexOrThrow(StudenteDbHelper.COLONNA_MEDIA)));
            rating.setRating(c.getColumnIndexOrThrow(StudenteDbHelper.COLONNA_RATING));
            editCell1.setText(c.getString(c.getColumnIndexOrThrow(StudenteDbHelper.COLONNA_NUM1)));
            editCell2.setText(c.getString(c.getColumnIndexOrThrow(StudenteDbHelper.COLONNA_NUM2)));
            editCell3.setText(c.getString(c.getColumnIndexOrThrow(StudenteDbHelper.COLONNA_NUM3)));
        }


    }//end onCreate------------------_______-------------------_____________-------------------
//--------------------------------------------------------------------------------------------------------
public View.OnClickListener onConnect = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ensureDiscoverable();
        Intent serverIntent = new Intent(getApplicationContext(), DeviceListActivity.class);
        startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_INSECURE);
    }
};

    private void initBT(){
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // If the adapter is null, then Bluetooth is not supported
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            finish();
        }
    }
    @Override
    public void onStart() {
        super.onStart();

        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);


        } else if (mChatService == null) {
           setupBluetooth();
        }
    };

    private void setupBluetooth(){
        mChatService = new BluetoothChatService(this, mHandler);
        mOutStringBuffer = new StringBuffer("");
        View.OnClickListener onShare = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(createMessage());
            }
        };
        cond.setOnClickListener(onShare);
    }
    private String createMessage(){
        String datiStudente = null;
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor c = db.query(
                StudenteDbHelper.STUDENTE_TABLE_NAME,
                new String[]{StudenteDbHelper.COLONNA_NOME,StudenteDbHelper.COLONNA_COGNOME,
                        StudenteDbHelper.COLONNA_NASCITA,StudenteDbHelper.COLONNA_SITOWEB,StudenteDbHelper.COLONNA_MASCHIO,
                        StudenteDbHelper.COLONNA_FEMMINA,StudenteDbHelper.COLONNA_ESAMIOK,StudenteDbHelper.COLONNA_ESAMIDAFARE,
                        StudenteDbHelper.COLONNA_MEDIA,StudenteDbHelper.COLONNA_RATING,StudenteDbHelper.COLONNA_NUM1,
                        StudenteDbHelper.COLONNA_NUM2,StudenteDbHelper.COLONNA_NUM3},
                null,
                null,
                null,
                null,
                null
        );
        c.moveToFirst();
            return "Nome: "+c.getString(c.getColumnIndexOrThrow(StudenteDbHelper.COLONNA_NOME))+"\n"+
                    "Cognome: "+c.getString(c.getColumnIndexOrThrow(StudenteDbHelper.COLONNA_COGNOME))+"\n"+
           "Data di nascita "+c.getString(c.getColumnIndexOrThrow(StudenteDbHelper.COLONNA_NASCITA))+"\n"+
            "Sito "+c.getString(c.getColumnIndexOrThrow(StudenteDbHelper.COLONNA_SITOWEB))+"\n"+
            "Maschio "+Boolean.parseBoolean(c.getString(c.getColumnIndexOrThrow(StudenteDbHelper.COLONNA_MASCHIO)))+"\n"+
            "Femmina "+Boolean.parseBoolean(c.getString(c.getColumnIndexOrThrow(StudenteDbHelper.COLONNA_FEMMINA)))+"\n"+
            "Esami superati :"+c.getString(c.getColumnIndexOrThrow(StudenteDbHelper.COLONNA_ESAMIOK))+"\n"+
            "Esami da sostenere :"+c.getString(c.getColumnIndexOrThrow(StudenteDbHelper.COLONNA_ESAMIDAFARE))+"\n"+
            "Media "+c.getString(c.getColumnIndexOrThrow(StudenteDbHelper.COLONNA_MEDIA))+"\n"+
            "Rating "+c.getString(c.getColumnIndexOrThrow(StudenteDbHelper.COLONNA_RATING))+"\n"+
            "Numero 1: "+c.getString(c.getColumnIndexOrThrow(StudenteDbHelper.COLONNA_NUM1))+"\n"+
            "Numero 2: "+c.getString(c.getColumnIndexOrThrow(StudenteDbHelper.COLONNA_NUM2))+"\n"+
            "Numero 3: "+c.getString(c.getColumnIndexOrThrow(StudenteDbHelper.COLONNA_NUM3));
    }

    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.MESSAGE_STATE_CHANGE:
                    switch (msg.arg1) {
                        case BluetoothChatService.STATE_CONNECTED:

                            break;
                        case BluetoothChatService.STATE_CONNECTING:

                            break;
                        case BluetoothChatService.STATE_LISTEN:
                        case BluetoothChatService.STATE_NONE:
                            break;
                    }
                    break;
                case Constants.MESSAGE_WRITE:
                    byte[] writeBuf = (byte[]) msg.obj;
                    // construct a string from the buffer
                    String writeMessage = new String(writeBuf);
                   // mConversationArrayAdapter.add("Me:  " + writeMessage);
                    break;
                case Constants.MESSAGE_READ:
                    byte[] readBuf = (byte[]) msg.obj;
                    // construct a string from the valid bytes in the buffer
                    String readMessage = new String(readBuf, 0, msg.arg1);
                   // mConversationArrayAdapter.add(mConnectedDeviceName + ":  " + readMessage);
                    break;
                case Constants.MESSAGE_DEVICE_NAME:
                    // save the connected device's name
                    mConnectedDeviceName = msg.getData().getString(Constants.DEVICE_NAME);
                    if (null != getApplicationContext()) {
                        Toast.makeText(getApplicationContext(), "Connected to "
                                + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
                    }
                    break;
                case Constants.MESSAGE_TOAST:
                    if (null != getApplicationContext()) {
                        Toast.makeText(getApplicationContext(), msg.getData().getString(Constants.TOAST),
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mChatService != null) {
            mChatService.stop();
        }
    }
    @Override
    public void onResume() {
        super.onResume();

        if (mChatService != null) {
            // Only if the state is STATE_NONE, do we know that we haven't started already
            if (mChatService.getState() == BluetoothChatService.STATE_NONE) {
                // Start the Bluetooth chat services
                mChatService.start();
            }
        }
    }
    private void ensureDiscoverable() {
        if (mBluetoothAdapter.getScanMode() !=
                BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            //abilita la visibilità a 300sec
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivity(discoverableIntent);
        }
    }
    private void sendMessage(String message) {
        // Check that we're actually connected before trying anything
        if (mChatService.getState() != BluetoothChatService.STATE_CONNECTED) {
            Toast.makeText(getApplicationContext(),"non connesso", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check that there's actually something to send
        if (message.length() > 0) {
            // Get the message bytes and tell the BluetoothChatService to write
            byte[] send = message.getBytes();
            mChatService.write(send);

            // Reset out string buffer to zero and clear the edit text field
            mOutStringBuffer.setLength(0);
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CONNECT_DEVICE_SECURE:
                // When DeviceListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK) {
                    connectDevice(data, true);
                }
                break;
            case REQUEST_CONNECT_DEVICE_INSECURE:
                // When DeviceListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK) {
                    connectDevice(data, false);
                }
                break;
            case REQUEST_ENABLE_BT:
                // When the request to enable Bluetooth returns
                if (resultCode == Activity.RESULT_OK) {
                    // Bluetooth is now enabled, so set up a chat session
                    setupBluetooth();
                } else {
                    // User did not enable Bluetooth or an error occurred

                    Toast.makeText(getApplicationContext(),"bt not enabled",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }
    private void connectDevice(Intent data, boolean secure) {
        // Get the device MAC address
        String address = data.getExtras()
                .getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
        // Get the BluetoothDevice object
        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
        // Attempt to connect to the device
        mChatService.connect(device, secure);
    }


    //-----------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------------
    // the callback received when the user "sets" the date in the dialog
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDisplay();
                }
            };
    // updates the date in the TextView
    private void updateDisplay() {
       editDataNasc.setText(
                new StringBuilder()
                        .append(mDay).append("-")
                        .append(mMonth + 1).append("-")
                        .append(mYear).append(" "));
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
    }

    //-----------------------------------------------------------------------------------

    public View.OnClickListener abilitaconferma = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            if (modifica.isChecked()) {

                editNome.setFocusableInTouchMode(true);
                editCognome.setFocusableInTouchMode(true);
                editDataNasc.setFocusableInTouchMode(true);
                editSito.setFocusableInTouchMode(true);
                editEsamiok.setFocusableInTouchMode(true);
                editEsamiDaFare.setFocusableInTouchMode(true);
                editMedia.setFocusableInTouchMode(true);
                editCell1.setFocusableInTouchMode(true);
                editCell2.setFocusableInTouchMode(true);
                editCell3.setFocusableInTouchMode(true);
                maschio.setClickable(true);
                femmina.setClickable(true);
                rating.setClickable(true);
            } else {

                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(StudenteDbHelper.COLONNA_NOME, editNome.getText().toString());
                values.put(StudenteDbHelper.COLONNA_COGNOME,editCognome.getText().toString());
                values.put(StudenteDbHelper.COLONNA_NASCITA,editDataNasc.getText().toString());
                values.put(StudenteDbHelper.COLONNA_SITOWEB,editSito.getText().toString());
                values.put(StudenteDbHelper.COLONNA_ESAMIOK,editEsamiok.getText().toString());
                values.put(StudenteDbHelper.COLONNA_ESAMIDAFARE,editEsamiDaFare.getText().toString());
                values.put(StudenteDbHelper.COLONNA_MEDIA,editMedia.getText().toString());
                values.put(StudenteDbHelper.COLONNA_NUM1,editCell1.getText().toString());
                values.put(StudenteDbHelper.COLONNA_NUM2,editCell2.getText().toString());
                values.put(StudenteDbHelper.COLONNA_NUM3,editCell3.getText().toString());
                values.put(StudenteDbHelper.COLONNA_MASCHIO,maschio.isChecked());
                values.put(StudenteDbHelper.COLONNA_FEMMINA,femmina.isChecked());
                values.put(StudenteDbHelper.COLONNA_RATING,rating.getRating());

                long newRowId;
                newRowId = db.insert(
                        StudenteDbHelper.STUDENTE_TABLE_NAME,
                        null,
                        values);
                Toast toast = Toast.makeText(getApplicationContext(),"Salvataggio completato",Toast.LENGTH_LONG);
                toast.show();

              /*  SharedPreferences preferences = getSharedPreferences(SCHEDA_PREF, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("nome", editNome.getText().toString());
                editor.putString("cognome", editCognome.getText().toString());
                editor.putString("datanascita", editDataNasc.getText().toString());
                editor.putString("sitoweb", editSito.getText().toString());
                editor.putString("esamisvolti", editEsamiok.getText().toString());
                editor.putString("esamidafare", editEsamiDaFare.getText().toString());
                editor.putString("media", editMedia.getText().toString());
                editor.putString("cellulare1", editCell1.getText().toString());
                editor.putString("cellulare2", editCell2.getText().toString());
                editor.putString("cellulare3", editCell3.getText().toString());
                editor.putBoolean("maschio",maschio.isChecked());
                editor.putBoolean("femmina",femmina.isChecked());
                editor.putFloat("rating",rating.getRating());

                editor.apply();*/

              //salvare i dati inseriti nel db

                editNome.setFocusable(false);
                editCognome.setFocusable(false);
                editDataNasc.setFocusable(false);
                editSito.setFocusable(false);
                editEsamiok.setFocusable(false);
                editEsamiDaFare.setFocusable(false);
                editMedia.setFocusable(false);
                editCell1.setFocusable(false);
                editCell2.setFocusable(false);
                editCell3.setFocusable(false);
                maschio.setClickable(false);
                femmina.setClickable(false);
                rating.setClickable(false);
            }
        }
    };

}
