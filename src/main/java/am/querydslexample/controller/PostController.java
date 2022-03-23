package am.querydslexample.controller;

import am.querydslexample.model.Post;
import am.querydslexample.repositories.PostCustomRepository;
import am.querydslexample.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepository;
    private final PostCustomRepository customRepository;

    @GetMapping("/post")
    public String redirect() {
        return "posts";
    }

    @PostMapping("/post/add")
    public String add(Post post) {
        postRepository.save(post);
        return "posts";
    }

    @GetMapping("/post/all")
    public String getAll(ModelMap map) {
        List<Post> all = postRepository.findAll();
        map.addAttribute("posts", all);
        return "show-posts";
    }
    @PostMapping("/post/filter")
    public String getAllByFilter(ModelMap map, @RequestParam(name = "text") String text,
                                 @RequestParam(name = "field") String field) {
        List<Post> all = customRepository.findByFilter(text,field);
        map.addAttribute("posts", all);
        return "posts";
    }
}
