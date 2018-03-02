package AssignmentA1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.Iterator;


public class MainProperty2 {
	
	public static List<Apartment> apartments = new ArrayList<Apartment>();
	public static List<House> houses = new ArrayList<House>();
	public static List<Village> villas = new ArrayList<Village>();
	
	//define three method
	public static void main(String[] args) {
		readInputPropertyFile(); 
		//printAllProperties();
		//giveRentalProperty();
	}
	
	public static void readInputPropertyFile (){
		Scanner s = null;
		try {
			s = new Scanner((Readable) new BufferedReader(new FileReader("src/AssignmentA2/InputProperty.txt")));
			while(s.hasNext()) {
				String c = s.next();
				//System.out.println(c);
				System.out.println("");
				int count = 0;
				int i = 0;
				Object[] attrs = new Object[7];
				switch (c) {
					
					case "1":
						//Apartment(String registerNumber, String ownerName, String postalAddress, int rentalCostPerDay,
						//int totalRentalDays, int storeyNumber, int bedNumber)
						i=1;
						count=1;
						while (count <= 6) {
							attrs[count] = s.next();
							//System.out.println(attrs[count]);
							count++;
							
						}
						apartments.add(new Apartment(i, (String)attrs[5], (String)attrs[6], Integer.parseInt((String)attrs[3]), 
								Integer.parseInt((String)attrs[2]), Integer.parseInt((String)attrs[1]), Integer.parseInt((String)attrs[4])));
						System.out.println(apartments);
						break;
					
					case "2":
						i=2;
						count=1;
						while (count <= 6) {
							attrs[count] = s.next();
							count++;
						}
						houses.add(new House(i, (String)attrs[5], (String)attrs[6], Integer.parseInt((String)attrs[3]), 
								Integer.parseInt((String)attrs[2]), Integer.parseInt((String)attrs[1]), Integer.parseInt((String)attrs[4])));
						System.out.println(houses);
						break;
					
					case "3":
						count = 0;
						i=3;
						while (count <= 6) {
							attrs[count] = s.next();
							//System.out.print(attrs[count]);
							count++;
						}
						villas.add(new Village(i, (String)attrs[5], (String)attrs[6], Integer.parseInt((String)attrs[2]), 
								Integer.parseInt((String)attrs[1]), Integer.parseInt((String)attrs[0]),
								Integer.parseInt((String)attrs[3]),Integer.parseInt((String)attrs[4])));
						System.out.println(villas);
						break;
				}
			
			}
		}
		catch (IOException ex){
			System.out.println(ex.getMessage());
		}
		finally {
				if(s!=null) {
					s.close();
				}
			}
	}
	}

