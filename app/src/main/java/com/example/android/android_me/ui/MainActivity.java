/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.android_me.R;

// This activity is responsible for displaying the master list of all images
// Implement the MasterListFragment callback, OnImageClickListener
public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{

    private int headPartId;
    private int bodyPartId;
    private int legsPartId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Define the behavior for onImageSelected
    public void onImageSelected(int position) {
        // Create a Toast that displays the position that was clicked
        Toast.makeText(this, "Position clicked = " + position, Toast.LENGTH_SHORT).show();

        int bodyFragment = position / 12;
        int bodyFragmentId = position - (bodyFragment * 12);

        switch (bodyFragment) {
            case 0:
                headPartId = bodyFragmentId;
                break;
            case 1:
                bodyPartId = bodyFragmentId;
                break;
            case 2:
                legsPartId = bodyFragmentId;
                break;
            default:
                break;
        }

        Bundle partsBundle = new Bundle();
        partsBundle.putInt("headPartId", headPartId);
        partsBundle.putInt("bodyPartId", bodyPartId);
        partsBundle.putInt("legsPartId", legsPartId);

        final Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtras(partsBundle);

        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }

}
