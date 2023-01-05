package com.untouchable.everytime.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookStoreDTO {

    Long bookStore_PK;

    Long price;
    int bookStatus;
    String location;
    int dealing;

    Long user_PK;

}
