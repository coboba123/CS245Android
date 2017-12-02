package cpp.cs245final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    private TextView tv;
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        iv = findViewById(R.id.diamondDogLogo);
        tv = findViewById(R.id.splashscreen);
        Animation myanimation = AnimationUtils.loadAnimation(this,R.anim.transition);
        tv.startAnimation(myanimation);
        iv.startAnimation(myanimation);
        final Intent i = new Intent(this,MenuActivity.class);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally{
                    startActivity(i);
                    finish();
                }
            }
        };

        timer.start();
    }
}
