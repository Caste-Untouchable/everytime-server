package com.untouchable.everytime.Entity;

import com.untouchable.everytime.User.Entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSchedules {
    @Id
    @OneToOne
    User user;

    @OneToMany
    ArrayList<UserSchedule> userSchedule;
}
