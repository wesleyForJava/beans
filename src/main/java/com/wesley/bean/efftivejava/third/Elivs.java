package com.wesley.bean.efftivejava.third;
import java.util.regex.Pattern;
public class Elivs implements AutoCloseable {
	public static final Elivs INSTACE=new Elivs();

	private Elivs() {
		throw new RuntimeException();
	}
	
	public static Elivs getInstance() {
		return INSTACE;
	}
	public void LeaveTheBuildings() {
		
	}
    private Object readResolve() {
    	return INSTACE;
    }
	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}	
}
