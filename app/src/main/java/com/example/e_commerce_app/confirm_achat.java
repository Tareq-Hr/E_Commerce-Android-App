package com.example.e_commerce_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalPaymentDetails;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.math.BigDecimal;

public class confirm_achat extends AppCompatActivity {
    TextView m_response,total;
    PayPalConfiguration m_configuration;
    String m_paypalClientId="Ad3vuRcSFEsw4Wx6PX430xzAyzyKKNKkcgOwYrq52kHgC0-e9Ozg13e61d9lNpDPCCCX2iVt7ssdnxgB";
    Intent m_service;
    int m_paypalRequestCode= 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_achat);
        m_response = (TextView) findViewById(R.id.response);
        total = (TextView) findViewById(R.id.total);
        m_configuration = new PayPalConfiguration()
                .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                .clientId(m_paypalClientId);
        m_service = new Intent(this, PayPalService.class);
        m_service.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, m_configuration);
        startService(m_service);
        //hada bash bash ybaan z3ma total fhad l'interf hta yji lprix bsah
        int tot = 10;
        total.setText(getString(R.string.currency,tot));

    }
     public void confirmer(View view){
         int prix = 10;
         PayPalPayment payment = new PayPalPayment(new BigDecimal(prix),"USD","Cours",
                 PayPalPayment.PAYMENT_INTENT_SALE);
         Intent intent = new Intent(this,PaymentActivity.class);
         intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, m_configuration);
         intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
         startActivityForResult(intent, m_paypalRequestCode);
         }
         protected void onActivityResult(int requestCode, int resultCode, Intent data){

         super.onActivityResult(requestCode,resultCode, data);
         if(requestCode == m_paypalRequestCode){

             if(resultCode == Activity.RESULT_OK)
             {

                 PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                 if (confirmation != null){
                     String state = confirmation.getProofOfPayment().getState();
                     if(state.equals("approved"))
                         m_response.setText("l'opération de paiement est réussie !");
                     else
                         m_response.setText("l'opération de paiement non réussie !");

                 }
                 else
                     m_response.setText("Confirmation is null");
             }

         }

         }



}