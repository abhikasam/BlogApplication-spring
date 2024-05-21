package com.example.backend.blog.repository;

import com.example.backend.blog.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IArticleRepository extends JpaRepository<Article,Integer> {
    @Query(value = "SELECT * FROM article WHERE authorid IN :authorIds", nativeQuery = true)
    List<Article> findArticlesByAuthorIds(@Param("authorIds") List<Integer> authorIDs);

    @Query(value = "Select a.* from article a " +
            "join articlecategory ac on a.articleid = ac.articleid " +
            "join category c on ac.categoryid = c.categoryid " +
            "where authorid in :authorIds and c.categoryid in :categoryIds",nativeQuery = true)
    List<Article> findArticlesByAuthorIdsAndCategoryIds(
            @Param("authorIds") int[] authorIds,
            @Param("categoryIds") int[] categoryIds);
}
