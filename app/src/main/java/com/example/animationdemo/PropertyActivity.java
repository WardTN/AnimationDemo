package com.example.animationdemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class PropertyActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_play_value_anim, btn_play_object_anim, btn_play_self_view;
    private ImageView iv;
    private TextView tv;
    private boolean isReverse;  //是否正序播放
    private ValueAnimator valueAnimator;
    private ViewGroup.LayoutParams params;
    private MyPointView myPointView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        iv = findViewById(R.id.iv);
        btn_play_value_anim = findViewById(R.id.btn_play_value_anim);
        btn_play_object_anim = findViewById(R.id.btn_play_object_anim);
        tv = findViewById(R.id.tv);
        myPointView = findViewById(R.id.pointview);
        btn_play_self_view = findViewById(R.id.btn_play_self_view);

        btn_play_value_anim.setOnClickListener(this);
        btn_play_object_anim.setOnClickListener(this);
        btn_play_self_view.setOnClickListener(this);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_play_value_anim:
                doValueAnim();
                break;

            case R.id.btn_play_object_anim:
                doObjectAnim();
                break;
            case R.id.btn_play_self_view:
                ObjectAnimator animator = ObjectAnimator.ofInt(myPointView, "pointRadius", 0, 300, 100);
                animator.setDuration(2000);
                animator.start();
                break;
        }
    }

    //ValueAnim
    private void doValueAnim() {
        //1.创建一个ValueAnimator
        valueAnimator = ValueAnimator.ofInt(0, 400);
        valueAnimator.setDuration(1000);
        //2.这个ValueAnimator 进行监听 获取变化的值
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int curValue = (int) valueAnimator.getAnimatedValue();
                //3.将这个值赋值给对象
                iv.layout(curValue, curValue,
                        curValue + iv.getWidth(), curValue + iv.getHeight());

            }
        });


        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                Log.e("CHEN", "animation start");
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Log.e("CHEN", "animation end");
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                Log.e("CHEN", "animation cancel");
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                Log.e("CHEN", "animation repeat");
            }
        });
        valueAnimator.start();
    }

    private void doObjectAnim() {
        int n = (int) (Math.random() * 2);
        switch (n) {
            case 0:
                ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(tv, "alpha", 1, 0, 1);
                alphaAnim.setDuration(2000);
                alphaAnim.start();
                break;
            case 1:
                ObjectAnimator rotateAnim = ObjectAnimator.ofFloat(tv, "rotationX", 0, 270, 0);
                rotateAnim.setDuration(2000);
                rotateAnim.start();
                break;
        }
    }


}
