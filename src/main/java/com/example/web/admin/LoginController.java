package com.example.web.admin;

import com.example.po.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private UserService userService;
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.checkUser(username,password);
        if(user!=null){
            user.setPassword(null);//防止把密码传到前端
            session.setAttribute("user",user);
            return "admin/index";
        }
        else{
            attributes.addFlashAttribute("message","用户名或密码错误，请重新登录");
            //model存放的是在当前请求域中，重定向之后是另外的请求，请求重定向无法保存model中的信息
            return "redirect:/admin";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
