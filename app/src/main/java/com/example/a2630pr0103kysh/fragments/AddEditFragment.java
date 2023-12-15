package com.example.a2630pr0103kysh.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a2630pr0103kysh.R;
import com.example.a2630pr0103kysh.data.Notes;
import com.example.a2630pr0103kysh.data_base.DataBaseManager;
import com.example.a2630pr0103kysh.databinding.FragmentAddEditBinding;


public class AddEditFragment extends Fragment {
    Notes note = new Notes();
    FragmentAddEditBinding binding;
    DataBaseManager dataBaseManager;
    public AddEditFragment() {
        // Required empty public constructor
    }
    public AddEditFragment(Notes note){
        this.note = note;
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
        if (note.getId() != 0){
            binding.buttonAddEdit.setText("Изменить");
            binding.buttonDel.setVisibility(View.VISIBLE);
            binding.editTextAddEditTitle.setText(note.getTitle());
            binding.editTextAddEditText.setText(note.getText());
        }
        binding.buttonAddEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setText(binding.editTextAddEditText.getText().toString());
                note.setTitle(binding.editTextAddEditTitle.getText().toString());
                if (note.getId() == 0){
                    dataBaseManager.addNote(note);
                    Toast.makeText(getContext(), "Заметка добавлена", Toast.LENGTH_SHORT).show();
                }
                else {
                    dataBaseManager.editNote(note);
                    Toast.makeText(getContext(), "Заметка изменена", Toast.LENGTH_SHORT).show();
                }
                getParentFragmentManager().popBackStack();
            }
        });
        binding.buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseManager.delNote(note);
                Toast.makeText(getContext(), "Запись удалена", Toast.LENGTH_SHORT).show();
                getParentFragmentManager().popBackStack();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddEditBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dataBaseManager.closeDb();
    }
}