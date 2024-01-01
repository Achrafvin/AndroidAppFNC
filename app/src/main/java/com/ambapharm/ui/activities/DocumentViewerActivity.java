package com.ambapharm.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.ambapharm.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class DocumentViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_viewer);

        String documentPath = getIntent().getStringExtra("documentPath");
        PDFView pdfView = findViewById(R.id.pdfView);
        pdfView.fromFile(new File(documentPath))
                .load();
    }
}