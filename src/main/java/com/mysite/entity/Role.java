package com.mysite.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity(name = "T_ROLE")
public class Role implements Auditable, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(nullable = false, length = 16)
    private String roleName;

    @Column(length = 256)
    private String description;

    @Column(nullable = false)
    private Long createBy;

    @Column(nullable = false)
    private LocalDateTime createDatetime;

    @Column(nullable = false)
    private Long updateBy;

    @Column(nullable = false)
    private LocalDateTime updateDatetime;

    @ManyToMany(fetch = FetchType.EAGER) // can't lazy-load here
    @JoinTable(name = "T_ROLE_RESOURCE_MAPPING", joinColumns = @JoinColumn(name = "ROLE_ID"), inverseJoinColumns = @JoinColumn(name = "RESOURCE_ID"))
    private Set<Resource> resources = new HashSet<>();
}
