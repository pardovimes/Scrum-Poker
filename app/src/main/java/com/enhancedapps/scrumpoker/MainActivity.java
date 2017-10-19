package com.enhancedapps.scrumpoker;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TableLayout tableLayout;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableLayout = (TableLayout) findViewById(R.id.tableLayoutCards);

        for(int i = 1; i<=5; i++){

            TableRow tableRow = new TableRow(this);

            for(int j = 1; j<=4; j++){

                TableRow.LayoutParams layout = new TableRow.LayoutParams();
                layout.width = TableRow.LayoutParams.WRAP_CONTENT;
                layout.height = TableRow.LayoutParams.WRAP_CONTENT;
                layout.weight = 1;

                final ImageButton imageButton = new ImageButton(this);

                imageButton.setId(R.id.imageButton+i+j);
                imageButton.setBackground(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                imageButton.setImageResource(R.mipmap.ic_launcher);
                imageButton.setLayoutParams(layout);


                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(context,v.getId()+"",Toast.LENGTH_SHORT).show();
                        animatedStartActivity();
                    }
                });

                tableRow.addView(imageButton);
            }

            tableLayout.addView(tableRow);
        }
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
