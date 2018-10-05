package codesquad.web;

import codesquad.domain.Cart;
import codesquad.dto.CartDTO;
import codesquad.dto.CartProductDTO;
import codesquad.security.SessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/api/cart")
public class ApiCartController {

    @GetMapping("")
    public ResponseEntity<CartDTO> listCart(HttpSession session) {
        if(!SessionUtils.isCartExisted(session)) {
            return new ResponseEntity<>(new CartDTO(), HttpStatus.OK);
        }
        return new ResponseEntity<>(SessionUtils.getCartInSession(session).toDTO(), HttpStatus.OK);
    }

    @PostMapping("/{productId}")
    public ResponseEntity<Integer> addCartProduct(@RequestBody CartProductDTO cartProductDTO, HttpSession session) {
        log.debug("[add Cart] productId: {}, quantity: {}", cartProductDTO.getProductId(), cartProductDTO.getQuantity());
        Cart cart;
        if(!SessionUtils.isCartExisted(session)) {
            cart = Cart.fromDTO(cartProductDTO);
            SessionUtils.setCartInSession(session, cart);
            return new ResponseEntity<>(1, HttpStatus.OK);
        }
        cart = SessionUtils.getCartInSession(session);
        return new ResponseEntity<>(cart.addProduct(cartProductDTO).getProducts().size(), HttpStatus.OK);
}

    @PutMapping("/{productId}")
    public ResponseEntity<CartDTO> updateCartProduct(@RequestBody CartProductDTO cartProductDTO, HttpSession session) {
        return new ResponseEntity<>(SessionUtils.getCartInSession(session).updateProduct(cartProductDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> removeCartProduct(@PathVariable Long productId, HttpSession session) {
        SessionUtils.getCartInSession(session).removeProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
