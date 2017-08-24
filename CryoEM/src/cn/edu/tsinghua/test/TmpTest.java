package cn.edu.tsinghua.test;

import java.util.HashMap;
import java.util.Map;

public class TmpTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String str = "A=4 B=3 C=6";
		Map<String, String> map = convert(str);
		System.out.println(map);
		System.out.println(map.get("A"));

	}
	
	public static Map<String, String> convert(String str) {
	    String[] tokens = str.split(" |=");
	    Map<String, String> map = new HashMap<String, String>();
	    for (int i=0; i<tokens.length-1; ) 
	    	map.put(tokens[i++], tokens[i++]);
	    return map;
	}

}
