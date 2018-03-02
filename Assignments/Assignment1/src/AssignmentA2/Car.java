package AssignmentA2;

public class Car extends Vehicle{
	private int PassengersNumber;
	
	public Car(){
		this.PassengersNumber=0;
	}
	
	public Car(int number){
		this.PassengersNumber=number;
	}
	
	public Car(int num, String name, int day, int cost, int number){
		super(1,name,day,cost);
		this.PassengersNumber=number;
	}
	
	public int getPassengersNumber(){
		return PassengersNumber;
	}
	
	public void setPassengersNumber(int number){
		this.PassengersNumber=number;
	}
	
	@Override
	public String toString(){
		return "\n" + super.toString()+
		        "\n\tpassenger number: " + getPassengersNumber() +
		        "\n\ttotal income: " + super.TotalIncome();
	}

}
