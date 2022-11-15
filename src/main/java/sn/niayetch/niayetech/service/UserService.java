package sn.niayetch.niayetech.service;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import sn.niayetch.niayetech.entity.User;
import sn.niayetch.niayetech.exception.ResourceNotFoundException;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> getUsers();

     User getUser(@PathVariable(value="id") Long userId) throws ResourceNotFoundException;

     User updateUser(@PathVariable(value="id") Long userId, @RequestBody User userDetails);

    Map<String ,String>  createUser(@RequestBody User user) throws ValidationException;
    Map<String ,String> resetPassword(@RequestBody String email) throws ValidationException, ResourceNotFoundException;

    Map<String ,Boolean> deleteUser(@PathVariable(value="id") Long userId);
    Map<String ,String>  changeEmail(@RequestBody String email);
    Map<String, String> changePassword(String password, String newPassword);


}
