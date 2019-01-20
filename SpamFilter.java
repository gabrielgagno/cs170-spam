import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * Main class of the program. contains the main method.
 * @version 1.0
 * @author Gabriel John P. Gagno
 * 
 *
 */

public class SpamFilter{
	public static void main(String[] args){
		ArrayList<Word> hambag = new ArrayList<Word>();
		ArrayList<Word> spambag = new ArrayList<Word>();
		String ham = "ham.txt";
		String spam = "spam.txt";
		SpamFrame window = new SpamFrame("GMail (Gab Mail) Spam Filter");
		
		//SpamUtils.doList(ham, "hambag.txt");
		//SpamUtils.doList(spam, "spambag.txt");
		
	}
}
