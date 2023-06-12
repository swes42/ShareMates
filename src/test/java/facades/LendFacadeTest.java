/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package facades;

import dtos.LendDTO;
import entities.Equipment;
import entities.User;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Selina A.S.
 */



public class LendFacadeTest {

    private EntityManager mockEntityManager;
    private TypedQuery<User> mockUserQuery;
    private TypedQuery<Equipment> mockEquipmentQuery;
    private User mockUser;
    private Equipment mockEquipment;
    private List<User> mockUserList;
    private List<Equipment> mockEquipmentList;
    private LendFacade lendFacade;
    
    
    @BeforeEach
    public void setUp() {
        mockEntityManager = mock(EntityManager.class);
        lendFacade = new LendFacade(mockEntityManager);

        mockUserQuery = mock(TypedQuery.class);
        mockEquipmentQuery = mock(TypedQuery.class);
        mockUser = mock(User.class);
        mockEquipment = mock(Equipment.class);
        mockUserList = Collections.singletonList(mockUser);
        mockEquipmentList = Collections.singletonList(mockEquipment);

        when(mockEntityManager.createQuery(anyString(), eq(User.class))).thenReturn(mockUserQuery);
        when(mockEntityManager.createQuery(anyString(), eq(Equipment.class))).thenReturn(mockEquipmentQuery);
        when(mockUserQuery.getResultList()).thenReturn(mockUserList);
        when(mockEquipmentQuery.getResultList()).thenReturn(mockEquipmentList);

    }
    
    @Test
    public void testCreateLend(){
        //Arrange
        String username = "Benjamin";
        String equipmentName = "HP x360";
        when(mockUser.getUsername()).thenReturn(username);
        when(mockEquipment.getEquipmentName()).thenReturn(equipmentName);
        
        //Act
        LendDTO result = lendFacade.createLend(username, equipmentName);
        
        //Assert
        assertNotNull(result);
        assertEquals(username, result.getUsername());
        assertEquals(equipmentName, result.getEquipment());
    }
    
}
