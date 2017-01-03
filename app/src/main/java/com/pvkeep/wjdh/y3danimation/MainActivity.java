package com.pvkeep.wjdh.y3danimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private My3dAnimation animation;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageview);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation = new My3dAnimation(0f, 180f, imageView.getWidth() / 2.0f, imageView.getHeight() / 2.0f);
                animation.setDuration(1000);
                animation.setInterpolator(new LinearInterpolator());
                animation.setFillAfter(true);
                imageView.startAnimation(animation);
            }
        });
    }
}
