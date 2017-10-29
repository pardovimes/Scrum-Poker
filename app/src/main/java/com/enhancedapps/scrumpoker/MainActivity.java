package com.enhancedapps.scrumpoker;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TableLayout tableLayoutCards;
    private Button buttonTapToReveal;
    private Button buttonCardSelected;
    private String cardSelected;
    private String[] numbers = new String[] {
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

        tableLayoutCards = (TableLayout) findViewById(R.id.tableLayoutCards);
        buttonTapToReveal = (Button) findViewById(R.id.buttonTapToReveal);
        buttonCardSelected = (Button) findViewById(R.id.buttonCardSelected);

        List<Button> buttons = initiateButtons();

        for (int i = 0; i<buttons.size(); i=i+3){

            TableRow tableRow = new TableRow(this);

            tableRow.addView(buttons.get(i));
            tableRow.addView(buttons.get(i+1));
            tableRow.addView(buttons.get(i+2));

            tableLayoutCards.addView(tableRow);
        }

        buttonTapToReveal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCardNumber(cardSelected);
            }
        });

        buttonCardSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTableCards();
            }
        });
    }

    private List<Button> initiateButtons(){
        List<Button> buttons = new ArrayList<Button>();

        final float scale = this.getResources().getDisplayMetrics().density;

        TableRow.LayoutParams layout = new TableRow.LayoutParams();
        layout.width = TableRow.LayoutParams.MATCH_PARENT;
        layout.height = (int) (80 * scale + 0.5f);
        layout.weight = 1;
        layout.leftMargin = (int) (18 * scale + 0.5f);
        layout.rightMargin = (int) (18 * scale + 0.5f);
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
                    cardSelected = ((Button) v).getText().toString();
                    showTapToReveal();
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
                cardSelected = ((Button) v).getText().toString();
                showTapToReveal();
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

    private void showTapToReveal() {

        ActivitySwitcher.animationOut(
                findViewById(R.id.tableLayoutCards),
                getWindowManager(),
                new ActivitySwitcher.AnimationFinishedListener() {
                    @Override
                    public void onAnimationFinished() {
                        ActivitySwitcher.animationIn(
                                buttonTapToReveal,
                                getWindowManager()
                        );
                    }
                });
    }

    private void showCardNumber(final String text) {

        ActivitySwitcher.animationOut(
                buttonTapToReveal,
                getWindowManager(),
                new ActivitySwitcher.AnimationFinishedListener() {
                    @Override
                    public void onAnimationFinished() {
                        buttonCardSelected.setText(text);
                        ActivitySwitcher.animationIn(
                                buttonCardSelected,
                                getWindowManager()
                        );
                    }
                });
    }

    private void showTableCards(){

        ActivitySwitcher.animationOut(
                buttonCardSelected,
                getWindowManager(),
                new ActivitySwitcher.AnimationFinishedListener() {
                    @Override
                    public void onAnimationFinished() {
                        ActivitySwitcher.animationIn(
                                tableLayoutCards,
                                getWindowManager()
                        );
                    }
                });
    }
}
