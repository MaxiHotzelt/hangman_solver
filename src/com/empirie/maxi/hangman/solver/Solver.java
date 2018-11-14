package com.empirie.maxi.hangman.solver;

import java.util.ArrayList;
import java.util.List;

import com.empirie.maxi.hangman.utils.FileUtils;

public class Solver {
	private List<String> initialWortListe = new ArrayList<String>();
	private List<String> activeWortListe = new ArrayList<String>();
	private List<String> uebergangsWortListe = new ArrayList<String>();
	private List<Character> blacklist = new ArrayList<Character>(); 
	private int[] buchstabenAnzahl = new int[30];
	private char[] momentanesWort;
	private boolean nachLaengeAussortiert = false;
	
	public Solver() {
		loadWordsFromFile();
	}
	
	private void loadWordsFromFile() {
		FileUtils fu = new FileUtils();
		initialWortListe.addAll(fu.getWortListe());
	}
	
	public char getChar(char[] wort) {
		momentanesWort = wort;
		bereiteWortlisteAuf();
		
		resetBuchstabenCount();
        zaehleBuchstaben();
        
		return findeBestenBuchstaben();
	}
	
	private char findeBestenBuchstaben() {
        schlieﬂeBereitsGewaehlteBuchstabeAus();
        
		int buchstabenIndex = findeBuchstabenIndexMitGroestemVorkommen();
		
		buchstabeAufBlackList(buchstabenIndex);
		
		return getBuchstabeDurchIndex(buchstabenIndex);
	}
	
	private void bereiteWortlisteAuf() {
		if(!nachLaengeAussortiert) {
			schlieﬂeWoerterAnhandLaengeAus();
		}
		//muss gelˆscht werden, weil durch evtl. neuen gefunden Buchstaben die activeWortListe anders aussieht
		activeWortListe.clear();
		
		schlieﬂeWoerterAnhandGefunderBuchstabenAus();
	}
	
	private void schlieﬂeBereitsGewaehlteBuchstabeAus() {
		for (Character buchstabe : blacklist) {
			buchstabenAnzahl[getPositionBuchstabe(buchstabe)] = -1;
		}
	}
	
	private void buchstabeAufBlackList(int indexBuchstabe) {
		blacklist.add(getBuchstabeDurchIndex(indexBuchstabe));
	}
	
	private void schlieﬂeWoerterAnhandLaengeAus() {
		int wortLaenge = momentanesWort.length;
		for (String wort : initialWortListe) {
			if(wort.length() == wortLaenge) {
				uebergangsWortListe.add(wort);
			}
		}
		nachLaengeAussortiert = true;
	}
	
	private void schlieﬂeWoerterAnhandGefunderBuchstabenAus() {
		for (String wort : uebergangsWortListe) {
			char [] wortToChar = wort.toCharArray();
			boolean doAddWort = true;
			for(int i = 0; i < wort.length(); i++) {
				if(momentanesWort[i] != '_') {
					if(momentanesWort[i] != wortToChar[i]) {
						doAddWort = false;
					}	
				}
			}
			if(doAddWort) {
				activeWortListe.add(wort);
			}
		}
		
	}
	
	private int findeBuchstabenIndexMitGroestemVorkommen() {
		int indexHoechsterBuchstabe = -1;
		int anzahlVorkommnisBuchstabe = -1;
		
		for (int i = 0; i < buchstabenAnzahl.length; i++) {
			if(buchstabenAnzahl[i] > anzahlVorkommnisBuchstabe) {
				anzahlVorkommnisBuchstabe = buchstabenAnzahl[i];
				indexHoechsterBuchstabe = i;
			}
		}
		
		return indexHoechsterBuchstabe;
	}
	
	public void resetSolver() {
		resetBuchstabenCount();
		nachLaengeAussortiert = false;
		blacklist.clear();
	}
	
	
	private void resetBuchstabenCount() {
		for(int i = 0; i < buchstabenAnzahl.length; i++) {
			buchstabenAnzahl[i] = 0;
		}
	}
	
	
	public void zaehleBuchstaben() {
		for (String wort : activeWortListe) {
			wort = wort.toLowerCase();
			setAnzahlBuchstaben(wort);
		}
	}
	
	
	private void setAnzahlBuchstaben(String wort) {
		for(char c : wort.toCharArray()) {
			//erhˆht buchstabenIndex
			int currentBuchstabenIdx = getPositionBuchstabe(c);
			if(buchstabenAnzahl[currentBuchstabenIdx] != -1) {
				buchstabenAnzahl[currentBuchstabenIdx] = buchstabenAnzahl[currentBuchstabenIdx] + 1;
			}
		}
	}
	
	
	private char getBuchstabeDurchIndex(int indexBuchstabe) {
		switch (indexBuchstabe) {
		case 0:
			return 'a';
		case 1:
			return 'b';
		case 2:
			return 'c';
		case 3:
			return 'd';
		case 4:
			return 'e';
		case 5:
			return 'f';
		case 6:
			return 'g';
		case 7:
			return 'h';
		case 8:
			return 'i';
		case 9:
			return 'j';
		case 10:
			return 'k';
		case 11:
			return 'l';
		case 12:
			return 'm';
		case 13:
			return 'n';
		case 14:
			return 'o';
		case 15:
			return 'p';
		case 16:
			return 'q';
		case 17:
			return 'r';
		case 18:
			return 's';
		case 19:
			return 't';
		case 20:
			return 'u';
		case 21:
			return 'v';
		case 22:
			return 'w';
		case 23:
			return 'x';
		case 24:
			return 'y';
		case 25:
			return 'z';
		case 26:
			return '‰';
		case 27:
			return 'ˆ';
		case 28:
			return '¸';
		case 29:
			return 'ﬂ';
		default:
			return '0';
		}
	}
	
	
	private int getPositionBuchstabe(char c) {
		switch (c) {
		case 'a':
			return 0;
		case 'b':
			return 1;
		case 'c':
			return 2;
		case 'd':
			return 3;
		case 'e':
			return 4;
		case 'f':
			return 5;
		case 'g':
			return 6;
		case 'h':
			return 7;
		case 'i':
			return 8;
		case 'j':
			return 9;
		case 'k':
			return 10;
		case 'l':
			return 11;
		case 'm':
			return 12;
		case 'n':
			return 13;
		case 'o':
			return 14;
		case 'p':
			return 15;
		case 'q':
			return 16;
		case 'r':
			return 17;
		case 's':
			return 18;
		case 't':
			return 19;
		case 'u':
			return 20;
		case 'v':
			return 21;
		case 'w':
			return 22;
		case 'x':
			return 23;
		case 'y':
			return 24;
		case 'z':
			return 25;
		case '‰':
			return 26;
		case 'ˆ':
			return 27;
		case '¸':
			return 28;
		case 'ﬂ':
			return 29;
		default:
			return -1;
		}
	}
	
	
	public void addWortZuListe(String wort) {
		initialWortListe.add(wort);
	}
	
}
