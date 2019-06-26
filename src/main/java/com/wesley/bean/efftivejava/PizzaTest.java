package com.wesley.bean.efftivejava;

import com.wesley.bean.efftivejava.Pizza.Topping;
import com.wesley.bean.efftivejava.sub.Calzone;
import com.wesley.bean.efftivejava.sub.NyPizza;
import com.wesley.bean.efftivejava.sub.NyPizza.Size;

public class PizzaTest {
	public static void main(String[] args) {
            NyPizza nyPizza=new NyPizza.Builder(Size.SMALL).addTopping(Topping.SAUSAGE).build();
            Calzone calzone=new Calzone.Builder().addTopping(Topping.HAM).sauceInside().build();
            System.out.println(nyPizza.toString());
            System.out.println(calzone.toString());
	}
}
