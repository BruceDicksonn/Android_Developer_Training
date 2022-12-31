package com.example.apprecyclerview;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import androidx.navigation.fragment.NavHostFragment;

import com.example.apprecyclerview.databinding.FragmentFirstBinding;

import java.util.LinkedList;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private RecyclerView recyclerView;
    private WordListAdapter adapter;
    private final LinkedList<String> mWordList = new LinkedList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        for(int i = 0; i < 20; i++){
            mWordList.addLast("Word - " + i);
        }

        binding = FragmentFirstBinding.inflate(inflater, container, false);

        adapter = new WordListAdapter(getActivity(),mWordList);
        recyclerView = binding.recyclerview;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int wordListSize = mWordList.size();

                mWordList.addLast("+ Word " + wordListSize);

                recyclerView.getAdapter().notifyItemInserted(wordListSize);

                recyclerView.smoothScrollToPosition(wordListSize);
            }
        });

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}