package com.mobcom.gakedaiorderapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mobcom.gakedaiorderapp.R;
import com.mobcom.gakedaiorderapp.viewmodel.MenuViewModel;

public class OrderActivity extends AppCompatActivity {

    NavController navController;
    MenuViewModel menuViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Button continueMenu = findViewById(R.id.btn_continue_menu);
        TextView code = findViewById(R.id.tv_kode_checkout);
        Intent intent = getIntent();
        String kode = intent.getStringExtra("Code");
        code.setText(kode);
        continueMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                menuViewModel.resetCart();
                Intent mintent = new Intent(v.getContext(), MainActivity.class);
                startActivity(mintent);

            }
        });
    }
}