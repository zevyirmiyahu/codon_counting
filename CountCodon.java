package codon_counting;

import java.util.HashMap;

import edu.duke.FileResource;

/*
 * This is a program designed for an assignment for a Duke Coursera course. It utilizes 
 * the duke learn to program java library and counts the total number of unique codons 
 * for frame1 = 0, frame2 = 1 and frame3 = 2. The program also prints the count of the 
 * most frequent codon.
 * 
 * @author	Zev Yirmiyahu
 * 
 */

public class CountCodon {
	
	private HashMap<String, Integer> map = new HashMap<String, Integer>();

	
	public CountCodon() {
		String dna = readFile();
		int frame1 = 0;
		int frame2 = 1;
		int frame3 = 2;
		
		buildCodonMap(dna, frame1);
		buildCodonMap(dna, frame2);
		buildCodonMap(dna, frame3);
	}
	
	
	public void buildCodonMap(String dna, int start) {
		
		map.clear(); //Must clear since being called 3 times
				
		for(int i = start; i + 3 < dna.length(); i += 3) { //Each codon is 3 units long
			String codon = dna.substring(i, i + 3).toUpperCase();

			if(map.containsKey(codon)) {
				map.put(codon, map.get(codon) + 1);
			}
			else {
				map.put(codon, 1);
			}
		}
		printCodonCounts(start, 5);
	}
	
	
	public String getMostCommonCodon() {
		
		String mostCommon = "";
		int temp = 0; 
		
		for(String s : map.keySet()) {
			if(map.get(s) > temp) {
				temp = map.get(s);
				mostCommon = s;
			}
		}
		return mostCommon;
	}
	
	
	public void printCodonCounts(int start, int end) {
		
		System.out.println("Reading frame " + start + " results in " + map.size() + " unique codons.");
		
		String mostCommonCodon = getMostCommonCodon();
		System.out.println("The most freuquent codon is + " 
				+ mostCommonCodon + " with count " + map.get(mostCommonCodon));
		
		for(String s : map.keySet()) {
			System.out.println(s + "\t" + map.get(s));

			/*
			if(map.get(s) > start && map.get(s) < end) {
				System.out.println(s + "\t" + map.get(s));
			}  */
		}
		System.out.println(); //spacing)
	}
	
	public String readFile() {
		
		String dna = "";
		FileResource fr = new FileResource();
		
		for(String s : fr.lines()) {
			dna = dna + s;
		}
		return dna;
	}
	
	public static void main(String args[]) {
		
		new CountCodon();
		
		
	}
}
