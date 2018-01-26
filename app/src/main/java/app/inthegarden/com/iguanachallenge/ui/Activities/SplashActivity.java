package app.inthegarden.com.iguanachallenge.ui.Activities;

import android.content.Intent;
import android.os.Handler;
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
            @Override
            public void run() {
                onStop();
            }
        },1000);
    }

}

