package AssignmentA2;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class VehicleMethods {
	private ArrayList<Car> cars = new ArrayList<Car>();
	private ArrayList<Truck> trucks = new ArrayList<Truck>();
	
	public void FillInVehicles(Car[] car, Truck[] truck){
		for (int i =0; i < car.length;i++){
			cars.add(car[i]);
			cars.get(0).RentalItem(i+2);
			cars.get(0).RentalItem(i+0);
			cars.get(0).RentalItem(i+1);
		}
		System.out.println(cars);
		for(int j=0; j< truck.length;j++){
			trucks.add(truck[j]);
			trucks.get(j).RentalItem(0+j);
			trucks.get(j).RentalItem(j+1);
			trucks.get(j).RentalItem(j+1);
		}
		System.out.println(trucks);
	}
	
	public void PrintAllVehicles(){
		String myFileName="OutputVehicle.txt";
		
		try{
		PrintWriter myOutFile = new PrintWriter(myFileName);
		for(int i=0;i< cars.size();i++){
			myOutFile.println(cars.get(i).toString());
		}
		for(int i=0;i< trucks.size();i++){
			myOutFile.println(trucks.get(i).toString());
		}
		myOutFile.close();
		}catch(Exception ex){
			System.out.println("exception" +ex.getMessage() + "caught");
		}
		
	}
	
	public void ReadInputVehicleFile(String filename){
		try{
			FileReader myFile = new FileReader(filename);
			Scanner scanMyFile = new Scanner(myFile);
			while(scanMyFile.hasNext()){
				String words=scanMyFile.nextLine();
				String[] array= words.split(" ");
				int[] result=new int[array.length-1];
				for (int i=0;i<array.length-1;i++){
					result[i]= Integer.parseInt(array[i]);}
				if (result[0] ==1)
					cars.add(new Car(result[0],array[array.length-1],
							result[1],result[2],result[3]));
				else
					trucks.add(new Truck(result[0],array[array.length-1],
							result[1],result[2],result[3]));
				}
			}catch(Exception ex){
				System.out.println("exception" + ex.getMessage()+"caught");
			}
	}

}
