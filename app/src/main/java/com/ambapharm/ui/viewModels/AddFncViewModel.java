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


/**
 * ViewModel for managing data in the AddFncActivity.
 * This ViewModel stores and handles operations on a list of ListItem objects, which represent
 * different types of items (medications, documents, images) related to FNCs.
 */
public class AddFncViewModel extends ViewModel {

    private MutableLiveData<List<ListItem>> items = new MutableLiveData<>();
    private static AddFncViewModel instance;


    /**
     * Private constructor to enforce singleton pattern.
     */
    private AddFncViewModel() {
        items.setValue(new ArrayList<>());
    }

    /**
     * Provides a singleton instance of AddFncViewModel.
     *
     * @return The singleton instance of AddFncViewModel.
     */
    public static synchronized AddFncViewModel getInstance() {
        if (instance == null) {
            instance = new AddFncViewModel();
        }
        return instance;
    }


    /**
     * Returns a LiveData object containing the list of ListItem objects.
     *
     * @return LiveData containing the list of items.
     */
    public LiveData<List<ListItem>> getItems() {
        return items;
    }


    /**
     * Initializes data in the ViewModel. Loads initial data if the current item list is null or empty.
     */
    public void initializeData() {
        if (items.getValue() == null || items.getValue().isEmpty()) {
            loadData();
        }
    }


    /**
     * Loads initial data into the ViewModel. This is a placeholder for initial data and
     * would typically be replaced with data from a database or network call.
     */
    private void loadData() {
        List<ListItem> itemList = new ArrayList<>();
        itemList.add(new MedicationItem("Medication 1","Description 1","MoreInfo 1","20"));
        itemList.add(new MedicationItem("Medication 2","Description 2","MoreInfo 2","22"));
        itemList.add(new MedicationItem("Medication 3","Description 3","MoreInfo 3","24"));
        items.setValue(itemList);
    }


    /**
     * Adds a medication item to the list.
     *
     * @param item The MedicationItem to be added to the list.
     */
    public void addMedicationItem(MedicationItem item) {
        List<ListItem> currentItems = items.getValue();
        if (currentItems == null) {
            currentItems = new ArrayList<>();
        }
        currentItems.add(new MedicationItem(item.getMainTitle(), item.getSubtitle(), item.getComment(), item.getNum()));
        items.setValue(currentItems);
    }

    /**
     * Adds a document or image item to the list based on the file path.
     *
     * @param title     The title of the item.
     * @param filePath  The file path of the item, used to determine the type (document or image).
     */
    public void addDocAndImage(String title, String filePath) {
        List<ListItem> currentItems = items.getValue();
        if (currentItems == null) {
            currentItems = new ArrayList<>();
        }
        if(filePath.contains(".pdf"))
            currentItems.add(new DocumentItem(title, filePath));
        else
            currentItems.add(new ImageDocItem(title, filePath));
        items.setValue(currentItems);
    }


    /**
     * Removes an item from the list at the specified position.
     *
     * @param position The position of the item to be removed.
     */
    public void removeItem(int position) {
        if (items.getValue() != null) {
            List<ListItem> updatedList = new ArrayList<>(items.getValue());
            updatedList.remove(position);
            items.setValue(updatedList);
        }
    }

}
