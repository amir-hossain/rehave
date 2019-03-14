package com.example.amir.rehave.link;

import com.example.amir.rehave.model.DataModel;
import com.example.amir.rehave.model.MainFragmentData;

import java.util.List;

public class LinkListeners {
    public interface DataTableListener{
        void listenDatable(List<DataModel> datas);
    }
}
