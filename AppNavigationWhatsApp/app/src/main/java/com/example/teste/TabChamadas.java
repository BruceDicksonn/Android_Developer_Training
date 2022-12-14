package com.example.teste;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TabChamadas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabChamadas extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TabChamadas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabChamadas.
     */
    // TODO: Rename and change types and number of parameters
    public static TabChamadas newInstance(String param1, String param2) {
        TabChamadas fragment = new TabChamadas();
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

        return inflater.inflate(R.layout.tab_chamadas, container, false);
    }

    ArrayList<String[]> contatos = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        RecyclerView recycler = view.findViewById(R.id.recyclerChamadas);
        fill();

        AdapterChamadas adapter = new AdapterChamadas(view.getContext(), contatos);

        recycler.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        recycler.setAdapter(adapter);

        super.onViewCreated(view, savedInstanceState);
    }

    public void fill(){
        contatos.add(new String[]{"Bruce","+559899897845"});
        contatos.add(new String[]{"Bruce","+559899897845"});
        contatos.add(new String[]{"Bruce","+559899897845"});
        contatos.add(new String[]{"Bruce","+559899897845"});
        contatos.add(new String[]{"Bruce","+559899897845"});
        contatos.add(new String[]{"Bruce","+559899897845"});
        contatos.add(new String[]{"Bruce","+559899897845"});
        contatos.add(new String[]{"Bruce","+559899897845"});
        contatos.add(new String[]{"Bruce","+559899897845"});
        contatos.add(new String[]{"Bruce","+559899897845"});
    }
}