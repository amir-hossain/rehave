package com.example.amir.rehave;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.text.Html;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

public class Utils {

    public static int notification=0;
    public static String newCommentedPostId;

    public static  String getSectionName(int section) {
        if(section==Constants.Section.ADDICTION.toInt()){
            return "মাদক  ও  মাদকাসক্তি  বিষয়ে তথ্য";
        }else if(section==Constants.Section.PROTECTION.toInt()){
            return "রিলাপ্স প্রতিরোধ";
        }else if(section==Constants.Section.ARCHIVE.toInt()){
            return "আর্কাইভ";
        }else {
            return "not listed";
        }
    }

    @BindingAdapter({"showHtml"})
    public static void showHtml(WebView webView, String text) {

        webView.loadDataWithBaseURL("file:///android_asset/",text,"text/html","UTF-8","about:blank");

    }


    public static boolean isNetworkConnected(Context context){
        ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo()!=null;
    }
}
