package gabriel.carrinho.compras.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Cart {
	private List<CartItem> products = new ArrayList<>();

	public CartItem searchItem(CartItem cartItem) {
		// predicado para encontrar um item
		Predicate<CartItem> searchItem = ci -> ci.equals(cartItem);

		// verifica se existe algo na lista
		if (products.isEmpty()) {
			return null;
		}
		// filtra de acordo com o predicado encontra o primeiro match e o retorna
		// o método orElse age como um get, porém oferece a opção de retornar um valor
		// padrão se o get estiver vazio
		return products.stream().filter(searchItem).findFirst().orElse(null);

	}

	public void addProduct(CartItem cartItem) {
		CartItem item = this.searchItem(cartItem);

		// se o item já existir incrementa a quantidade
		if (item != null) {
			item.increaseQuantity();
		} else {
			products.add(cartItem);
		}
	}

	public void removeOneProduct(CartItem cartItem) {
		CartItem item = this.searchItem(cartItem);

		// so remove se existir um item
		if (item != null) {
			// se a quantidade for de um item o item será removido
			if (item.getQuantity() == 1) {
				this.removeProduct(cartItem);
			}
			// se a quantidade for mais de um só diminui
			item.decreaseQuantity();
		}
	}

	public void removeProduct(CartItem cartItem) {
		products.remove(cartItem);
	}

	public List<CartItem> getProducts() {
		return products;
	}

	public Double getTotalPrice() {
		return products.stream().map(p -> p.getTotalPrice()).reduce(0.00, (p1, p2) -> p1 += p2);
	}

}
