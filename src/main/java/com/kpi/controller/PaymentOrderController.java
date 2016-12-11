package com.kpi.controller;

import com.kpi.model.PaymentOrder;
import com.kpi.service.PaymentOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
@RequestMapping(value = "/paymentorder")
public class PaymentOrderController {

    @Autowired
    PaymentOrderService paymentOrderService;

    @RequestMapping(value = "/get")
    public ModelAndView get(ModelAndView model) {
        model.setViewName("paymentorderlist");
        model.addObject("paymentorders", paymentOrderService.getAll());
        return model;
    }

    @RequestMapping(value = "/delete")
    public ModelAndView delete(@RequestParam Long id, ModelAndView model) {
        try {
            paymentOrderService.delete(id);
        } catch (SQLException e) {
            model.addObject("exception", e.getMessage());
            e.printStackTrace();
        }
        return get(model);
    }

    @RequestMapping(value = "/preUpdate")
    public ModelAndView preUpdate(@RequestParam Long id, ModelAndView model) {
        PaymentOrder paymentOrder = paymentOrderService.getById(id);
        model.setViewName("paymentorder");
        model.addObject("paymentorder", paymentOrder);
        model.addObject("operation", "update");
        return model;
    }

    @RequestMapping(value = "/update")
    public ModelAndView update(PaymentOrder teacher, ModelAndView model) {
        try {
            paymentOrderService.update(teacher);
        } catch (SQLException e) {
            model.addObject("exception", e.getMessage());
            e.printStackTrace();
        }
        return get(model);
    }

    @RequestMapping(value = "/preInsert")
    public ModelAndView preInsert(ModelAndView model) throws SQLException {
        model.setViewName("paymentorder");
        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setId((long) (paymentOrderService.getMaxId() + 1));
        model.addObject("paymentorder", paymentOrder);
        model.addObject("operation", "insert");
        return model;
    }

    @RequestMapping(value = "/insert")
    public ModelAndView insert(PaymentOrder paymentOrder, ModelAndView model) {
        try {
            paymentOrderService.insert(paymentOrder);
        } catch (SQLException e) {
            model.addObject("exception", e.getMessage());
            e.printStackTrace();
        }
        return get(model);
    }
}
