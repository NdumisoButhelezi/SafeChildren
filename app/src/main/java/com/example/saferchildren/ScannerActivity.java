package com.example.saferchildren;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.HashMap;
import java.util.Map;

public class ScannerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        // Start the scanner
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.setPrompt("Scan a QR code");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                String qrData = result.getContents();
                processQRCode(qrData);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void processQRCode(String qrData) {
        String userId = qrData; // Extract user ID from QR data

        // Get current timestamp
        long timestamp = System.currentTimeMillis();

        // Create a scan record
        Map<String, Object> scanRecord = new HashMap<>();
        scanRecord.put("userId", userId);
        scanRecord.put("timestamp", timestamp);
        scanRecord.put("action", "scan-in"); // or "scan-out"

        // Save to Firebase Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("scans")
                .add(scanRecord)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Scan recorded successfully", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed to record scan", Toast.LENGTH_SHORT).show();
                });
    }
}
