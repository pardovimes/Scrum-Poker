package com.enhancedapps.scrumpoker;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
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

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        // animateIn this activity
        ActivitySwitcher.animationIn(findViewById(R.id.container),
                getWindowManager());
        super.onResume();
    }

    /**** HOW ANIMATION WORKS
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
    ***/
}
