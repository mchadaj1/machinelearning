package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mateusz on 04.03.16.
 */

/**
 * Kontroler serwujący aplikację użytkownikowi.
 */
@Controller
public class Welcome {
    @RequestMapping("/")
    public ModelAndView index(ModelAndView mav)
    {
        mav.setViewName("index");
        return mav;
    }
}
