package be.intec.newreserveblog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikePost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name="blogPost_id")
    private BlogPost blogPost;



    @CreationTimestamp
    private Timestamp likeCreatedDate;


    @UpdateTimestamp
    private Timestamp likeUpdatedDate;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

}
