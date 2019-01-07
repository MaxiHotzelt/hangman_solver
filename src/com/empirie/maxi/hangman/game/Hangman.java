package com.empirie.maxi.hangman.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import com.empirie.maxi.hangman.solver.KaisSolver;
import com.empirie.maxi.hangman.solver.Solver;
import com.empirie.maxi.hangman.utils.FileUtils;

public class Hangman {
	private static boolean gameOver = false;
	private static boolean win = false;
	private static int versuche = 1;
	private static int leben = 6;
	
	private List<Character> blacklist = new ArrayList<Character>(); 
	private String gesuchtesWortTrimmed;
	private String gesuchtesWortOrginal;
	private char[] momentanesWort;
	private StringBuilder gesuchtesWortClone = new StringBuilder(); 
	private Solver solver = new Solver();
	private KaisSolver kaisSolver = new KaisSolver();
	private boolean isWortNomen = false;
	
	public static void main(String[] args) {
			long start = System.currentTimeMillis();
			boolean isPlayAgain = true;
			String playAgain = "y";
			
			Hangman hangman = new Hangman();
			hangman.initGame();
			
			while(isPlayAgain) {
				while(!gameOver) {
					hangman.playGame();
				}
				if(win) {
					System.out.println("Sie haben gewonnen!");
					System.out.println("Versuche	:	" + versuche);
					playAgain = "n";
				} else {
					System.out.println("Sie haben leider verloren.");
				}
				
				
				System.out.print("Nochmal spielen? (y/n) ");
				if(playAgain.equals("y")) {
					System.out.println("\n\n_____________________________________________\n");
					hangman.resetGame();
				} else {
					isPlayAgain = false;
				}
				
			}
			System.out.println(System.currentTimeMillis() - start);
		}
	
	private void initGame() {
		gesuchtesWortOrginal = getRandomWort();
		//gesuchtesWortOrginal = "openairkonzert";
		gesuchtesWortTrimmed = gesuchtesWortOrginal.toLowerCase();
		isWortNomen();
		momentanesWort = new char[gesuchtesWortTrimmed.length()];
		for (int i = 0; i < momentanesWort.length; i++) {
			momentanesWort[i] = '_';
		}

		gesuchtesWortClone.replace(0, gesuchtesWortClone.length(), gesuchtesWortTrimmed);
	}
	
	public void playGame() {
			
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}


			//char myChar = kaisSolver.getChar(momentanesWort);
			char myChar = solver.getChar(momentanesWort);
			//char myChar = getChar();
			while(isBuchstabeAufBlacklist(myChar)) {
				myChar = getChar();
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
			
	private String getRandomWort() {
		FileUtils fu = new FileUtils();
		Random rand = new Random();
		int wortIdx = rand.nextInt(fu.getWortListe().size()-1) + 0;
		return fu.getWortListe().get(wortIdx);
	}

	private void resetGame() {
			String neuesWort = gesuchtesWortTrimmed.toString();
			neuesWort = neuesWort.replaceAll("[,\\s\\[\\]]", "");
			solver.addWortZuListe(neuesWort);
			solver.resetSolver();
			solver.zaehleBuchstaben();
			
			gameOver = false;
			leben = 6;
			blacklist.clear();
			momentanesWort = new char[gesuchtesWortTrimmed.length()];
			for (int i = 0; i < momentanesWort.length; i++) {
				momentanesWort[i] = '_';
			}

			gesuchtesWortClone.replace(0, gesuchtesWortClone.length(), gesuchtesWortTrimmed);
			
			versuche++;
		}
	
	private void isWortNomen() {
		if(Character.isUpperCase(gesuchtesWortOrginal.charAt(0))) {
			isWortNomen = true;
		}
	}
	
	private char getChar() {
			System.out.print("Buchstaben eingeben: ");
			String myStringChar = readText();
			
			return myStringChar.charAt(0);
		}
		
	private boolean isBuchstabeAufBlacklist(char myChar) {
			return blacklist.contains(myChar);
		}
		
	private boolean isBuchstabeVorhanden(char myChar) {
			return gesuchtesWortClone.toString().contains(String.valueOf(myChar));
		}
		
	private void setzeBuchstabenEin(char myChar) {
			while(isBuchstabeVorhanden(myChar)) {
				int indexOfBuchstabe = gesuchtesWortClone.toString().indexOf(myChar);
				gesuchtesWortClone.replace(indexOfBuchstabe, indexOfBuchstabe+1, "_");
				if(indexOfBuchstabe == 0 && isWortNomen) {
					momentanesWort[indexOfBuchstabe] = Character.toUpperCase(myChar);
				} else {
					momentanesWort[indexOfBuchstabe] = myChar;
				}
				
			}
			
		}
		
	private boolean isWortGefunden() {
		return gesuchtesWortTrimmed.equals(String.valueOf(momentanesWort).toLowerCase());
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
			System.out.println("Blacklist	:	" + blacklist.toString().replaceAll("[\\[\\]]", ""));
			System.out.print("Wort		:	");
			for(int i = 0; i < momentanesWort.length; i++) {
				System.out.print(momentanesWort[i] + " ");
			}
			
			System.out.println("\nLeben		:	" + leben + "\n\n");
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
