package com.harshitraj.admincampuscraving;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.DeliveryViewHolder> {

    private List<String> customerNames;
    private List<String> paymentStatus;

    public DeliveryAdapter(List<String> customerNames, List<String> paymentStatus) {
        this.customerNames = customerNames;
        this.paymentStatus = paymentStatus;
    }

    @NonNull
    @Override
    public DeliveryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_item, parent, false);
        return new DeliveryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryViewHolder holder, int position) {
        holder.customerNameTextView.setText(customerNames.get(position));
        String status = paymentStatus.get(position);
        holder.paymentStatusTextView.setText(status);
        setColor(status, holder);
    }

    @Override
    public int getItemCount() {
        return customerNames.size();
    }

    static class DeliveryViewHolder extends RecyclerView.ViewHolder {
        TextView customerNameTextView;
        TextView paymentStatusTextView;
        CardView deliveryStatusColor;

        public DeliveryViewHolder(@NonNull View itemView) {
            super(itemView);
            customerNameTextView = itemView.findViewById(R.id.customerName);
            paymentStatusTextView = itemView.findViewById(R.id.paymentStatus);
            deliveryStatusColor = itemView.findViewById(R.id.deliveryStatusColor);
        }
    }

    private void setColor(String status, DeliveryViewHolder holder) {
        int textColor;
        int cardColor;
        switch (status) {
            case "received":
                textColor = ContextCompat.getColor(holder.itemView.getContext(), R.color.green);
                cardColor = ContextCompat.getColor(holder.itemView.getContext(), R.color.green);
                break;
            case "not received":
                textColor = ContextCompat.getColor(holder.itemView.getContext(), R.color.red);
                cardColor = ContextCompat.getColor(holder.itemView.getContext(), R.color.red);
                break;
            case "pending":
                textColor = ContextCompat.getColor(holder.itemView.getContext(), R.color.grey);
                cardColor = ContextCompat.getColor(holder.itemView.getContext(), R.color.grey);
                break;
            default:
                textColor = ContextCompat.getColor(holder.itemView.getContext(), R.color.black);
                cardColor = ContextCompat.getColor(holder.itemView.getContext(), R.color.white);
                break;
        }
        holder.paymentStatusTextView.setTextColor(textColor);
        holder.deliveryStatusColor.setCardBackgroundColor(cardColor);
    }

}