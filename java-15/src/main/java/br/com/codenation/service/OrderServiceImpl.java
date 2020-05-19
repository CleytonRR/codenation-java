package br.com.codenation.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import br.com.codenation.model.OrderItem;
import br.com.codenation.model.Product;
import br.com.codenation.repository.ProductRepository;
import br.com.codenation.repository.ProductRepositoryImpl;

public class OrderServiceImpl implements OrderService {

	private ProductRepository productRepository = new ProductRepositoryImpl();
	/**
	 * Calculate the sum of all OrderItems
	 */
	@Override
	public Double calculateOrderValue(List<OrderItem> items) {
		return items.stream()
				.mapToDouble(e -> {
					Double sumTotaly = 0D;
					Double discount = 0D;
					Optional<Product> product = this.productRepository.findById(e.getProductId());
					if (product.isPresent()) {
						sumTotaly = product.get().getValue() * e.getQuantity();
						discount = product.get().getIsSale() ? (sumTotaly * 20) / 100 : 0D;
						sumTotaly -= discount;
					}
					return sumTotaly;
				})
				.reduce(0D, (n1, n2) -> n1 + n2);
	}

	/**
	 * Map from idProduct List to Product Set
	 */
	@Override
	public Set<Product> findProductsById(List<Long> ids) {
		return null;
	}

	/**
	 * Calculate the sum of all Orders(List<OrderIten>)
	 */
	@Override
	public Double calculateMultipleOrders(List<List<OrderItem>> orders) {
		return null;
	}

	/**
	 * Group products using isSale attribute as the map key
	 */
	@Override
	public Map<Boolean, List<Product>> groupProductsBySale(List<Long> productIds) {
		return null;
	}

}