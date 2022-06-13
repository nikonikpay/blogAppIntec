package be.intec.newreserveblog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Timestamp;


@Entity
@Table
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="author_id")
    private Author author;


    @NotBlank(message = "Please give your post a title")
    @Size(min = 3, max = 40, message = "The title should be at least 3 character and at last 40.")
    private String title;

    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Please enter your content using letters")
    private String body;

    @CreationTimestamp
    private Timestamp postCreatedDate;

    @UpdateTimestamp
    private Timestamp postUpdatedDate;

}
