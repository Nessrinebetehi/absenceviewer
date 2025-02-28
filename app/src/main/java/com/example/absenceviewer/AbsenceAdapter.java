package com.example.absenceviewer;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AbsenceAdapter extends RecyclerView.Adapter<AbsenceAdapter.ViewHolder> {

    private final List<AbsenceModel> absenceList;

    public AbsenceAdapter(List<AbsenceModel> absenceList) {
        this.absenceList = absenceList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.absence_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AbsenceModel absence = absenceList.get(position);
        holder.studentName.setText("Nom: " + absence.getStudentName());
        holder.date.setText("Date: " + absence.getDate());
        holder.status.setText("Statut: " + absence.getStatus());

        if (absence.isAlert()) {
            holder.studentName.setTextColor(Color.RED);
            holder.status.setTextColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return absenceList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView studentName, date, status;

        public ViewHolder(View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.studentName);
            date = itemView.findViewById(R.id.date);
            status = itemView.findViewById(R.id.status);
        }
    }
}
