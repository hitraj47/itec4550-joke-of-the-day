package com.bewareofraj.itec4550jokeoftheday.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.AsyncTask;

public class RetrieveJokeTask extends AsyncTask<String, Void, String> {

    protected String doInBackground(String... urls) {
    	String joke = "";
    	try {
        	URL url = new URL(urls[0]);
    		InputStream is = url.openStream();
    		BufferedReader br = new BufferedReader(new InputStreamReader(is));

    		String html = "";
    		String line;
    		while ((line = br.readLine()) != null)
    			html = html + line;

    		br.close();
    		is.close();

    		final Pattern pattern = Pattern.compile("<body>(.+?)</body>");
    		final Matcher matcher = pattern.matcher(html);
    		matcher.find();
    		joke = matcher.group(1);

    	} catch (IOException e) {
    		e.printStackTrace();
    	}
		return joke;
    }

}