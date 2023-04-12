package com.example.internalfiles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MainActivity extends AppCompatActivity {

    //components
    EditText edt;
    TextView outputTxt;

    //vars
    String input = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt = (EditText) findViewById(R.id.inputEdt);
        outputTxt = (TextView) findViewById(R.id.outputTxt);

        try
        {
            FileInputStream fis = openFileInput("info.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            String line = br.readLine();
            while(line != null)
            {
                sb.append(line + '\n');
                line = br.readLine();
            }
            outputTxt.setText(sb.toString());
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This function saves the contennt of the user
     * @param view
     */
    public void Save(View view)
    {
        String temp = edt.getText().toString();
        input += temp;

        try {
            FileOutputStream fos = openFileOutput("info.txt", MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(input);
            bw.close();
            Toast.makeText(this, "Saved information", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This function earases the content of the user
     * @param view
     */
    public void Reset(View view)
    {
        try {
            FileOutputStream fos = openFileOutput("info.txt", MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.flush();
            bw.close();
            Toast.makeText(this, "All writing history erased", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This function exits the app and saves the content
     * @param view
     */
    public void Exit(View view)
    {
        Save(view);
        finish();
    }

    /**
     * this function creates an options menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * This function switches to the credits activity
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent si = new Intent(this, MainActivity2.class);
        startActivity(si);
        return true;
    }
}