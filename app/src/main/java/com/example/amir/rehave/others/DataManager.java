package com.example.amir.rehave.others;

public class DataManager {
    public DataManager(GetUserData userData){
        
    }

    public DataManager(GetAdminData userData){

    }
    public  static interface GetAdminData{
        //get data from server
        void getAdminData(String data);
    }

    public  static interface GetUserData{
        //get data from server
        void getUserData(String data);
    }
}
