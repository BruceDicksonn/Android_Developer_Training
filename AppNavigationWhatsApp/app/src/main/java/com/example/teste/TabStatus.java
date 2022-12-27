package com.example.teste;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TabStatus#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabStatus extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TabStatus() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabStatus.
     */
    // TODO: Rename and change types and number of parameters
    public static TabStatus newInstance(String param1, String param2) {
        TabStatus fragment = new TabStatus();
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

        View view = inflater.inflate(R.layout.tab_conversas, container, false);

        ListView listView = view.findViewById(R.id.listViewConversas);

        String[] chatArray = {
                "Status - Biel","Status - Gil",
                "Status - Tutu","Status - Alessandro",
                "Status - Carlos","Status - Peterson",
                "Status - Fernando","Status - Bruce",
                "Status - Paulo","Status - Danilo"
        };

        ArrayAdapter<String> chats = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, chatArray);
        listView.setAdapter(chats);


        return view;
    }
}