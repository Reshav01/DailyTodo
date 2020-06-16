package com.example.daily_todo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class AddEditNoteActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    //CREATING KEYS AS CONSTANTS
    public static final String EXTRA_ID =
            "com.example.simple_todo.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.example.simple_todo.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.example.simple_todo.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY =
            "com.example.simple_todo.EXTRA_PRIORITY";
    public static final String EXTRA_CHECKBOX =
            "com.example.simple_todo.EXTRA_CHECKBOX";
    public static final String EXTRA_DATE =
            "com.example.simple_todo.EXTRA_DATE";


    private EditText editTextTitle;
    private EditText editTextDescription;
    private NumberPicker numberPickerPriority;
    private EditText IScompBox;
    private EditText DatePickerEditText;
    private EditText InputDate;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        //setting datepicker
        EditText DatePickerEditText = (EditText) findViewById(R.id.InputTodoDate);
        DatePickerEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePickerFrag = new DatePickerFragment();
                datePickerFrag.show(getSupportFragmentManager(), "date picker");
            }
        });

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        IScompBox = findViewById(R.id.chkbxIsComplete);
        IScompBox.setText("Not Complete");
        numberPickerPriority = findViewById(R.id.number_picker_priority);
        InputDate = findViewById(R.id.InputTodoDate);



        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(5);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white);
        Intent intent1 = getIntent();
        if (intent1.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            editTextTitle.setText(intent1.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(intent1.getStringExtra(EXTRA_DESCRIPTION));
            InputDate.setText(intent1.getStringExtra(EXTRA_DATE));
            IScompBox.setText(intent1.getStringExtra(EXTRA_CHECKBOX));
            numberPickerPriority.setValue(intent1.getIntExtra(EXTRA_PRIORITY, 1));
        }else {
            setTitle("Add Note");
        }
    }

    //datepicker
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance().format(c.getTime());
        EditText dateEditText = (EditText) findViewById(R.id.InputTodoDate);
        dateEditText.setText(currentDateString);

    }

    //calling save-note
    private void saveNote() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        String Status = IScompBox.getText().toString();
        String inputDate1 = InputDate.getText().toString();
        int priority = numberPickerPriority.getValue();



        //validation
        if(title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(AddEditNoteActivity.this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_CHECKBOX, Status);
        data.putExtra(EXTRA_DATE, inputDate1);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1){
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();

    }

    //pasting menu created above
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    //click icon

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //passing item in switch
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
