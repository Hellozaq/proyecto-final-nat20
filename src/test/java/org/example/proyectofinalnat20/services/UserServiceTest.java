package org.example.proyectofinalnat20.services;

import org.example.proyectofinalnat20.entity.Role;
import org.example.proyectofinalnat20.entity.User;
import org.example.proyectofinalnat20.repository.RoleRepository;
import org.example.proyectofinalnat20.repository.UserRepository;
import org.example.proyectofinalnat20.services.implementations.UserServiceImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImplementation userService;

    @Test
    void getAllUsers_whenExistUsers_returnListOfUsers() {
        //Arrange
        User user = new User();
        user.setUserId(1L);
        user.setName("Test");
        user.setPassword("Test");
        user.setRole(null);

        User user2 = new User();
        user2.setUserId(2L);
        user2.setName("Test2");
        user2.setPassword("Test2");
        user2.setRole(null);
        //Act
        when(userRepository.findAll()).thenReturn(List.of(user, user2));
        //Assert

        List<User> users = userService.findAll();
        assertEquals(2, users.size());
        assertEquals("Test", users.get(0).getName());
        assertEquals("Test2", users.get(1).getName());
    }

    @Test
    void getAllUsers_whenNoExistUsers_returnEmptyList() {
        //Arrange
        //Act
        when(userRepository.findAll()).thenReturn(List.of());
        //Assert
        List<User> users = userService.findAll();
        assertEquals(0, users.size());
    }

    @Test
    void getUserById_whenExistUser_returnUser() {
        //Arrange
        User user = new User();
        user.setUserId(1L);
        user.setName("Test");
        user.setPassword("Test");
        user.setRole(null);
        //Act
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        //Assert
        User userFound = userService.findById(1L);
        assertEquals("Test", userFound.getName());
    }

    @Test
    void getUserById_whenNoExistUser_throwsException() {
        //Arrange
        //Act
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        //Assert
        assertThrows(RuntimeException.class, () -> userService.findById(1L));
    }

    @Test
    void saveUser_whenNotExist_createUserSuccessfully() {
        // Arrange
        User user = new User();
        user.setUserId(1L);
        user.setName("Test");
        user.setEmail("test@example.com");
        user.setPassword("Test");
        user.setRole(null);

        User savedUser = new User();
        savedUser.setUserId(1L);
        savedUser.setName("Test");
        savedUser.setEmail("test@example.com");
        savedUser.setPassword("encodedPassword");
        savedUser.setRole(null);

        // Act
        when(userRepository.findByEmail("test@example.com")).thenReturn(java.util.Optional.empty());
        when(passwordEncoder.encode("Test")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Assert
        User userSaved = userService.save(user);
        assertEquals("Test", userSaved.getName());
        assertEquals("encodedPassword", userSaved.getPassword());
    }

    @Test
    void saveUser_whenExist_throwException(){
        //Arrange
        User user = new User();
        user.setUserId(1L);
        user.setName("Test");
        user.setPassword("Test");
        user.setRole(null);
        //Act
        when(userRepository.save(user)).thenThrow(new RuntimeException("User already exists"));
        //Assert
        assertThrows(RuntimeException.class, () -> userService.save(user));
    }

    @Test
    void deleteUser_whenExist_deleteUserSuccessfully(){
        //Arrange
        User user = new User();
        user.setUserId(1L);
        user.setName("Test");
        user.setPassword("Test");
        user.setRole(null);
        //Act
        when(userRepository.existsById(1L)).thenReturn(true);
        //Assert
        userService.deleteById(1L);
        assertEquals(0, userService.findAll().size());
    }

    @Test
    void deleteUser_whenNoExist_throwException(){
        //Arrange
        //Act
        //Assert
        assertThrows(RuntimeException.class, () -> userService.deleteById(1L));
    }

    @Test
    void getUserByName_whenExist_returnUser(){
        //Arrange
        User user = new User();
        user.setUserId(1L);
        user.setName("Test");
        user.setPassword("Test");
        user.setRole(null);
        //Act
        when(userRepository.findByName("Test")).thenReturn(java.util.Optional.of(user));
        //Assert
        User userFound = userService.findByName("Test");
        assertEquals("Test", userFound.getName());
    }

    @Test
    void getUserByName_whenNoExist_throwException(){
        //Arrange
        //Act
        when(userRepository.findByName("Test")).thenReturn(java.util.Optional.empty());
        //Assert
        assertThrows(RuntimeException.class, () -> userService.findByName("Test"));
    }

    @Test
    void addRoleToUser_whenExistUserAndRole_addRoleToUserSuccessfully(){
        //Arrange

        Role role = new Role();
        role.setRoleId(1L);
        role.setName("Test");


        User user = new User();
        user.setUserId(1L);
        user.setName("Test");
        user.setPassword("Test");
        user.setRole(null);
        //Act
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        when(roleRepository.findById(1L)).thenReturn(java.util.Optional.of(role));
        //Assert
        User userWithRole = userService.addRoleToUser(1L, 1L);
        assertEquals(1L, userWithRole.getRole().getRoleId());
    }

    @Test
    void addRoleToUser_whenNoExistUser_throwException(){
        //Arrange
        //Act
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        //Assert
        assertThrows(RuntimeException.class, () -> userService.addRoleToUser(1L, 1L));
    }

    @Test
    void addRoleToUser_whenExistUserAndNoExistRole_throwException(){
        //Arrange
        User user = new User();
        user.setUserId(1L);
        user.setName("Test");
        user.setPassword("Test");
        user.setRole(null);
        //Act
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        //Assert
        assertThrows(RuntimeException.class, () -> userService.addRoleToUser(1L, 1L));
    }

    @Test
    void removeRoleFromUser_whenExistUserAndRole_removeRoleFromUserSuccessfully(){
        //Arrange

        Role role = new Role();
        role.setRoleId(1L);
        role.setName("Test");

        User user = new User();
        user.setUserId(1L);
        user.setName("Test");
        user.setPassword("Test");
        user.setRole(role);

        //Act
        when(userRepository.save(user)).thenReturn(user);
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        when(roleRepository.findById(1L)).thenReturn(java.util.Optional.of(role));

        //Assert
        User userWithoutRole = userService.removeRoleFromUser(1L, 1L);
        assertNull(userWithoutRole.getRole());
    }

    @Test
    void removeRoleFromUser_whenNoExistUser_throwException(){
        //Arrange
        //Act
        //Assert
        assertThrows(RuntimeException.class, () -> userService.removeRoleFromUser(1L, 1L));
    }

    @Test
    void removeRoleFromUser_whenExistUserAndNoExistRole_throwException(){
        //Arrange
        User user = new User();
        user.setUserId(1L);
        user.setName("Test");
        user.setPassword("Test");
        user.setRole(null);
        //Act
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        //Assert
        assertThrows(RuntimeException.class, () -> userService.removeRoleFromUser(1L, 1L));
    }

    @Test
    void getUserByEmail_whenExist_returnUser() {
        // Arrange
        User user = new User();
        user.setUserId(1L);
        user.setName("Test");
        user.setEmail("test@example.com");
        user.setPassword("Test");
        user.setRole(null);

        // Act
        when(userRepository.findByEmail("test@example.com")).thenReturn(java.util.Optional.of(user));

        // Assert
        Optional<User> userFound = userService.findByEmail("test@example.com");
        assertTrue(userFound.isPresent());
        assertEquals("test@example.com", userFound.get().getEmail());
    }

    @Test
    void getUserByEmail_whenNoExist_returnEmptyOptional() {
        // Arrange

        // Act
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(java.util.Optional.empty());

        // Assert
        Optional<User> userFound = userService.findByEmail("nonexistent@example.com");
        assertFalse(userFound.isPresent());
    }
}
