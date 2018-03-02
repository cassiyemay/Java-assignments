package AssignmentA2;
import java.io.FileReader;
import java.util.ArrayList;

public class main {
	public static void main(String[] args){
	
		PropertyMethods properties = new PropertyMethods();
		String filename ="src/AssignmentA2/InputProperty.txt";
		properties.ReadInputPropertyFile(filename);
		properties.PrintAllProperties();
		properties.GiveRentalProperty();
		
		Car[] car = {new Car(1,"John",0,50,3), new Car(1,"Mike",0,40,1),
				new Car(1,"Jane",0,50,2)};
		Truck[] truck = {new Truck(2,"Oven",1,60,50), new Truck(2,"Daniel",2,60,100),
				new Truck(2,"Cindy",3,60,50)};
		car[0].setOwner("Brown John");
		car[0].setRentalCostPerDay(60);
		car[1].RentalItem(2);
		car[1].setPassengersNumber(2);
		car[2].setTotalRentalDays(1);
		truck[0].setCargoWeight(50);
		truck[1].setRentalCostPerDay(80);
		truck[2].setTotalRentalDays(1);
		truck[2].RentalItem(3);
		
		VehicleMethods vehicles = new VehicleMethods();
		vehicles.FillInVehicles(car,truck);
		String filename1 ="src/AssignmentA2/InputVehicle.txt";
		vehicles.ReadInputVehicleFile(filename1);
		vehicles.PrintAllVehicles();
		
		
	}
	

}
