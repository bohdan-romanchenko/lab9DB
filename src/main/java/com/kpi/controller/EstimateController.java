package com.kpi.controller;

import com.kpi.model.Estimate;
import com.kpi.service.EstimateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
@RequestMapping(value = "/estimate")
public class EstimateController {

    @Autowired
    EstimateService estimateService;

    @RequestMapping(value = "/get")
    public ModelAndView get(ModelAndView model) {
        model.setViewName("estimatelist");
        model.addObject("estimates", estimateService.getAll());
        return model;
    }

    @RequestMapping(value = "/delete")
    public ModelAndView delete(@RequestParam Long id, ModelAndView model) {
        try {
            estimateService.delete(id);
        } catch (SQLException e) {
            model.addObject("exception", e.getMessage());
            e.printStackTrace();
        }
        return get(model);
    }

    @RequestMapping(value = "/preUpdate")
    public ModelAndView preUpdate(@RequestParam Long id, ModelAndView model) {
        Estimate estimate = estimateService.getById(id);
        model.setViewName("estimate");
        model.addObject("estimate", estimate);
        model.addObject("operation", "update");
        return model;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(Estimate estimate, ModelAndView model) {
        try {
            estimateService.update(estimate);
        } catch (SQLException e) {
            model.addObject("exception", e.getMessage());
            e.printStackTrace();
        }
        return get(model);
    }

    @RequestMapping(value = "/preInsert")
    public ModelAndView preInsert(ModelAndView model) throws SQLException {
        model.setViewName("estimate");
        Estimate estimate = new Estimate();
        estimate.setId((long) (estimateService.getMaxId() + 1));
        model.addObject("estimate", estimate);
        model.addObject("operation", "insert");
        return model;
    }

    @RequestMapping(value = "/insert")
    public ModelAndView insert(Estimate estimate, ModelAndView model) {
        try {
            estimateService.insert(estimate);
        } catch (SQLException e) {
            model.addObject("exception", e.getMessage());
            e.printStackTrace();
        }
        return get(model);
    }
}
