package com.example.daily_todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolder> {

    //member variable
//    private List<Note> notes = new ArrayList<>();

    //member variable for update
    private OnItemClickListener listener;

    //for animations
    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority() == newItem.getPriority() &&
                    oldItem.getIsComplete().equals(newItem.getIsComplete()) &&
                    oldItem.getInputDate().equals(newItem.getInputDate());
        }
    };

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {

        Note currentNote = getItem(position);
        holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewDescription.setText(currentNote.getDescription());

        holder.textViewPriority.setText(String.valueOf(currentNote.getPriority()));
        holder.textViewStatus.setText(String.valueOf(currentNote.getIsComplete()));
        //setting status background color..
        if(holder.textViewStatus.getText().equals("Not Complete")){
            holder.textViewStatus.setBackgroundResource(R.drawable.ststusnocomplete);

        }else if (holder.textViewStatus.getText().equals("Complete")){
            holder.textViewStatus.setBackgroundResource(R.drawable.status);
        } else {
            holder.textViewStatus.setBackgroundResource(R.drawable.gradientbackground);
        }

        holder.textViewDate.setText(currentNote.getInputDate());
    }

//    @Override
//    public int getItemCount() {
//        return notes.size();
//    }
    //method
    //the way to get list of notes into recycle view.
//    public void setNotes(List<Note> notes) {
//        this.notes = notes;
//        notifyDataSetChanged();
//    }

    public Note getNoteAt(int position) {

        return getItem(position);
    }

    class NoteHolder extends  RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPriority;
        private TextView textViewStatus;
        private TextView textViewDate;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
            textViewStatus = itemView.findViewById(R.id.chkbxIsComplete);
            textViewDate = itemView.findViewById(R.id.InputTodoDate);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //adapter position
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }

                }
            });
        }
    }

    //update function..

    public interface OnItemClickListener {
        void onItemClick(Note note);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {

        this.listener = listener;

    }


}
