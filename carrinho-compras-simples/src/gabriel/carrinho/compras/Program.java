package gabriel.carrinho.compras;

import gabriel.carrinho.compras.entities.Cart;
import gabriel.carrinho.compras.entities.CartItem;
import gabriel.carrinho.compras.entities.Product;

public class Program {
	public static void main(String[] args) {

		Cart cart = new Cart();

		for (int i = 0; i < 10; i++) {
			cart.addProduct(new CartItem(new Product("P" + i, 500.00 * i)));
		}
		for (int i = 5; i < 14; i++) {
			cart.addProduct(new CartItem(new Product("P" + i, 500.00 * i)));
		}

		cart.getProducts().forEach(System.out::println);
	}
}
