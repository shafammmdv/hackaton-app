package com.app.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@Entity
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Table(name = "app_user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @Column(name = "user_id")
    String userId;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "fin_code")
    String finCode;

    @OneToOne(mappedBy = "user")
    private Otp otp;

    @ManyToOne
    @JoinTable(
            name = "rel_user_with_primary_category",
            joinColumns =
                    {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns =
                    {@JoinColumn(name = "category_id", referencedColumnName = "id")})
    private Category primaryCategory;

    @ManyToOne
    @JoinTable(
            name = "rel_user_with_secondary_category",
            joinColumns =
                    {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns =
                    {@JoinColumn(name = "category_id", referencedColumnName = "id")})
    private Category secondaryCategory;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "rel_user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private List<Role> roles;
}
