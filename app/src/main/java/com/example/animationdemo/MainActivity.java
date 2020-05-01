package com.example.animationdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv, iv_frame;

    private Button btn_frame, btn_tween_trans, btn_tween_scale, btn_tween_rotate, btn_tween_alpha, btn_tween_group, btn_tween_group2, btn_attribute, btn_interpolator;

    private boolean isUsingXML = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.zoom_out, R.anim.zoom_in);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = findViewById(R.id.iv);
        iv_frame = findViewById(R.id.iv_frame);
        btn_frame = findViewById(R.id.btn_frame);
        btn_tween_trans = findViewById(R.id.btn_tween_trans);
        btn_tween_alpha = findViewById(R.id.btn_tween_alpha);
        btn_tween_scale = findViewById(R.id.btn_tween_scale);
        btn_tween_rotate = findViewById(R.id.btn_tween_rotate);
        btn_tween_group = findViewById(R.id.btn_tween_group);
        btn_tween_group2 = findViewById(R.id.btn_tween_group2);
        btn_interpolator = findViewById(R.id.btn_interpolator);
        btn_attribute = findViewById(R.id.btn_attribute);

        iv.setOnClickListener(this);
        btn_tween_trans.setOnClickListener(this);
        btn_tween_alpha.setOnClickListener(this);
        btn_tween_scale.setOnClickListener(this);
        btn_tween_rotate.setOnClickListener(this);
        btn_tween_group.setOnClickListener(this);
        btn_tween_group2.setOnClickListener(this);
        btn_frame.setOnClickListener(this);
        btn_attribute.setOnClickListener(this);
        btn_interpolator.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_frame:    //帧动画
                iv_frame.setImageResource(R.drawable.ring_animation);
                AnimationDrawable animationDrawable = (AnimationDrawable) iv_frame.getDrawable();
                animationDrawable.start();
                break;
            case R.id.btn_tween_alpha:  //透明度动画
                if (isUsingXML) {
                    Animation alphaAnim = AnimationUtils.loadAnimation(this, R.anim.alpha);
                    alphaAnim.setDuration(2000);
                    iv.startAnimation(alphaAnim);
                } else {
                    Animation animation = new AlphaAnimation(0, 1);
                    animation.setDuration(1000);
                    iv.startAnimation(animation);
                }
                break;
            case R.id.btn_tween_trans:  //位移动画
                if (isUsingXML) {
                    Animation transAnim = AnimationUtils.loadAnimation(this, R.anim.translate);
                    transAnim.setDuration(2000);
                    iv.startAnimation(transAnim);
                } else {
                    /**
                     *  动画起始点X坐标
                     *  动画终点X坐标
                     *  动画起始Y坐标
                     *  动画终点Y坐标
                     */
                    Animation transAnim = new TranslateAnimation(0, 500, 0, 0);
                    transAnim.setDuration(2000);
                    transAnim.setFillAfter(true);
                    transAnim.setInterpolator(new AccelerateDecelerateInterpolator());
                    iv.startAnimation(transAnim);
                }
                break;
            case R.id.btn_tween_scale:      //缩放动画
                if (isUsingXML) {
                    Animation scaleAnim = AnimationUtils.loadAnimation(this, R.anim.scale);
                    scaleAnim.setDuration(2000);
                    iv.startAnimation(scaleAnim);
                } else {
                    /**
                     * 第一个参数fromX为动画起始时 X坐标上的伸缩尺寸
                     * 第二个参数toX为动画结束时 X坐标上的伸缩尺寸
                     * 第三个参数fromY为动画起始时Y坐标上的伸缩尺寸
                     * 第四个参数toY为动画结束时Y坐标上的伸缩尺寸
                     * 说明: 0.0表示收缩到没有;1.0表示正常无伸缩;值小于1.0表示收缩;值大于1.0表示放大
                     * 第五个参数pivotXType为动画在X轴相对于物件位置类型
                     * 第六个参数pivotXValue为动画相对于物件的X坐标的开始位置
                     * 第七个参数pivotXType为动画在Y轴相对于物件位置类型
                     * 第八个参数pivotYValue为动画相对于物件的Y坐标的开始位置
                     */
                    Animation scaleAnim = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    scaleAnim.setDuration(1000);
                    iv.startAnimation(scaleAnim);
                }
                break;
            case R.id.btn_tween_rotate:     //旋转动画
                if (isUsingXML) {
                    Animation rotateAnim = AnimationUtils.loadAnimation(this, R.anim.rotate);
                    rotateAnim.setDuration(3000);
                    iv.startAnimation(rotateAnim);
                } else {
                    /**
                     * 第一个参数fromDegrees为动画起始时角度
                     * 第二个参数toDegrees为动画结束角度
                     * 第三个参数pivotXType为动画在X轴相对于物件位置类型
                     * 第四个参数pivotXValue为动画相对于物件的X坐标的开始位置
                     * 第五个参数pivotXType为动画在Y轴相对于物件位置类型
                     * 第六个参数pivotYValue为动画相对于物件的Y坐标的开始位置
                     */
                    Animation animation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(1000);
                    iv.startAnimation(animation);
                }
                break;
            case R.id.btn_tween_group:
                Animation groupAnim = AnimationUtils.loadAnimation(this, R.anim.scale);
                groupAnim.setDuration(2000);
                iv.startAnimation(groupAnim);
                final Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.rotate);
                animation2.setDuration(2000);
                groupAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        iv.startAnimation(animation2);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                break;

            case R.id.btn_tween_group2:
                Animation alphaAnim = AnimationUtils.loadAnimation(this, R.anim.alpha);
                Animation transAnim = AnimationUtils.loadAnimation(this, R.anim.translate);
                Animation rotateAnim = AnimationUtils.loadAnimation(this, R.anim.rotate);
                Animation scaleAnim = AnimationUtils.loadAnimation(this, R.anim.scale);

                AnimationSet animatorSet2 = new AnimationSet(true); //是否共用同一个插值器
                animatorSet2.addAnimation(alphaAnim);
                animatorSet2.addAnimation(transAnim);
                animatorSet2.addAnimation(rotateAnim);
                animatorSet2.addAnimation(scaleAnim);
                animatorSet2.setDuration(2000);
                animatorSet2.setStartOffset(1000);
                iv.startAnimation(animatorSet2);
                break;

            case R.id.btn_attribute:
                Intent intent = new Intent(this, PropertyActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_interpolator:
                Intent intent2 = new Intent(this, InterpolatorActivity.class);
                startActivity(intent2);
                break;
            case R.id.iv:
                Toast.makeText(MainActivity.this,"DingDing",Toast.LENGTH_LONG).show();
                break;

        }
    }
}
