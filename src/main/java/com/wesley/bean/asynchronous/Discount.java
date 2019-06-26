package com.wesley.bean.asynchronous;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import com.wesley.bean.array.eleven.Shop;
import com.wesley.bean.asynchronous.ExchangeService.Money;

public class Discount {
	private static final 		List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
			new Shop("LetsSaveBig"),
			new Shop("MyFavoriteShop"),
			new Shop("BuyItAll"));
    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        public final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }
    private static double apply(double price, Code code) {
        Util.delay();
        return Util.format(price * (100 - code.percentage) / 100);
    }
    public static void main(String[] args) {

		/*
		 * List<String> findPricesInUSD = findPricesInUSD("tegoupu");
		 * System.out.println(findPricesInUSD.toString());
		 */
    	
	}
    
//    public static List<String> findPricesInUSD(String product) {
//        List<CompletableFuture<Double>> priceFutures = new ArrayList<>();
//        for (Shop shop : shops) {
//            CompletableFuture<Double> futurePriceInUSD = 
//                CompletableFuture.supplyAsync(() -> shop.getPrice(product))
//                .thenCombine(
//                    CompletableFuture.supplyAsync(
//                        () ->  ExchangeService.getRate(Money.EUR, Money.USD)),
//                    (price, rate) -> price * rate
//                );
//            priceFutures.add(futurePriceInUSD);
//        }
        // Drawback: The shop is not accessible anymore outside the loop,
        // so the getName() call below has been commented out.
//        List<String> prices = priceFutures
//                .stream()
//                .map(CompletableFuture::join)
//                .map(price -> /*shop.getName() +*/ " price is " + price)
//                .collect(Collectors.toList());
//        return prices;
//    }
}