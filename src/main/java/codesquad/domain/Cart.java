package codesquad.domain;

import codesquad.dto.CartDTO;
import codesquad.dto.CartProductDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Cart {
    private Map<Long, Long> products = new HashMap<>();

    private Cart(CartProductDTO cartProductDTO) {
        this.products.put(cartProductDTO.getProductId(), cartProductDTO.getQuantity());
    }

    public static Cart fromDTO(CartProductDTO cartProductDTO) {
        return new Cart(cartProductDTO);
    }

    public CartDTO toDTO() {
        return new CartDTO(this.products);
    }

    public CartDTO addProduct(CartProductDTO cartProductDTO) {
        Long productId = cartProductDTO.getProductId();
        Long quantity = cartProductDTO.getQuantity();
        if(hasProduct(productId)) {
            quantity += products.get(productId);
        }
        products.put(productId, quantity);
        return this.toDTO();
    }

    public CartDTO updateProduct(CartProductDTO cartProductDTO) {
        products.put(cartProductDTO.getProductId(), cartProductDTO.getQuantity());
        return this.toDTO();
    }

    public void removeProduct(Long productId) {
        products.remove(productId);
    }

    public boolean hasProduct(Long productId) {
        return products.containsKey(productId);
    }

    public boolean hasProduct(Long productId, Long quantity) {
        return products.containsKey(productId) && products.get(productId) == quantity;
    }
}
