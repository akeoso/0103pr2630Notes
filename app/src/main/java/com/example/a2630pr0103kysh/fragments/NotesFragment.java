package com.example.a2630pr0103kysh.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a2630pr0103kysh.R;
import com.example.a2630pr0103kysh.adapters.NotesAdapter;
import com.example.a2630pr0103kysh.data.Notes;
import com.example.a2630pr0103kysh.data_base.DataBaseManager;
import com.example.a2630pr0103kysh.databinding.FragmentNotesBinding;

import java.util.List;


public class NotesFragment extends Fragment {

    FragmentNotesBinding binding;
    DataBaseManager dataBaseManager;
    public NotesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataBaseManager = new DataBaseManager(getContext());
        dataBaseManager.openDb();
        List<Notes> notesList = dataBaseManager.getNotes();
        NotesAdapter.OnNoteClickListener onNoteClickListener = new NotesAdapter.OnNoteClickListener() {
            @Override
            public void OnNoteClick(Notes note, int pos) {
                AddEditFragment addEditFragment = new AddEditFragment(note);
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, addEditFragment, null)
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .commit();
            }
        };
        NotesAdapter notesAdapter = new NotesAdapter(getContext(), notesList, onNoteClickListener);
        binding.recyclerView.setAdapter(notesAdapter);
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentContainerView, AddEditFragment.class, null)
                        .commit();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNotesBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dataBaseManager.closeDb();
    }
}