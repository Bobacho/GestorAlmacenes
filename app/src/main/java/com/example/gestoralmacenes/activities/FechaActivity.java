package com.example.gestoralmacenes.activities;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.gestoralmacenes.R;

import java.time.LocalDate;
import java.util.Calendar;

public class FechaActivity extends AppCompatActivity {

    private DatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fecha);
        datePicker=new DatePicker(this);
        datePicker.setCalendarViewShown(true);
        datePicker.setSpinnersShown(false);
        datePicker.setMaxDate(Calendar.getInstance().getTimeInMillis());
        LinearLayout layout=(LinearLayout)findViewById(R.id.linearLayoutFechas);
        layout.addView(datePicker);
        Button boton=findViewById(R.id.button6);
        switch(getIntent().getIntExtra("Fecha",0))
        {
            case 1:
                boton.setText("Siguiente");
                boton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent(FechaActivity.this, FechaActivity.class);
                        i.putExtra("Fecha",2);
                        i.putExtra("FechaInicioDia",datePicker.getDayOfMonth());
                        i.putExtra("FechaInicioMes",datePicker.getMonth()+1);
                        i.putExtra("FechaInicioAño",datePicker.getYear());
                        Log.d("Fecha inicio",i.getIntExtra("FechaInicioDia",1)+"/"+i.getIntExtra("FechaInicioMes",1)+"/"+i.getIntExtra("FechaInicioAño",1970));
                        startActivity(i);
                    }
                });
                break;
            case 2:
                boton.setText("Finalizar");
                boton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent(FechaActivity.this, Menu.class);
                        i.putExtra("FechaInicioDia",getIntent().getIntExtra("FechaInicioDia",1));
                        i.putExtra("FechaInicioMes",getIntent().getIntExtra("FechaInicioMes",1));
                        i.putExtra("FechaInicioAño",getIntent().getIntExtra("FechaInicioAño",1970));
                        i.putExtra("FechaFinDia",datePicker.getDayOfMonth());
                        i.putExtra("FechaFinMes",datePicker.getMonth()+1);
                        i.putExtra("FechaFinAño",datePicker.getYear());
                        i.putExtra("Filtros",1);
                        Log.d("Fechas",i.getIntExtra("FechaInicioDia",1)+"/"+i.getIntExtra("FechaInicioMes",1)+"/"+i.getIntExtra("FechaInicioAño",1970)+"\n"+i.getIntExtra("FechaFinDia",1)+"/"+i.getIntExtra("FechaFinMes",1)+"/"+i.getIntExtra("FechaFinAño",1970));
                        startActivity(i);
                    }
                });
                break;
        }
    }
}