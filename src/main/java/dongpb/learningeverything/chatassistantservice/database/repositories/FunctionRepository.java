package dongpb.learningeverything.chatassistantservice.database.repositories;

import dongpb.learningeverything.chatassistantservice.database.entities.FunctionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FunctionRepository extends JpaRepository<FunctionEntity,Integer> {
    List<FunctionEntity> findByResourceCode(String resourceCode);
}
