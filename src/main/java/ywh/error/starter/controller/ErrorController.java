package ywh.error.starter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ywh.error.starter.model.Error;
import ywh.error.starter.service.ErrorService;

import java.sql.SQLException;
import java.util.Collection;

@Controller
@RequestMapping("ywh")
public class ErrorController {

    @Autowired
    private ErrorService errorService;

    @RequestMapping("/index")
    public String selectErrors(Model model) throws SQLException {
        Collection<Error> errors = errorService.listErrors();
        model.addAttribute("errors",errors);
        return "error";
    }

    @RequestMapping("/deleteError/{id}")
    public String deleteError(@PathVariable("id") Integer id, Model model) throws SQLException {
        try {
            errorService.deleteError(id);
        }catch (Exception e){
            return "errordata";
        }finally {
            Collection<Error> errors = errorService.listErrors();
            model.addAttribute("errors",errors);
        }
        return "error";
    }

    @RequestMapping("/getError/{id}")
    public String getError(@PathVariable("id") Integer id,Model model) throws SQLException {
        Error error = errorService.getError(id);
        model.addAttribute("error",error);
        return "geterror";
    }
}
