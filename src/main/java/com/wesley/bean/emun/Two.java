package com.wesley.bean.emun;

public class Two {

public class TrafficLight {  
    One color = One.RED;  
    public void change() {  
        switch (color) {  
        case RED:  
            color = One.GREEN;  
            break;  
        case YELLOW:  
            color = One.RED;  
            break;  
        case GREEN:  
            color = One.YELLOW;  
            break;
		default:
			break;  
        }  
    }  
}  

}
