import java.util.*;
/**
 * Represents a word and a term to be used in the bag of words
 * @author Gabriel John P. Gagno
 * @version 1.0
 *
 */
public class Word{
	private String term;
	private int count;
	
	public Word(String term, int count){
		this.term = term;
		this.count = count;
	}
	
	public String getTerm(){
		return this.term;
	}
	
	public int getCount(){
		return this.count;
	}
	
	public void increment(){
		this.count++;
	}
}
