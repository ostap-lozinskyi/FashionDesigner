package ua.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.Component;
import ua.model.filter.ComponentFilter;
import ua.model.request.ComponentRequest;
import ua.model.view.ComponentView;
import ua.repository.ComponentRepository;
import ua.repository.ComponentViewRepository;
import ua.repository.IngredientRepository;
import ua.repository.MsRepository;
import ua.service.ComponentService;

@Service
public class ComponentServiceImpl implements ComponentService {

	private final ComponentRepository repository;
	
	private final ComponentViewRepository componentViewrepository;
	
	private final IngredientRepository ingredientRepository;
	
	private final MsRepository msRepository;

	@Autowired
	public ComponentServiceImpl(ComponentRepository repository, IngredientRepository ingredientRepository,
			MsRepository msRepository, ComponentViewRepository componentViewrepository) {
		this.repository = repository;
		this.ingredientRepository = ingredientRepository;
		this.msRepository = msRepository;
		this.componentViewrepository = componentViewrepository;
	}

	@Override
	public List<String> findAllIngredients() {
		return ingredientRepository.findAllIngredientNames();
	}

	@Override
	public List<String> findAllMss() {
		return msRepository.findAllMsNames();
	}

	@Override
	public Page<ComponentView> findAllView(Pageable pageable, ComponentFilter filter) {
		return componentViewrepository.findAllView(filter, pageable);
	}
	
	@Override
	public void save(ComponentRequest request) {
		Component component = new Component();
		component.setAmount(new BigDecimal(request.getAmount()));
		component.setId(request.getId());
		component.setIngredient(request.getIngredient());
		component.setMs(request.getMs());
		repository.save(component);
	}

	@Override
	public ComponentRequest findOneRequest(Integer id) {
		Component component = repository.findOneRequest(id);
		ComponentRequest request = new ComponentRequest();
		request.setAmount(String.valueOf(component.getAmount()));
		request.setId(component.getId());
		request.setIngredient(component.getIngredient());
		request.setMs(component.getMs());
		return request;
	}

	@Override
	public void delete(Integer id) {
		repository.delete(id);
	}

}