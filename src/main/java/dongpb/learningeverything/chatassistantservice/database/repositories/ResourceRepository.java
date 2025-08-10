package dongpb.learningeverything.chatassistantservice.database.repositories;

import dongpb.learningeverything.chatassistantservice.database.entities.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends JpaRepository<ResourceEntity, Integer> {
    ResourceEntity findByResourceCode(String resourceCode);
}
