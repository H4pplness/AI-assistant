package dongpb.learningeverything.chatassistantservice.database.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "resources")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceEntity extends BaseEntity{
    @Id
    @Column(unique = true)
    String resourceCode;

    String resourceName;
    String description;
}
