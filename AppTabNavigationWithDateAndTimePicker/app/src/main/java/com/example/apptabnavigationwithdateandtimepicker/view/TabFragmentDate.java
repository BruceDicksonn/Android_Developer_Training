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

import com.example.apptabnavigationwithdateandtimepicker.R;
import com.example.apptabnavigationwithdateandtimepicker.adapters.FragmentDatePicker;
import com.example.apptabnavigationwithdateandtimepicker.databinding.FragmentDatePickerDialogBinding;
import com.example.apptabnavigationwithdateandtimepicker.databinding.FragmentTabDateBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TabFragmentDate#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabFragmentDate extends Fragment {

    Button date;
    static TextView result;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TabFragmentDate() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabFragmentDate.
     */
    // TODO: Rename and change types and number of parameters
    public static TabFragmentDate newInstance(String param1, String param2) {
        TabFragmentDate fragment = new TabFragmentDate();
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

        return inflater.inflate(R.layout.fragment_tab_date, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        result = view.findViewById(R.id.dateResult);

        date = view.findViewById(R.id.date);
        date.setOnClickListener(showDatePicker);


        super.onViewCreated(view, savedInstanceState);
    }

    public static void getDateCaptured(int year, int month, int day) {

        String y = Integer.toString(year);
        String m = Integer.toString(month + 1);
        String d = Integer.toString(day);
        String r = d + "/" + m + "/" + y ;

        result.setText(r);
        result.setVisibility(View.VISIBLE);


    }

    View.OnClickListener showDatePicker = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FragmentDatePicker picker = new FragmentDatePicker();
            picker.show(getFragmentManager(),"datePicker");
        }
    };
}