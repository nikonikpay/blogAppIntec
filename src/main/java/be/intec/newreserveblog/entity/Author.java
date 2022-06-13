package be.intec.newreserveblog.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@DynamicUpdate
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @NotBlank(message = "Please enter your first name using letters")
    @Size(min = 3, max = 20, message = "Your First name can not be less than 3 character or greater than 20")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Please enter your last name using letters")
    @Size(min = 3, max = 20, message = "Your last name can not be less than 3 character or greater than 20")
    private String lastName;

    @Column(name = "user_name", unique = true)
    @NotBlank(message = "Please enter your user name using letters")
    @Size(min = 3, max = 20, message = "Your user name can not be less than 3 character or greater than 20")
    private String userName;


    @NotBlank(message = "Please enter your password using letters")
    private String password;


    @NotBlank(message = "Please enter your email using letters")
    @Size(min = 3, max = 20, message = "Your email can not be less than 3 character or greater than 20")
    @Email(message = "Invalid Email. please enter the correct email")
    private String email;



    @NotBlank(message = "Please enter your street using letters")
    @Size(min = 3, max = 20, message = "Your street can not be less than 3 character or greater than 20")
    private String street;

    @Column(name = "house_number")
    @NotBlank(message = "Please enter your user name using letters")
    @Size(min = 3, max = 20, message = "Your user name can not be less than 3 character or greater than 20")
    private String houseNr;



    @NotBlank(message = "Please enter your user name using letters")
    @Size(min = 3, max = 20, message = "Your user name can not be less than 3 character or greater than 20")
    private String city;


    @Digits(integer = 9, fraction = 0, message = "zip code can not be more than 9 digit")
    @Positive(message = "zip code can not be a negative number")
    private int zip;

    @Column(name = "avatar_path")
    private String avatarPath;

    @CreationTimestamp
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp updatedDate;


    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
               joinColumns = @JoinColumn(name = "author_id"),
               inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role) {
        this.roles.add(role);
    }

    //    @Column(name="blog_posts")
    //    private List<BlogPost> blogPosts;

}
