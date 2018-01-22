package istecnico.csc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickRegister(View view){
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        view.startAnimation(animAlpha);
        Intent i = new Intent(MainActivity.this, Register.class);
        startActivity(i);
    }

    public void onClickSignIn(View view){
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        view.startAnimation(animAlpha);
        Intent i = new Intent(MainActivity.this, LogIn.class);
        startActivity(i);
    }

    public void onClickAbout(View view){
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        view.startAnimation(animAlpha);
        Intent i = new Intent(MainActivity.this, About.class);
        startActivity(i);
    }
}