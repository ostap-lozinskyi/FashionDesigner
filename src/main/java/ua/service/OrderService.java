package ua.service;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Order;
import ua.model.filter.OrderFilter;
import ua.model.request.OrderRequest;
import ua.model.view.MealView;
import ua.model.view.OrderView;
import ua.model.view.PlaceView;

public interface OrderService {

	List<String> findAllMealsNames();
	
	List<String> findStatusForSearch();

	List<PlaceView> findAllPlaceViews();
	
	PlaceView findPlaceViewById(Integer id);

	Page<OrderView> findAll(Pageable pageable, OrderFilter filter);
	
	List<OrderView> findOrderViewsForTable(Integer tableId);
	
	List<MealView> findMealViewsForOrder(Integer orderId);
	
	Order findOrderById(Integer id);
	
	List<Order> findOrderByPlaceId(Integer id);

	void saveOrder(OrderRequest request, Principal principal);

	OrderRequest findOneRequest(Integer id);

//	void deleteOrder(Integer id);
	
	void updateOrderStatus(Integer id, String newStatus);
	
}
