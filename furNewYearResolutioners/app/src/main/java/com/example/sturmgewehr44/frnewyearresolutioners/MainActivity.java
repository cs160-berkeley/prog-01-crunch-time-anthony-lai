package com.example.sturmgewehr44.frnewyearresolutioners;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        currentExercise = Exercise.PUSHUP;
        tagMapToExercise = new HashMap<String, Exercise>();
        addValuestoHashmap();
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    public void changeExercise(View view) {
        System.out.println(view.getTag().toString());
        currentExercise = tagMapToExercise.get(view.getTag().toString());
        LinearLayout sideways = (LinearLayout) ((LinearLayout) view.getParent()).getParent();
        int children = sideways.getChildCount();
        for (int i=0; i<children; i++) {
            LinearLayout vertical = (LinearLayout) sideways.getChildAt(i);
            vertical.getChildAt(0).setBackgroundColor(Color.RED);
        }
        view.setBackgroundColor(Color.YELLOW);
        TextView ending = (TextView) findViewById(R.id.ending);
        System.out.println(ending.getText());
        ending.setText((String) view.getTag());
        System.out.println(ending.getText());
    }

    private enum Exercise {
        PUSHUP, SITUP, SQUATS, LEGLIFT, JUMPINGJACKS, PULLUP,
        CYCLING, WALKING, JOGGING, SWIMMING, STAIRCLIMBING
    }

    private void addValuestoHashmap() {
        tagMapToExercise.put("pushup", Exercise.PUSHUP);
        tagMapToExercise.put("situp", Exercise.SITUP);
        tagMapToExercise.put("squats", Exercise.SQUATS);
        tagMapToExercise.put("leg_lift", Exercise.LEGLIFT);
        tagMapToExercise.put("plank", Exercise.JUMPINGJACKS);
        tagMapToExercise.put("jumping_jacks", Exercise.PULLUP);
        tagMapToExercise.put("cycling", Exercise.CYCLING);
        tagMapToExercise.put("walking", Exercise.WALKING);
        tagMapToExercise.put("jogging", Exercise.JOGGING);
        tagMapToExercise.put("swimming", Exercise.SWIMMING);
        tagMapToExercise.put("stair_climbing", Exercise.STAIRCLIMBING);
    }

    private Exercise currentExercise;

    public int Calories;

    private HashMap<String, Exercise> tagMapToExercise;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
