package com.example.saferchildren;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ScanListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ScanAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Query query = FirebaseFirestore.getInstance()
                .collection("scans")
                .orderBy("timestamp", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<ScanRecord> options = new FirestoreRecyclerOptions.Builder<ScanRecord>()
                .setQuery(query, ScanRecord.class)
                .build();

        adapter = new ScanAdapter(options);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
