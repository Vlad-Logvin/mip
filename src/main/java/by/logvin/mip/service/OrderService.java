package by.logvin.mip.service;

import by.logvin.mip.model.entity.Order;

public interface OrderService {
    Order findById(Long id);

    Order save(Order order);

    void delete(Long id);
}
