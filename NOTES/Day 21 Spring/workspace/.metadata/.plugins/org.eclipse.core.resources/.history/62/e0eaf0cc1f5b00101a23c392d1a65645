package training.iqgateway.controller;
import javax.validation.Valid;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
   
@Controller
public class EmployeeController {  
       
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String showForm(Model theModel) {  
        theModel.addAttribute("employee", new Employee());
        return "portal";
    }  
       
    @RequestMapping("/welcome")
    public String processForm(@Valid @ModelAttribute("employee") Employee employee, BindingResult result) { 
    	System.out.println(result);
        if (result.hasErrors()) {
            return "portal";
        }  
        else {  
            return "welcome";
        }  
    }  
}