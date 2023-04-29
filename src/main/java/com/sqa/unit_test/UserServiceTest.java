package com.sqa.unit_test;

import com.sqa.models.dtos.NguoinopthueDTO;
import com.sqa.models.entities.Nguoinopthue;
import com.sqa.repositories.UserRepository;
import com.sqa.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;


public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListAllUser() {
        List<Nguoinopthue> userList = new ArrayList<>();
        Nguoinopthue user1 = new Nguoinopthue("1", "01TranDuc", "password1", "tencanhan1");
        Nguoinopthue user2 = new Nguoinopthue("2", "username2", "password2", "tencanhan2");
        userList.add(user1);
        userList.add(user2);
        when(userRepository.findAll()).thenReturn(userList);

        List<Nguoinopthue> result = userService.listAllUser();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testSave() {
        Nguoinopthue user = new Nguoinopthue("id1", "username1", "password1", "tencanhan1");

        userService.save(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testGet() {
        Nguoinopthue user = new Nguoinopthue("1", "ABC", "1234567", "Nguyen Van A");
        when(userRepository.findById("1")).thenReturn(Optional.of(user));

        Nguoinopthue result = userService.get("1");
        assertEquals(user, result);
    }
    @Test
    public void testDelete() {
        userService.delete("id1");

        verify(userRepository, times(1)).deleteById("id1");
    }



    @Test
    public void testCheckLogin() {
        List<Nguoinopthue> list = new ArrayList<>();
        list.add(new Nguoinopthue("1", "user1", "userName1"));
        list.add(new Nguoinopthue("2", "user2", "userName2"));
        list.add(new Nguoinopthue("3", "user3", "userName3"));
        when(userRepository.findAll()).thenReturn(list);

        Nguoinopthue user1 = new Nguoinopthue("4", "user4", "userName4");
        boolean result1 = userService.checkLogin(user1);
        assertEquals(false, result1);

        Nguoinopthue user2 = new Nguoinopthue("1", "user1", "userName1");
        System.out.println(user2);
        boolean result2 = userService.checkLogin(user2);
        assertEquals(true, result2);
        assertEquals("1", user2.getId());
        assertEquals("user1", user2.getUsername());
        assertEquals("userName1", user2.getPassword());
    }

    @Test
    public void testCheckLoginWithIncorrectCredentials() {
        List<Nguoinopthue> userList = new ArrayList<>();
        Nguoinopthue user1 = new Nguoinopthue("id1", "username1", "password1", "tencanhan1");
        Nguoinopthue user2 = new Nguoinopthue("id2", "username2", "password2", "tencanhan2");
        userList.add(user1);
        userList.add(user2);
        when(userRepository.findAll()).thenReturn(userList);

        Nguoinopthue user = new Nguoinopthue(null, "username3", "password3", null);
        boolean result = userService.checkLogin(user);

        assertFalse(result);
    }

    @Test
    public void testConvertToDto() {
        Nguoinopthue user = new Nguoinopthue("1", "user1", "pass1");
        NguoinopthueDTO dto = userService.convertToDto(user);
        assertEquals("1", dto.getId());
        assertEquals("user1", dto.getUsername());
    }
}
