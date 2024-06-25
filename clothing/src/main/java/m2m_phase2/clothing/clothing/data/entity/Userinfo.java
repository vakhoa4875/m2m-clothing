package m2m_phase2.clothing.clothing.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "Userinfo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Userinfo implements Serializable {

    private static final long serialVersionUID = 6023540828358092434L;

    @Id
    @Column(name = "user_id")
    private int userId;

    @Column(name = "fullname", length = 127)
    private String fullname;

    @Column(name = "gender", length = 20)
    private String gender;

    @Column(name = "avatar", length = 255)
    private String avatar;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "description", length = 300)
    private String description;

    @Column(name = "job_title", length = 63)
    private String jobTitle;

    @OneToOne
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private Account account;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (((Integer) userId == null) ? 0 : ((Integer) userId).hashCode());
        result = prime * result + ((fullname == null) ? 0 : fullname.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Userinfo{" +
                "userId=" + userId +
                ", fullname='" + fullname + '\'' +
                ", gender='" + gender + '\'' +
                ", avatar='" + avatar + '\'' +
                ", dob=" + dob +
                ", description='" + description + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                '}';
    }
}
