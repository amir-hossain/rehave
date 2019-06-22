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
    public static String aboutText="<p>      <strong>মুক্তির</strong>      <strong> </strong>      <strong>মিছিল</strong>      <strong> </strong>      <strong>কি</strong>      <strong> </strong>      <strong>?</strong>  </p>  <p>      • এটি একটি মাদকাসক্ত চিকিৎসা পরবর্তী সেবা গ্রহন সংক্রান্ত মোবাইল আপ্লিকেশন      যা মাদকাসক্ত চিকিৎসা নেবার পর একজন মাদকমুক্ত ব্যাক্তি বা মাদকগ্রহন করছেন      তাদের মাদক মুক্ত রাখতে সাহায্য করবে।  </p>  <p>      • বাংলাদেশ সহ সমগ্র বিশ্বে আলোর মিছিল একমাত্র বাংলাভাষী মাদকমুক্ত রিকভারিং      সাহায্যকারি মোবাইল অ্যাপ ।  </p>  <p>      • একজন বাংলাদেশের চিকিৎসা মনোবিজ্ঞানী ও মাদকাসক্ত পেশাজীবী অ্যাপটিকে তৈরি      করেছেন তাই আলোর মিছিলে বিশেষজ্ঞ প্রভাব দ্বারা প্রভাবিত ।  </p>  <p>      • এই আপ্লিকেসন টির চারটি ভাগ আছে যার উপর ভিত্তি করে ১। মাদক গ্রহন ২।      রিকভারিং ৩। আলোর মিছিল ফোরাম ৪। আলোর মিছিল আর্কাইভ  </p>  <p>      • নির্দিষ্ট পুনর্বাসন কেন্দ্র থেকে অ্যাপটি নেওয়া যাবে ।  </p>  <p>      • ব্যাক্তির সকল তথ্য পরিপূর্ণ ভাবে গোপন রেখে আপটি পরিচালিত হবে  </p>  <p>      <strong>মুক্তির</strong>      <strong> </strong>      <strong>মিছিলের</strong>      <strong> </strong>      <strong>উদ্দেশ্য</strong>      <strong> </strong>      <strong></strong>  </p>  <p>      বাংলাদেশে প্রায় ১ কোটি মাদকাসক্তের জন্য মাত্র ৩৩০ টি পুনর্বাসন কেন্দ্র আছে      যেখানে পুরাতুন রোগীর হার ৫০% বেশি ও প্রথম মাদকাসক্ত চিকিৎসা নেবার পরও ৬০      -৭০ % রোগী পুনরায় মাদকাসক্ত রোগে জড়িয়ে পড়ে তাই এই হার কমানোর জন্য মুক্তির      মিছিলের এই ছোট প্রয়াস  </p>  <p>      <strong>সাধারন</strong>      <strong> </strong>      <strong>কয়েকটি</strong>      <strong> </strong>      <strong>শর্তাবলি</strong>  </p>  <p>      • মুক্তির মিছিলের বর্তমান ভার্সনটি একটি নির্দিষ্ট গোষ্ঠী যারা মাদকাসক্তর      পুনর্বাসন ভিত্তিক চিকিৎসা গ্রহন করেছেন তাদের জন্য প্রযোজ্য তাই সবাই এই আপের      আওতাভুক্ত হবে না  </p>  <p>      • যারা লেখাপড়া জানেন তাদের জন্য এই অ্যাপটি ব্যাবহার উপযোগী  </p>  <p>      • আপ্লিকেশনটি বর্তমানে পুনর্বাসন কেন্দ্র ও আলোর মিছিল কর্তৃপক্ষ দ্বারা      নিয়ন্ত্রিত  </p>  <p>      • আপ্লিকেশনটির সদস্যদের সকল তথ্য প্রায় পরিপূর্ণ ভাবে গোপনীয় । তাই      আপ্লিকেশনে কোন নাম, ঠিকানা,পরিচয়,ছবি,ভিডিও দেওয়ার কোন সুযোগ দেওয়া হয় নাই      তবে শুধু মাত্র নির্দিষ্ট কিছু আইডি দেওয়া হয়েছে । যা আলোর মিছিলের কর্তৃপক্ষ      দ্বারা নিয়ন্ত্রিত থাকবে  </p>  <p>      • কেউ যদি এই আপ্লিকেশনে ব্যাবহার না করতে চায় তাহলে সে নিজ থেকে আপটি মুছে      ফেলতে পারবে সাথে তার সকল তথ্য মুছে যাবে  </p>  <p>      • কোন ভাবে নাম, ছবি, ভিডিও ও পরিচয় পাওয়া যায় এমন কোন তথ্য ফোরামে দেওয়া যাবে      না তবে নির্দিষ্ট বিশেষজ্ঞ সাথে কথা বলতে গেলে তার ই মেইল বা মেসেঞ্জার      ব্যাবহার করতে পারবেন  </p>  <p>      • শুধু মাত্র লিখা ও মন্তব্য প্রদান ছাড়া একজন সদস্য আপ্লিকেশনে কোন কিছু      শেয়ার করতে পারবেনা  </p>  <p>      • সাহায্য ও কথোপকথনের জন্য একটি ছোট ফোরাম রাখা হয়েছে যেখানে একজন রিকভারি      তার কথা, মাদকের টান বা কোন তথ্য সবাইকে বা নির্দিষ্ট বিশেষজ্ঞকে জানাতে পারবে  </p>  <p>      • নির্দিষ্ট পুনর্বাসনের ব্যাক্তি অন্য পুনর্বাসনের ব্যাক্তি বা অন্যান্যদের      সাথে এই অ্যাপ দিয়ে যোগাযোগ করতে পারবেনা  </p>  <p>      • গোপনীয়তা বা আইন বিরুদ্ধ কিছু হলে আইডি বন্ধ করে দেওয়া সহ আইনগত ব্যাবস্থা      নেওয়া হবে  </p>  <p>      • আলোর মিছিলের একটি আর্কাইভ আছে যেখান থেকে আপ্লিকেশনের সদস্য বিভিন্ন ভিডিও,      রিকভারিং অনুপ্রেরনা মুলক অডিও, মাদকাসক্ত বিষয়ে বই, বিশেষজ্ঞ পরামর্শ,      অনুপ্রেরনা মুলক ছবি, স্লোগান, মাদকাসক্ত বিষয়ক পণ্য, সাপ্তাহিক মাদকের উপর      খবর, পুনরবাসনের ঠিকানা সহ অন্যান্য বিষয় সংযুক্ত থাকবে। ";

    public static String statusText="<div>\n" +
            "    আজকের দিনে আমার অবস্থা\n" +
            "</div>\n" +
            "<div>\n" +
            "    প্রশ্ন ১। আপনি কি পুনর্বাসন চিকিৎসা নেবার পর মাদক গ্রহন করছেন ?\n" +
            "</div>\n" +
            "<div>\n" +
            "    প্রশ্ন ২। আপনি কি পুনর্বাসন চিকিৎসা নেবার পর মাদকগ্রহন বন্ধ করেছেন ?\n" +
            "</div>\n" +
            "<div>\n" +
            "    প্রশ্ন ৩। মতামত জানাতে অনিচ্ছুক\n" +
            "</div>\n" +
            "<div>\n" +
            "    ফলাফল - তাহলে আপনার করনীয়\n" +
            "</div>\n" +
            "<div>\n" +
            "    প্রশ্নমালা ১ এর জন্য পরিবর্তনের কৌশল A to E ধাপ সহ ফোরাম ও আর্কাইভ ব্যাবহার\n" +
            "    করা জরুরী\n" +
            "</div>\n" +
            "<div>\n" +
            "    প্রশ্নমালা ২ এর জন্য শুধু D ও E ধাপ সহ ফোরাম ও আর্কাইভ ব্যাবহার করা জরুরী\n" +
            "</div>\n" +
            "<div>\n" +
            "    প্রশ্নমালা ৩ এর জন্য যে কোন পরিবর্তনের কৌশল ধাপ সহ ফোরাম ও আর্কাইভ\n" +
            "    স্বেচ্ছায় ব্যাবহার করতে পারেন\n" +
            "</div>\n" +
            "<div>\n" +
            "    <br/>\n" +
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
