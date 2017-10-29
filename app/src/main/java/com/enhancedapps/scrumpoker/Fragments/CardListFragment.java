package com.enhancedapps.scrumpoker.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.enhancedapps.scrumpoker.MainActivity;
import com.enhancedapps.scrumpoker.R;

import java.util.ArrayList;
import java.util.List;

public class CardListFragment extends Fragment {

    private TableLayout tableLayoutCards;
    private String[] numbers = new String[] {
            "0","1/2","1",
            "2","3","5",
            "8","13","20",
            "40","99","100",
            "∞","?"
    };

    public CardListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_card_list, container, false);

        tableLayoutCards = (TableLayout) v.findViewById(R.id.tableLayoutCards);


        List<Button> buttons = initiateButtons(v);

        for (int i = 0; i<buttons.size(); i=i+3){

            TableRow tableRow = new TableRow(v.getContext());

            tableRow.addView(buttons.get(i));
            tableRow.addView(buttons.get(i+1));
            tableRow.addView(buttons.get(i+2));

            tableLayoutCards.addView(tableRow);
        }


        return v;
    }


    private List<Button> initiateButtons(View v){
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

            Button button = new Button(v.getContext());
            button.setBackgroundResource(R.drawable.button_border);
            button.setTextColor(Color.WHITE);
            button.setText(number);
            button.setTextSize(TypedValue.COMPLEX_UNIT_SP,22);

            button.setLayoutParams(layout);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Tap to reveal",Toast.LENGTH_SHORT).show();
                }
            });

            buttons.add(button);
        }

        //COFFE BUTTON (LAST)
        Button button = new Button(v.getContext());
        button.setBackgroundResource(R.drawable.button_border);
        button.setTextColor(Color.WHITE);
        button.setText("COFFE");

        button.setLayoutParams(layout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Coffe",Toast.LENGTH_SHORT).show();
            }
        });

        buttons.add(button);

        return buttons;
    }

}
