package com.example.amir.rehave.data;

import com.google.firebase.database.DataSnapshot;

public class DataListeners {
    public interface DataTableListener {
        void listenDataTable(DataSnapshot snapshot);
    }
}
