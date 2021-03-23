package com.simples.app.RedeSocial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simples.app.RedeSocial.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
