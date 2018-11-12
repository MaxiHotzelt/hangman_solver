package com.empirie.maxi.hangman.solver;

import java.util.ArrayList;
import java.util.List;

public class Solver {
	private List<String> wortListe = new ArrayList<String>();
	private int[] buchstabenAnzahl = new int[26];
	
	public Solver() {
		loadWords();
        
        zaehleBuchstaben();
	}

	private void loadWords() {
		wortListe.add("Stromaggregat");
        wortListe.add("Computergehaeuse");
        wortListe.add("Feuerwerk");
        wortListe.add("Feuerwehr");
        wortListe.add("Puzzleteil");
        wortListe.add("Pizzateig");
        wortListe.add("Gleichberechtigungsbeauftragter");
        wortListe.add("Haengewandschrankhalterung");
        wortListe.add("lokomotive");
        wortListe.add("photovoltaikanlage");
        wortListe.add("Autowaschanlage");
        wortListe.add("Element");
        wortListe.add("Wagenheber");
        wortListe.add("Haarwurzel");
        wortListe.add("develop");
        wortListe.add("Anwendungsentwickler");
        wortListe.add("Fachmann");
        wortListe.add("Feuerwehrmann");
        wortListe.add("Jahr");
        wortListe.add("Uhr");
        wortListe.add("Prozent");
        wortListe.add("Million");
        wortListe.add("Mensch");
        wortListe.add("gehen");
        wortListe.add("verschieden");
        wortListe.add("Leben");
        wortListe.add("allerdings");
        wortListe.add("verstehen");
        wortListe.add("Mutter");
        wortListe.add("ueberhaupt");
        wortListe.add("besonders");
        wortListe.add("politisch");
        wortListe.add("Gesellschaft");
        wortListe.add("moeglichkeit");
        wortListe.add("Unternehmen");
        wortListe.add("buch");
        wortListe.add("haben");
        wortListe.add("ich");
        wortListe.add("werden");
        wortListe.add("sie");
        wortListe.add("dies");
	}
	
	private void loadWordsFromFile() {
		
	}
	
	public char getChar(char[] wort) {
		int indexHoechsterBuchstabe = -1;
		int anzahlVorkommnisBuchstabe = 0;
		
		for (int i = 0; i < buchstabenAnzahl.length; i++) {
			if(buchstabenAnzahl[i] > anzahlVorkommnisBuchstabe) {
				anzahlVorkommnisBuchstabe = buchstabenAnzahl[i];
				indexHoechsterBuchstabe = i;
			}
		}
		
		ausschlieﬂenBenutzerBuchstaben(indexHoechsterBuchstabe);
		
		return getBuchstabeFromArray(indexHoechsterBuchstabe);
	}
	
	private void ausschlieﬂenBenutzerBuchstaben(int indexOfBuchstabe) {
		buchstabenAnzahl[indexOfBuchstabe] = -1;
	}
	
	public void resetBuchstabenCount() {
		for(int i = 0; i < buchstabenAnzahl.length; i++) {
			buchstabenAnzahl[i] = 0;
		}
	}
	
	public void zaehleBuchstaben() {
		for (String wort : wortListe) {
			wort = wort.toLowerCase();
			setAnzahlBuchstaben(wort);
		}
	}
	
	private void setAnzahlBuchstaben(String wort) {
		for(char c : wort.toCharArray()) {
			//erhˆht buchstabenIndex
			int currentBuchstabenIdx = getPositionBuchstabe(c);
			buchstabenAnzahl[currentBuchstabenIdx] = buchstabenAnzahl[currentBuchstabenIdx] + 1;
		}
	}
	
	
	private char getBuchstabeFromArray(int indexBuchstabe) {
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
		default:
			return -1;
		}
	}
	
	
	public void addWortZuListe(String wort) {
		wortListe.add(wort);
	}
	
}
