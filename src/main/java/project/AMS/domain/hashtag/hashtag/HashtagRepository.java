package project.AMS.domain.hashtag.hashtag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    public Hashtag save(Hashtag hashtag);
    public Optional<Hashtag> findById(Long hashtagId);
    public Optional<Hashtag> findByName(String name);

}
