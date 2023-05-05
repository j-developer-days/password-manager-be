package com.jdev.passwordManager.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

@Entity
@Table(name = "t_group_accounts")
public class GroupAccountEntity {

    @Id
    @GeneratedValue(generator = "t_group_accounts__sequence-generator")
    @SequenceGenerator(name = "t_group_accounts__sequence-generator", sequenceName = "t_group_accounts_id_seq",
            allocationSize = 1)
    private Short id;

    @Column(name = "group_name")
    private String groupName;

    @OneToMany(mappedBy = "groupAccountEntity")
    @ToString.Exclude
    private List<AccountEntity> accountEntities;

}
