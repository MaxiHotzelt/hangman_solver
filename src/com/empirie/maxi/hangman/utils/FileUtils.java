package com.empirie.maxi.hangman.utils;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {


	private List<String> wortListe = new ArrayList<String>();
	  
	
	public FileUtils() {
		createWortListe();
	}
	
	public String wortAufbereiten(String wort) {
		/*
		 * TODO große umlaute abfangen
		 */
		wort = wort.replaceAll("ä", "ae");
		wort = wort.replaceAll("ö", "oe");
		wort = wort.replaceAll("ü", "ue");
		wort = wort.replaceAll("ß", "ss");
		return wort.replaceAll("[^a-zA-Z]", "");
	}
	
	public List<String> getWortListe() {
		return wortListe;
	}
	
	private void createWortListe() {
		try {
			File fileDir = new File("E:\\develop\\hangman_solver\\src\\com\\empirie\\maxi\\hangman\\utils\\wortliste.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
		    String wort = "";
		    
		    while (wort != null) {
    	    	if(wort != null && !wort.equals("")) {
    	    		wort = wortAufbereiten(wort);
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
	

