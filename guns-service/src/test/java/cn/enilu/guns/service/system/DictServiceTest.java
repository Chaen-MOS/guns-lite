package cn.enilu.guns.service.system;

import cn.enilu.guns.bean.entity.system.Dict;
import cn.enilu.guns.bean.vo.SpringContextHolder;
import cn.enilu.guns.dao.cache.DictCache;
import cn.enilu.guns.dao.system.DictRepository;
import cn.enilu.guns.service.system.impl.ConstantFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * DictService unit tests using Mockito
 * Tests the dictionary service with mocked repository and cache dependencies
 */
@RunWith(MockitoJUnitRunner.class)
public class DictServiceTest {

    @Mock
    private DictRepository dictRepository;

    @Mock
    private DictCache dictCache;

    @Mock
    private SpringContextHolder springContextHolder;

    @InjectMocks
    private DictService dictService;

    private Dict testDict;
    private Dict dictItem;

    @Before
    public void setUp() {
        // Initialize test data
        testDict = new Dict();
        testDict.setId(1L);
        testDict.setName("TEST_DICT");
        testDict.setValue("0");
        testDict.setPid(0L);

        dictItem = new Dict();
        dictItem.setId(2L);
        dictItem.setName("Item 1");
        dictItem.setValue("1");
        dictItem.setPid(1L);
    }

    /**
     * Test queryByPid returns list of child dictionaries
     */
    @Test
    public void testQueryByPidReturnsChildDictionaries() {
        // Arrange
        List<Dict> expectedList = new ArrayList<>();
        expectedList.add(dictItem);
        when(dictRepository.findByPid(1L)).thenReturn(expectedList);

        // Act
        List<Dict> result = dictService.queryByPid(1L);

        // Assert
        assertNotNull("Result should not be null", result);
        assertEquals("Should return correct number of items", 1, result.size());
        assertEquals("First item should match test data", "Item 1", result.get(0).getName());
        verify(dictRepository, times(1)).findByPid(1L);
    }

    /**
     * Test queryByPid with empty result
     */
    @Test
    public void testQueryByPidReturnsEmptyListWhenNoChildren() {
        // Arrange
        List<Dict> emptyList = new ArrayList<>();
        when(dictRepository.findByPid(999L)).thenReturn(emptyList);

        // Act
        List<Dict> result = dictService.queryByPid(999L);

        // Assert
        assertNotNull("Result should not be null", result);
        assertEquals("Should return empty list", 0, result.size());
        verify(dictRepository, times(1)).findByPid(999L);
    }

    /**
     * Test get returns dictionary by id
     */
    @Test
    public void testGetReturnsDictByValidId() {
        // Arrange
        Optional<Dict> optional = Optional.of(testDict);
        when(dictRepository.findById(1L)).thenReturn(optional);

        // Act
        Dict result = dictService.get(1L);

        // Assert
        assertNotNull("Result should not be null", result);
        assertEquals("ID should match", 1L, result.getId().longValue());
        assertEquals("Name should match", "TEST_DICT", result.getName());
        verify(dictRepository, times(1)).findById(1L);
    }

    /**
     * Test get returns null for non-existent id
     */
    @Test
    public void testGetReturnsNullForNonExistentId() {
        // Arrange
        Optional<Dict> emptyOptional = Optional.empty();
        when(dictRepository.findById(999L)).thenReturn(emptyOptional);

        // Act
        Dict result = dictService.get(999L);

        // Assert
        assertNull("Result should be null for non-existent id", result);
        verify(dictRepository, times(1)).findById(999L);
    }



    /**
     * Test queryByPid with multiple children
     */
    @Test
    public void testQueryByPidWithMultipleChildren() {
        // Arrange
        List<Dict> childList = new ArrayList<>();
        Dict child1 = new Dict();
        child1.setId(2L);
        child1.setName("Child 1");
        child1.setPid(1L);
        
        Dict child2 = new Dict();
        child2.setId(3L);
        child2.setName("Child 2");
        child2.setPid(1L);

        childList.add(child1);
        childList.add(child2);
        when(dictRepository.findByPid(1L)).thenReturn(childList);

        // Act
        List<Dict> result = dictService.queryByPid(1L);

        // Assert
        assertNotNull("Result should not be null", result);
        assertEquals("Should return correct number of children", 2, result.size());
        assertEquals("First child name should match", "Child 1", result.get(0).getName());
        assertEquals("Second child name should match", "Child 2", result.get(1).getName());
        verify(dictRepository, times(1)).findByPid(1L);
    }

    /**
     * Test get is called with correct repository method
     */
    @Test
    public void testGetCallsRepositoryFindById() {
        // Arrange
        when(dictRepository.findById(5L)).thenReturn(Optional.of(testDict));

        // Act
        dictService.get(5L);

        // Assert
        verify(dictRepository, times(1)).findById(5L);
    }

    /**
     * Test queryByPid verifies repository is called
     */
    @Test
    public void testQueryByPidVerifiesRepositoryCall() {
        // Arrange
        List<Dict> expectedList = new ArrayList<>();
        expectedList.add(dictItem);
        when(dictRepository.findByPid(1L)).thenReturn(expectedList);

        // Act
        dictService.queryByPid(1L);

        // Assert - Verify mock was called exactly once with correct parameter
        verify(dictRepository, times(1)).findByPid(1L);
    }
}
