package uz.market.uzum.dtos.order;

public record PayOrderDTO (
        Long orderId,
        Long userId,
        Integer amount

){
}
