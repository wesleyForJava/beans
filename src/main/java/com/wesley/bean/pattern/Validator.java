package com.wesley.bean.pattern;

public  class Validator{
		private final ValidationStrategy strategy;
		public Validator(ValidationStrategy v){
		this.strategy = v;
		}
		public boolean validate(String s){
		return strategy.execute(s);
		}
		public static void main(String[] args) {
//			Validator numericValidator = new Validator(new IsNumeric());
//			boolean b1 = numericValidator.validate("aaaa");
//			System.out.println(b1);
//			Validator lowerCaseValidator = new Validator(new IsAllLowerCase ());
//			boolean b2 = lowerCaseValidator.validate("bbb");
//			System.out.println(b2);
			
			Validator numericValidator =
					new Validator((String s) -> s.matches("[a-z]+"));
			boolean b1 = numericValidator.validate("aaaa");
			Validator lowerCaseValidator =
					new Validator((String s) -> s.matches("\\d+"));
			boolean b2 = lowerCaseValidator.validate("bbbb");
			System.out.println(b1);
			System.out.println(b2);
			
		}

}
