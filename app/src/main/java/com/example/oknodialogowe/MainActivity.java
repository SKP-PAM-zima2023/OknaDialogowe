package com.example.oknodialogowe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int p = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button info = findViewById(R.id.buttonInfo);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                dialogBuilder.setTitle("Informacja");
                dialogBuilder.setMessage("Witamy w aplikacji");
                dialogBuilder.setCancelable(false);

                dialogBuilder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getBaseContext(), "Okno zostało zamknięte",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();

            }
        });

        Button choice = findViewById(R.id.buttonChoice);
        choice.setOnClickListener(view -> {

            CharSequence[] choices = {"Łatwy", "Średni", "Trudny"};
            final int[] temp_p = new int[1];
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Wybierz poziom trudności");
            builder.setSingleChoiceItems(choices, p, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                   temp_p[0] = i;
                }
            });

            builder.setPositiveButton("OK", (dialogInterface, i) -> {
                p = temp_p[0];
                Toast.makeText(getBaseContext(), "Wybrano poziom: "+ choices[p],
                        Toast.LENGTH_SHORT).show();
            });

            builder.setNegativeButton("Anuluj", (dialogInterface, i) ->{
                Toast.makeText(getBaseContext(), "Zrezygnownano z wyboru poziomu",
                        Toast.LENGTH_SHORT).show();
            });

            builder.setCancelable(false);

            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        });
    }

    public void window(View view) {
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<=100; i++){
                    progressBar.setProgress(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();


    }
}