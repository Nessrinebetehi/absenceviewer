package com.example.absenceviewer;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private List<Student> studentList;
    private HashMap<String, Integer> absenceCountMap;

    public StudentAdapter(List<Student> studentList, HashMap<String, Integer> absenceCountMap) {
        this.studentList = studentList;
        this.absenceCountMap = absenceCountMap;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.name.setText(student.getName());
        holder.date.setText(student.getDate());
        holder.status.setText(student.getStatus());
        holder.subject.setText("Matière : " + student.getSubject());

        int absences = absenceCountMap.getOrDefault(student.getName(), 0);
        if (absences >= 2) {
            holder.warning.setVisibility(View.VISIBLE);
            holder.warning.setText("⚠️ Attention : " + absences + " absences !");
            holder.warning.setTextColor(Color.RED);
        } else {
            holder.warning.setVisibility(View.GONE);
        }

        if (student.getStatus().equalsIgnoreCase("Absent")) {
            holder.status.setTextColor(Color.RED);
        } else {
            holder.status.setTextColor(Color.BLACK);
        }
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, date, status, subject, warning;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.studentName);
            date = itemView.findViewById(R.id.absenceDate);
            status = itemView.findViewById(R.id.absenceStatus);
            subject = itemView.findViewById(R.id.subject);
            warning = itemView.findViewById(R.id.absenceWarning);
        }
    }
}
