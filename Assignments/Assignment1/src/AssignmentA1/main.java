package AssignmentA1;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
	public static void main(String[] args){
	Scanner in = new Scanner(System.in);
	Apartment[] a = {new Apartment(1,"chen","henry_street",50,0,2,1),
			new Apartment(1,"zengcha","henry_street",50,0,1,2),
			new Apartment(1,"kevin","henry_street",50,0,3,4)
		};
		House[] b ={new House(2,"Michale","O'connel",50,0,1,2),
				new House(2,"Jane","paradise_12",50,2,2,60),
				new House(2,"Michale","dublin12",60,4,1,60)} ;
		Village[] c ={new Village(3,"Devin","Hernystreet",50,0,1,10,50),
				new Village(3,"George","somewhere_23",50,0,2,20,40),
				new Village(3,"John","galfon_street",50,0,3,20,55)
		};
		a[0].setNumberOfBeds(4);
		a[1].setName("zengchen");
		a[2].setRentalCostPerDay(60);
		b[0].setTotalNumberOfSeasonRentalDay(2);
		b[1].setClearingFee(70);
		b[2].setTotalNumberOfStoreys(2);
		c[0].setRoomServiceCostPerDay(20);
		c[1].setTaxPerDay(50);
		c[2].setTotalNumberOfRooms(4);

		Methods properties = new Methods();
		properties.FillInProperties(a,b,c);
		properties.PrintAllProperties();

	}
		

}
