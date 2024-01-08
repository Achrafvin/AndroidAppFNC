package com.gapharma.ui.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gapharma.ui.adapters.ListItem;
import com.gapharma.ui.adapters.items.FncListItem;

import java.util.ArrayList;
import java.util.List;


/**
 * ViewModel for managing a list of FNC items.
 * This ViewModel stores and handles operations on a list of ListItem objects, each representing an FNC item.
 * It currently populates the list with mock data for demonstration purposes.
 */
public class FncListViewModel extends ViewModel {

    private MutableLiveData<List<ListItem>> items = new MutableLiveData<>();


    /**
     * Provides LiveData for the list of FNC items. When called, it also triggers loading of data.
     *
     * @return LiveData containing the list of FNC items.
     */
    public LiveData<List<ListItem>> getItems() {
        loadData();
        return items;

    }


    /**
     * Loads data into the ViewModel. This method currently populates the list with mock data.
     * In a production scenario, this method would be modified to load real data, possibly from a database or network source.
     */
    private void loadData() {
        List<ListItem> itemList = new ArrayList<>();
        itemList.add(new FncListItem("Document 1", "(12/10/2023)"));
        itemList.add(new FncListItem("Document 2", "(12/10/2023)"));
        itemList.add(new FncListItem("Document 3", "(12/10/2023)"));
        itemList.add(new FncListItem("Document 4", "(12/10/2023)"));
        itemList.add(new FncListItem("Document 5", "(12/10/2023)"));
        itemList.add(new FncListItem("Document 6", "(12/10/2023)"));
        itemList.add(new FncListItem("Document 7", "(12/10/2023)"));
        itemList.add(new FncListItem("Document 8", "(12/10/2023)"));
        itemList.add(new FncListItem("Document 9", "(12/10/2023)"));
        itemList.add(new FncListItem("Document 10", "(12/10/2023)"));
        itemList.add(new FncListItem("Document 11", "(12/10/2023)"));
        items.setValue(itemList);
    }

}
