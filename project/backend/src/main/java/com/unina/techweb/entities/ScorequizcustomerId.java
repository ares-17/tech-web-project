package com.unina.techweb.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Embeddable
public class ScorequizcustomerId implements Serializable {
    private static final long serialVersionUID = 5341899353871268599L;
    @NotNull
    @Column(name = "uidquiz", nullable = false)
    private UUID uidquiz;

    @NotNull
    @Column(name = "uidcustomer", nullable = false)
    private UUID uidcustomer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ScorequizcustomerId entity = (ScorequizcustomerId) o;
        return Objects.equals(this.uidquiz, entity.uidquiz) &&
                Objects.equals(this.uidcustomer, entity.uidcustomer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uidquiz, uidcustomer);
    }

}