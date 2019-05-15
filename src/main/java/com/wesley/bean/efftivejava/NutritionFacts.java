package com.wesley.bean.efftivejava;

public class NutritionFacts {
     //Builder parttern
	private static int servicingSize;
	private static int servicings;
	private static int catories;
	private static int fact;
	private static int sodium;
	private static int carbohydrate;
	
	
	public static class Builder{
		private static int servicingSize;
		private static int servicings;

		//optinal defualt value is zero;
		private static int catories=0;
		private static int fact=0;
		private static int sodium=0;
		private static int carbohydrate=0;
		
		
		
		public Builder(int servicingSize,int servicings) {
			this.servicings=servicings;
			this.servicingSize=servicingSize;
		} 
		
		public Builder catories(int val) {
			catories = val;
			return this;
		}
		public Builder fact(int val) {
			fact = val;
			return this;
		}

		public Builder sodium(int val) {
			sodium = val;
			return this;
		}

		public Builder carbohydrate(int val) {
			carbohydrate = val;
			return this;
		}
		public NutritionFacts build() {
			return new NutritionFacts(this);
		}
	}


	public NutritionFacts(Builder builder) {
		this.servicingSize=builder.servicingSize;
		this.servicings=builder.servicings;
		this.catories=builder.catories;
		this.fact=builder.fact;
		this.sodium=builder.sodium;
		this.carbohydrate=builder.carbohydrate;
	}
	
	
	@Override
	public String toString() {
		return this.servicings+"\n"+this.carbohydrate+"\r"+this.fact+"\r";
	}


	public static void main(String[] args) {
		NutritionFacts ntf=new NutritionFacts.Builder(1, 1).carbohydrate(1).fact(3).build();
		System.out.println(ntf.toString());
	}
}
