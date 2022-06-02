package com.quy.thanh;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;

import com.quy.thanh.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    long speed = 1000;
    int count = 25;
    String[] strings = {"1", "2", "3", "4", "5", "8", "9", "10", "11"};
    int size = strings.length;
    int index = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.text2.setText(strings[0]);
        binding.text3.setText(strings[1]);
        binding.text4.setText(strings[2]);
        binding.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loopAnim1();
            }
        });
    }

    boolean isEndLoop = false;

    void loopAnim1() {
        count--;
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
        if (count == 0) {
            return;
        }
        count--;
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
                            if(index == size - 1){
                                index = -1;
                            }
                            index++;
                            binding.text2.setText(strings[index]);
                            loopAnim3();
                        }

                    }
                });
    }

    void loopAnim3() {
        if (count == 0) {
            return;
        }
        count--;
        binding.layout3.animate()
                .translationX(-binding.layout4.getWidth())
                .setDuration(speed)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        if(index == size - 1){
                            index = -1;
                        }
                        index++;
                        binding.text3.setText(strings[index]);
                        loopAnim4();
                    }
                });
    }

    void loopAnim4() {
        if (count == 0) {
            return;
        }
        count--;
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
                        binding.layout3.setVisibility(View.VISIBLE);
                        if(index == size - 1){
                            index = -1;
                        }
                        index++;
                        binding.text4.setText(strings[index]);
                        loopAnim2();
                    }
                });
    }
}