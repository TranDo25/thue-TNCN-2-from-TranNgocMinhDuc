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
        Nguoinopthue user = new Nguoinopthue("id1", "username1", "password1", "tencanhan1");
        when(userRepository.findById("id1")).thenReturn(Optional.of(user));

        Nguoinopthue result = userService.get("id1");

        assertNotNull(result);
        assertEquals("username1", result.getUsername());
        assertEquals("password1", result.getPassword());
        assertEquals("tencanhan1", result.getTencanhan());
    }

    @Test
    public void testDelete() {
        userService.delete("id1");

        verify(userRepository, times(1)).deleteById("id1");
    }



    @Test
    public void testCheckLoginWithCorrectCredentials() {
        List<Nguoinopthue> userList = new ArrayList<>();
        Nguoinopthue user1 = new Nguoinopthue("id1", "username1", "password1", "tencanhan1");
        Nguoinopthue user2 = new Nguoinopthue("id2", "username2", "password2", "tencanhan2");
        userList.add(user1);
        userList.add(user2);
        when(userRepository.findAll()).thenReturn(userList);

        Nguoinopthue user = new Nguoinopthue(null, "username1", "password1", null);
        boolean result = userService.checkLogin(user);

        assertTrue(result);
        assertEquals("id1", user.getId());
        assertEquals("tencanhan1", user.getTencanhan());
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
        Nguoinopthue user = new Nguoinopthue("id1", "username1", "password1", "tencanhan1");

        NguoinopthueDTO result = userService.convertToDto(user);

        assertNotNull(result);
        assertEquals("username1", result.getUsername());
        assertEquals("password1", result.getPassword());
        assertEquals("tencanhan1", result.getTencanhan());
    }
}
