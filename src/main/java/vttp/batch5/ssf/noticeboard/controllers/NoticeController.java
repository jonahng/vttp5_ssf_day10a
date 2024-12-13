package vttp.batch5.ssf.noticeboard.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp.batch5.ssf.noticeboard.models.Notice;
import vttp.batch5.ssf.noticeboard.models.notice;

// Use this class to write your request handlers


@Controller
@RequestMapping("")
public class NoticeController {


    @GetMapping("")
    public String landingPage(Model model){
        System.out.println("landing page accessed");
        Notice notice = new Notice();
        model.addAttribute("notice", notice);
        return "notice";
    }

}
