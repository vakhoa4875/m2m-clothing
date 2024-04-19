//package m2m_phase2.clothing.clothing.data.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//import m2m_phase2.clothing.clothing.entity.Account;
//import m2m_phase2.clothing.clothing.entity.Product;
//
//import java.io.Serializable;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "Comment")
//@Data
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class Comment implements Serializable {
//    private static final long serialVersionUID = 2859623393838379704L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "comment_id")
//    private int commentId;
//
//    @Column(name = "comment")
//    private String comment;
//
//    @Column(name = "product_id")
//    private int productId;
//
//    @Column(name = "user_id")
//    private int userId;
//
//    @Column(name = "create_date")
//    private LocalDateTime createDate;
//
//    @ManyToOne
//    private Product product;
//
//    @ManyToOne
//    private UserE user_id;
//
//
//    @PrePersist
//    protected void onPrePersist() {
//        createDate = LocalDateTime.now();
//    }
//}
