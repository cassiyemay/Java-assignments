package AssignmentA2;

public class Vehicle implements RentalItem{
	private int TypeOfVehicle;
	private String Owner;
	private int TotalRentalDays;
	private int RentalCostPerDay;
	
	public Vehicle(){
		this(0,"",0,0);
	}
		
	public Vehicle(int number,String owner, int totaldays, int cost){
		this.TypeOfVehicle=number;
		this.Owner=owner;
		this.TotalRentalDays=totaldays;
		this.RentalCostPerDay=cost;
	}
	
	public int getTypeOfVehicle(){
		return TypeOfVehicle;
	}
	
	public String getOwner(){
		return Owner;
	}
	
	public int getTotalRentalDays(){
		return TotalRentalDays;
	}
	
	public int getRentalCostPerDay(){
		return RentalCostPerDay;
	}
	
	public void setTypeOfVehicle(int number){
		this.TypeOfVehicle=number;
	}
	
	public void setOwner(String name){
		this.Owner=name;
	}
	
	public void setTotalRentalDays(int num){
		this.TotalRentalDays=num;
	}
	
	public void setRentalCostPerDay(int cost){
		this.RentalCostPerDay=cost;
	}
	
	//abstract method
	public void RentalItem(int rentaldays){
		this.TotalRentalDays = this.TotalRentalDays + rentaldays -1;
	}
	
	public int TotalIncome(){
		return TotalRentalDays * RentalCostPerDay;
	}
	
	@Override
	public String toString(){
		return "\n\tType of Vehicles: " + TypeOfVehicle +
				"\n\tOwner: " + Owner + 
				"\n\tRental Cost PerDay: " + RentalCostPerDay+ 
				"\n\tTotal Rental Days: " + TotalRentalDays ;
	}
}
