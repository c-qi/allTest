package org.zhire.jpa;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "time")
class Time extends Base {

    @Column
    private String name;

    @Column
    private Long ctime;
}
