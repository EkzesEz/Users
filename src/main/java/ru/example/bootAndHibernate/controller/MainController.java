package ru.example.bootAndHibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ru.example.bootAndHibernate.entity.User;
import ru.example.bootAndHibernate.repository.UserRepository;
import ru.example.bootAndHibernate.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	
	@GetMapping("//")
	public String rootHandler(){
		return "index";
	}
	
    @GetMapping("/{id}")
    public String hello(@PathVariable Long id, Model model) {
    	User user = userService.getById(id);
    	if (user == null) return "noSuchUser";
    	model.addAttribute("user", user);
    	return "showUser";
    }
    
    @GetMapping("/allUsers")
    public String showAllUsers(Model model){
    	model.addAttribute("users", userRepository.findAll());
    	return "allUsersList";
    }
    
    @GetMapping("/updateUserForm/{id}")
    public String updateUserForm(@PathVariable Long id, Model model) {
    	model.addAttribute("user", userService.getById(id));
    	return "updateUserForm";
    }
    
    @PostMapping("updateUser/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User user) {
    	userRepository.findById(id)
    	.map(oldUser -> {
    		oldUser.setAge(user.getAge());
    		oldUser.setFirstName(user.getFirstName());
    		oldUser.setSecondName(user.getSecondName());
    		return userRepository.save(oldUser);
    	})
    	.orElseThrow(() -> new IllegalArgumentException());
    	return "redirect:/" + id;
    }

    @DeleteMapping("deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
    	try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "noSuchUser";
		}
    	return "redirect:/allUsers";
    }
    
    @GetMapping("/showUser")
    public String updateUser(@ModelAttribute User user) {
    	return "redirect:" + user.getId();
    }
    
    @GetMapping("/addUser")
    public String addUserForm() {
    	return "addUserForm";
    }
    
    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user) {
    	userRepository.save(user);
    	return "redirect:" + user.getId();
    }
    
    @GetMapping("/newDefaultUser")
    public String createDefaultUser(){
    	User user = User.builder()
    			.firstName("Ivan")
    			.secondName("Ivanov")
    			.age(50)
    			.build();
    	userRepository.save(user);
    	return "redirect:allUsers";
    }
}