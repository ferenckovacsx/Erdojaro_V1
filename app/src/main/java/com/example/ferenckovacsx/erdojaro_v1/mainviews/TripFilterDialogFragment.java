package com.example.ferenckovacsx.erdojaro_v1.mainviews;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.crystal.crystalrangeseekbar.widgets.CrystalSeekbar;
import com.example.ferenckovacsx.erdojaro_v1.R;

/**
 * Created by ferenckovacsx on 2017-06-24.
 */

public class TripFilterDialogFragment extends DialogFragment {

    private EditText mEditText;
    private Button easyButton;
    private Button hardButton;
    private Button filterResultsButton;
    private ImageButton cyclingButton;
    private ImageButton hikingButton;
    private ImageButton tanosvenyButton;
    private CrystalRangeSeekbar distanceRangeBar;
    private CrystalRangeSeekbar durationRangeBar;
    private TextView distanceRangeMinTextView;
    private TextView distanceRangeMaxTextView;
    private TextView durationRangeMinTextView;
    private TextView durationRangeMaxTextView;
    private int distanceMin;
    private int distanceMax;
    private int durationMin;
    private int durationMax;

    public static final int FILTERDIALOG_REQUEST_CODE = 1;

    private DialogInterface.OnDismissListener onDismissListener;


    public TripFilterDialogFragment() {

        // Empty constructor is required for DialogFragment

        // Make sure not to add arguments to the constructor

        // Use `newInstance` instead as shown below

    }

    public static TripFilterDialogFragment newInstance(String title) {

        TripFilterDialogFragment frag = new TripFilterDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {

        View filterView = inflater.inflate(R.layout.dialogfragment_trip_filter, container);

        easyButton = (Button) filterView.findViewById(R.id.filter_easy_button);
        hardButton = (Button) filterView.findViewById(R.id.filter_hard_button);
        distanceRangeBar = (CrystalRangeSeekbar) filterView.findViewById(R.id.filter_distance_rangebar);
        durationRangeBar = (CrystalRangeSeekbar) filterView.findViewById(R.id.filter_duration_rangebar);
        distanceRangeMinTextView = (TextView) filterView.findViewById(R.id.filter_distance_range_textMin);
        distanceRangeMaxTextView = (TextView) filterView.findViewById(R.id.filter_distance_range_textMax);
        durationRangeMinTextView = (TextView) filterView.findViewById(R.id.filter_duration_range_textMin);
        durationRangeMaxTextView = (TextView) filterView.findViewById(R.id.filter_duration_range_textMax);
        cyclingButton = (ImageButton) filterView.findViewById(R.id.filter_cycling_button);
        hikingButton = (ImageButton) filterView.findViewById(R.id.filter_hiking_button);
        tanosvenyButton = (ImageButton) filterView.findViewById(R.id.filter_tanosveny_button);
        filterResultsButton = (Button) filterView.findViewById(R.id.filter_results_button);

        easyButton.setSelected(true);
        hardButton.setSelected(true);
        tanosvenyButton.setSelected(true);
        cyclingButton.setSelected(true);
        hikingButton.setSelected(true);

        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (!easyButton.isSelected()) {

                    easyButton.setSelected(true);
                    Log.i("TripFilter", "easy ON");
                } else if (hardButton.isSelected()) {

                    easyButton.setSelected(false);
                    Log.i("TripFilter", "easy OFF");

                }
            }
        });

        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (!hardButton.isSelected()) {
                    hardButton.setSelected(true);
                    Log.i("TripFilter", "hard ON");

                } else if (easyButton.isSelected()) {
                    hardButton.setSelected(false);
                    Log.i("TripFilter", "hard OFF");
                }
            }
        });

        cyclingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (!cyclingButton.isSelected()) {

                    cyclingButton.setSelected(true);
                    Log.i("TripFilter", "cycling ON");
                } else {

                    cyclingButton.setSelected(false);
                    Log.i("TripFilter", "cycling OFF");

                }
            }
        });

        hikingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (!hikingButton.isSelected()) {

                    hikingButton.setSelected(true);
                    Log.i("TripFilter", "hiking ON");
                } else {

                    hikingButton.setSelected(false);
                    Log.i("TripFilter", "hiking OFF");

                }
            }
        });

        tanosvenyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (!tanosvenyButton.isSelected()) {

                    tanosvenyButton.setSelected(true);
                    Log.i("TripFilter", "tanosveny ON");
                } else {

                    tanosvenyButton.setSelected(false);
                    Log.i("TripFilter", "tanosveny OFF");

                }
            }
        });


        distanceRangeBar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                distanceRangeMinTextView.setText(String.valueOf(minValue) + " km");
                distanceRangeMaxTextView.setText(String.valueOf(maxValue) + " km");
                distanceMin = minValue.intValue();
                distanceMax = maxValue.intValue();
            }
        });

        durationRangeBar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                durationRangeMinTextView.setText(String.valueOf(minValue) + " óra");
                durationRangeMaxTextView.setText(String.valueOf(maxValue) + " óra");
                durationMin = minValue.intValue();
                durationMax = maxValue.intValue();
            }
        });

        filterResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                boolean difficultyHard;
                boolean difficultyEasy;
                boolean isItTanosveny;
                boolean isItCycling;
                boolean isItHiking;

                difficultyEasy = easyButton.isSelected();
                difficultyHard = hardButton.isSelected();
                isItTanosveny = tanosvenyButton.isSelected();
                isItCycling = cyclingButton.isSelected();
                isItHiking = hikingButton.isSelected();

                Log.i("TripFilterBundle", "easy: " + difficultyEasy);
                Log.i("TripFilterBundle", "hard: " + difficultyHard);
                Log.i("TripFilterBundle", "tanosveny: " + isItTanosveny);

                Intent intent = new Intent();
                intent.putExtra("easy", difficultyEasy);
                intent.putExtra("hard", difficultyHard);
                intent.putExtra("tanosveny", isItTanosveny);
                intent.putExtra("cycling", isItCycling);
                intent.putExtra("hiking", isItHiking);
                intent.putExtra("distanceMin", distanceMin);
                intent.putExtra("distanceMax", distanceMax);
                intent.putExtra("durationMin", durationMin);
                intent.putExtra("durationMax", durationMax);
                getTargetFragment().onActivityResult(getTargetRequestCode(), FILTERDIALOG_REQUEST_CODE, intent);

                //dismiss dialogfagment
                dismiss();

            }
        });


        return filterView;

    }


    @Override

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
            Log.i("filterDialog", "dismiss");

        }
    }


}