package AssignmentA2;

public class Property implements RentalItem {
	private int uniqueRegisterNumber;
	private String name;
	private String postalAddress;
	private int rentalCostPerDay;
	private int totalNumberOfSeasonRentalDay;
	
	public Property(){
		this(1,"","",0,0);
	}
	
	public Property(int num, String name,String address, int cost, int totalnum){
		this.uniqueRegisterNumber=num;
		this.name=name;
		this.postalAddress=address;
		this.rentalCostPerDay=cost;
		this.totalNumberOfSeasonRentalDay=totalnum;
	}
	
	public int getRegisterNumber(){
		return uniqueRegisterNumber;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPostalAddress(){
		return postalAddress;
	}
	
	public int getRentalCostPerDay(){
		return rentalCostPerDay;
	}
	
	public int getTotalNumberOfSeasonRentalDay(){
		return totalNumberOfSeasonRentalDay;
	}
	
	public void setRegisterNumber(int num){
		this.uniqueRegisterNumber=num;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public void setPostalAddress(String address){
		this.postalAddress=address;
	}
	
	public void setRentalCostPerDay(int cost){
		this.rentalCostPerDay=cost;
	}
	
	public void setTotalNumberOfSeasonRentalDay(int totalnum){
		this.totalNumberOfSeasonRentalDay=totalnum;
	}
	
	public void RentalProperty(int RentalDays){
		totalNumberOfSeasonRentalDay += RentalDays;
	}
	
	public double totalIncome(){
		return rentalCostPerDay * totalNumberOfSeasonRentalDay;
	}
	//abstract method
	public void RentalItem(int rentaldays){
		totalNumberOfSeasonRentalDay +=  rentaldays;
		
	}
	
	public String toString() {
		return "\n\tpropertyID: " + uniqueRegisterNumber + 
				"\n\tname: " + name + 
				"\n\tpostal address: " + postalAddress +
				"\n\trental CostPerDay: " + rentalCostPerDay + 
				"\n\ttotal Number Of Season Rental Day: " + totalNumberOfSeasonRentalDay;
				
	}
}
