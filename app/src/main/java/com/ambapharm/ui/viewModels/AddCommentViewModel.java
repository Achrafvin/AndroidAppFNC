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

public class AddCommentViewModel extends ViewModel {

    private MutableLiveData<List<ListItem>> items = new MutableLiveData<>();

    public LiveData<List<ListItem>> getItems() {
        loadData();
        return items;
    }

    private void loadData() {
        List<ListItem> itemList = new ArrayList<>();
        itemList.add(new MedicationItem("Medication 1","Description","MoreInfo","20"));
        itemList.add(new MedicationItem("Medication 1","Description","MoreInfo","20"));
        itemList.add(new DocumentItem("Document 5"));
        itemList.add(new ImageDocItem("Image Document 5"));
        items.setValue(itemList);
    }

}
