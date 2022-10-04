package gabriel.carrinho.compras.entities;

public class CartItem {
	private Integer quantity = 1;
	private Product product;

	public CartItem(Product product) {
		this.product = product;
		this.quantity = 1;
	}

	public void increaseQuantity() {
		this.quantity++;
	}

	public void decreaseQuantity() {
		this.quantity--;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Product getProduct() {
		return product;
	}

	public Double getTotalPrice() {
		return this.getProduct().getPrice() * this.quantity;
	}

	// hashcode e equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartItem other = (CartItem) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%d, %s, %.2f", this.quantity, this.product.getName(), this.getTotalPrice()).toString();
	}
}
