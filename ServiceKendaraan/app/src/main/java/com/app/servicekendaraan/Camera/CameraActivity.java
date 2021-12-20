package com.app.servicekendaraan.Camera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import com.app.servicekendaraan.R;

public class CameraActivity extends AppCompatActivity {

    static final int ACTION_IMAGE_CAPTURE = 1;
    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_camera);
        Button buttonCamera = (Button) findViewById(R.id.btnCam);
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override            public void onClick(View v) {
                Intent takeCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takeCamera.resolveActivity(getPackageManager()) !=null){
                    startActivityForResult(takeCamera, ACTION_IMAGE_CAPTURE);                }
            }
        });
    }
}