package AssignmentA1;

public class Village extends Property{
	private int totalNumberOfRooms;
	private int roomServiceCostPerDay;
	private int taxPerDay;
	
	public Village(){
		this(0,0,0);
	}
	
	public Village(int num1, int num2,int num3){
		this.totalNumberOfRooms=num1;
		this.roomServiceCostPerDay=num2;
		this.taxPerDay=num3;
	}
	
	public Village(int num, String name,String address, int cost, int totalnum,int num1,int num2,int num3){
		super(3,name,address,cost,totalnum);
		this.totalNumberOfRooms=num1;
		this.roomServiceCostPerDay=num2;
		this.taxPerDay=num3;
	}
	
	public int getTotalNumberOfRooms(){
		return totalNumberOfRooms;
	}
	
	public int getRoomServiceCostPerDay(){
		return roomServiceCostPerDay;
	}
	
	public int getTaxPerDay(){
		return taxPerDay;
	}
	
	@Override
	public double totalIncome(){
		return super.totalIncome() + super.getTotalNumberOfSeasonRentalDay() *
				(taxPerDay + roomServiceCostPerDay * totalNumberOfRooms);
	}
	public void setTotalNumberOfRooms(int num){
		this.totalNumberOfRooms=num;
	}
	
	public void setRoomServiceCostPerDay(int num){
		this.roomServiceCostPerDay=num;
	}
	
	public void setTaxPerDay(int num){
		this.taxPerDay=num;
	}
	
	@Override
	public String toString(){
		return "\n" + super.toString()+
				"\n\trooms ervice cost perday: " + getRoomServiceCostPerDay()+
				"\n\ttotal number of rooms: " + getTotalNumberOfRooms()+
				"\n\ttax perday: " + getTaxPerDay()+
				"\n\ttotal lincome: " + totalIncome();
	}
	

}
