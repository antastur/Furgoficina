package com.example.furgoficina;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;


import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

//
public class DatePickerFragment extends DialogFragment  {
    private DatePickerDialog.OnDateSetListener listener;

    public static DatePickerFragment newInstance(DatePickerDialog.OnDateSetListener listener) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setListener(listener);
        return fragment;
    }

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    @Override
public Dialog onCreateDialog(Bundle savedInstanceState) {
     // Usar el dia actual como dia por defecto
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Crea y devuelve una nueva instancia de un objeto DatePickerDialog
        return new DatePickerDialog(getActivity(), listener, year, month, day);
        }

}

