package com.spring.codeblog.utils;

import com.spring.codeblog.model.Post;
import com.spring.codeblog.repository.CodeblogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DummyData {

    @Autowired
    CodeblogRepository codeblogRepository;

//    @PostConstruct
    public void savePost(){
        List<Post> postList = new ArrayList<>();

        Post post1 = new Post();
        post1.setAutor("Pri");
        post1.setData(LocalDate.now());
        post1.setTitulo("Docker");
        post1.setTexto("Teste teste");


        Post post2 = new Post();
        post2.setAutor("Pri");
        post2.setData(LocalDate.now());
        post2.setTitulo("Docker");
        post2.setTexto("Teste teste");

        postList.add(post1);
        postList.add(post2);

        codeblogRepository.saveAll(Arrays.asList(post1, post2));


    }
}
