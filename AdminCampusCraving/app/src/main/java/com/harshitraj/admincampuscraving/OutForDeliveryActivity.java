package com.harshitraj.admincampuscraving;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OutForDeliveryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DeliveryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_for_delivery);

        recyclerView = findViewById(R.id.outForDeliveryRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> customerNames = new ArrayList<>();
        customerNames.add("Rohit");
        customerNames.add("Harshit Raj");
        customerNames.add("Garv");
        customerNames.add("Prince");
        customerNames.add("Purwa");

        List<String> paymentStatus = new ArrayList<>();
        paymentStatus.add("received");
        paymentStatus.add("not received");
        paymentStatus.add("pending");
        paymentStatus.add("received");
        paymentStatus.add("not received");

        adapter = new DeliveryAdapter(customerNames, paymentStatus);
        recyclerView.setAdapter(adapter);
    }
}