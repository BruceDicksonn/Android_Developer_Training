package com.example.apptabnavigationwithdateandtimepicker.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptabnavigationwithdateandtimepicker.R;
import com.example.apptabnavigationwithdateandtimepicker.adapters.FragmentTimerPicker;
import com.example.apptabnavigationwithdateandtimepicker.databinding.FragmentTabTimerBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TabFragmentTimer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabFragmentTimer extends Fragment {

    Button time;
    static TextView timeResult;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TabFragmentTimer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabFragmentTimer.
     */
    // TODO: Rename and change types and number of parameters
    public static TabFragmentTimer newInstance(String param1, String param2) {
        TabFragmentTimer fragment = new TabFragmentTimer();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_timer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        time = view.findViewById(R.id.timeButton);
        time.setOnClickListener(showTimePicker);
        timeResult = view.findViewById(R.id.timeResult);

        super.onViewCreated(view, savedInstanceState);
    }

    public static void getTimeCaptured(int hour, int minutes){

        String h = Integer.toString(hour);
        String m = Integer.toString(minutes);
        String res = h + ":" + m;

        timeResult.setText(res);
        timeResult.setVisibility(View.VISIBLE);

    }

    View.OnClickListener showTimePicker = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FragmentTimerPicker picker = new FragmentTimerPicker();
            picker.show(getFragmentManager(), "timePicker");
        }
    };

}