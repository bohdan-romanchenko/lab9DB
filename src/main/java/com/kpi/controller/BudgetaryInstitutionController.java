package com.kpi.controller;

import com.kpi.model.BudgetaryInstitution;
import com.kpi.service.BudgetaryInstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
@RequestMapping(value = "/budgetaryinstitution")
public class BudgetaryInstitutionController {

    @Autowired
    BudgetaryInstitutionService budgetaryInstitutionService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ModelAndView get(ModelAndView model) {
        model.setViewName("budgetaryinstitutionlist");
        model.addObject("budgetaryInstitutions", budgetaryInstitutionService.getAll());
        return model;
    }

    @RequestMapping(value = "/delete")
    public ModelAndView delete(@RequestParam Integer id, ModelAndView model) {
        try {
            budgetaryInstitutionService.delete(id);
        } catch (SQLException e) {
            model.addObject("exception", e.getMessage());
            e.printStackTrace();
        }
        return get(model);
    }

    @RequestMapping(value = "/preUpdate")
    public ModelAndView preUpdate(@RequestParam Integer id, ModelAndView model) {
        BudgetaryInstitution budgetaryInstitution = budgetaryInstitutionService.getById(id);
        model.setViewName("budgetaryinstitution");
        model.addObject("budgetaryInstitution", budgetaryInstitution);
        model.addObject("operation", "update");
        return model;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(BudgetaryInstitution budgetaryInstitution, ModelAndView model) {
        try {
            budgetaryInstitutionService.update(budgetaryInstitution);
        } catch (SQLException e) {
            model.addObject("exception", e.getMessage());
            e.printStackTrace();
        }
        return get(model);
    }

    @RequestMapping(value = "/preInsert")
    public ModelAndView preInsert(ModelAndView model) throws SQLException {
        model.setViewName("budgetaryinstitution");
        BudgetaryInstitution budgetaryInstitution = new BudgetaryInstitution();
        budgetaryInstitution.setId(budgetaryInstitutionService.getMaxId() + 1);
        model.addObject("budgetaryInstitution", budgetaryInstitution);
        model.addObject("operation", "insert");
        return model;
    }

    @RequestMapping(value = "/insert")
    public ModelAndView insert(BudgetaryInstitution budgetaryInstitution, ModelAndView model) {
        try {
            budgetaryInstitutionService.insert(budgetaryInstitution);
        } catch (SQLException e) {
            model.addObject("exception", e.getMessage());
            e.printStackTrace();
        }
        return get(model);
    }
}
