package vttp.batch5.ssf.noticeboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import vttp.batch5.ssf.noticeboard.models.Notice;
import vttp.batch5.ssf.noticeboard.models.notice;
import vttp.batch5.ssf.noticeboard.services.NoticeService;

// Use this class to write your request handlers


@Controller
@RequestMapping("")
public class NoticeController {
    @Autowired
    NoticeService noticeService;



    @GetMapping("")
    public String landingPage(Model model){
        System.out.println("landing page accessed");
        Notice notice = new Notice();
        model.addAttribute("notice", notice);
        return "notice";
    }


    @PostMapping("/notice")
    public String getForm(@Valid @ModelAttribute("notice") Notice notice, BindingResult bindingResult, Model model){
        System.out.println("notice recieved:" + notice.toString());
        if(bindingResult.hasErrors()){
            System.out.println("notice not accepted for binding errors");
            return "notice";
        }
        
        noticeService.postToNoticeServer(notice);
        //Change this to an error page or success page
        return "notice"; //change this to the next page!!

    }

}
