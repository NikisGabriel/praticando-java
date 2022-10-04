package gabriel.carrinho.compras.entities;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CartTest {
	Cart cart;

	@BeforeEach
	public void init() {
		cart = new Cart();
	}

	@Test
	void testAddItem() {
		CartItem item = new CartItem(new Product("TV", 1200.00));

		cart.addProduct(item);
		boolean res = cart.getProducts().contains(item);
		assertTrue(res);
	}

	@Test
	void testAddItemExistente() {
		CartItem item = new CartItem(new Product("TV", 1200.00));

		Predicate<CartItem> searchItem = ci -> ci.equals(item);

		cart.addProduct(item);
		cart.addProduct(item);
		cart.addProduct(item);
		CartItem res = cart.getProducts().stream().filter(searchItem).findFirst().get();
		assertEquals(3, res.getQuantity());
	}

	@Test
	void testRemoveItem() {
		CartItem item = new CartItem(new Product("TV", 1200.00));

		cart.addProduct(item);
		cart.removeProduct(item);
		boolean res = cart.getProducts().contains(item);
		assertFalse(res);
	}

	@Test
	void testRemoveUmItem() {
		CartItem item = new CartItem(new Product("TV", 1200.00));

		Predicate<CartItem> searchItem = ci -> ci.equals(item);

		cart.addProduct(item);
		cart.addProduct(item);
		cart.addProduct(item);
		cart.removeOneProduct(item);

		CartItem res = cart.getProducts().stream().filter(searchItem).findFirst().get();
		assertEquals(2, res.getQuantity());
	}

	@Test
	void testRemoveDeUmEmUm() {
		CartItem item = new CartItem(new Product("TV", 1200.00));

		cart.addProduct(item);
		cart.addProduct(item);
		cart.addProduct(item);
		cart.removeOneProduct(item);
		cart.removeOneProduct(item);
		cart.removeOneProduct(item);

		boolean res = cart.getProducts().contains(item);
		assertFalse(res);
	}

	@Test
	void testRemoveItemInexistente() {
		CartItem item1 = new CartItem(new Product("TV", 1200.00));
		CartItem item2 = new CartItem(new Product("Celular", 2300.00));

		cart.addProduct(item1);
		cart.removeOneProduct(item2);

		boolean res = cart.getProducts().contains(item1);
		assertTrue(res);
	}

	@Test
	void testGetPreçoTotal() {
		CartItem item = new CartItem(new Product("TV", 1200.00));
		CartItem item2 = new CartItem(new Product("Celular", 2000.00));

		cart.addProduct(item);
		cart.addProduct(item);
		cart.addProduct(item);
		cart.addProduct(item2);

		Double res = cart.getTotalPrice();
		assertEquals(5600.00, res);
	}

}
