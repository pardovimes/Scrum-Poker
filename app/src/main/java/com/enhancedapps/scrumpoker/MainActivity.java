package com.enhancedapps.scrumpoker;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TableLayout tableLayout;
    String[] numbers = new String[] {
        "0","1/2","1",
        "2","3","5",
        "8","13","20",
        "40","99","100",
        "∞","?"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableLayout = (TableLayout) findViewById(R.id.tableLayoutCards);

        List<Button> buttons = initiateButtons();

        for (int i = 0; i<buttons.size(); i=i+3){

            TableRow tableRow = new TableRow(this);

            tableRow.addView(buttons.get(i));
            tableRow.addView(buttons.get(i+1));
            tableRow.addView(buttons.get(i+2));

            tableLayout.addView(tableRow);
        }
    }

    private List<Button> initiateButtons(){
        List<Button> buttons = new ArrayList<Button>();

        final float scale = this.getResources().getDisplayMetrics().density;

        TableRow.LayoutParams layout = new TableRow.LayoutParams();
        layout.width = TableRow.LayoutParams.MATCH_PARENT;
        layout.height = (int) (80 * scale + 0.5f);
        layout.weight = 1;
        layout.leftMargin = (int) (25 * scale + 0.5f);
        layout.rightMargin = (int) (25 * scale + 0.5f);
        layout.topMargin = (int) (5 * scale + 0.5f);
        layout.bottomMargin = (int) (5 * scale + 0.5f);

        for (String number:numbers) {

            Button button = new Button(this);
            button.setBackgroundResource(R.drawable.button_border);
            button.setTextColor(Color.WHITE);
            button.setText(number);
            button.setTextSize(TypedValue.COMPLEX_UNIT_SP,22);

            button.setLayoutParams(layout);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context,v.getId()+"",Toast.LENGTH_SHORT).show();
                    animatedStartActivity();
                }
            });

            buttons.add(button);
        }

        //COFFE BUTTON (LAST)
        Button button = new Button(this);
        button.setBackgroundResource(R.drawable.button_border);
        button.setTextColor(Color.WHITE);
        button.setText("COFFE");

        button.setLayoutParams(layout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,v.getId()+"",Toast.LENGTH_SHORT).show();
                animatedStartActivity();
            }
        });

        buttons.add(button);

        return buttons;
    }

    @Override
    protected void onResume() {
        // animateIn this activity
        ActivitySwitcher.animationIn(findViewById(R.id.container),
                getWindowManager());
        super.onResume();
    }

    private void animatedStartActivity() {
        // we only animateOut this activity here.
        // The new activity will animateIn from its onResume() - be sure to
        // implement it.
        final Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        // disable default animation for new intent
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        ActivitySwitcher.animationOut(
                findViewById(R.id.container),
                getWindowManager(),
                new ActivitySwitcher.AnimationFinishedListener() {
                    @Override
                    public void onAnimationFinished() {
                        startActivity(intent);
                    }
                });
    }

}
