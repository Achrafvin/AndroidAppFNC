package com.ambapharm.ui.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ambapharm.ui.adapters.ListItem;
import com.ambapharm.ui.adapters.items.DocumentItem;
import com.ambapharm.ui.adapters.items.ImageDocItem;
import com.ambapharm.ui.adapters.items.MedicationItem;

import java.util.ArrayList;
import java.util.List;

public class AddFncViewModel extends ViewModel {

    private MutableLiveData<List<ListItem>> items = new MutableLiveData<>();

    public LiveData<List<ListItem>> getItems() {
        loadData();
        return items;
    }

    private void loadData() {
        List<ListItem> itemList = new ArrayList<>();
        itemList.add(new MedicationItem("Medication 1","Description 1","MoreInfo 1","20"));
        itemList.add(new MedicationItem("Medication 2","Description 2","MoreInfo 2","22"));
        itemList.add(new MedicationItem("Medication 3","Description 3","MoreInfo 3","24"));
        itemList.add(new MedicationItem("Medication 4","Description 4","MoreInfo 4","26"));
        items.setValue(itemList);
    }

    public void addImageFromCamera(String imagePath) {
        if (items.getValue() != null) {
            List<ListItem> updatedList = new ArrayList<>(items.getValue());
            String imageName = imagePath.substring(imagePath.lastIndexOf('/') + 1);
            ImageDocItem newImageItem = new ImageDocItem(imageName, imagePath);
            updatedList.add(newImageItem);
            items.setValue(updatedList);
        } else {
            List<ListItem> newList = new ArrayList<>();
            String imageName = imagePath.substring(imagePath.lastIndexOf('/') + 1);
            newList.add(new ImageDocItem(imageName, imagePath));
            items.setValue(newList);
        }
    }

    public void addImageFromGallery(String title, String filePath) {
        List<ListItem> currentItems = items.getValue();
        if (currentItems == null) {
            currentItems = new ArrayList<>();
        }
        currentItems.add(new ImageDocItem(title, filePath));
        items.setValue(currentItems);
    }


    public void addDocumentItem(String title, String filePath) {
        List<ListItem> currentItems = items.getValue();
        if (currentItems == null) {
            currentItems = new ArrayList<>();
        }
        currentItems.add(new DocumentItem(title, filePath));
        items.setValue(currentItems);
    }

    public void removeItem(int position) {
        if (items.getValue() != null) {
            List<ListItem> updatedList = new ArrayList<>(items.getValue());
            updatedList.remove(position);
            items.setValue(updatedList);
        }
    }

}
