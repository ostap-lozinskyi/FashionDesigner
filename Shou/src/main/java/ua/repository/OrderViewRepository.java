package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.model.filter.OrderFilter;
import ua.model.view.OrderView;

public interface OrderViewRepository {

	Page<OrderView> findAllView(OrderFilter filter, Pageable pageable);
		
}
