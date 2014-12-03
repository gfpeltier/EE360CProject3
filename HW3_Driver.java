/**
 * @author Grant Peltier (gfp237)
 * Main driver class for EE360C Programming Assignment 3, Fall 2014
 */

package Assignment3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class HW3_Driver {

	
	public static HashMap<Integer, String> dictionary;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		dictionary = new HashMap<Integer, String>();
		
		if (args.length != 1) 
		{
			System.err.println ("Error: Incorrect number of command line arguments");
			System.exit(-1);
		}
		File input = new File(args[0]);
		
		try{
		BufferedReader inRead = new BufferedReader(new FileReader(input));
		String s = inRead.readLine();
		int dictNum = Integer.parseInt(s);
		for(int k = 0; k < dictNum; k++){
			s = inRead.readLine();
			dictionary.put(k, s);
		}
		s = inRead.readLine();
		ArrayList<String> output = findSpaces(s, 0, "", 0);
		System.out.println(output.size());
		Iterator<String> i = output.iterator();
		while(i.hasNext()){
			System.out.println(i.next());
		}
		
		}catch (FileNotFoundException e) {
            // File not found
            e.printStackTrace();

		}catch (IOException e) {
            // Not able to read line
            e.printStackTrace();
      }
	}
	
	public static ArrayList<String> findSpaces(String in, int index, String known, int st){
		ArrayList<String> out = new ArrayList<String>();
		if(index > in.length()){return out;}
		String poss = known;
		String tmp = "";
		int wordStart = st;
		while(index <= in.length()){ 
			if(index < in.length()){
				tmp = in.substring(wordStart, index);
			}else{
				tmp = in.substring(wordStart);
			}
			if(dictionary.containsValue(tmp)){
				out.addAll(findSpaces(in, index+1, poss, wordStart));
				poss += tmp + " ";
				wordStart = index;
			}
			index++;
		}
		if(!dictionary.containsValue(tmp)){
			return out;
		}
		if(!poss.isEmpty()){
			poss = poss.substring(0, poss.length()-1);
			out.add(poss);}
		return out;
	}

}
