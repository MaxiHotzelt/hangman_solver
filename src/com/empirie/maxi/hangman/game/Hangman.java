package com.empirie.maxi.hangman.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.empirie.maxi.hangman.solver.Solver;

public class Hangman {
	private static boolean gameOver = false;
	private static boolean win = false;
	private int leben = 6;
	public static int versuche = 0;
	
	private List<Character> blacklist = new ArrayList<Character>(); 
	private List<Character> gesuchtesWort = new ArrayList<Character>(); 
	private List<Character> gesuchtesWortClone = new ArrayList<Character>(); 
	private List<Character> momentanesWort = new ArrayList<Character>(); 
	private Solver solver = new Solver();

	
	public static void main(String[] args) {
		boolean playAgain = true;
		String isPlayAgain = "y";
		
		Hangman hangman = new Hangman();
		hangman.initGame();
		
		while(playAgain) {
			while(!gameOver) {
				hangman.playGame();
			}
			if(win) {
				System.out.println("Sie haben gewonnen!");
				System.out.println("Versuche	:	" + hangman.getVersuche());
				isPlayAgain = "n";
			} else {
				System.out.println("Sie haben leider verloren.");
			}
			
			
			System.out.print("Nochmal spielen? (y/n) ");
			if(isPlayAgain.equals("y")) {
				System.out.println("\n\n_____________________________________________\n");
				hangman.resetGame();
			} else {
				playAgain = false;
			}
			
		}
	}
	
	public void playGame() {
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		char[] test = {'a', 'b'};
		
		char myChar = solver.getChar(test);
		while(isBuchstabeAufBlacklist(myChar)) {
			myChar = getBuchstabe();
		}
		
		if(isBuchstabeVorhanden(myChar)) {
			setzeBuchstabenEin(myChar);	
			
			if(isWortGefunden()) {
				win = true;
				gameOver = true;
				ausgabe();
			} else {
				addCharZuBlacklist(myChar);
				ausgabe();
			}
			
		} else {
			lebenAbziehen();
			if(isTod()){
				win = false;
				gameOver = true;
				ausgabe();
			} else {
				addCharZuBlacklist(myChar);
				ausgabe();
			}
		}
		
		
	}
	
	
	private void resetGame() {
		String neuesWort = gesuchtesWort.toString();
		neuesWort = neuesWort.replaceAll("[,\\s\\[\\]]", "");
		solver.addWortZuListe(neuesWort);
		solver.resetBuchstabenCount();
		solver.zaehleBuchstaben();
		
		gameOver = false;
		leben = 6;
		blacklist.clear();
		
		versuche++;
	}
	
	private int getVersuche() {
		return versuche;
	}
	
	private void initGame() {
		gesuchtesWort.add('t');
		gesuchtesWort.add('e');
		gesuchtesWort.add('s');
		gesuchtesWort.add('t');
		momentanesWort.add('_');
		momentanesWort.add('_');
		momentanesWort.add('_');
		momentanesWort.add('_');
		
		gesuchtesWortClone = new ArrayList<Character>(gesuchtesWort);
		
		
	}
	
	private char getBuchstabe() {
		System.out.print("Buchstaben eingeben: ");
		String myStringChar = readText();
		
		return myStringChar.charAt(0);
	}
	private boolean isBuchstabeAufBlacklist(char myChar) {
		if(blacklist.contains(myChar)) {
			return true;
		} else {
			return false;
		}
	}
	private boolean isBuchstabeVorhanden(char myChar) {
		if(gesuchtesWortClone.contains(myChar)) {
			return true;
		} else {
			return false;
		}
	}
	private void setzeBuchstabenEin(char myChar) {
		while(isBuchstabeVorhanden(myChar)) {
			int indexOfBuchstabe = gesuchtesWortClone.indexOf(myChar);
			gesuchtesWortClone.remove(indexOfBuchstabe);
			gesuchtesWortClone.add(indexOfBuchstabe, '_');
			
			momentanesWort.remove(indexOfBuchstabe);
			momentanesWort.add(indexOfBuchstabe, myChar);
		}
		
	}
	
	private boolean isWortGefunden() {
		if(gesuchtesWort.equals(momentanesWort)) {
			return true;
		} else {
			return false;
		}
	}
	private void addCharZuBlacklist(char myChar) {
		blacklist.add(myChar);
	}
	
	private void lebenAbziehen() {
		leben = leben - 1;
	}
	private boolean isTod() {
		if(leben == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	private void ausgabe() {
		System.out.println("Blacklist	:	" + blacklist);
		System.out.println("Wort		:	" + momentanesWort);
		System.out.println("Leben		:	" + leben + "\n\n");
	}
	
	
	
	
	
	
	
	
	
	private static String readText() {
	    try {
	        return new BufferedReader(new InputStreamReader(System.in)).readLine();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return "";
	    }
	}
	
}
