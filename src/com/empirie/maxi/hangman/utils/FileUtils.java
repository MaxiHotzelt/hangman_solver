package com.empirie.maxi.hangman.utils;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.ws.util.StringUtils;
public class FileUtils {


	private List<String> wortListe = new ArrayList<String>();
	  
	
	public FileUtils() {
		createWortListe();
	}
	
	public String trimWort(String wort) {
		return wort.replaceAll("[^a-zA-Z]", "");
	}
	
	public List<String> getWortListe() {
		return wortListe;
	}
	
	private void createWortListe() {
		try {
			File fileDir = new File("E:\\develop\\hangman_solver\\bin\\com\\empirie\\maxi\\hangman\\utils\\wortsammlung.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
		    String wort = "";
		    
		    while (wort != null) {
    	    	if(wort != null && !wort.equals("") && !wort.equals("åland")) {
    	    		wort = trimWort(wort);
    	    		wortListe.add(wort);
    	    	}
    	     	wort = br.readLine();
    	    }
    	   
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
	

