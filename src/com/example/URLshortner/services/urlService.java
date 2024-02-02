package com.example.URLshortner.services;

import com.example.URLshortner.entity.url;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class urlService implements url {
    private Map<String, String> longToShort = new HashMap<>();
    private Map<String, String> shortToLong = new HashMap<>();
    private Map<String, Integer> longCount = new HashMap<>();
    private String generateId(String id){
        int length =9;
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for(int i=0;i<length;i++){
            int index = random.nextInt(id.length());
            char randomChar = id.charAt(index);
            str.append(randomChar);
        }
        return str.toString();
    }

    @Override
    public String registerNewUrl(String longUrl) {
        if(longToShort.containsKey(longUrl))return longToShort.get(longUrl);

        String capAlphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowAlphabets = "abcdefghijklmnopqrstuvwxyz";
        String numeric = "0123456789";

        String shortUrl = "http://short.url/";

        String uniqueId = generateId(capAlphabets + lowAlphabets + numeric);


        //checking unique
        while(shortToLong.containsKey(shortUrl+uniqueId)){
            uniqueId = generateId(capAlphabets + lowAlphabets + numeric);
        }
        shortUrl+=uniqueId;


        //for storing
        longToShort.put(longUrl,shortUrl);
        shortToLong.put(shortUrl,longUrl);
        longCount.put(longUrl,0);

        return shortUrl;
    }

    @Override
    public String registerNewUrlWithGivenShortUrl(String longUrl, String shortUrl) {
        if(shortToLong.containsKey(shortUrl))return " this shortUrl is already linked to some other url";

        longToShort.put(longUrl,shortUrl);
        shortToLong.put(shortUrl,longUrl);
        longCount.put(longUrl,0);

        return shortUrl;
    }

    @Override
    public String getUrl(String shortUrl) {
        if(shortToLong.containsKey(shortUrl)){
            String longUrl=shortToLong.get(shortUrl);
            int count=longCount.get(longUrl);

            longCount.put(longUrl,count+1);

            return longUrl;
        }
        return null;
    }

    @Override
    public Integer getHitCount(String longUrl) {
        if(longCount.containsKey(longUrl)){
            return longCount.get(longUrl);
        }
        return 0;
    }

    @Override
    public String delete(String longUrl) {
        if(longToShort.containsKey(longUrl)){
            String shortUrl=longToShort.get(longUrl);
            longToShort.remove(longUrl);
            shortToLong.remove(shortUrl);

            return "successful";
        }
        return "failed as it does not exist";
    }
}
