package com.sqa.unit_test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.sqa.services.AdminService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.sqa.models.entities.SystemAdmin;
import com.sqa.repositories.AdminRepository;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminService adminService;

    @Before
    public void setUp() {
        SystemAdmin admin = new SystemAdmin();
        admin.setUsername("admin");
        admin.setPassword("password");

        when(adminRepository.kiemTraUsername("admin")).thenReturn(admin);
    }

    @Test
    public void testCheckLoginWithCorrectPassword() {
        SystemAdmin admin = new SystemAdmin();
        admin.setUsername("admin");
        admin.setPassword("password");

        SystemAdmin result = adminService.checkLogin(admin);

        assertEquals("admin", result.getUsername());
        assertEquals("password", result.getPassword());
    }

    @Test
    public void testCheckLoginWithIncorrectPassword() {
        SystemAdmin admin = new SystemAdmin();
        admin.setUsername("admin");
        admin.setPassword("wrong_password");

        SystemAdmin result = adminService.checkLogin(admin);

        assertEquals(null, result);
    }
}

