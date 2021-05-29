package ua.controller.admin;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.entity.TypeOfClothes;
import ua.model.filter.SimpleFilter;
import ua.service.TypeOfClothesService;
import ua.validation.flag.TypeOfClothesFlag;

@Controller
@RequestMapping("/admin/adminTypeOfClothes")
@SessionAttributes("typeOfClothes")
public class AdminTypeOfClothesController {

    public static final String REDIRECT_ADMIN_ADMIN_TYPE_OF_CLOTHES = "redirect:/admin/adminTypeOfClothes";

    private final TypeOfClothesService typeOfClothesService;

    private String error = "";

    @Autowired
    public AdminTypeOfClothesController(TypeOfClothesService typeOfClothesService) {
        this.typeOfClothesService = typeOfClothesService;
    }

    @ModelAttribute("typeOfClothes")
    public TypeOfClothes getForm() {
        return new TypeOfClothes();
    }

    @ModelAttribute("simpleFilter")
    public SimpleFilter getFilter() {
        return new SimpleFilter();
    }

    /**
     * Show TypeOfClothes page
     */
    @GetMapping
    public String showTypeOfClothes(Model model, @PageableDefault Pageable pageable, @ModelAttribute("simpleFilter") SimpleFilter simpleFilter) {
        model.addAttribute("showTypesOfClothes", typeOfClothesService.findAll(pageable, simpleFilter));
        model.addAttribute("error", error);
        error = "";
        if (typeOfClothesService.findAll(pageable, simpleFilter).hasContent() || pageable.getPageNumber() == 0)
            return "adminTypeOfClothes";
        else
            return REDIRECT_ADMIN_ADMIN_TYPE_OF_CLOTHES + buildParams(pageable, simpleFilter);
    }

    /**
     * Deleting TypeOfClothes
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, @PageableDefault Pageable pageable,
                         @ModelAttribute("simpleFilter") SimpleFilter simpleFilter) {
        typeOfClothesService.delete(id);
        return REDIRECT_ADMIN_ADMIN_TYPE_OF_CLOTHES + buildParams(pageable, simpleFilter);
    }

    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public String databaseError() {
        error = "You can't delete this TypeOfClothes because it is used!";
        return REDIRECT_ADMIN_ADMIN_TYPE_OF_CLOTHES;
    }

    @PostMapping
    public String save(@ModelAttribute("typeOfClothes") @Validated(TypeOfClothesFlag.class) TypeOfClothes typeOfClothes,
                       BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable,
                       @ModelAttribute("simpleFilter") SimpleFilter simpleFilter) {
        if (br.hasErrors())
            return showTypeOfClothes(model, pageable, simpleFilter);
        typeOfClothesService.save(typeOfClothes);
        return cancel(status, pageable, simpleFilter);
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Integer id, Model model, @PageableDefault Pageable pageable,
                         @ModelAttribute("simpleFilter") SimpleFilter simpleFilter) {
        model.addAttribute("typeOfClothes", typeOfClothesService.findOne(id));
        return showTypeOfClothes(model, pageable, simpleFilter);
    }

    @GetMapping("/cancel")
    public String cancel(SessionStatus status, @PageableDefault Pageable pageable,
                         @ModelAttribute("simpleFilter") SimpleFilter simpleFilter) {
        status.setComplete();
        return REDIRECT_ADMIN_ADMIN_TYPE_OF_CLOTHES + buildParams(pageable, simpleFilter);
    }

    private String buildParams(Pageable pageable, SimpleFilter simpleFilter) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("?page=");
        if (!(typeOfClothesService.findAll(pageable, simpleFilter).hasContent()))
            buffer.append(pageable.getPageNumber());
        else {
            buffer.append(pageable.getPageNumber());
        }
        buffer.append("&size=");
        buffer.append(pageable.getPageSize());
        if (pageable.getSort() != null) {
            buffer.append("&sort=");
            Sort sort = pageable.getSort();
            sort.forEach(order -> {
                buffer.append(order.getProperty());
                if (order.getDirection() != Direction.ASC)
                    buffer.append(",desc");
            });
        }
        buffer.append("&search=");
        buffer.append(simpleFilter.getSearch());
        return buffer.toString();
    }
}