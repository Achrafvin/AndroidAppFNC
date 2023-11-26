package com.ambapharm.ui.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ambapharm.ui.adapters.ListItem;
import com.ambapharm.ui.adapters.items.FncListItem;

import java.util.ArrayList;
import java.util.List;

public class FncListViewModel extends ViewModel {

    private MutableLiveData<List<ListItem>> items = new MutableLiveData<>();

    public LiveData<List<ListItem>> getItems() {
        loadData();
        return items;

    }
    private void loadData() {
        List<ListItem> itemList = new ArrayList<>();
        itemList.add(new FncListItem("Document 1", "(12/10/2023)"));
        itemList.add(new FncListItem("Document 2", "(12/10/2023)"));
        itemList.add(new FncListItem("Document 3", "(12/10/2023)"));
        itemList.add(new FncListItem("Document 3", "(12/10/2023)"));
        itemList.add(new FncListItem("Document 3", "(12/10/2023)"));
        itemList.add(new FncListItem("Document 3", "(12/10/2023)"));
        itemList.add(new FncListItem("Document 3", "(12/10/2023)"));
        itemList.add(new FncListItem("Document 3", "(12/10/2023)"));
        itemList.add(new FncListItem("Document 3", "(12/10/2023)"));
        itemList.add(new FncListItem("Document 3", "(12/10/2023)"));
        itemList.add(new FncListItem("Document 3", "(12/10/2023)"));
        itemList.add(new FncListItem("Document 3", "(12/10/2023)"));
        itemList.add(new FncListItem("Document 3", "(12/10/2023)"));
        itemList.add(new FncListItem("Document 3", "(12/10/2023)"));
        items.setValue(itemList);
    }



}
