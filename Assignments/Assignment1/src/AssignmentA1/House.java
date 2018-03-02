package AssignmentA1;

public class House extends Property{
	private int totalNumberOfStoreys;
	private int clearingFee;
	
	public House(){
		this(0,0);
	}
	
	public House(int num, int fee){
		this.totalNumberOfStoreys=num;
		this.clearingFee=fee;
	}
	
	public House(int num, String name,String address, int cost, int totalnum,int num1,int fee){
		super(2,name,address,cost,totalnum);
		this.totalNumberOfStoreys=num;
		this.clearingFee=fee;
	}
	
	
	public void setTotalNumberOfStoreys(int num){
		this.totalNumberOfStoreys=num;
	}
	
	public void setClearingFee(int fee){
		this.clearingFee=fee;
	}
	
	public int getTotalNumberOfStoreys(){
		return totalNumberOfStoreys;
	}
	
	public int getClearingFee(){
		return clearingFee;
	}
	
	@Override
	public double totalIncome(){
		return super.totalIncome() + this.getClearingFee();
	}
	
	@Override
	public String toString(){
		return "\n" + super.toString()+
				"\n\ttotal number of storeys: " + this.getTotalNumberOfStoreys() + 
				"\n\tclearing fee: " + getClearingFee() +
				"\n\ttotal income: " + totalIncome();
	}
	

}
