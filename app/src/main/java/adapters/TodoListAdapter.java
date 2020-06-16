package adapters;

import android.content.Context;
import android.widget.*;
import entities.*;
import java.util.*;
import android.view.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.daily_todo.R;




public class TodoListAdapter extends ArrayAdapter<Todo> {

    private Context context;
    private List<Todo> todos;


    public TodoListAdapter(@NonNull Context context, List<Todo> todos) {
        super(context, R.layout.todo_layout, todos);
        this.context = context;
        this.todos = todos;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.todo_layout, parent, false);

        TextView textView_title = view.findViewById(R.id.textView_title);
        textView_title.setText(todos.get(position).getTitle());

        TextView textView_description = view.findViewById(R.id.textView_description);
        textView_description.setText(todos.get(position).getDescription());
        return view;
    }
}
