package com.example.gpslocationapp;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText locationInput;
    private Button findButton;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationInput = findViewById(R.id.locationInput);
        findButton = findViewById(R.id.findButton);
        resultText = findViewById(R.id.resultText);

        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String locationName = locationInput.getText().toString().trim();
                if (!locationName.isEmpty()) {
                    getCoordinates(locationName);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a location!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getCoordinates(String location) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocationName(location, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                double latitude = address.getLatitude();
                double longitude = address.getLongitude();

                resultText.setText("Coordinates:\nLatitude: " + latitude + "\nLongitude: " + longitude);
            } else {
                resultText.setText("Location not found!");
            }
        } catch (IOException e) {
            Toast.makeText(this, "Error fetching location!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
