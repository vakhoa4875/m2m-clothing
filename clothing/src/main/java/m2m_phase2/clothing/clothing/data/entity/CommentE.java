package m2m_phase2.clothing.clothing.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Comment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentE implements Serializable {
    private static final long serialVersionUID = 2859623393838379704L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int commentId;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserE user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    private Date createDate;
}
