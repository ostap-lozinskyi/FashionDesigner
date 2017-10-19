package ua.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.entity.Place;
import ua.model.filter.OrderFilter;
import ua.model.request.OrderRequest;
import ua.service.OrderService;
import ua.validation.flag.OrderFlag;

@Controller
@RequestMapping("/place/{id}/order")
@SessionAttributes("order2")
public class IdOrderController {

	private final OrderService service;

	@Autowired
	public IdOrderController(OrderService service) {
		this.service = service;
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
	public String show(@PathVariable Integer id, Model model, @PageableDefault Pageable pageable, 
			@ModelAttribute("orderFilter") OrderFilter filter) {
		model.addAttribute("meals", service.findAllMealsNames());
		model.addAttribute("orders", service.findOrderViewsForTable(id));
		
		model.addAttribute("placeCurrent", service.findPlaceViewById(id));
		return "idOrder";
	}

	@PostMapping
	public String save(@PathVariable Integer id, @ModelAttribute("order") @Validated(OrderFlag.class) OrderRequest request,
			BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable,
			@ModelAttribute("orderFilter") OrderFilter filter, Principal principal) {
		if (principal == null) {
			return "redirect:/login";
		}
		Place place=new Place();
		place.setId(id);
		request.setPlace(place);
		request.setStatus("Accepted");
		if (!request.getMeals().isEmpty())
			service.saveOrder(request, principal);
		return "redirect:/place/{id}/order";
	}

}