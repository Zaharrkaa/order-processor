package ru.zaharka.orderservice.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.zaharka.commonlibraries.dto.order.OrderDto;
import ru.zaharka.commonlibraries.dto.order.OrderItemDto;
import ru.zaharka.commonlibraries.dto.order.OrderResponseDto;
import ru.zaharka.orderservice.entity.Order;
import ru.zaharka.orderservice.entity.OrderItem;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    Order orderDtoToOrder(OrderDto orderDto);

    OrderItemDto orderItemToOrderItemDto(OrderItem orderItem);

    OrderItem orderItemDtoToOrderItem(OrderItemDto orderItemDto);

    OrderResponseDto orderToOrderResponseDto(Order order);
}
