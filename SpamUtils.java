import java.util.*;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Utility class for the application. Contains certain methods not fit with any of the
 * existing classes.
 * @author Gabriel John P. Gagno
 * @version 1.0
 *
 */
public final class SpamUtils{
	private static Scanner input;
	private static PrintWriter out;
	private static ArrayList<Word> hambag;
	private static ArrayList<Word> spambag;
	private static ArrayList<Word> dictionary;
	private static String hamFile;
	private static String spamFile;
	private static float total;
	private static float probSpam;
	private static float probHam;
	private static File hamDir;
	private static File spamDir;
	private static float hamSampleSize;
	private static float spamSampleSize;
	private static float hamFrequency;
	private static float spamFrequency;
	private SpamUtils(){
		System.out.println("ERROR");
	}
	
	public static String getHamFile(){
		return hamFile;
	}
	
	public static String getSpamFile(){
		return spamFile;
	}
	
	public static void setFileName(String hamSpam, String hs, File x){
		if(hs.equals("ham")){
			hamFile = hamSpam;
			hamDir = x;
		}
		else{
			spamFile = hamSpam;
			spamDir = x;
		}
	}
	
	public static ArrayList<Word> getBag(String hamSpam){
		if(hamSpam.equals(hamFile)){
			return hambag;
		}
		else{
			return spambag;
		}
	}
	
	public static void doList(String hamSpam){
		if(hamSpam.equals(hamFile)){
			System.out.println("HERE");
			hambag = makeBag(hamSpam);
		}
		else{
			System.out.println(spamFile);
			spambag = makeBag(hamSpam);
		}
	}
	
	public static ArrayList<Word> makeBag(String hamSpam){
		ArrayList<Word> bag = new ArrayList<Word>();
		ArrayList<String> get = new ArrayList<String>();
		//String[] get = new String[10];
		get = SpamUtils.returnList(hamSpam);
		StringTokenizer tok;
		for(String x : get){
			System.out.println(x);
			try{
			tok = new StringTokenizer(x, "!(),[]. \n");
			}
			catch(NullPointerException zz){
				break;
			}
			while(tok.hasMoreTokens()){
				String tk = tok.nextToken();
				boolean isIt = isPresentInList(tk, bag);
				if(isIt){
					int s = search(tk, bag);
					bag.get(s).increment();
				}
				else{
					bag.add(new Word(tk, 1));
				}
				if(hamSpam.equals(hamFile)){
					hamFrequency++;
				}
				else{
					spamFrequency++;
				}
			}
		}
		for(Word x : bag){
			System.out.println(x.getTerm() + " - " + x.getCount());
		}
		return bag;
	}
	
	public static ArrayList<String> returnList(String fileName){
		//String[] get = new String[]{};
		ArrayList<String> get = new ArrayList<String>();
		int i = 0;
		try{
			//input = new Scanner(new File(fileName));
			if(fileName.equals(hamFile)){
				System.out.println("DITO");
				input = new Scanner(hamDir);
			}
			else{
				System.out.println("DOON");
				input = new Scanner(spamDir);
			}
		}
		catch(FileNotFoundException e){
		}
		while(input.hasNextLine()){
			System.out.println(i);
			//get[i++] = input.nextLine();
			get.add(input.nextLine());
			total++;
		}
		if(fileName.equals(hamFile)){
			//hamSampleSize = i;
			hamSampleSize++;
		}
		else{
			//spamSampleSize = i;
			spamSampleSize++;
		}
		return get;
	}
	
	public static boolean isPresentInList(String searchTerm, ArrayList<Word> wordList){
		if(wordList.isEmpty()){
			return false;
		}
		int listSize = wordList.size();
		for(int i=0;i<listSize;i++){
			if(searchTerm.equals(wordList.get(i).getTerm())){
				return true;
			}
		}
		return false;
	}
	
	public static int search(String searchTerm, ArrayList<Word> wordList){
		int listSize = wordList.size();
		for(int i=0;i<listSize;i++){
			if(searchTerm.equals(wordList.get(i).getTerm())){
				return i;
			}
		}
		return -1;
	}
	public static float detect(String statement){
		System.out.println(statement);
		System.out.println(total);
		System.out.println("SPAM: "+ spambag.size() /*spamSampleSize*/);
		System.out.println("HAM: "+ hambag.size() /*hamSampleSize*/);
		probSpam = spamSampleSize/total;
		probHam = hamSampleSize/total;
		System.out.println("PROBSPAM" + probSpam);
		System.out.println("PROBSPAM" + probHam);
		float subjectSpam = 1;
		float subjectHam = 1;
		StringTokenizer tok = new StringTokenizer(statement, "!(),[]. \n");
		while(tok.hasMoreTokens()){
			String tk = tok.nextToken();
			int iSpam = search(tk, spambag);
			int iHam = search(tk, hambag);
			if(iSpam==-1){
				System.out.println("NOT IN SPAM");
				subjectSpam = 0;
			}
			if(iHam==-1){
				System.out.println("NOT IN HAM");
				subjectHam = 0;
			}
			if(iSpam!=-1){
				System.out.println(subjectSpam);
				subjectSpam *= spambag.get(iSpam).getCount()/spamFrequency;
			}
			if(iHam!=-1){
				System.out.println(subjectHam);
				subjectHam *= hambag.get(iHam).getCount()/hamFrequency;
			}
		}
		
		float totalProb = (subjectSpam*probSpam)/((subjectSpam*probSpam)+(subjectHam*probHam));
		System.out.println(totalProb);
		return totalProb;
	}
	
	public static int countDictionary(){
		//ArrayList<Word> dictionary = new ArrayList<Word>();
		int count = 0;
		int spamLength = spambag.size();
		int hamLength = hambag.size();
		for(int i=0;i<spamLength;i++){
			if(search(spambag.get(i).getTerm(), hambag)!=-1){
				count++;
			}
		}
		for(int i=0;i<hamLength;i++){
			if(search(hambag.get(i).getTerm(), spambag)!=-1){
				count++;
			}
		}
		return count;
	}
}
