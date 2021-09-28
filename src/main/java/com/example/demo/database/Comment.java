package com.example.demo.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "text")
    private String message;

    @Column(columnDefinition = "bigint")
    private Instant time;

    /**
     * Itt a @ManyToOne-ban azért nincs konfiguráció, mert elegendő az egyik irány. Itt a @ManyToOne és a User típus szerint megfogja találni.
     * Nem látszik, de ő: "FetchType fetch() default EAGER;" Azaz már a findById(int) hivatkozáskor, mikor lekérjük a rekordot, "egy levegővel" betölti a User objektumot is.
     * Azaz User-re való hivatkozáskor NEM generál további SQL-t, hanem már memóriában tárolja.
     *
     * Mivel ő egy idegen kulcs, tehát egy külön "adat oszlop" az adatbázisban, így szükséges a @JoinColumn.
     *
     * A @JsonIgnore NEM JPA/Hibernate annotáció, csak JSON-re való alakítás esetén figyelmen kívül lesz hagyva.
     */
    @ManyToOne
    @JoinColumn
//    @JsonIgnore
    private User user;

    /**
     * ManyToMany:
     */
//    @ManyToMany
//    @JoinTable(
//            name = "Track_Provider",
//            joinColumns = {@JoinColumn(name = "a_id")},
//            inverseJoinColumns = {@JoinColumn(name = "b_id")}
//    )
//    private List<Many2> manyToManyExample;
}
