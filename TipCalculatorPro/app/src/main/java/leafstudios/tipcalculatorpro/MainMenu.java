package leafstudios.tipcalculatorpro;

import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {

    Spinner foodRatingSpinner;
    Spinner serviceRatingSpinner;
    Spinner otherRatingSpinner;
    EditText totalAmtEdit;
    EditText maxTipEdit;
    TextView resAmountText;
    TextView resPercentText;

    ArrayAdapter <CharSequence> foodRatingAdapter;
    ArrayAdapter <CharSequence> serviceRatingAdapter;
    ArrayAdapter <CharSequence> otherRatingAdapter;

    int foodRating;
    int serviceRating;
    int otherRating;

    double foodPart;
    double servicePart;
    double otherPart;

    double totalAmt;
    double maxPercent;

    double resAmt;
    double resPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //setup food spinner
        foodRatingSpinner = (Spinner) findViewById(R.id.foodSpinner);
        foodRatingAdapter = ArrayAdapter.createFromResource(this, R.array.ratings, android.R.layout.simple_spinner_item);
        foodRatingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodRatingSpinner.setAdapter(foodRatingAdapter);

        //setup server spinner
        serviceRatingSpinner = (Spinner) findViewById(R.id.serviceSpinner);
        serviceRatingAdapter = ArrayAdapter.createFromResource(this, R.array.ratings, android.R.layout.simple_spinner_item);
        serviceRatingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        serviceRatingSpinner.setAdapter(serviceRatingAdapter);

        //setup other spinner
        otherRatingSpinner = (Spinner) findViewById(R.id.otherSpinner);
        otherRatingAdapter = ArrayAdapter.createFromResource(this, R.array.ratings, android.R.layout.simple_spinner_item);
        otherRatingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        otherRatingSpinner.setAdapter(serviceRatingAdapter);

        //setup EditText
        totalAmtEdit = (EditText) findViewById(R.id.totalAmtEdit);
        totalAmtEdit.setFilters(new InputFilter[] {new DecimalDigitsIF(6,2)});
        maxTipEdit = (EditText) findViewById(R.id.maxTipEdit);
        maxTipEdit.setFilters(new InputFilter[] {new DecimalDigitsIF(2,2)});

        //setup changing TextViews
        resAmountText = (TextView) findViewById(R.id.resAmount);
        resPercentText = (TextView) findViewById(R.id.resPercent);

        //set up the parts that make up the 100% of rating
        //it is hard coded at the moment
        foodPart = .5;
        servicePart = .3;
        otherPart = .2;

    }

    public void calculateTip(View view)
    {
        Log.d("test", "CALCULATING");
        double ans;

        // gather values from user-inputted choices and values
        totalAmt = Double.parseDouble(totalAmtEdit.getText().toString());
        maxPercent = Double.parseDouble(maxTipEdit.getText().toString());
        foodRating = Integer.parseInt(foodRatingSpinner.getSelectedItem().toString());
        serviceRating = Integer.parseInt(serviceRatingSpinner.getSelectedItem().toString());
        otherRating = Integer.parseInt(otherRatingSpinner.getSelectedItem().toString());

        //calculate food part
        double food = 0;
        switch(foodRating) {
            case 0:
                food = 0;
                break;
            case 1:
                food = maxPercent * (foodPart * .2);
                break;
            case 2:
                food = maxPercent * (foodPart * .4);
                break;
            case 3:
                food = maxPercent * (foodPart * .6);
                break;
            case 4:
                food = maxPercent * (foodPart * .8);
                break;
            case 5:
                food = maxPercent * (foodPart);
                break;
        }
        //calculate service part
        double service = 0;
        switch(serviceRating)
        {
            case 0:
                service = 0;
                break;
            case 1:
                service = maxPercent * (servicePart * .2);
                break;
            case 2:
                service = maxPercent * (servicePart * .4);
                break;
            case 3:
                service = maxPercent * (servicePart* .6);
                break;
            case 4:
                service = maxPercent * (servicePart* .8);
                break;
            case 5:
                service = maxPercent * (servicePart);
                break;
        }
        //calculate other part
        double other = 0;
        switch(otherRating)
        {
            case 0:
                other = 0;
                break;
            case 1:
                other = maxPercent * (otherPart * .2);
                break;
            case 2:
                other = maxPercent * (otherPart * .4);
                break;
            case 3:
                other = maxPercent * (otherPart * .6);
                break;
            case 4:
                other = maxPercent * (otherPart * .8);
                break;
            case 5:
                other = maxPercent * (otherPart);
                break;
        }
        //figure out percentage of the max tip
        Log.d("test", "food: " + food);
        Log.d("test", "service: " + service);
        Log.d("test", "other: " + other);
        double givenPercent = food + service + other;
        ans = totalAmt * givenPercent;
        resAmt = ans;
        resPercent = ans / totalAmt;
        Log.d("test", "COMPLETED");
        updateResults();
    }

    public void updateResults()
    {
        Log.d("test", "UPDATING RESULTS");
        DecimalFormat df = new DecimalFormat("#.00");
        String a = "$" + df.format(resAmt);
        resAmountText.setText(a);
        String b = df.format(resPercent * 100) + "%";
        resPercentText.setText(b);
    }
}
