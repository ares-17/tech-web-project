package com.unina.techweb.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uid", nullable = false)
    private UUID id;

    @Size(max = 255)
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Column(name = "createdat")
    private Instant createdat;

    @OneToMany(mappedBy = "uidquiz", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Question> questions = new LinkedHashSet<>();

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "createdby", nullable = false)
    private Customer createdby;

    @NotNull
    @Column(name = "maxerrors", nullable = false)
    private Integer maxerrors;

}