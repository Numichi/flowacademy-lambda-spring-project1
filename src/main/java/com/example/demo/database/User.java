package com.example.demo.database;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id // Egyedi azonosító. Kötelező az @Entity miatt.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Egyedi ID generátor, MySQL esetében "AUTO_INCREMENT"-tet definiál
    private Integer id;

    /**
     * A @Column nem kötelező, de az adott adatbázis tábla  egy oszlopa konfigurálható.
     * "username NOT NULL" és UNIQUE lesz.
     * Magyarul a String sose lehet NULL és egy táblában az adott String 1x szerepelhet.
     */
    @Column(unique = true, nullable = false)
    private String username;

    /**
     * A columnDefinition egyedi MySQL alapú típus definíciót jelent.
     */
    @Column(columnDefinition = "tinytext", nullable = false)
    private String passwd;

    /**
     * 1:N kapcsolat. Egy adott user-hez több komment tartozhat
     *
     * mappedBy-hoz a Comment class-ban lévő User típus attribútum neve. -> private User [user];
     * cascade pedig biztosítja az információ szinkronizálását. Ha törlünk egy felhasználót, akkor törli az összes kommentjét is.
     * Nem látszik, de alapértelmezetten: "FetchType fetch() default LAZY;" Lazy azt jelenti, hogy csak akkor tölti be az adatot, mikor hivatkozunk rá. Tehát egy másil SQL-ben fogja lekérni az adatokat.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> commentList;
}
