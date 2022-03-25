package com.example.eproject.project.ownerinterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eproject.R;

public class EditProduct extends AppCompatActivity {
    private Button productedit;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //This Line Hide Actionbar

        setContentView(R.layout.update_popout);

        //INTENT
        productedit = (Button) findViewById(R.id.editproductbtn);
        productedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOwnerEditProductInterface();
            }
        });

        //DropDown Coding
        spinner = (Spinner) findViewById(R.id.editCategoryDD);
        adapter = ArrayAdapter.createFromResource(this, R.array.AddProductDD, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " Selected", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
    public void openOwnerEditProductInterface(){
        Intent intent = new Intent(this, OwnerShopInterface.class);
        startActivity(intent);
    }
}


