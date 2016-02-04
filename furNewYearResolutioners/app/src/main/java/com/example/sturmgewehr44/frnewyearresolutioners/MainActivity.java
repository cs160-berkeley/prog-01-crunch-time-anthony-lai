package com.example.sturmgewehr44.frnewyearresolutioners;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.EditText;

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
        tagExerciseToReps = new HashMap<Exercise, Integer>();
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

//    protected void onResume(Bundle savedInstanceState) {
//        while (true) {
//            analyzeReps();
//        }
//    }

    public void changeExercise(View view) {
        currentExercise = tagMapToExercise.get(view.getTag().toString());
        System.out.println(currentExercise);
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
        analyzeReps();
    }

    private void analyzeReps() {
        EditText amountBox = (EditText) findViewById(R.id.amount);
        if (TextUtils.isEmpty(amountBox.getText())) {
            return;
        }
        int amount = Integer.parseInt(amountBox.getText().toString());
        System.out.println(amount);
        System.out.println(currentExercise);
        Calories = amount * 100 / tagExerciseToReps.get(currentExercise);
        System.out.println(Calories);
        TextView arbeit = (TextView) findViewById(R.id.arbeit);
        arbeit.setText(String.valueOf(Calories));
//        //display calories
        //display in secondaries
    }

    private enum Exercise {
        PUSHUP, SITUP, SQUATS, LEGLIFT, PLANK, JUMPINGJACKS, PULLUP,
        CYCLING, WALKING, JOGGING, SWIMMING, STAIRCLIMBING
    }

    private void addValuestoHashmap() {
        tagMapToExercise.put("Pushups", Exercise.PUSHUP);
        tagMapToExercise.put("Situps", Exercise.SITUP);
        tagMapToExercise.put("Squats", Exercise.SQUATS);
        tagMapToExercise.put("Leg Lifts", Exercise.LEGLIFT);
        tagMapToExercise.put("Plank", Exercise.PLANK);
        tagMapToExercise.put("Jumping Jacks", Exercise.JUMPINGJACKS);
        tagMapToExercise.put("Pullup", Exercise.PULLUP);
        tagMapToExercise.put("Cycling", Exercise.CYCLING);
        tagMapToExercise.put("Walking", Exercise.WALKING);
        tagMapToExercise.put("Jogging", Exercise.JOGGING);
        tagMapToExercise.put("Swimming", Exercise.SWIMMING);
        tagMapToExercise.put("Stair_climbing", Exercise.STAIRCLIMBING);

        tagExerciseToReps.put(Exercise.PUSHUP, 350);
        tagExerciseToReps.put(Exercise.SITUP, 200);
        tagExerciseToReps.put(Exercise.SQUATS, 225);
        tagExerciseToReps.put(Exercise.LEGLIFT, 25);
        tagExerciseToReps.put(Exercise.PLANK, 25);
        tagExerciseToReps.put(Exercise.JUMPINGJACKS, 10);
        tagExerciseToReps.put(Exercise.PULLUP, 100);
        tagExerciseToReps.put(Exercise.CYCLING, 12);
        tagExerciseToReps.put(Exercise.WALKING, 20);
        tagExerciseToReps.put(Exercise.JOGGING, 12);
        tagExerciseToReps.put(Exercise.SWIMMING, 13);
        tagExerciseToReps.put(Exercise.STAIRCLIMBING, 15);
    }

    private Exercise currentExercise;

    public int Calories;

    private HashMap<String, Exercise> tagMapToExercise;

    private HashMap<Exercise, Integer> tagExerciseToReps;

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
