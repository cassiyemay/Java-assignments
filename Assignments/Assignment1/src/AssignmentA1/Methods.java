package AssignmentA1;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.Scanner;
import java.io.PrintWriter;
import javax.swing.*;

public class Methods {
	private ArrayList<Apartment> apartments= new ArrayList<Apartment>();
	private ArrayList<House> houses = new ArrayList<House>();
	private  ArrayList<Village> villages = new ArrayList<Village>();

	
	public void FillInProperties(Apartment[] a,House[] b,Village[] c ){
		
		for(int i=0;i<a.length;i++){
		apartments.add(a[i]);
		apartments.get(i).RentalProperty(0);
		apartments.get(i).RentalProperty(1);
		apartments.get(i).RentalProperty(1);
		}
		for(int i=0;i<b.length;i++){
		houses.add(b[i]);
		houses.get(i).RentalProperty(1);
		houses.get(i).RentalProperty(1);
		houses.get(i).RentalProperty(2);
		}
		for(int i=0;i<c.length;i++){
		villages.add(c[i]);
		villages.get(i).RentalProperty(0);
		villages.get(i).RentalProperty(2);
		villages.get(i).RentalProperty(2);
	}
	}
	
	public void PrintAllProperties(){
		try{
		for(int i=0;i< apartments.size();i++){
			System.out.println(apartments.get(i).toString());
		}
		for(int i=0;i< houses.size();i++){
			System.out.println(houses.get(i).toString());
		}
		for(int i=0;i< villages.size();i++){
			System.out.println(villages.get(i).toString());
		}
		}catch(Exception ex){
			System.out.println("exception" +ex.getMessage() + "caught");
		}
		
	}

	}
	

