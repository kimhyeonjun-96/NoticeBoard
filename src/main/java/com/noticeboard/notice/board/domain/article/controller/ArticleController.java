package com.noticeboard.notice.board.domain.article.controller;


import com.noticeboard.notice.board.domain.article.dto.ArticleDTO;
import com.noticeboard.notice.board.domain.article.service.ArticleService;
import com.noticeboard.notice.board.domain.member.dto.MemberDTO;
import com.noticeboard.notice.board.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final MemberService memberService;

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
    public String writeNewArticle(@PathVariable("id") Long memberId, @ModelAttribute("articleDto") ArticleDTO articleDTO) {

        articleService.saveNewArticle(memberId, articleDTO);
        return "redirect:/members/mypage";
    }

    /**
     * 글 수정 폼으로 이동
     */
    @GetMapping("/articles/{id}/update")
    public String createUpdateArticle(@PathVariable("id")Long memberId, @RequestParam("articleId")Long articleId, Model model) {

        model.addAttribute("articleDTO", articleService.findArticleDTOById(articleId));
        return "articles/updateArticleForm";
    }

    /**
     * 글 수정
     */
    @PostMapping("/articles/{id}/update")
    public String updateArticle(@PathVariable("id")Long memberId, @ModelAttribute("articleDTO")ArticleDTO articleDTO) {

        articleService.updateArtile(articleDTO);
        MemberDTO byOneMember = memberService.findByOneMember(SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/members/mypage";
    }

    /**
     * 글 삭제
     */
    @PostMapping("/articles/{id}/remove")
    public String removeArticle(@PathVariable("id") Long memberId, @RequestParam("articleId")Long articleId) {

        articleService.removeArticle(memberId, articleId);
        return "redirect:/members/mypage";
    }
}
