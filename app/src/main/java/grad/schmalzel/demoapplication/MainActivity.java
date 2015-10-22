package grad.schmalzel.demoapplication;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button myButton;
    TextView myTextView;
    CheckBox myCheckbox;

    int counter = 0;
    private Button myResetButton;
    private Button locButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();

        getLocation();
    }

    private void getLocation() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try {
            Location l = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            myTextView.setText("Latitude:" + l.getLatitude()+", Longitude: " + l.getLongitude());
        } catch (SecurityException e) {
            Log.e("MainActivity",e.getMessage());
        }
    }


    private void setupUI() {
        // Find all views
        myButton = (Button) findViewById(R.id.myButton);
        locButton = (Button) findViewById(R.id.locButton);
        myResetButton = (Button) findViewById(R.id.myResetButton);
        myTextView = (TextView) findViewById(R.id.myTv);
        myCheckbox = (CheckBox) findViewById(R.id.myCb);

        // Set on click listeners for views
        myButton.setOnClickListener(incrementListener);
        myResetButton.setOnClickListener(resetListener);
        myCheckbox.setOnCheckedChangeListener(enableCounterListener);
    }

    View.OnClickListener incrementListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            counter++;

            String counterValueStr = String.valueOf(counter);

            myTextView.setText(counterValueStr);

            Log.i("MainActivity/incrementListener", "Counter is: " + counterValueStr);

        }
    };

    View.OnClickListener resetListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            counter = 0;

            String counterValueStr = String.valueOf(counter);

            myTextView.setText(counterValueStr);

        }
    };

    CompoundButton.OnCheckedChangeListener enableCounterListener =
            new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                /*if(b) {
                    myButton.setEnabled(true);
                } else {
                    myButton.setEnabled(false);
                }*/

                myButton.setEnabled(b);
            }
        };


    View.OnClickListener updateLocListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getLocation();
        }
    };


}
