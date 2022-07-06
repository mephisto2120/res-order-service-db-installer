package com.tryton.template.service;

import com.tryton.template.mapper.TestMapper;
import com.tryton.template.mapper.XmlTestMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestServiceTest {

    @Mock
    private TestMapper testMapperMock;
    @Mock
    private XmlTestMapper xmlTestMapperMock;
    @InjectMocks
    private TestService testService;

    @Test
    void shouldCallTestMapper() {
        //given
        when(testMapperMock.testCall()).thenReturn(666);

        //when
        int result = testService.testCall();

        //then
        verify(testMapperMock).testCall();
        verifyNoInteractions(xmlTestMapperMock);
        assertEquals(666, result);
        verifyNoMoreInteractions(testMapperMock);
    }

    @Test
    void shouldCallXmlTestMapper() {
        //given
        when(xmlTestMapperMock.testCall()).thenReturn(777);

        //when
        int result = testService.testCallXml();

        //then
        verify(xmlTestMapperMock).testCall();
        verifyNoInteractions(testMapperMock);
        assertEquals(777, result);
        verifyNoMoreInteractions(xmlTestMapperMock);
    }
}
