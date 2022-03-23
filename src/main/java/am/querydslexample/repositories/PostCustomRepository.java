package am.querydslexample.repositories;

import am.querydslexample.dto.PostFilter;
import am.querydslexample.model.Post;

import java.util.List;

public interface PostCustomRepository {
    List<Post> findByFilter(String text,String field);
}
