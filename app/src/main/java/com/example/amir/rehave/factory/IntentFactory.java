package com.example.amir.rehave.factory;

import android.content.Context;
import android.content.Intent;

import com.example.amir.rehave.AddictionInfoDetailsActivity;
import com.example.amir.rehave.ProtectionDetailsActivity;
import com.example.amir.rehave.R;

public class IntentFactory {
    
    public static Intent getIntent(Context context,String name){
        Intent intent=null;
        if(name.equals(context.getString(R.string.adiction_information))){
            intent=new Intent(context, AddictionInfoDetailsActivity.class);

        }else if(name.equals(context.getString(R.string.relapse_protection))){
            intent=new Intent(context, ProtectionDetailsActivity.class);

        }else if(name.equals(context.getString(R.string.archive))){

            throw  new RuntimeException("অনুগ্রহ করে আর্কাইভ সেকশনে দেখুন ");

        }else {
            throw  new RuntimeException("Intent not listed");
        }

        return intent;
    }
}
