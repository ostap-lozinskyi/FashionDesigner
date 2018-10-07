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

import ua.entity.SectionOfClothes;
import ua.model.filter.SimpleFilter;
import ua.service.SectionOfClothesService;
import ua.validation.flag.SectionOfClothesFlag;

@Controller
@RequestMapping("/admin/adminSectionOfClothes")
@SessionAttributes("sectionOfClothes")
public class AdminSectionOfClothesController {

    private final SectionOfClothesService sectionOfClothesService;

    String error = "";

    @Autowired
    public AdminSectionOfClothesController(SectionOfClothesService sectionOfClothesService) {
        this.sectionOfClothesService = sectionOfClothesService;
    }

    @ModelAttribute("sectionOfClothes")
    public SectionOfClothes getForm() {
        return new SectionOfClothes();
    }

    @ModelAttribute("simpleFilter")
    public SimpleFilter getFilter() {
        return new SimpleFilter();
    }

    /**
     * Show SectionOfClothes page
     */
    @GetMapping
    public String showSectionOfClothes(Model model, @PageableDefault Pageable pageable, @ModelAttribute("simpleFilter") SimpleFilter simpleFilter) {
        model.addAttribute("showSectionsOfClothes", sectionOfClothesService.findAll(pageable, simpleFilter));
        model.addAttribute("error", error);
        error = "";
        if (sectionOfClothesService.findAll(pageable, simpleFilter).hasContent() || pageable.getPageNumber() == 0)
            return "adminSectionOfClothes";
        else
            return "redirect:/admin/adminSectionOfClothes" + buildParams(pageable, simpleFilter);
    }

    /**
     * Deleting TypeOfClothes
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, @PageableDefault Pageable pageable,
                         @ModelAttribute("simpleFilter") SimpleFilter simpleFilter) {
        sectionOfClothesService.delete(id);
        return "redirect:/admin/adminSectionOfClothes" + buildParams(pageable, simpleFilter);
    }

    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public String databaseError() {
        error = "You can't delete this SectionOfClothes because it is used!";
        return "redirect:/admin/adminSectionOfClothes";
    }

    @PostMapping
    public String save(@ModelAttribute("sectionOfClothes") @Validated(SectionOfClothesFlag.class) SectionOfClothes sectionOfClothes, BindingResult br,
                       Model model, SessionStatus status, @PageableDefault Pageable pageable,
                       @ModelAttribute("simpleFilter") SimpleFilter simpleFilter) {
        if (br.hasErrors())
            return showSectionOfClothes(model, pageable, simpleFilter);
        sectionOfClothesService.save(sectionOfClothes);
        return cancel(status, pageable, simpleFilter);
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Integer id, Model model, @PageableDefault Pageable pageable,
                         @ModelAttribute("simpleFilter") SimpleFilter simpleFilter) {
        model.addAttribute("sectionOfClothes", sectionOfClothesService.findOne(id));
        return showSectionOfClothes(model, pageable, simpleFilter);
    }

    @GetMapping("/cancel")
    public String cancel(SessionStatus status, @PageableDefault Pageable pageable,
                         @ModelAttribute("simpleFilter") SimpleFilter simpleFilter) {
        status.setComplete();
        return "redirect:/admin/adminSectionOfClothes" + buildParams(pageable, simpleFilter);
    }

    private String buildParams(Pageable pageable, SimpleFilter simpleFilter) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("?page=");
        if (!(sectionOfClothesService.findAll(pageable, simpleFilter).hasContent()))
            buffer.append(String.valueOf(pageable.getPageNumber()));
        else {
            buffer.append(String.valueOf(pageable.getPageNumber()));
        }
        buffer.append("&size=");
        buffer.append(String.valueOf(pageable.getPageSize()));
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