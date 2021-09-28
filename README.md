Teszt
=====
Több szintű tesztek vannak.
- **Unit teszt / Egység teszt**: Lényegében egy adott metórust tesztelünk. [Wiki](https://hu.wikipedia.org/wiki/Egys%C3%A9gtesztel%C3%A9s)
- **Integration test**: Unit teszt esetekor a két vagy több független elemek megfelelően működnek, de együtt hibásan.  
E célból van szükség az integrációs tesztekre. Azaz több komponens vagy csak al-komponensek együtt működését tesztelkül.

[Unit vs Integration tesztelés](https://hu.education-wiki.com/8640637-unit-test-vs-integration-test)

Kód szinten
-----------
[Spring Tutorial](https://spring.io/guides/gs/testing-web/)

Class annotációk:
- **@SpringBootTest**: | [API Doc](https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/context/SpringBootTest.html)
- **@ExtendWith({MockitoExtension.class})**: Csak akkor szükséges, mikor önállóan szeretnéd használni a Mockito-t Spring kontextus nélkül.

Attribútum annotációk:
- **@Mock**: Mockol egy elemet, és úgy viselkedik, mint egy "interface" és megadhatjuk, hogy milyen paraméterre (ha van), 
mit adjon vissza: `give(mockedElement.method(anyInt())).willReturn("value")`. Jelen példa: akármilyen integerre, egy "value" String-el térjen vissza, mert ez az elvárt működése.
- **@Spy**: Amennyivan egy `class`-ban szükségünk van (meg akarjuk tartani) az egy vagy több implementált metódusra, akkor ez az annotáció használandó.
- **@InjectMock**: Ha egy olyan `class`-t adunk meg, amely konstruktora a fentebb említett @Spy vagy @Mock típusával egyezik meg, akkor bele injektálja.
```java
class A {}
class B {
    private A a;
    public B(A a) {
        this.a = a;
    }
}

@ExtendWith({MockitoExtension.class})
class Test {
    @Mock A a;
    @InjectMocks B injected; // new B(a)
}
```
- **@SpyBean** és **@MockBean**: IoC részévé válik a mock-olt vagy a spy-olt elem. Így BÁRMI hívja majd a Spring alkalmazáson belül, akkor a mock-olt vagy spy-olt elemet fogja elérni.
- **@Captor**: Csak mockolt elemeken működik (Mock, Spy). Nevezhetjük metódus történés ellenőrzésnek is. Az ellenőrzést a `verify` metódussal ellenőrizzük. Lásd példa kódot a projektben.
FONTOS: Mivel ez egy "history" ellenőrző (mondjuk így), csak a metódus futtatás után van értelme használni. Előtte nyilván nincs hozzá még adat.
  - "Az adott paramétert kapta meg?"
  - "Az adott metódus hányszor futott le?"
  - ...

Metódus annotációk
- **@Test**: Metódusokra tesszük, hogy ő egy teszt eset
- **@Disable**: Teszt esetet nem futtatja le
- **@DisplayName**: Teszt futtatás esetén, nem metódus név jelenik meg, hanem az ide beleírt.


Hibernate
=====
[Hibernate & JPA](https://www.baeldung.com/learn-jpa-hibernate) |
[Init Load](https://www.baeldung.com/spring-boot-data-sql-and-schema-sql) |
[Default Column Values in JPA](https://www.baeldung.com/jpa-default-column-values)

Konfiguráció
------------
Maven függőség:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
```

Property beállítások:
```properties
spring.datasource.url=jdbc:mysql://<IP>:<POST>/<DB_NAME>
spring.datasource.username=<USERNAME>
spring.datasource.password=<PASSWORD>
spring.jpa.show-sql=<true|false>
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=none // @Entity alapú tábla létrehozás és/vagy törlés "create" és társai.
spring.sql.init.mode=always // Spring 2.5-től él, ez esetben futtatja a schema.sql és a data.sql-eket
```

Magyarázó kommentek: `src\main\java\com\example\demo\database\User.java` és `Comment.java` alatti class-okban található.

[ManyToMany](https://www.baeldung.com/hibernate-many-to-many) | [ManyToOne, OneToMany](https://www.baeldung.com/hibernate-one-to-many)

Érdekesség:
----------
Van olyan, hogy adatbázis migráció. [Database Migration with Spring Boot](https://thorben-janssen.com/database-migration-with-spring-boot/)


eyJhbG1hZmEiOiJrb3J0ZWZhIiwic3ViIjoiZDNkN2ZiNTItNGJiYy00YmFhLWJmZDctNjhhZmY5N2EwOWJmIiwiaWF0IjoxNjMyODE3NzQ4LCJleHAiOjE2MzI4MTc3NTJ9