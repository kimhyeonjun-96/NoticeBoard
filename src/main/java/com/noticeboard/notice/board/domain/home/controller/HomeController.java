package com.noticeboard.notice.board.domain.home.controller;

import com.noticeboard.notice.board.domain.article.dto.ArticleDTO;
import com.noticeboard.notice.board.domain.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ArticleService articleService;

    @GetMapping("/")
    public String home(Model model, @PageableDefault(size = 10) Pageable pageable) {

        Page<ArticleDTO> allArticles = articleService.getAllArticles(pageable);
        model.addAttribute("allArticles", allArticles);
        return "home";
    }

}
