package com.example.amir.rehave;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.text.Html;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.amir.rehave.model.DataModel;

public class Utils {

    public static String aboutText="<div>\n"+
            "<strong>আলোর মিছিল কি?</strong> \n"+
            "    <br/>\n"+
            "    <br/>\n"+
            "</div>\n"+
            "<div>\n"+
            "    এটি একটি মাদকাসক্ত চিকিৎসা পরবর্তী সেবা গ্রহন সংক্রান্ত মোবাইল আপ্লিকেশন যা\n"+
            "    মাদকাসক্ত চিকিৎসা নেবার পর একজন মাদকমুক্ত ব্যাক্তি বা মাদকগ্রহন করছেন তাদের\n"+
            "    মাদক মুক্ত রাখতে সাহায্য করবে।  \n"+
            "</div>\n"+
            "<div>\n"+
            "    বাংলাদেশ সহ সমগ্র বিশ্বে আলোর মিছিল একমাত্র বাংলাভাষী মাদকমুক্ত রিকভারিং\n"+
            "    সাহায্যকারি মোবাইল অ্যাপ। \n"+
            "</div>\n"+
            "<div>\n"+
            "    নির্দিষ্ট পুনর্বাসন কেন্দ্র থেকে অ্যাপটি নেওয়া যাবে \n"+
            "</div>\n"+
            "<div>\n"+
            "    <br/>\n"+
            "</div>\n"+
            "<div>\n"+
            "<strong>আলোর মিছিল কিভাবে কাজ করে ?</strong>\n"+
            "</div>\n"+
            "<div>\n"+
            "    <br/>\n"+
            "</div>\n"+
            "<div>\n"+
            "    এই আপ্লিকেসন টির তিনটি ভাগ আছে যার উপর ভিত্তি করে  ১। মাদক গ্রহন  ২।\n"+
            "    রিকভারিং ৩। আলোর মিছিল আর্কাইভ   \n"+
            "</div>\n"+
            "<div>\n"+
            "    প্রথম দুটি ভাগ একটি পরিবর্তন মানক প্রশ্ন দ্বারা নির্ধারণ করা হয় যার নাম\n"+
            "    দেওয়া হয়েছে\n"+
            "</div>\n"+
            "<div>\n"+
            "                                    \n"+
            "</div>\n"+
            "<div>\n"+
            "     \" আজকের দিনে আমার অবস্থা\"\n"+
            "</div>\n"+
            "<div>\n"+
            "    এই পরিবর্তন মানকটি নির্ধারণ করবে আজ আপনাকে কি করতে হবে \n"+
            "</div>\n"+
            "<div>\n"+
            "    করনীয় গুলো লিখিত আকারে বিভিন্ন ভাগে ভাগ করা আছে ও সাথে বিভিন্ন কৌশল প্রদান\n"+
            "    করা আছে যা পড়ে ও প্রয়োগ করে বিভিন্ন সমস্যার সমাধান পাওয়া যাবে । \n"+
            "</div>\n"+
            "<div>\n"+
            "    বিশ্বখ্যাত পরিবর্তন মডেলের উপর ভিত্তি করে এই আপটি দাড় করানো হয়েছে তবে উক্ত\n"+
            "    আপটিতে নারকটিস এনোনিমাস, মেডিক্যাল বা জৈবিক মডেল, সামাজিক মডেল ও বাংলাদেশের\n"+
            "    পুনর্বাসন সংস্কৃতির অনেক উপাদান গ্রহন করা হয়েছে।  \n"+
            "</div>\n"+
            "<div>\n"+
            "    <br/>\n"+
            "</div>\n"+
            "<div>\n"+
            "    আলোর মিছিলের বর্তমান ভার্সনটি একটি নির্দিষ্ট গোষ্ঠী যারা মাদকাসক্তর\n"+
            "    পুনর্বাসন ভিত্তিক চিকিৎসা গ্রহন করেছেন তাদের জন্য   প্রযোজ্য তাই সবাই এই\n"+
            "    আপের আওতাভুক্ত হবে না  \n"+
            "</div>\n"+
            "<div>\n"+
            "    যারা লেখাপড়া জানেন তাদের জন্য এই আপটি ব্যাবহার উপযোগী \n"+
            "</div>\n"+
            "<div>\n"+
            "    আপ্লিকেশনটি বর্তমানে পুনর্বাসন কেন্দ্র ও আলোর মিছিল কর্তৃপক্ষ দ্বারা\n"+
            "    নিয়ন্ত্রিত \n"+
            "</div>\n"+
            "<div>\n"+
            "    আপ্লিকেশনটির সদস্যদের সকল তথ্য  প্রায় পরিপূর্ণ ভাবে গোপনীয় । তাই আপ্লিকেশনে\n"+
            "    কোন নাম, ঠিকানা,পরিচয়,ছবি,ভিডিও দেওয়ার কোন সুযোগ দেওয়া হয় নাই তবে শুধু\n"+
            "    মাত্র নির্দিষ্ট কিছু আইডি দেওয়া হয়েছে । যা আলোর মিছিলের কর্তৃপক্ষ দ্বারা\n"+
            "    নিয়ন্ত্রিত থাকবে \n"+
            "</div>\n"+
            "<div>\n"+
            "    কেউ যদি এই আপ্লিকেশনে ব্যাবহার না করতে চায় তাহলে সে নিজ থেকে আপটি মুছে\n"+
            "    ফেলতে পারবে সাথে তার সকল তথ্য মুছে যাবে \n"+
            "</div>\n"+
            "<div>\n"+
            "    শুধু মাত্র লিখা ও মন্তব্য প্রদান ছাড়া একজন সদস্য আপ্লিকেশনে কোন কিছু শেয়ার\n"+
            "    করতে পারবেনা  \n"+
            "</div>\n"+
            "<div>\n"+
            "    সাহায্য ও কথোপকথনের জন্য একটি ছোট ফোরাম রাখা হয়েছে যেখানে একজন রিকভারি তার\n"+
            "    কথা, মাদকের টান বা কোন তথ্য সবাইকে বা নির্দিষ্ট বিশেষজ্ঞকে জানাতে পারবে \n"+
            "</div>\n"+
            "<div>\n"+
            "    আলোর মিছিলের একটি আর্কাইভ আছে যেখান থেকে আপ্লিকেশনের সদস্য বিভিন্ন ভিডিও,\n"+
            "    রিকভারিং অনুপ্রেরনা মুলক অডিও, মাদকাসক্ত বিষয়ে বই, বিশেষজ্ঞ পরামর্শ, \n"+
            "    অনুপ্রেরনা মুলক ছবি, স্লোগান, মাদকাসক্ত বিষয়ক পণ্য, সাপ্তাহিক মাদকের উপর\n"+
            "    খবর, পুনরবাসনের ঠিকানা সহ অন্যান্য বিষয় সংযুক্ত থাকবে।\n"+
            "</div>";

    public static int notification=0;
    public static boolean newCommentAdded;

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
