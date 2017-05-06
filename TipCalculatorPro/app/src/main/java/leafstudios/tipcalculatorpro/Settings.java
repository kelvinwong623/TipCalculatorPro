package leafstudios.tipcalculatorpro;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Settings extends AppCompatActivity {

    EditText foodSet;
    EditText otherSet;
    EditText serviceSet;

    Button setButton;
    Button resetButton;

    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        foodSet = (EditText) findViewById(R.id.foodSet);
        serviceSet = (EditText) findViewById(R.id.serviceSet);
        otherSet = (EditText) findViewById(R.id.otherSet);

        setButton = (Button) findViewById(R.id.setButton);
        resetButton = (Button) findViewById(R.id.resetButton);

        settings = getPreferences(Context.MODE_PRIVATE);

        foodSet.setText(settings.getString("foodPart", "0.5"));
        serviceSet.setText(settings.getString("servicePart", "0.3"));
        otherSet.setText(settings.getString("otherPart", "0.2"));
    }

    public void set(View view)
    {
        double food = Double.parseDouble(foodSet.getText().toString());
        double service = Double.parseDouble(serviceSet.getText().toString());
        double other = Double.parseDouble(otherSet.getText().toString());
        SharedPreferences.Editor editor;

        if(food + service + other == 1)
        {
            editor = settings.edit();
            editor.putString("foodPart", foodSet.getText().toString());
            editor.putString("servicePart", serviceSet.getText().toString());
            editor.putString("otherPart", otherSet.getText().toString());
            editor.commit();
        }
        else
        {
            Log.d("test", "OVERFLOW");
        }

    }

    public void reset(View view)
    {

    }



}
