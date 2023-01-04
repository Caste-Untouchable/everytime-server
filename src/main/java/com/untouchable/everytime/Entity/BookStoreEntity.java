package com.untouchable.everytime.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class BookStoreEntity {

    @Id
    Long BookStore_PK;

    @Column
    Long price;
    int bookStatus;
    String location;
    int dealing;

    @ManyToOne
    UserEntity seller;

}
