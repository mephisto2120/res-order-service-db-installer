package com.tryton.template.service;

import com.tryton.template.mapper.TestMapper;
import com.tryton.template.mapper.XmlTestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private final TestMapper testMapper;
    private final XmlTestMapper xmlTestMapper;

    @Autowired
    public TestService(TestMapper testMapper, XmlTestMapper xmlTestMapper) {
        this.testMapper = testMapper;
        this.xmlTestMapper = xmlTestMapper;
    }

    public int testCall() {
        return testMapper.testCall();
    }

    public int testCallXml() {
        return xmlTestMapper.testCall();
    }
}