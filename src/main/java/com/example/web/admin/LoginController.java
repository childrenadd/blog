package com.example.web.admin;

import com.example.po.User;
import com.example.service.LoginService;
import com.example.service.UserService;
import com.example.util.LoginParam;
import com.example.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }
    @PostMapping("/login")
    public ModelAndView login(@RequestParam String username, @RequestParam String password, HttpSession session,
                        RedirectAttributes attributes){
        ModelAndView modelAndView = new ModelAndView();
        LoginParam loginParam = new LoginParam();
        loginParam.setUsername(username);
        loginParam.setPassword(password);
        Result result = loginService.login(loginParam);
        if(result.getCode().equals(200)){
            session.setAttribute("user",result.getUser());
            modelAndView.addObject("token","TOKEN_"+(String)result.getData());
            modelAndView.setViewName("admin/index");
        }/*else{
            attributes.addFlashAttribute("message","用户名和密码错误");
            modelAndView.addObject("model","new ModelAttribute()");
            modelAndView.setViewName("redirect:/admin");
        }*/
        /*Set<String> token = redisTemplate.keys("TOKEN*");
        System.out.println("ddddddddddddddddddddd"+token);*/
        return modelAndView;
    }
    @GetMapping("/hello")
    @ResponseBody
    public Map hello(){
        Map param= new HashMap();
        param.put("token","123");
        return param;
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
