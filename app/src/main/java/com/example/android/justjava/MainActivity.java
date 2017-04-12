/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

package com.example.android.justjava;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    private int quantity;
    private int price;
    private String name;
    private int coffeePrice = 5;


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        displayQuantity(quantity);

        CharSequence toastText;
        int duration = Toast.LENGTH_SHORT;
        String orderSummary;
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));

         /**
          * toast if try to order 0 coffees
          * special toast and email with order for 100 coffees
          * toast and email order for regular orders
          */
        if(quantity==0){
            toastText = "Dude coffee is life";
            Toast.makeText(this, toastText, duration).show();
        } else if(quantity==100){
            orderSummary = createOrderSummary(quantity);
            toastText = "Favorite customer!";
            Toast.makeText(this, toastText, duration).show();
            intent.putExtra(Intent.EXTRA_SUBJECT, "Jacobsen and Svart order for " + getName());
            intent.putExtra(Intent.EXTRA_TEXT,orderSummary);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        } else {
            orderSummary = createOrderSummary(quantity);
            toastText = "Nice order bro";
            Toast.makeText(this, toastText, duration).show();
            intent.putExtra(Intent.EXTRA_TEXT,orderSummary);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }

    /**
     * This method is called when +button is clicked
     */
    public void increment(View view){
        if(quantity==100) {
            Toast.makeText(this, "No more than 100 coffees I beg you",Toast.LENGTH_SHORT).show();
            displayTotal(calculatePrice());
        } else {
            quantity = quantity + 1;
            displayQuantity(quantity);
            displayTotal(calculatePrice());
        }
    }

    /**
     * This method is called when -button is clicked
     */
    public void decrement(View view){
        if(quantity==0){
            Toast.makeText(this,"No.",Toast.LENGTH_SHORT).show();
            displayTotal(calculatePrice());
        } else {
            quantity = quantity - 1;
            calculatePrice();
            displayQuantity(quantity);
            displayTotal(calculatePrice());
        }
    }

    /**
     * Display quantity on screen
     */
    private void displayQuantity(int amount) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + amount);
    }

    /**
    * Display order summary in screen
    */
    private void displayTotal(int total){
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText("Total: $" + total);
    }

    /**
     * Calculate the price
     */
    private int calculatePrice(){
        int cream = addWhippedCream() ? 1 : 0;
        int chocloate = addChocolate() ? 2 : 0;
        price = (cream + chocloate + coffeePrice) * quantity;
        return price;
    }

    /**
     * Create order summary
     */
    private String createOrderSummary(int quantity){
        String orderSummary = "Name: Mr " + getName();
        orderSummary += "\nWant whipped cream: " + addWhippedCream();
        orderSummary += "\nWant chocolate: "+ addChocolate();
        orderSummary += "\nQuantity: " + quantity;
        orderSummary += "\nTotal: $" + calculatePrice();
        orderSummary += "\nThank you!";

        return orderSummary;
    }

    /**
    * Adding whipped Cream
    */
    private boolean addWhippedCream(){
        CheckBox checkBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean addWhippedCream = checkBox.isChecked();

        return addWhippedCream;
    }

    /**
     * Adding whipped Cream
     */
    private boolean addChocolate(){
        CheckBox checkBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean addChocolate = checkBox.isChecked();

        return addChocolate;
    }

    /**
     * Get name from UI
     */
    private String getName(){
        EditText editText = (EditText) findViewById(R.id.name_field);
        name = editText.getText().toString();

        return name;
    }

    public void checkBoxClick(View view){
        displayTotal(calculatePrice());
    }
}