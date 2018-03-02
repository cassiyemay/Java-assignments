package AssignmentA1;

public class Apartment extends Property {
	private int storeyNumber;
	private int numberOfBeds;
	
	public Apartment(){
		this(0,0);
	}
	
	public Apartment(int num, int num1){
		this.storeyNumber=num;
		this.numberOfBeds=num1;
	}
	
	public Apartment(int num, String name,String address, int cost, int totalnum,int num1,int num2){
		super(1,name,address,cost,totalnum);
		this.storeyNumber=num1;
		this.numberOfBeds=num2;
		
	}
	
	public void setStoreyNumber(int num){
		this.storeyNumber=num;
	}
	
	public void setNumberOfBeds(int num){
		this.numberOfBeds=num;
	}
	
	public int getStoreyNumber(){
		return storeyNumber;
	}
	
	public int getNumberOfBeds(){
		return numberOfBeds;
	}

	@Override
	public String toString(){
		return "\n" + super.toString()+
		        "\n\tstorey number: " + getStoreyNumber() + 
				"\n\tnumber of beds: " + getNumberOfBeds() +
				"\n\ttotal income: " + super.totalIncome();
	}

}
