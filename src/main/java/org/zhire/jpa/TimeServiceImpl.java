package org.zhire.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeServiceImpl implements TimeService {

    @Autowired
    private TimeRepository timeRepository;
    @Override
    public void save() {
        Time time = new Time();
        Time save = timeRepository.save(time);
        System.out.println(save);
    }
}
