package com.example.pankajkatiyar.creditcard;

import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnCompute = (Button)findViewById(R.id.btnCompute);
        Button btnClear = (Button)findViewById(R.id.btnClear);
        final EditText editCardBalance = (EditText)findViewById(R.id.editCardBalance);
        final EditText editMinPay = (EditText)findViewById(R.id.editMinPay);
        final EditText editRate = (EditText)findViewById(R.id.editRate);
        final TextView txtFinalBal = (TextView)findViewById(R.id.txtFinalBal);
        final TextView txtINTPaid = (TextView)findViewById(R.id.txtINTPaid);
        final TextView txtMthRemain   = (TextView)findViewById(R.id.txtMthRemaining);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editCardBalance.setText("");
                editMinPay.setText("");
                editRate.setText("");
                txtFinalBal.setText("");
                txtINTPaid.setText("");
                txtMthRemain.setText("");
            }
        });

        btnCompute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double interest = Float.parseFloat(editRate.getText().toString());
                double principle = Float.parseFloat(editCardBalance.getText().toString());
                double minPay = Float.parseFloat(editMinPay.getText().toString());
                double mthinterest, mthPrinciple;
                double finalCardBal = 0, TotalInterest = 0;
                int count=1;
                boolean cont = true;
                mthinterest = Math.round(principle * interest / 120);
                if (minPay<=mthinterest){
                    Toast.makeText(MainActivity.this, "Please increase Minimum Payment", Toast.LENGTH_SHORT).show();
                }else {
                    Log.d("Principle1: ", String.valueOf(principle));
                    do {
                        mthinterest = Math.round(principle * interest / 120);
                        mthPrinciple = minPay - mthinterest;
                        principle = principle - mthPrinciple;
                        TotalInterest = TotalInterest + mthinterest;
                        Log.d("Minimum Pay: ", String.valueOf(minPay));
                        Log.d("Principle: ", String.valueOf(principle));
                        if (principle <= 0 || principle <= minPay) {
                            count++;
                            cont = false;
                        } else {
                            count++;
                        }
                    } while (cont);
                    finalCardBal = Float.parseFloat(editCardBalance.getText().toString());
                    txtFinalBal.setText(String.valueOf(finalCardBal));
                    txtINTPaid.setText(String.valueOf(TotalInterest));
                    txtMthRemain.setText(String.valueOf(count));
                }

            }
        });

    }
}
