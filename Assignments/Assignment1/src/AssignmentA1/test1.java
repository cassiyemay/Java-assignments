package AssignmentA1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class test1 {
	public static void main(String[] args){
		String[] data = { "abc", "def", "ghi", "jkl" };

		String searchedValue = "ghi";

		int pos = 0;

		boolean found = false;

		while (pos < data.length)

		{

		   if (data[pos].equals(searchedValue))

		   {

		      found = true;

		   }

		   else

		   {      

		      found = false;

		   }

		   pos++;

		}
		
		System.out.println(pos);
		if (found) 

		{ 

		   System.out.println("Found at position: " + pos); }
		
		else 

		{ 

		   System.out.println("Not found"); 

		}

		}
		

}
