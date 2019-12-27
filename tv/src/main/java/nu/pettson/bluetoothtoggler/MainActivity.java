/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package nu.pettson.bluetoothtoggler;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/*
 * MainActivity class that does everything that the app should do...
 */
public class MainActivity extends Activity {

    TextView bluetoothIs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bluetoothIs = (TextView) findViewById(R.id.bluetoothIs);
        setBluetoothText();
    }

    public void clickBluetoothOn(View v) {
        setBluetooth(true);
        setBluetoothText();
    }

    public void clickBluetoothOff(View v) {
        setBluetooth(false);
        setBluetoothText();
    }

    public void setBluetoothText() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        boolean isEnabled = true;

        if (bluetoothAdapter == null) {
            bluetoothIs.setText("Bluetooth is not supported on this device");
        }else {
            isEnabled = bluetoothAdapter.isEnabled();

            if (isEnabled) {
                bluetoothIs.setText("Bluetooth is turned on");
            } else {
                bluetoothIs.setText("Bluetooth is turned off");
            }
        }
    }

    public boolean setBluetooth(boolean enable) {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter != null) {
            boolean isEnabled = bluetoothAdapter.isEnabled();
            if (enable && !isEnabled) {
                return bluetoothAdapter.enable();
            }
            else if(!enable && isEnabled) {
                return bluetoothAdapter.disable();
            }
        }

        // No need to change bluetooth state
        return true;
    }
}
