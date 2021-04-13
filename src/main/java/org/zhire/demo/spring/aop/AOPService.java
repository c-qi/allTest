package org.zhire.demo.spring.aop;

import org.zhire.demo.spring.ioc.IOCUser;

import java.util.List;

public interface AOPService {
    List<IOCUser>  printUser(IOCUser user);
}
