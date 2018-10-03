package codesquad.web;

import codesquad.domain.Cart;
import codesquad.dto.CartDTO;
import codesquad.dto.CartProductDTO;
import codesquad.security.SessionUtils;
import codesquad.support.test.AcceptanceTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiCartControllerAcceptanceTest extends AcceptanceTest {

    private static final Logger log = LoggerFactory.getLogger(ApiCartControllerAcceptanceTest.class);
    private String cookie;
    private CartProductDTO product;
    private CartProductDTO existedProduct;
    private CartProductDTO newProduct;

    @Before
    public void setUp() {
        product = new CartProductDTO(1L, 3L);
        existedProduct = new CartProductDTO(1L, 5L);
        newProduct = new CartProductDTO(2L, 1L);

        ResponseEntity<CartDTO> response = template().postForEntity("/api/cart/1", product, CartDTO.class);
        cookie = response.getHeaders().getFirst(response.getHeaders().SET_COOKIE);
    }

    @Test
    public void 새로운_상품추가_성공() {
        ResponseEntity<CartDTO> response = template().exchange("/api/cart/2", HttpMethod.POST, applicationJsonHeader(newProduct), CartDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getProducts().containsKey(2L));
    }

    @Test
    public void 기존_상품추가_성공() {
        ResponseEntity<CartDTO> response = template().exchange("/api/cart/1", HttpMethod.POST, applicationJsonHeader(existedProduct), CartDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getProducts().containsKey(1L));
        assertThat(response.getBody().getProducts().get(1L)).isEqualTo(8L);
    }

    @Test
    public void 상품수정_성공() {
        ResponseEntity<CartDTO> response = template().exchange("/api/cart/1", HttpMethod.PUT, applicationJsonHeader(existedProduct), CartDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getProducts().get(1L)).isEqualTo(5L);
    }

    @Test
    public void 상품삭제_성공() {
        ResponseEntity<Void> response = template().exchange("/api/cart/1", HttpMethod.DELETE, applicationJsonHeader(), Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private HttpEntity applicationJsonHeader() {
        HttpHeaders headers = new HttpHeaders();
        if (cookie != null) {
            headers.add("Cookie", cookie);
        }
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity(null, headers);
    }

    private HttpEntity applicationJsonHeader(CartProductDTO cartProductDTO) {
        HttpHeaders headers = new HttpHeaders();
        if (cookie != null) {
            headers.add("Cookie", cookie);
        }
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity(cartProductDTO, headers);
    }
}
