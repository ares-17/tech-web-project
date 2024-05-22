package com.unina.techweb.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "scorequizcustomer")
public class Scorequizcustomer {
    @EmbeddedId
    private ScorequizcustomerId id;

    @MapsId("uidquiz")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "uidquiz", nullable = false)
    private Quiz uidquiz;

    @MapsId("uidcustomer")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "uidcustomer", nullable = false)
    private Customer uidcustomer;

    @NotNull
    @Column(name = "score", nullable = false)
    private Long score;

}