package com.quy.thanh;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;

import com.quy.thanh.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    long speed = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loopAnim1();
            }
        });
    }

    boolean isEndLoop = false;

    void loopAnim1() {
        binding.play.animate()
                .alpha(0)
                .setDuration(speed)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        binding.layout1.animate()
                                .translationX(-binding.layout4.getWidth())
                                .setDuration(speed * 3)
                                .setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        loopAnim2();
                                    }
                                });
                    }
                });

    }
    void loopAnim2() {
        isEndLoop = false;
        binding.layout4.setTranslationX(0);
        binding.layout2.animate()
                .translationX(-binding.layout4.getWidth())
                .setDuration(speed)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        if (!isEndLoop) {
                            loopAnim3();
                        }

                    }
                });
    }

    void loopAnim3() {
        binding.layout3.animate()
                .translationX(-binding.layout4.getWidth())
                .setDuration(speed)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        loopAnim4();
                    }
                });
    }

    void loopAnim4() {
        isEndLoop = true;
        binding.layout2.setTranslationX(binding.layout4.getWidth());
        binding.layout2.animate()
                .translationX(0)
                .setDuration(speed);
        binding.layout3.setTranslationX(0);
        binding.layout3.setVisibility(View.GONE);
        binding.layout4.animate()
                .translationX(-binding.layout4.getWidth())
                .setDuration(speed)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        //binding.layout1.setTranslationX(0);
//                        binding.layout2.setTranslationX(0);
//                        binding.layout3.setTranslationX(0);
//                        binding.layout2.animate()
//                                .translationX(0)
//                                .setDuration(speed);
                        binding.layout3.setVisibility(View.VISIBLE);
                        loopAnim2();
                    }
                });
    }
}