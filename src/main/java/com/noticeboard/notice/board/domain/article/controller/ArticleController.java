package com.noticeboard.notice.board.domain.article.controller;


import com.noticeboard.notice.board.domain.article.dto.ArticleDTO;
import com.noticeboard.notice.board.domain.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    /**
     * 새로운 글 작성 폼으로 이동
     */
    @GetMapping("/articles/{id}/new")
    public String createWriteNewArticle(Model model) {

        model.addAttribute("articleDto", new ArticleDTO());
        return "articles/writeNewArticle";
    }

    /**
     * 새로운 글 작성
     */
    @PostMapping("/articles/{id}/new")
    public String writeNewArticle(@PathVariable("id") Long memberId, @ModelAttribute("articleDto")ArticleDTO articleDTO) {

        articleService.saveNewArticle(memberId, articleDTO);
        return "redirect:/members/mypage";
    }
}
