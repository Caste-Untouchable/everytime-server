package com.untouchable.everytime.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserFriendShipDTO {

   Long userFriendshipPK;

   String user1;

   String user2;

}