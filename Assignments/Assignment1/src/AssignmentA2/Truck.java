package AssignmentA2;

public class Truck extends Vehicle{
	private int CargoWeight;
	
	public Truck(){
		this(0);
	}
	
	public Truck(int number){
		this.CargoWeight=number;
	}
	
	public Truck(int num, String name, int day, int cost, int number){
		super(2,name,day,cost);
		this.CargoWeight=number;
	}
	
	public int getCargoWeight(){
		return CargoWeight;
	}
	
	public void setCargoWeight(int num){
		this.CargoWeight=num;
	}
	
	@Override
	public int TotalIncome(){
		return super.TotalIncome() + CargoWeight;
	}
	
	@Override
	public String toString(){
		return "\n" + super.toString()+
		        "\n\tcargo weight: " + getCargoWeight() +
		        "\n\ttotal income: " + TotalIncome();
	}

}
