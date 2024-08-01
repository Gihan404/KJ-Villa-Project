package com.kayjay.villareservation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InquiryAdapter extends RecyclerView.Adapter<InquiryAdapter.InquiryViewHolder> {

    private List<Inquiry> inquiryList;

    public InquiryAdapter(List<Inquiry> inquiryList) {
        this.inquiryList = inquiryList;
    }

    @NonNull
    @Override
    public InquiryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view, parent, false);
        return new InquiryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InquiryViewHolder holder, int position) {
        Inquiry inquiry = inquiryList.get(position);
        holder.textViewTitle.setText(inquiry.getTitle());
        holder.textViewType.setText(inquiry.getType());
        holder.textViewDetails.setText(inquiry.getDetails());
    }

    @Override
    public int getItemCount() {
        return inquiryList.size();
    }

    static class InquiryViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewType;
        TextView textViewDetails;

        public InquiryViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewType = itemView.findViewById(R.id.textViewType);
            textViewDetails = itemView.findViewById(R.id.textViewDetails);
        }
    }
}