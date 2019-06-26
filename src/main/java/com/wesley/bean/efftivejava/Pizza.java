package com.wesley.bean.efftivejava;

import java.util.EnumSet;
import java.util.Set;

import java.util.Objects;

public abstract class Pizza {

	    public enum Topping { HAM, MUSHROOM, ONION, PEPPER, SAUSAGE }
	    public final Set<Topping> toppings;

	    public abstract static class Builder<T extends Builder<T>> {
	        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);
	        public T addTopping(Topping topping) {
	            toppings.add(Objects.requireNonNull(topping));
	            return self();
	        }

	        public abstract Pizza build();

	        // Subclasses must override this method to return "this"
	        protected abstract T self();
	    }
	    
	   public Pizza(Builder<?> builder) {
	        toppings = builder.toppings.clone(); // See Item 50
	    }
}
