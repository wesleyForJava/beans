package com.wesley.bean.efftivejava;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;

import com.google.common.io.Files;
import com.wesley.bean.pojo.User;

public class StaticFactory {
	
	public static void main(String[] args) throws ClassNotFoundException {
//		EnumSet<top> of = EnumSet.of(top.JACK, top.QUEUE, top.KING);
//		Iterator<top> iterator = of.iterator();
//		while (iterator.hasNext()) {
//			System.out.println(iterator.next());
//		}
//		Instant start = Instant.now();
//		Date from = Date.from(start);
//		System.out.println(from.toString());
		
		
	}
	static enum top{
		JACK,QUEUE,KING
	}
	
	public static User getInstance() {
		return null;
	}
	  
    static class Player{
    	public static final int TYPE_RUNNER = 1;
	    public static final int TYPE_SWIMMER = 2;
	    public static final int TYPE_RACER = 3;
	    int type;

	    private Player(int type) {
	        this.type = type;
	    }

	    public static Player newRunner() {
	        return new Player(TYPE_RUNNER);
	    }
	    public static Player newSwimmer() {
	        return new Player(TYPE_SWIMMER);
	    }
	    public static Player newRacer() {
	        return new Player(TYPE_RACER);
	    }
    }
}
