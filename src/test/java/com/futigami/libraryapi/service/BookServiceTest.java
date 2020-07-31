package com.futigami.libraryapi.service;

import com.futigami.libraryapi.model.entity.Book;
import com.futigami.libraryapi.model.repository.BookRepository;
import com.futigami.libraryapi.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class BookServiceTest {

    BookService service;

    @MockBean
    BookRepository repository;

    @BeforeEach
    public void setUp(){

        this.service = new BookServiceImpl(repository);
    }

    @Test
    @DisplayName("Salva um livro")
    public void saveBookTest(){

        Book book = Book.builder()
                .isbn("123")
                .author("D")
                .title("A aventura").build();
        Mockito.when(repository.save(book))
                .thenReturn(Book.builder()
                .id(1l)
                .isbn("123").title("A aventura").author("D")
                .build());

        Book savedBook = service.save(book);

        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getIsbn()).isEqualTo("123");
        assertThat(savedBook.getTitle()).isEqualTo("A aventura");
        assertThat(savedBook.getAuthor()).isEqualTo("D");

    }
}
