package com.example.a2630pr0103kysh.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2630pr0103kysh.R;
import com.example.a2630pr0103kysh.data.Notes;

import java.util.List;

import kotlin.jvm.internal.Lambda;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    public interface OnNoteClickListener{
        void OnNoteClick(Notes note, int pos);
    }
    OnNoteClickListener onClickListener;
    LayoutInflater layoutInflater;
    List<Notes> notesList;
    public NotesAdapter(Context context, List<Notes> notesList, OnNoteClickListener onClickListener){
        this.layoutInflater = LayoutInflater.from(context);
        this.notesList = notesList;
        this.onClickListener = onClickListener;
    }
    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_note, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Notes note = notesList.get(position);
        String title = note.getTitle();
        String text = note.getText();
        if (note.getTitle().length() > 15)
            holder.tvTitle.setText(title.substring(0, 13) + "...");
        else holder.tvTitle.setText(title);
        if (note.getText().length() > 43)
            holder.tvText.setText(text.substring(0, 40) + "...");
        else holder.tvText.setText(text);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.OnNoteClick(note, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.textViewText);
            tvTitle = itemView.findViewById(R.id.textViewTitle);
        }
    }
}
