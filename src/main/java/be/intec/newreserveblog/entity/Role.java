package be.intec.newreserveblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45)
    private String name;

    @ManyToMany( mappedBy = "roles")
    private Set<Author> authors = new HashSet<>();

    public void addUser(Author author) {
        this.authors.add(author);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
