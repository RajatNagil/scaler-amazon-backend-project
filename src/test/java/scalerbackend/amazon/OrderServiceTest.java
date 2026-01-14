package com.amazonclone;

import com.amazonclone.dto.PlaceOrderRequest;
import com.amazonclone.models.*;
import com.amazonclone.repositories.*;
import com.amazonclone.services.CartService;
import com.amazonclone.services.OrderServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Mock private UserRepository userRepository;
    @Mock private OrderRepository orderRepository;
    @Mock private OrderItemRepository orderItemRepository;
    @Mock private AddressRepository addressRepository;
    @Mock private CartService cartService;

    @InjectMocks private OrderServiceImpl orderService;

    User user;
    Cart cart;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        user = User.builder()
                .id(1L)
                .email("order@user.com")
                .build();

        Product product = Product.builder()
                .id(100L)
                .title("Laptop")
                .price(2000.0)
                .build();

        CartItem cartItem = CartItem.builder()
                .id(10L)
                .product(product)
                .quantity(1)
                .price(2000.0)
                .build();

        cart = Cart.builder()
                .id(1L)
                .user(user)
                .items(List.of(cartItem))
                .totalAmount(2000.0)
                .build();

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("order@user.com", null)
        );

        when(userRepository.findByEmail(user.getEmail()))
                .thenReturn(Optional.of(user));

        when(cartService.getCartEntity(user))
                .thenReturn(cart);
    }

    @Test
    void testPlaceOrder() {
        PlaceOrderRequest req = new PlaceOrderRequest(
                "12", "Main St", "City", "State", "Country", "12345"
        );

        var result = orderService.placeOrder(req);

        assertEquals(2000.0, result.getAmount());
        assertEquals("PLACED", result.getOrderStatus());
    }
}
