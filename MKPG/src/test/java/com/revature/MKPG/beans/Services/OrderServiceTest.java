package com.revature.MKPG.beans.Services;

import com.revature.MKPG.beans.Repositories.OrderRepo;
import com.revature.MKPG.entities.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepo orderRepo;

    @InjectMocks
    private OrderService orderService;

    private Order order;

    @BeforeEach
    public void setup(){
        order = Order.builder()
                .orderId(1)
                .quantity(3)
                .deliveryDate("16/09/2022")
                .status("status")
                .build();
    }

    @Test
    void testForCreateOrder() {

        given(orderRepo.save(order)).willReturn(order);

        Order savedOrder = orderService.createOrder(order);

        assertThat(savedOrder).isNotNull();
        assertThat(savedOrder.getOrderId()).isGreaterThan(0);
    }

    @Test
    void testForGetAllOrder() {
        Order order1 = Order.builder()
                .orderId(2)
                .quantity(5)
                .deliveryDate("20/09/2022")
                .status("status2")
                .build();
        given(orderRepo.findAll()).willReturn(List.of(order,order1));
        List<Order> orderList = orderService.getAllOrder();

        assertThat(orderList).isNotNull();
        assertThat(orderList.size()).isEqualTo(2);
    }

    @Test
    void testForGetOrderById() {
        given(orderRepo.findById(1)).willReturn(Optional.of(order));
        Order savedOrder = orderService.getOrderById(order.getOrderId()).get();
        assertThat(savedOrder).isNotNull();
    }

    @Test
    void testForUpdateOrder() {
        given(orderRepo.save(order)).willReturn(order);
        order.setDeliveryDate("18/09/2022");
        order.setStatus("GetStatus");

        Order updatedOrder = orderService.updateOrder(order);
        assertThat(updatedOrder.getDeliveryDate()).isEqualTo("18/09/2022");
        assertThat(updatedOrder.getStatus()).isEqualTo("GetStatus");
    }

    @Test
    void testForDeleteById() {
        int orderId = 1;
        willDoNothing().given(orderRepo).deleteById(orderId);
        orderService.deleteById(orderId);
        verify(orderRepo, times(1)).deleteById(orderId);
    }
}