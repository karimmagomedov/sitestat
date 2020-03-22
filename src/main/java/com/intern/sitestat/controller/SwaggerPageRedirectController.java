package com.intern.sitestat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore(value = "Don't need it in documentation")
@Controller
public class SwaggerPageRedirectController {

    @GetMapping("/")
    public String redirectFromStartPage() {
        return "redirect:swagger-ui.html";
    }
}
