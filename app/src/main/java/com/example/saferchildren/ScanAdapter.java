package com.example.saferchildren;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ScanAdapter extends FirestoreRecyclerAdapter<ScanRecord, ScanAdapter.ScanViewHolder> {

    public ScanAdapter(@NonNull FirestoreRecyclerOptions<ScanRecord> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ScanViewHolder holder, int position, @NonNull ScanRecord model) {
        holder.userId.setText(model.getUserId());
        holder.timestamp.setText(String.valueOf(model.getTimestamp()));
        holder.action.setText(model.getAction());
    }

    @NonNull
    @Override
    public ScanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scan, parent, false);
        return new ScanViewHolder(view);
    }

    class ScanViewHolder extends RecyclerView.ViewHolder {
        TextView userId, timestamp, action;

        public ScanViewHolder(@NonNull View itemView) {
            super(itemView);
            userId = itemView.findViewById(R.id.userId);
            timestamp = itemView.findViewById(R.id.timestamp);
            action = itemView.findViewById(R.id.action);
        }
    }
}
