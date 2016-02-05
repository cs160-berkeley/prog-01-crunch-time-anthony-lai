package com.example.sturmgewehr44.frnewyearresolutioners;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.EditText;
import java.util.HashMap;
import android.text.Editable;

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
        tagExerciseToMetric = new HashMap<Exercise, String>();
        amountBox = (EditText) findViewById(R.id.amount);
        amountBox.addTextChangedListener(inputChanged);
        addValuestoHashmap();
    }

//    protected void onStart(Bundle savedInstanceState) {
//        unused1 = (HorizontalScrollView) findViewById(R.id.horizontalScrollView);
//        sideways = (LinearLayout) unused1.getChildAt(0);
//        children = sideways.getChildCount();
//        for (int i=0; i<children; i++) {
//            LinearLayout vertical = (LinearLayout) sideways.getChildAt(i);
//            vertical.getChildAt(0).setBackgroundColor(Color.rgb(238, 238, 238));
//        }
//    }

    public void changeExercise(View view) {
        currentExercise = tagMapToExercise.get(view.getTag().toString());
        System.out.println(currentExercise);
        sideways = (LinearLayout) ((LinearLayout) view.getParent()).getParent();
        children = sideways.getChildCount();
//        for (int i=0; i<children; i++) {
//            LinearLayout vertical = (LinearLayout) sideways.getChildAt(i);
//            vertical.getChildAt(0).setBackgroundColor(Color.rgb(238, 238, 238));
//        }
//        view.setBackgroundColor(Color.YELLOW);
        TextView ending = (TextView) findViewById(R.id.ending);
        ending.setText(((String) view.getTag()).toLowerCase());
        analyzeReps();
    }

    private void analyzeReps() {
        TextView minreps = (TextView) findViewById(R.id.RepMinutes);
        minreps.setText(tagExerciseToMetric.get(currentExercise));
        if (TextUtils.isEmpty(amountBox.getText())) {
            return;
        }
        int amount = Integer.parseInt(amountBox.getText().toString());
        Calories = amount * 100 / tagExerciseToReps.get(currentExercise);
        TextView arbeit = (TextView) findViewById(R.id.arbeit);
        arbeit.setText(String.valueOf(Calories));
        TextView pushupArbeit = (TextView) findViewById(R.id.arbeitPushup);
        TextView situpArbeit = (TextView) findViewById(R.id.arbeitSitup);
        TextView joggingArbeit = (TextView) findViewById(R.id.arbeitJogging);
        TextView jjacksArbeit = (TextView) findViewById(R.id.arbeitJumpingJacks);
        int pushValue = Calories * tagExerciseToReps.get(Exercise.PUSHUP) / 100;
        int sitValue = Calories * tagExerciseToReps.get(Exercise.SITUP) / 100;
        int jogValue = Calories * tagExerciseToReps.get(Exercise.JOGGING) / 100;
        int jackValue = Calories * tagExerciseToReps.get(Exercise.JUMPINGJACKS) / 100;
        pushupArbeit.setText(String.valueOf(pushValue));
        situpArbeit.setText(String.valueOf(sitValue));
        joggingArbeit.setText(String.valueOf(jogValue));
        jjacksArbeit.setText(String.valueOf(jackValue));
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

        tagExerciseToMetric.put(Exercise.PUSHUP, "");
        tagExerciseToMetric.put(Exercise.SITUP, "");
        tagExerciseToMetric.put(Exercise.SQUATS, "");
        tagExerciseToMetric.put(Exercise.LEGLIFT, "minutes of");
        tagExerciseToMetric.put(Exercise.PLANK, "minutes of");
        tagExerciseToMetric.put(Exercise.JUMPINGJACKS, "minutes of");
        tagExerciseToMetric.put(Exercise.PULLUP, "");
        tagExerciseToMetric.put(Exercise.CYCLING, "minutes of");
        tagExerciseToMetric.put(Exercise.WALKING, "minutes of");
        tagExerciseToMetric.put(Exercise.JOGGING, "minutes of");
        tagExerciseToMetric.put(Exercise.SWIMMING, "minutes of");
        tagExerciseToMetric.put(Exercise.STAIRCLIMBING, "minutes of");
    }

    private EditText amountBox;

    private TextWatcher inputChanged = new TextWatcher() {
        public void afterTextChanged(Editable s) {
            analyzeReps();
        }
        public void beforeTextChanged(CharSequence s, int start, int count, int after){
        }
        public void onTextChanged(CharSequence s, int start, int before, int after) {

        }
    };

    private Exercise currentExercise;

    public int Calories;

    private int children;

    private HashMap<String, Exercise> tagMapToExercise;

    private HashMap<Exercise, Integer> tagExerciseToReps;

    private HashMap<Exercise, String> tagExerciseToMetric;

    private HorizontalScrollView unused1;

    private LinearLayout sideways;

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
