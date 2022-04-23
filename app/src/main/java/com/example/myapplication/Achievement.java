package com.example.myapplication;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;


public class Achievement extends Fragment {
    ImageView backArrow, levelImg, three, seven, ten, fourteen;
    TextView stepsLeft,levelText;
    public static int totalSteps=0, goal = 3000;
    public static NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_achievement,null);
        backArrow = view.findViewById(R.id.backArrow);
        stepsLeft = view.findViewById(R.id.stepsUntilNextLvl);
        levelText = view.findViewById(R.id.lvlText);
        progressBar = view.findViewById(R.id.progressBar);
        levelImg = view.findViewById(R.id.mainAchievement);
        three = view.findViewById(R.id.three);
        ten = view.findViewById(R.id.ten);
        seven = view.findViewById(R.id.seven);
        fourteen = view.findViewById(R.id.fourteen);
        CardView more = view.findViewById(R.id.more);
        backArrow.setOnClickListener(v -> loadReportFragment());
        more.setOnClickListener(v -> loadTotalStepsFragment());
        loadTotalSteps();
        loadImages();
        levelImg.setOnClickListener(v -> DialogShow());
        return view;
    }
    private void loadImages(){
        if (totalSteps < 3000){
            levelImg.setBackgroundColor(Color.GRAY);
            levelImg.setImageResource(R.drawable.threek);
            goal = 3000;
            stepsLeft.setText(format.format(3000 - totalSteps));
            levelText.setText("1");

        }
        if (totalSteps >= 3000 && totalSteps < 7000){
            levelImg.setBackgroundColor(Color.BLUE);
            three.setBackgroundColor(Color.BLUE);
            levelImg.setImageResource(R.drawable.threek);
            goal = 7000;
            stepsLeft.setText(format.format(7000 - totalSteps));
            levelText.setText("2");

        }
        if (totalSteps >= 7000 && totalSteps < 10000){
            levelImg.setBackgroundColor(Color.BLUE);
            levelImg.setImageResource(R.drawable.sevenk);
            goal = 10000;
            three.setBackgroundColor(Color.BLUE);
            seven.setBackgroundColor(Color.BLUE);
            stepsLeft.setText(format.format(10000 - totalSteps));
            levelText.setText("3");
        }
        if (totalSteps >= 10000 && totalSteps < 14000){
            levelImg.setBackgroundColor(Color.BLUE);
            levelImg.setImageResource(R.drawable.tenk);
            goal = 14000;
            three.setBackgroundColor(Color.BLUE);
            seven.setBackgroundColor(Color.BLUE);
            ten.setBackgroundColor(Color.BLUE);
            stepsLeft.setText(format.format(14000 - totalSteps));
            levelText.setText("4");
        }
        if (totalSteps >= 14000 && totalSteps < 20000){
            levelImg.setBackgroundColor(Color.BLUE);
            levelImg.setImageResource(R.drawable.fourteenk);
            goal = 20000;
            stepsLeft.setText(format.format(20000 - totalSteps));
            levelText.setText("5");
            three.setBackgroundColor(Color.BLUE);
            seven.setBackgroundColor(Color.BLUE);
            ten.setBackgroundColor(Color.BLUE);
            fourteen.setBackgroundColor(Color.BLUE);
        }
        if (totalSteps >= 20000 && totalSteps < 30000){
            levelImg.setBackgroundColor(Color.BLUE);
            levelImg.setImageResource(R.drawable.twentyk);
            goal=30000;
            stepsLeft.setText(format.format(30000 - totalSteps));
            levelText.setText("6");
            three.setBackgroundColor(Color.BLUE);
            seven.setBackgroundColor(Color.BLUE);
            ten.setBackgroundColor(Color.BLUE);
            fourteen.setBackgroundColor(Color.BLUE);
        }
        if (totalSteps >= 30000 && totalSteps < 40000){
            levelImg.setBackgroundColor(Color.BLUE);
            levelImg.setImageResource(R.drawable.thirtyk);
            goal=40000;
            stepsLeft.setText(format.format(40000 - totalSteps));
            levelText.setText("7");
            three.setBackgroundColor(Color.BLUE);
            seven.setBackgroundColor(Color.BLUE);
            ten.setBackgroundColor(Color.BLUE);
            fourteen.setBackgroundColor(Color.BLUE);
        }
        if (totalSteps >= 40000 && totalSteps < 60000){
            levelImg.setBackgroundColor(Color.BLUE);
            levelImg.setImageResource(R.drawable.fourtyk);
            goal=60000;
            stepsLeft.setText(format.format(60000 - totalSteps));
            levelText.setText("8");
            three.setBackgroundColor(Color.BLUE);
            seven.setBackgroundColor(Color.BLUE);
            ten.setBackgroundColor(Color.BLUE);
            fourteen.setBackgroundColor(Color.BLUE);
        }
        if (totalSteps >= 60000 && totalSteps < 70000){
            levelImg.setBackgroundColor(Color.BLUE);
            levelImg.setImageResource(R.drawable.sixtyk);
            goal=700000;
            stepsLeft.setText(format.format(70000 - totalSteps));
            levelText.setText("9");
            three.setBackgroundColor(Color.BLUE);
            seven.setBackgroundColor(Color.BLUE);
            ten.setBackgroundColor(Color.BLUE);
            fourteen.setBackgroundColor(Color.BLUE);
        }
        float set = ((totalSteps * 100) / goal);
        int b = (int)Math.round(set);
        progressBar.setProgress(b);

    }
    private void loadTotalSteps(){
        Database db = Database.getInstance(getActivity());
        int todaysOffset = db.getSteps(Util.getToday());
        int boot = db.getCurrentSteps();
        int totalStart = db.getTotalWithoutToday();
        totalSteps = todaysOffset + boot + totalStart;
    }
    private void loadTotalStepsFragment() {
        Fragment newFragment = new TotalSteps();
        getParentFragmentManager().beginTransaction().replace(R.id.fragment,newFragment).commit();
    }
    private void loadReportFragment() {
        Fragment newFragment=new Overview();
        getParentFragmentManager().beginTransaction().replace(R.id.fragment,newFragment).commit();
    }
    protected void DialogShow(){
        final Dialog dialog=new Dialog(getActivity());
        dialog.setCancelable(true);
        View view=getActivity().getLayoutInflater().inflate(R.layout.achievement,null);
        dialog.setContentView(view);
        loadTotalSteps();
        ImageView levelImg = view.findViewById(R.id.imageView);
        TextView levelText = view.findViewById(R.id.textLevel);
        if(totalSteps < 3000){
            levelImg.setBackgroundColor(Color.GRAY);
            levelImg.setImageResource(R.drawable.threek);
            levelText.setText("1");
        }
        if(totalSteps >= 3000 && totalSteps < 7000){
            levelImg.setBackgroundColor(Color.BLUE);
            levelImg.setImageResource(R.drawable.threek);
            levelText.setText("2");
        }
        if(totalSteps >= 7000 && totalSteps < 10000){
            levelImg.setBackgroundColor(Color.BLUE);
            levelImg.setImageResource(R.drawable.sevenk);
            levelText.setText("3");
        }
        if(totalSteps >= 10000 && totalSteps < 14000){
            levelImg.setBackgroundColor(Color.BLUE);
            levelImg.setImageResource(R.drawable.tenk);
            levelText.setText("4");
        }
        if(totalSteps >= 14000 && totalSteps < 20000){
            levelImg.setBackgroundColor(Color.BLUE);
            levelImg.setImageResource(R.drawable.fourteenk);
            levelText.setText("5");
        }
        if(totalSteps >= 20000 && totalSteps < 30000){
            levelImg.setBackgroundColor(Color.BLUE);
            levelImg.setImageResource(R.drawable.twentyk);
            levelText.setText("6");
        }
        if(totalSteps >= 30000 && totalSteps < 40000){
            levelImg.setBackgroundColor(Color.BLUE);
            levelImg.setImageResource(R.drawable.thirtyk);
            levelText.setText("7");
        }
        if(totalSteps >= 40000 && totalSteps < 60000){
            levelImg.setBackgroundColor(Color.BLUE);
            levelImg.setImageResource(R.drawable.fourtyk);
            levelText.setText("8");
        }
        if(totalSteps >= 60000 && totalSteps < 70000){
            levelImg.setBackgroundColor(Color.BLUE);
            levelImg.setImageResource(R.drawable.sixtyk);
            levelText.setText("9");
        }
        Button close=view.findViewById(R.id.ok);
        close.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
}