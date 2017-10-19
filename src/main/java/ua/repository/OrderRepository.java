package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Order;
import ua.model.view.OrderView;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Query("SELECT new ua.model.view.OrderView(o.id, p.number, o.status) FROM Order o JOIN o.place p")
	List<OrderView> findAllOrderView();
	
	@Query("SELECT distinct o.status FROM Order o")
	List<String> findStatusForSearch();
	
//	@Query(value = "SELECT new ua.model.view.OrderView(o.id, p.number, o.status) FROM Order o JOIN o.place p",
//			countQuery = "SELECT count(o.id) FROM Order o JOIN o.place p")
//	Page<OrderView> findAllView(Pageable pageable);
	
	@Query(value = "SELECT new ua.model.view.OrderView(o.id, p.number, o.status) FROM Order o JOIN o.place p WHERE p.id=?1 AND (NOT (o.status='Is paid') OR o.status=null) ORDER BY o.status DESC")
	List<OrderView> findOrderViewsForTable(Integer tableId);

	@Query("SELECT o FROM Order o JOIN FETCH o.place WHERE o.id=?1")
	Order findOneRequest(Integer id);
	
	@Query("SELECT o FROM Order o WHERE o.id=?1")
	Order findOrderById(Integer id);
	
	@Query("SELECT o FROM Order o WHERE o.place.id=?1")
	List<Order> findOrderByPlaceId(Integer id);
	
}
