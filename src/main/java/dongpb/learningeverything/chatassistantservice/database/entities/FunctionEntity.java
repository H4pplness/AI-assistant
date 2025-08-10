package dongpb.learningeverything.chatassistantservice.database.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Entity(name = "functions")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FunctionEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "function_id")
    Integer functionId;

    String functionName;

    String description;

    Integer resourceId;

    String functionType;

    @JdbcTypeCode(SqlTypes.JSON)
    Map<String, Object> metadata;
}
