package istecnico.csc;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class SplashActivity extends AppCompatActivity {

    LinearLayout slinearLayout, elinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        slinearLayout  = (LinearLayout) findViewById(R.id.init_layer);
        elinearLayout = (LinearLayout) findViewById(R.id.end_layer);

        elinearLayout.setVisibility(View.INVISIBLE);

        new CountDownTimer(6000, 1000) {
            public void onTick(long millisUntilFinished){}
            public void onFinish() {
                slinearLayout.setVisibility(View.INVISIBLE);
                elinearLayout.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void onClickPlay(View view) {
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        view.startAnimation(animAlpha);
        Intent i = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing APP")
                .setMessage("Are you sure you want to close this app?")
                .setPositiveButton("Yes...", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No!", null)
                .show();
    }
}