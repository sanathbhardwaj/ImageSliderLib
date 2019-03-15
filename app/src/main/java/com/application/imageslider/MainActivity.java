package com.application.imageslider;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.graphics.Color.RED;
import static android.text.Html.fromHtml;

public class MainActivity extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;

    private TextView[] mDots;

    private SliderAdapter sliderAdapter;

    private Button mNextBtn;
    private Button mBackBtn;

    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNextBtn = (Button)findViewById(R.id.nxtBtn);
        mBackBtn = (Button)findViewById(R.id.prevBtn);


        mSlideViewPager = (ViewPager)findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout)findViewById(R.id.dotsLayout);

        sliderAdapter = new SliderAdapter(this);

        mSlideViewPager.setAdapter(sliderAdapter);

        addDotsIndicators(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);


        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mDots.length<2){

                    mSlideViewPager.setCurrentItem(mCurrentPage+1);

                }
                else {

                    //START NEW ACTIVITY

                    /*
                    Intent intent = new Intent(MainActivity.this,HomePage.class);
                    startActivity(intent);*/
                }

            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(mCurrentPage-1);
            }
        });
    }

    public void addDotsIndicators(int position){
        mDots = new TextView[3];
        mDotLayout.removeAllViews();

        for (int i = 0; i<mDots.length; i++){

            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getColor(R.color.colorTransparentWhite));

            mDotLayout.addView(mDots[i]);


        }

        if (mDots.length>0){

            mDots[position].setTextColor(getColor(R.color.colorWhite));

        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicators(i);

            mCurrentPage = i;

            if (i==0){

                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(false);
                mDotLayout.setEnabled(true);
                mDotLayout.setVisibility(View.VISIBLE);
                mBackBtn.setVisibility(View.INVISIBLE);
                mNextBtn.setText("Next");
                mBackBtn.setText("");


            }
            else if (i==mDots.length-1){

                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(false);
                mDotLayout.setEnabled(false);
                mDotLayout.setVisibility(View.INVISIBLE);
                mBackBtn.setVisibility(View.INVISIBLE);
                mNextBtn.setText("Finish");
                mBackBtn.setText("Back");


            }
            else
            {
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mDotLayout.setEnabled(true);
                mDotLayout.setVisibility(View.VISIBLE);
                mBackBtn.setVisibility(View.VISIBLE);
                mNextBtn.setText("Next");
                mBackBtn.setText("Back");

            }


        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
