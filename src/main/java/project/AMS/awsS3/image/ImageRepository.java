package project.AMS.awsS3.image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("select l from Image l where l.article.id = :id")
    List<Image> findByArticleId(@Param("id") Long id);

}
