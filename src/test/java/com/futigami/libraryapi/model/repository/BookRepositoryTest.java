package com.futigami.libraryapi.model.repository;

import com.futigami.libraryapi.model.entity.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    BookRepository repository;

    @Test
    @DisplayName("Retorna verdadeiro quando existir um livro na base e um isbn válido")
    public void returnTrueWhenIsbnExists(){
        String isbn = "123";
        Book book = Book.builder()
                .title("Aventuras")
                .author("Fulano")
                .isbn(isbn)
                .build();
        entityManager.persist(book);

        boolean exists = repository.existsByIsbn(isbn);

        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Retorna false quando não existir um livro na base e um isbn válido")
    public void returnFalseWhenIsbnDoesntExists(){
        String isbn = "123";

        boolean exists = repository.existsByIsbn(isbn);

        assertThat(exists).isFalse();
    }
}
