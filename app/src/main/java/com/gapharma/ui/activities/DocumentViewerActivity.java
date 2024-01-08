package com.gapharma.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.gapharma.databinding.ActivityDocumentViewerBinding;

import java.io.File;

/**
 * Activity for viewing PDF documents.
 * It retrieves the path of a PDF file passed through an intent and displays the PDF document in a PDFView.
 */
public class DocumentViewerActivity extends AppCompatActivity {

    private ActivityDocumentViewerBinding binding;

    /**
     * Called when the activity is starting. This method initializes the view for the PDF document viewer.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     *                           being shut down then this Bundle contains the data it most
     *                           recently supplied in onSaveInstanceState(Bundle).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDocumentViewerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        intializeView();

    }


    /**
     * Initializes the view by setting up the PDF document in the viewer.
     */
    private void intializeView() {
        setTheDocumentInViewer();
    }


    /**
     * Sets the PDF document in the viewer. Retrieves the document's path from the intent,
     * loads the PDF file and displays it in the PDFView.
     */
    private void setTheDocumentInViewer() {
        String documentPath = getIntent().getStringExtra("documentPath");
        binding.pdfView.fromFile(new File(documentPath)).load();
    }
}