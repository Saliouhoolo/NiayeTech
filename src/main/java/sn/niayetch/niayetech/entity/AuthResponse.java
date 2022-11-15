package sn.niayetch.niayetech.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sn.niayetch.niayetech.entity.dto.UserDto;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthResponse {
    private UserDto user;
    private  String token;

}
