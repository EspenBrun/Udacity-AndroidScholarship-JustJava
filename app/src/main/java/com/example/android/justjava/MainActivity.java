/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

package com.example.android.justjava;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int quantity = 2;

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        display(quantity);
        displayPrice(quantity*5); //calling the displayPrice method
    }

    /**
     * This method is called when +button is clicked
     */
    public void increment(View view){
        //TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        //char quantity = quantityTextView.getText()+1;
        quantity = quantity+1;
        display(quantity);
    }

    /**
     * This method is called when -button is clicked
     */
    public void decrement(View view){
        if(quantity>0){
            quantity = quantity - 1;
        }
        else{
            // Do nothing.
        }
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen
     */
    private void displayPrice(int number){
        /**
         * This creats a javathing called TextView, which I here name priceTextView,
         * and I then associate that with the corresponding TextView in the XML file
         */
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);

        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }


}