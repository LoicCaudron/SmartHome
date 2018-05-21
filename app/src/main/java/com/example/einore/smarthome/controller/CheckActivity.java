package com.example.einore.smarthome.controller;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.einore.smarthome.R;
import com.example.einore.smarthome.model.User;
import com.example.einore.smarthome.model.UserBDD;

public class CheckActivity extends AppCompatActivity {

    User user;
    UserBDD userBdd;

    int id;
    String phone_num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        setTitle("Vérification");

        final EditText temperature = (EditText) findViewById(R.id.temp);
        final EditText humidity = (EditText) findViewById(R.id.hum);
        final EditText battery = (EditText) findViewById(R.id.batt);

        Button check = (Button) findViewById(R.id.verifier);

        Bundle bundle= getIntent().getExtras();
        if(bundle != null){
            id = (int) bundle.get("ID");
            //phone_num = (String) bundle.getParcelable("PHONE");
        }
        else{
            id = 0;
        }

        userBdd = new UserBDD(CheckActivity.this);
        userBdd.openForRead();
        user = userBdd.getUser(id);
        userBdd.close();

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float temp_min,temp_max,humidity_min, humidity_max, battery_min, battery_max;
                float temp_check, humidity_check, battery_check;

                try{
                    phone_num = user.getPhone();
                    temp_min = user.getT_min();
                    temp_max = user.getT_max();
                    humidity_min = user.getHumidity_min();
                    humidity_max = user.getHumidity_max();
                    battery_min = user.getBattery_min();
                    battery_max = user.getBattery_max();

                    temp_check = Float.parseFloat(temperature.getText().toString());
                    humidity_check = Float.parseFloat(humidity.getText().toString());
                    battery_check = Float.parseFloat(battery.getText().toString());


                    if(temp_check<temp_min){
                        String message = "Tourne un peu la vanne du radiateur. Il ne fait que " + temp_check + "°C";
                        //SmsManager.getDefault().sendTextMessage(phone_num, null, message, null, null );
                        sms(message);
                        Toast.makeText(CheckActivity.this, "test réussi",Toast.LENGTH_LONG).show();
                    }

                    if(temp_check>temp_max){
                        String message = "Ouvre un peu une fenêtre. Il fait chaud ici! Il fait " + temp_check +"°C";
                        //SmsManager.getDefault().sendTextMessage(phone_num, null, message, null, null );
                        sms(message);
                    }

                    if(humidity_check<humidity_min){
                        String message = "Il fait sec ici:" + humidity_check + "%. Ouvre une fenêtre pour aérer un peu.";
                        //SmsManager.getDefault().sendTextMessage(phone_num, null, message, null, null );
                        sms(message);
                    }

                    if(humidity_check>humidity_max){
                        String message = "Il fait tellement humide ici:" + humidity_check + "% que je vais croire qu'il pleut à l'intérieur.";
                        //SmsManager.getDefault().sendTextMessage(phone_num, null, message, null, null );
                        sms(message);
                    }

                    if(battery_check<battery_min){
                        String message = "Il est temps de changer ma batterie. Je suis à plat. Il ne me reste que " + battery_check + "%.";
                        //SmsManager.getDefault().sendTextMessage(phone_num, null, message, null, null );
                        sms(message);
                    }

                    if(battery_check>battery_max){
                        String message = "Je ne sais pas ce qu'il m'arrive. Je suis survolté, ma batterie atteint " + battery_check + "%.";
                        //SmsManager.getDefault().sendTextMessage(phone_num, null, message, null, null );
                        sms(message);
                    }
                }
                catch(Exception e){
                    Toast.makeText(CheckActivity.this, e.toString(),Toast.LENGTH_LONG).show();
                }


            }


            private void sms(String message) {
                if(phone_num.length()>= 4){
                    SmsManager.getDefault().sendTextMessage(phone_num, null, message, null, null );
                }
            }
        });
    }

    // Here, thisActivity is the current activity
if (ContextCompat.checkSelfPermission(CheckActivity.this,
    Manifest.permission.SEND_SMS)
            != PackageManager.PERMISSION_GRANTED) {

        // Permission is not granted
        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale(CheckActivity.this,
                Manifest.permission.SEND_SMS)) {
            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.
        } else {
            // No explanation needed; request the permission
            ActivityCompat.requestPermissions(CheckActivity.this,
                    new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }
    } else {
        // Permission has already been granted
    }

}
