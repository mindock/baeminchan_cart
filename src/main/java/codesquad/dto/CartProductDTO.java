package codesquad.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartProductDTO {
    private Long productId;
    private Long quantity;

    public CartProductDTO(Long productId) {
        this.productId = productId;
    }
}
