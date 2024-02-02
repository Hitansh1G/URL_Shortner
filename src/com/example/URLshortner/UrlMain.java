package com.example.URLshortner;

import com.example.URLshortner.entity.url;
import com.example.URLshortner.services.urlService;

public class UrlMain {
    public static void main(String[] args) {
        url URL = new urlService();

        String url1 = URL.registerNewUrl("http://abcwertyjkknbvcdfghjhgfcxsdfgyu8765432345678uygvbhgbnvhabv/.com");
        System.out.println(url1);
    }
}
