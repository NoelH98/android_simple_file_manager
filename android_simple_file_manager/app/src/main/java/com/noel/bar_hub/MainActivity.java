package com.noel.bar_hub;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/*  @Author Noel.Eugene.Habaa */

public class MainActivity extends AppCompatActivity {

   private Button btn;

  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      setContentView(R.layout.activity_main);

      btn = (Button)findViewById(R.id.storage);

    btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(granted() == true){

            Intent i = new Intent(MainActivity.this, FileActivity.class);
            String path = Environment.getExternalStorageDirectory().getPath();
            i.putExtra("path",path);
            startActivity(i);

        }else{

            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                Toast.makeText(MainActivity.this,"Storage Permission Required!!",Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }

        }
    }
      });
  }

  private boolean granted(){
      int res = ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
      if(res == PackageManager.PERMISSION_GRANTED){
          return true;
      }else {
          return false;
      }
  }

  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
      super.onActivityResult(requestCode,resultCode,data);

  }

 @Override
    public void onDestroy(){
      super.onDestroy();
  }

}
