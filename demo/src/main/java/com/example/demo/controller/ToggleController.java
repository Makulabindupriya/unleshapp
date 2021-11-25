package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.VariableReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.component.ToggleComponent;

import no.finn.unleash.UnleashContext;

@Controller
//@RequestMapping("/project")
public class ToggleController {

	@Autowired
	private ToggleComponent toggleComponent;
	
	@GetMapping("toggle/default")
	public Boolean getToggleStatus() {
		System.out.println("con.method()");
		return toggleComponent.isDefaultToggleActive("Tester-Toggle");
	}
	
	@GetMapping("/toggle/userId")
	public boolean getDefaultToggleStatus() {
		UnleashContext context = UnleashContext.builder().userId("bindu").build();
		return toggleComponent.isUserIdToggleActive("demo-userId", context);
	}
	
	@GetMapping("/toggle/gradual")
	public boolean getUserIdToggleStatus() {
		UnleashContext context = UnleashContext.builder().userId("bindu").build();
		return toggleComponent.isGradualToggleActive("demo-gradual", context);
	}
	
	@GetMapping("toggle/features/{toggleName}")
	public String getToggleStatus(@PathVariable String toggleName) {
		System.out.println("In getToggleStatus(), pathVariable: " + toggleName);
		
		return "Toggle Name: " + toggleName + ", Status: " + toggleComponent.isDefaultToggleActive(toggleName);
	}
	
	
	@GetMapping("/toggle/beta")
	public boolean getBetaToggleStatus() {
		UnleashContext context = UnleashContext.builder().userId("bindu").build();
		return toggleComponent.isBetaToggleActive("demo-beta", context);
	}
	
	
	@RequestMapping("/window")
	public ModelAndView welcome() {
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("welcome.html");
	    return modelAndView;
	}
	
		
	@GetMapping("/")
	public String Welcome(Model model) { 
		boolean betaValue = toggleComponent.isDefaultToggleActive("demo-beta");
		boolean documentationValue = toggleComponent.isDefaultToggleActive("documentation");
		boolean demoValue = toggleComponent.isDefaultToggleActive("demonstration");
		boolean contactValue = toggleComponent.isDefaultToggleActive("contact");
		boolean toggleImg = toggleComponent.isDefaultToggleActive("toggleImg");
		
		model.addAttribute("Beta", betaValue);
		model.addAttribute("Documentation", documentationValue);
		model.addAttribute("Contact", contactValue);
		model.addAttribute("Demo", demoValue);
		model.addAttribute("ToggleImg", toggleImg);
		
		return "dashboard.html";
		
	}

	
	
	
	
}
