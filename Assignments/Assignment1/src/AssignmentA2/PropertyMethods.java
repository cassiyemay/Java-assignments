package AssignmentA2;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.Scanner;
import java.io.PrintWriter;
import javax.swing.*;

import AssignmentA1.Apartment;
import AssignmentA1.House;
import AssignmentA1.Village;


public class PropertyMethods {
	private ArrayList<Apartment> apartments = new ArrayList<Apartment>();
	private ArrayList<House> houses = new ArrayList<House>();
	private  ArrayList<Village> villages = new ArrayList<Village>();
	
	// method read data from input file
	public void ReadInputPropertyFile(String filename){
		//String myFileName="src/AssignmentA1/InputProperty.txt";
		try{
			FileReader myFile = new FileReader(filename);
			Scanner scanMyFile = new Scanner(myFile);
			while(scanMyFile.hasNext()){
				String words=scanMyFile.nextLine();
				String[] array= words.split(" ");
				int[] result=new int[array.length-2];
				for (int i=0;i<array.length-2;i++){
					result[i]= Integer.parseInt(array[i]);}
				if (result[0] ==1)
					apartments.add(new Apartment(result[0],array[array.length-2],
							array[array.length-1],result[3],result[4],result[1],result[2]));
				else if(result[0] ==2)
					houses.add(new House(result[0],array[array.length-2],
							array[array.length-1],result[3],result[4],result[1],result[2]));
				else
					villages.add(new Village(result[0],array[array.length-2],
							array[array.length-1],result[4],result[5],result[1],result[2],result[3]));
				}
			}catch(Exception ex){
				System.out.println("exception" + ex.getMessage()+"caught");
			}
		}
	
	//print out the result into a output file
	public void PrintAllProperties(){
		String myFileName="OutputProperty.txt";
		
		try{
		PrintWriter myOutFile = new PrintWriter(myFileName);
		for(int i=0;i< apartments.size();i++){
			myOutFile.println(apartments.get(i).toString());
		}
		for(int i=0;i< houses.size();i++){
			myOutFile.println(houses.get(i).toString());
		}
		for(int i=0;i< villages.size();i++){
			myOutFile.println(villages.get(i).toString());
		myOutFile.close();
		}
		}catch(Exception ex){
			System.out.println("exception" +ex.getMessage() + "caught");
		}
		
	}
	//display a frame and insert rental day according to propertyID inputed
	 public void GiveRentalProperty(){
		 //display a frame;
		 JFrame frame = new JFrame();
			frame.setSize(450, 100);
			JTextField xField = new JTextField(2);
		     JTextField yField = new JTextField(2);
		     JPanel myPanel = new JPanel();
		     frame.getContentPane().add(myPanel);
		     myPanel.add(new JLabel("PropertyID:"));
		      myPanel.add(xField);
		      myPanel.add(Box.createHorizontalStrut(5)); // a spacer
		      myPanel.add(new JLabel("Rental Days:"));
		      myPanel.add(yField);
		      JButton button = new JButton("OK");
		      myPanel.add(button);
		      JButton button1 = new JButton("Cancel");
		      myPanel.add(button1);
		    //get value from input on frame;
			     button.addActionListener(new ActionListener(){
			    	  public void actionPerformed(ActionEvent event){
			    		  int propertyid = Integer.parseInt(xField.getText());
			    		  int days = Integer.parseInt(yField.getText());
			    		  if(propertyid==1){
			    		  for(int i=0;i<apartments.size();i++)
			    				  apartments.get(i).setTotalNumberOfSeasonRentalDay(days); 
			    		  System.out.println(apartments);
			    		  }
			    		  
			    			  else if(propertyid==2){
			    				  for (int i=0;i<houses.size();i++)
			    				  houses.get(i).setTotalNumberOfSeasonRentalDay(days); 
			    				  System.out.println(houses);
			    				  }
			    		  
			    			  else if(propertyid==3){
			    				  for (int i=0;i<villages.size();i++)
			    				  villages.get(i).setTotalNumberOfSeasonRentalDay(days); 
			    				  System.out.println(villages);
			    				  }
			    	  else
			    		  System.out.println("the entered information is wrong.");}
			    		
			      });
			      button1.addActionListener(new ActionListener(){
			    	  public void actionPerformed(ActionEvent event){
			    		  System.out.print("canceled");
			    		  
			    	  }
			      });
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			
	 }
	}
	


