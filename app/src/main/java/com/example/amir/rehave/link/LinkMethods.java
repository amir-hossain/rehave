package com.example.amir.rehave.link;

import com.example.amir.rehave.data.DataListeners;
import com.example.amir.rehave.data.DataMethods;
import com.example.amir.rehave.model.MainFragmentData;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class LinkMethods implements DataListeners.DataTableListener {

    private LinkListeners.DataTableListener dataTableListener;

    public void setDataTableListener(String rootTable,LinkListeners.DataTableListener listener){

        DataMethods.setDataTableListener(rootTable,LinkMethods.this);
        this.dataTableListener=listener;
    }


    @Override
    public void listenDataTable(DataSnapshot snapshot) {

        List<MainFragmentData> datas=new ArrayList<>();
        MainFragmentData mainFragmentData;

        for(DataSnapshot childSnap : snapshot.getChildren()){
            if(!childSnap.getKey().equals("arch")){
                for(DataSnapshot grandChild: childSnap.getChildren()){

                    mainFragmentData=grandChild.getValue(MainFragmentData.class);
                    mainFragmentData.setPostId(grandChild.getKey());
                    datas.add(mainFragmentData);
                }
            }


        }



        List<MainFragmentData> randomData=getRandomTenData(datas);

        dataTableListener.listenDatable(randomData);



    }

    private List<MainFragmentData> getRandomTenData(List<MainFragmentData> datas) {
        List<MainFragmentData> ramdomData=new ArrayList<>();

//       all data now random

        Collections.shuffle(datas);

//        take Ten data

        for(int i=0;i<10;i++){

            ramdomData.add(datas.get(i));
        }

        return ramdomData;
    }
}
