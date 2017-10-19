package ua.controller.admin;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import ua.entity.Order;
import ua.model.filter.OrderFilter;
import ua.model.request.OrderRequest;
import ua.service.OrderService;
import ua.service.PlaceService;

@Controller
@RequestMapping("/admin/adminOrder")
@SessionAttributes("order")
public class AdminOrderController {

	private final OrderService service;
	
	private final PlaceService placeService;

	@Autowired
	public AdminOrderController(OrderService service, PlaceService placeService) {
		this.service = service;
		this.placeService = placeService;
	}

	@ModelAttribute("order")
	public OrderRequest getForm() {
		return new OrderRequest();
	}
	
	@ModelAttribute("orderFilter")
	public OrderFilter getFilter() {
		return new OrderFilter();
	}

	@GetMapping
	public String show(Model model, @PageableDefault Pageable pageable, 
			@ModelAttribute("orderFilter") OrderFilter filter) {
		model.addAttribute("meals", service.findAllMealsNames());
		model.addAttribute("places", service.findAllPlaceViews());
		model.addAttribute("orders", service.findAll(pageable, filter));
		model.addAttribute("statuses", service.findStatusForSearch());		
		if (service.findAll(pageable, filter).hasContent()||pageable.getPageNumber()==0)
			return "adminOrder";
		else
			return "redirect:/admin/adminOrder"+buildParams(pageable, filter);
	}

	@GetMapping("/updateStatus/{id}/{status}")
	public String update(@PathVariable Integer id, @PathVariable String status, Model model,
			@PageableDefault Pageable pageable,	@ModelAttribute("orderFilter") OrderFilter filter,
			Principal principal) {
		Integer placeId = service.findOrderById(id).getPlace().getId();
		service.updateOrderStatus(id, status);
		List<Order> tableOrders = service.findOrderByPlaceId(placeId);
		boolean hasUnpaidOrders = false;
		for (Order order : tableOrders) {
			if (!order.getStatus().equals("Is paid")) {
				hasUnpaidOrders = true;
			}
		}
		if (hasUnpaidOrders == false) {
			placeService.updatePlaceUserId(placeId, principal);
		}
		return "redirect:/admin/adminOrder"+buildParams(pageable, filter);
	}

	private String buildParams(Pageable pageable, OrderFilter filter) {
		StringBuilder buffer = new StringBuilder();		
		buffer.append("?page=");
		if(!(service.findAll(pageable, filter).hasContent())) 
			buffer.append(String.valueOf(pageable.getPageNumber()));
		else {
			buffer.append(String.valueOf(pageable.getPageNumber()+1));
		}
		buffer.append("&size=");
		buffer.append(String.valueOf(pageable.getPageSize()));
		if(pageable.getSort()!=null){
			buffer.append("&sort=");
			Sort sort = pageable.getSort();
			sort.forEach((order)->{
				buffer.append(order.getProperty());
				if(order.getDirection()!=Direction.ASC)
				buffer.append(",desc");
			});
		}
		return buffer.toString();
	}
}