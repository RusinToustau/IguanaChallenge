package app.inthegarden.com.iguanachallenge.ui.Activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import app.inthegarden.com.iguanachallenge.R;
import app.inthegarden.com.iguanachallenge.io.HTTPConnectionManager;

public class SplashActivity extends AppCompatActivity {
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        if(!HTTPConnectionManager.isNetworkinOnline(this)){
            failConection();
        }else{
            goToMenu();
        }
    }

    private void goToMenu(){
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MenuActivity.class));
                finish();
            }
        },2500);
    }

    private void failConection(){
        handler = new Handler();
        Toast.makeText(this,"Debes estar conectado para continuar"
                ,Toast.LENGTH_SHORT).show();
        handler.postDelayed(new Runnable() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run() {
                finishAffinity();
            }
        },1500);
    }

}

