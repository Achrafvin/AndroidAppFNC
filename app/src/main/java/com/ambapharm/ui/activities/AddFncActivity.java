package com.ambapharm.ui.activities;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ambapharm.R;
import com.ambapharm.databinding.ActivityAddFncBinding;
import com.ambapharm.helpers.ConstantFncNum;
import com.ambapharm.ui.adapters.ListItem;
import com.ambapharm.ui.viewModels.AddFncViewModel;
import com.ambapharm.ui.adapters.GenericAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * Activity for managing the addition of various elements to a pharmaceutical product anomaly report (FNC).
 * This includes handling descriptions, images, documents, and overall comments for the FNC.
 * The class supports operations like selecting images from gallery or taking new ones,
 * picking documents, and adding detailed descriptions.
 */
public class AddFncActivity extends BaseActivity implements GenericAdapter.OnItemDeleteListener {

    private ActivityAddFncBinding binding;
    private AddFncViewModel viewModel;
    private GenericAdapter adapter;
    private String toolbarTitle;
    private ActivityResultLauncher<Intent> galleryActivityResultLauncher;
    private ActivityResultLauncher<Intent> cameraActivityResultLauncher;
    private ActivityResultLauncher<Intent> documentPickerActivityResultLauncher;
    private ActivityResultLauncher<Intent> addDescriptionActivityResultLauncher;
    private static final float ROTATION_START = 0f;
    private static final float ROTATION_END = 45f;
    private Uri cameraImageUri;


    /**
     * Called when the activity is starting. This is where most initialization should go.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     *                           being shut down then this Bundle contains the data it most
     *                           recently supplied in onSaveInstanceState(Bundle).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeActivity();
    }

    /**
     * Initializes the activity's UI components and sets up the necessary data.
     */
    private void initializeActivity() {
        binding = ActivityAddFncBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = AddFncViewModel.getInstance();
        extractIntentData();
        setFncTitleForToolbar();
        initializeRecyclerView();
        observeViewModel();
        setupFloatingActionButtons();
        setupButtonListeners();
        initializeResultLauncher();
        viewModel.initializeData();

    }



    @Override
    protected void onResume() {
        super.onResume();
        if (binding.includedToolbar.buttonFile.getVisibility() == View.VISIBLE) {
            toggleFabMenu(false);
        }

    }

    /**
     * Sets the title for the toolbar based on FNC data.
     */
    private void setFncTitleForToolbar() {
        setSupportActionBar(binding.includedToolbar.appBarLayout.topAppBar);
        binding.includedToolbar.appBarLayout.topAppBar.setTitle(
                toolbarTitle != null && !toolbarTitle.isEmpty() ? toolbarTitle : getString(R.string.labelNcfNumber)
        );
        binding.includedToolbar.appBarLayout.topAppBar.setNavigationIcon(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        configureMenuItem(menu.findItem(R.id.headerIcon));
        return true;
    }

    private void configureMenuItem(MenuItem item) {
        item.setIcon(R.drawable.ic_save);
        item.setTitle(R.string.buttonSave);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.headerIcon) {
            saveAndNavigateToDashboard();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Initializes the RecyclerView used for displaying items (images, documents, etc.) in the FNC report.
     */
    private void initializeRecyclerView() {
        adapter = new GenericAdapter(this, new ArrayList<>(),  this);
        binding.cardView.setLayoutManager(new LinearLayoutManager(this));
        binding.cardView.setAdapter(adapter);
    }

    private void extractIntentData() {
        toolbarTitle = getIntent().getStringExtra(ConstantFncNum.FNC_KEY);
    }


    private void setupFloatingActionButtons() {
        binding.includedToolbar.fabMenu.setOnClickListener(view -> {
            hideKeyboard();
            toggleFabMenu(binding.includedToolbar.buttonFile.getVisibility() == View.GONE);
        });
    }

    private void toggleFabMenu(boolean show) {
        setFabVisibility(show ? View.VISIBLE : View.GONE);
        animateFabMenu(show);
    }

    private void setFabVisibility(int visibility) {
        binding.includedToolbar.buttonFile.setVisibility(visibility);
        binding.includedToolbar.buttonCamere.setVisibility(visibility);
        binding.includedToolbar.buttonDescription.setVisibility(visibility);
        binding.overlay.setVisibility(visibility);
        binding.includedToolbar.pjText.setVisibility(visibility);
        binding.includedToolbar.caText.setVisibility(visibility);
        binding.includedToolbar.lrText.setVisibility(visibility);
    }

    private void animateFabMenu(boolean open) {
        float startRotation = open ? ROTATION_START : ROTATION_END;
        float endRotation = open ? ROTATION_END : ROTATION_START;
        ObjectAnimator rotate = ObjectAnimator.ofFloat(binding.includedToolbar.fabMenu, "rotation", startRotation, endRotation);
        rotate.start();
    }

    private void setupButtonListeners() {
        binding.includedToolbar.buttonDescription.setOnClickListener(v -> navigateToAddDescriptionActivity());
        binding.includedToolbar.buttonCamere.setOnClickListener(v -> openCameraOptionsPopup());
        binding.includedToolbar.buttonFile.setOnClickListener(v -> openDocumentOptionsPopup());
    }

    private void initializeResultLauncher() {
        galleryActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        saveToStorage(imageUri);
                    }
                }
        );
        cameraActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        if (cameraImageUri != null) {
                            saveToStorage(cameraImageUri);
                        } else {
                            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );


        documentPickerActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Uri documentUri = result.getData().getData();
                        saveToStorage(documentUri);
                    }
                }
        );

        addDescriptionActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        viewModel.getItems().observe(this, this::updateItemList);
                    }
                }
        );

    }

    /**
     * Handles the selection and addition of files (images or documents) to the FNC report.
     *
     * @param imageUri The URI of the selected image or document.
     */
    private void saveToStorage(Uri imageUri) {
        String fileName = getFileName(imageUri);
        File file = new File(getFilesDir(), fileName);
        try (InputStream in = getContentResolver().openInputStream(imageUri);
             OutputStream out = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            viewModel.addDocAndImage(fileName, file.getAbsolutePath());
        } catch (IOException e) {
            Log.e("AddFncActivity", "Error saving document", e);
        }
    }

    /**
     * Extracts the file name from the given URI.
     *
     * @param uri The URI of the file.
     * @return The file name extracted from the URI.
     */
    @SuppressLint("Range")
    private String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    private void navigateToAddDescriptionActivity() {
        Intent intent = new Intent(this, AddDescriptionActivity.class);
        addDescriptionActivityResultLauncher.launch(intent);
    }

    private void openCameraOptionsPopup() {
        final View dialogView = LayoutInflater.from(this)
                .inflate(R.layout.photodialog, null);
        final BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(dialogView);
        dialogView.findViewById(R.id.btnChooseGallery).setOnClickListener(v -> {
            openGallery();
            dialog.dismiss();
        });
        dialogView.findViewById(R.id.btnTakePhoto).setOnClickListener(v -> {
            openCamera();
            dialog.dismiss();
        });
        dialog.show();
    }

    private void openDocumentOptionsPopup() {
        final View dialogView = LayoutInflater.from(this)
                .inflate(R.layout.documentdialog, null);
        final BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(dialogView);
        dialogView.findViewById(R.id.btnChooseDocument).setOnClickListener(v -> {
            openDocument();
            dialog.dismiss();
        });
        dialog.show();
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryActivityResultLauncher.launch(galleryIntent);
    }

    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                Log.e("AddFncActivity", "Error creating image", e);
            }
            if (photoFile != null) {
                cameraImageUri = FileProvider.getUriForFile(this,
                        "com.ambapharm.app",
                        photoFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cameraImageUri);
                cameraActivityResultLauncher.launch(cameraIntent);
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("MMdd", Locale.getDefault()).format(new Date());
        String imageFileName = "camera_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName,".jpg",storageDir);
    }


    private void openDocument() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        documentPickerActivityResultLauncher.launch(intent);
    }


    /**
     * Observes changes in the ViewModel and updates UI components accordingly.
     */
    private void observeViewModel() {
        viewModel.getItems().observe(this, items -> {
            updateItemList(items);
            toggleContentVisibility();
        });
    }


    /**
     * Updates the list of items in the RecyclerView adapter.
     *
     * @param items List of items to display in the RecyclerView.
     */
    private void updateItemList(List<ListItem> items) {
        if (items != null) {
            adapter.setItems(items);
            adapter.notifyDataSetChanged();
        }
    }

    private void toggleContentVisibility() {
        boolean isDataAvailable = isFncDataAvailable();
        binding.fncCardEmpty.setVisibility(isDataAvailable ? View.GONE : View.VISIBLE);
        binding.fncContentContainer.setVisibility(isDataAvailable ? View.VISIBLE : View.GONE);
    }

    private boolean isFncDataAvailable() {
        List<ListItem> items = viewModel.getItems().getValue();
        return items != null && !items.isEmpty();
    }

    private void saveAndNavigateToDashboard() {
        hideKeyboard();
        if(validateInput()) {
            startActivity(new Intent(this, SuccessActivity.class));
            finish();
        }

    }

    private boolean validateInput() {
        if (binding.issueCmt.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Champ requise", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    /**
     * Called when an item in the list is requested to be deleted.
     *
     * @param position The position of the item in the list.
     */
    @Override
    public void onDeleteItem(int position) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmer la Suppression")
                .setMessage("Êtes-vous sûr de vouloir supprimer cet élément ?")
                .setPositiveButton("Supprimé", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        viewModel.removeItem(position);
                    }
                })
                .setNegativeButton("Annuler", null)
                .show();

    }

}
