package com.ricardogodoy.wifitest;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private TextView interfaces;
    private EditText ssid;
    private EditText ssidPassword;
    private Button connect;

    private WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Interface
        interfaces = (TextView) this.findViewById(R.id.interfaces);
        ssid = (EditText) this.findViewById(R.id.ssid);
        ssidPassword = (EditText) this.findViewById(R.id.password);
        connect = (Button) this.findViewById(R.id.connect);

        // Wifi Manager
        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        // On 'connect' button click
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                WifiConfiguration wifiConfig = new WifiConfiguration();
                wifiConfig.SSID = String.format("\"%s\"", ssid.getText());
                wifiConfig.preSharedKey = String.format("\"%s\"", ssidPassword.getText());

                // Toast.makeText(MainActivity.this, wifiConfig.SSID + " / " +  wifiConfig.preSharedKey, Toast.LENGTH_LONG).show();

                int netId = wifiManager.addNetwork(wifiConfig);

                boolean success;

                success = wifiManager.disconnect();

                success = success && wifiManager.enableNetwork(netId, true);

                success = success && wifiManager.reconnect();

                Toast.makeText(MainActivity.this, success ? "Sucesso" : "Falha" + " ao conectar", Toast.LENGTH_LONG).show();
            }
        });
    }
}
