package com.example.demo_crud.controller;

import com.example.demo_crud.entity.User;
import com.example.demo_crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping({"/","/index"})
    public String index(Model model) {
        model.addAttribute("usersList", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/add_user")
    public String add(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "add_user";
    }

    @PostMapping("/add_user")
    public String save_add(@ModelAttribute("user") User user, Model model){
        if(userService.getUserByUsername(user.getUsername()) == null) {
            user.setPassword(user.getPassword());
            user.setRole(user.getRole());
            userService.saveUser(user);
        }
        else{
            model.addAttribute("user",user);
            model.addAttribute("alert","username existed");
            return "add_user";
        }
        return "redirect:/";
    }
    @GetMapping("/update_user/{id}")
    public String update(Model model,@PathVariable(name = "id") int id){
        User user = userService.getUserById(id);
        model.addAttribute("user",user);
        return "update_user";
    }

    @PostMapping("/update_user")
    public String save_update(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/";
    }
    @RequestMapping("/delete_user/{id}")
    public String deleteUser(@PathVariable(name = "id") int id){
        userService.deleteUser(id);
        return "redirect:/";
    }
}
