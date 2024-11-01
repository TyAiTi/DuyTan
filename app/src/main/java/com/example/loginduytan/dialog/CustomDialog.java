package com.example.loginduytan.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.loginduytan.R;
import com.google.android.material.button.MaterialButton;

public class CustomDialog extends Dialog {

    public CustomDialog(@NonNull Context context, String message) {
        super(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_custom, null);
        setContentView(view);

        TextView messageTextView = view.findViewById(R.id.messageTextView);
        MaterialButton buttonOk = view.findViewById(R.id.buttonOk);

        messageTextView.setText(message);

        buttonOk.setOnClickListener(v -> dismiss());

        setCancelable(false);
    }
}

