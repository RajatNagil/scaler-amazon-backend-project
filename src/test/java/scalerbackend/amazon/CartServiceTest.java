package com.amazonclone;

import com.amazonclone.dto.AddToCartRequest;
import com.amazonclone.dto.CartDto;
import com.amazonclone.models.*;
import com.amazonclone.repositories.*;
import com.amazonclone.services.CartServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CartServiceTest {

    @Mock private UserRepository userRepository;
    @Mock private CartRepository cartRepository;
    @Mock private ProductRepository productRepository;
    @Mock private CartItemRepository cartItemRepository;

    @InjectMocks private CartServiceImpl cartService;

    User user;
    Cart cart;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        user = User.builder()
                .id(1L)
                .email("test@user.com")
                .build();

        cart = Cart.builder()
                .id(1L)
                .user(user)
                .items(new ArrayList<>())
                .totalAmount(0.0)
                .build();

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("test@user.com", null)
        );

        when(userRepository.findByEmail(user.getEmail()))
                .thenReturn(Optional.of(user));

        when(cartRepository.findByUser(user))
                .thenReturn(Optional.of(cart));
    }

    @Test
    void testAddToCart() {

        Product p = Product.builder()
                .id(100L)
                .title("Laptop")
                .price(1000.0)
                .build();

        when(productRepository.findById(100L)).thenReturn(Optional.of(p));

        AddToCartRequest req = new AddToCartRequest(100L, 2);

        CartDto result = cartService.addToCart(req);

        assertEquals(1, result.getItems().size());
        assertEquals(2000.0, result.getTotalAmount());
    }
}
