package com.gapharma.ui.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gapharma.ui.adapters.ListItem;
import com.gapharma.ui.adapters.items.DocumentItem;
import com.gapharma.ui.adapters.items.ImageItem;
import com.gapharma.ui.adapters.items.DescriptionItem;

import java.util.ArrayList;
import java.util.List;


/**
 * ViewModel for managing data in the AddFncActivity.
 * This ViewModel stores and handles operations on a list of ListItem objects, which represent
 * different types of items (descriptions, documents, images) related to FNCs.
 */
public class AddFncViewModel extends ViewModel {

    private final MutableLiveData<List<ListItem>> items = new MutableLiveData<>();
    private static AddFncViewModel instance;

    private Long nextDescriptionItemId;

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
        long maxId = 0;
        List<ListItem> itemList = new ArrayList<>();
        itemList.add(new DescriptionItem(1L, "Medication 1","Description 1","MoreInfo 1","20"));
        itemList.add(new DescriptionItem(2L, "Medication 2","Description 2","MoreInfo 2","22"));
        itemList.add(new DescriptionItem(3L, "Medication 3","Description 3","MoreInfo 3","24"));
        for (ListItem item : itemList) {
            if (item instanceof DescriptionItem) {
                maxId = Math.max(maxId, ((DescriptionItem) item).getId());
            }
        }
        nextDescriptionItemId = maxId + 1;
        items.setValue(itemList);
    }


    /**
     * Adds a description item to the list.
     *
     * @param newItem The DescriptionItem to be added to the list.
     */
    public void addDescriptionItem(DescriptionItem newItem) {

        List<ListItem> currentItems = items.getValue();
        if (currentItems == null) {
            currentItems = new ArrayList<>();
        }

        boolean itemExists = false;
        for (int i = 0; i < currentItems.size(); i++) {
            ListItem item = currentItems.get(i);
            if (item instanceof DescriptionItem && ((DescriptionItem) item).getId().equals(newItem.getId())) {
                currentItems.set(i, newItem);
                itemExists = true;
                break;
            }
        }
        if (!itemExists) {
            newItem.setId(nextDescriptionItemId++);
            currentItems.add(newItem);
        }
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
            currentItems.add(new ImageItem(title, filePath));
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
