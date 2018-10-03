package codesquad.domain;

import codesquad.dto.CartProductDTO;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest {
    private CartProductDTO cartProductDTO;
    private Cart cart;

    @Before
    public void setUp() {
        cartProductDTO = new CartProductDTO(1L, 1L);
        cart = Cart.fromDTO(cartProductDTO);
    }

    @Test
    public void 새로운_상품추가_성공() {
        CartProductDTO newProduct = new CartProductDTO(2L, 3L);
        cart.addProduct(newProduct);
        assertThat(cart.hasProduct(2L, 3L)).isTrue();
    }

    @Test
    public void 기존_상품추가_성공() {
        CartProductDTO existedProduct = new CartProductDTO(1L, 5L);
        cart.addProduct(existedProduct);
        assertThat(cart.hasProduct(1L, 6L)).isTrue();
    }

    @Test
    public void 기존_상품수정_성공() {
        CartProductDTO existedProduct = new CartProductDTO(1L, 5L);
        cart.updateProduct(existedProduct);
        assertThat(cart.hasProduct(1L, 5L)).isTrue();
    }

    @Test
    public void 기존_상품삭제_성공() {
        cart.removeProduct(1L);
        assertThat(cart.hasProduct(1L)).isFalse();
    }
}
