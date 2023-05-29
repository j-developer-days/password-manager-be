package com.jdev.passwordManager.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.ZonedDateTime;

@NoArgsConstructor
@Data

@Entity
@Table(name = "t_accounts")
public class AccountEntity {

    @Id
    @GeneratedValue(generator = "t_accounts__sequence-generator")
    @SequenceGenerator(name = "t_accounts__sequence-generator", sequenceName = "t_accounts_id_seq",
            allocationSize = 1)
    private Integer id;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "account_url")
    private String accountURL;

    @Column(name = "account_password")
    private String accountPassword;

    @CreatedDate
    @Column(name = "creation_datetime")
    private ZonedDateTime creationZonedDateTime;

    @JoinColumn(name = "group_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private GroupAccountEntity groupAccountEntity;

}
