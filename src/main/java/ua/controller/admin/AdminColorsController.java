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

import ua.entity.Color;
import ua.model.filter.SimpleFilter;
import ua.service.ColorService;
import ua.validation.flag.ColorFlag;

@Controller
@RequestMapping("/admin/adminColors")
@SessionAttributes("color")
public class AdminColorsController {

    private final ColorService colorService;

    private String error = "";

    @Autowired
    public AdminColorsController(ColorService colorService) {
        this.colorService = colorService;
    }

    @ModelAttribute("color")
    public Color getForm() {
        return new Color();
    }

    @ModelAttribute("filter")
    public SimpleFilter getFilter() {
        return new SimpleFilter();
    }

    /**
     * Show Colors page
     */
    @GetMapping
    public String showColors(Model model, @PageableDefault Pageable pageable,
                             @ModelAttribute("filter") SimpleFilter filter) {
        model.addAttribute("showColors", colorService.findAll(pageable, filter));
        model.addAttribute("error", error);
        error = "";
        if (colorService.findAll(pageable, filter).hasContent() || pageable.getPageNumber() == 0)
            return "adminColors";
        else
            return "redirect:/admin/adminColors" + buildParams(pageable, filter);
    }

    /**
     * Deleting Color
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, @PageableDefault Pageable pageable,
                         @ModelAttribute("filter") SimpleFilter filter) {
        colorService.delete(id);
        return "redirect:/admin/adminColors" + buildParams(pageable, filter);
    }

    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public String databaseError() {
        error = "You can't delete this color because it is used!";
        return "redirect:/admin/adminColors";
    }

    @PostMapping
    public String save(@ModelAttribute("color") @Validated(ColorFlag.class) Color color, BindingResult br,
                       Model model, SessionStatus status, @PageableDefault Pageable pageable,
                       @ModelAttribute("filter") SimpleFilter filter) {
        if (br.hasErrors())
            return showColors(model, pageable, filter);
        colorService.save(color);
        return cancel(status, pageable, filter);
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Integer id, Model model, @PageableDefault Pageable pageable,
                         @ModelAttribute("filter") SimpleFilter filter) {
        model.addAttribute("color", colorService.findOne(id));
        return showColors(model, pageable, filter);
    }

    @GetMapping("/cancel")
    public String cancel(SessionStatus status, @PageableDefault Pageable pageable,
                         @ModelAttribute("filter") SimpleFilter filter) {
        status.setComplete();
        return "redirect:/admin/adminColors" + buildParams(pageable, filter);
    }

    private String buildParams(Pageable pageable, SimpleFilter filter) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("?page=");
        if (!(colorService.findAll(pageable, filter).hasContent()))
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
        buffer.append(filter.getSearch());
        return buffer.toString();
    }
}