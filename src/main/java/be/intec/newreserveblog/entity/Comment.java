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
import java.sql.Timestamp;


@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DynamicUpdate
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="blog_post_id")
    private BlogPost blogPost;

//    @ManyToOne
//    @JoinColumn(name="author_id")
//    private Author author;

    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Please enter your content using letters")
    private String body;

    @CreationTimestamp
    private Timestamp commentCreatedDate;


    @UpdateTimestamp
    private Timestamp commentUpdatedDate;

}
