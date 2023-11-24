package com.scaler.Splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Entity(name ="SPLITWISE_USER")
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(User.class)
public class User extends BaseModel{
    private String name;
    private String phoneNumber;
    private String email;
    @ManyToMany(mappedBy = "users")
    private List<Group> groups;
}

