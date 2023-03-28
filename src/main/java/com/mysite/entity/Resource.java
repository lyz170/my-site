package com.mysite.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity(name = "T_RESOURCE")
public class Resource implements Auditable, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourceId;

    @Column(nullable = false, length = 32)
    private String category;

    @Column(nullable = false, length = 8)
    private String method;

    @Column(nullable = false, length = 128)
    private String path;

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
}
