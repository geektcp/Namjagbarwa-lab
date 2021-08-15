package com.geektcp.alpha.spring.querydsl.service.model.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author tanghaiyang on 2018/5/15.
 */
@Data
@MappedSuperclass
public class BasePo implements Serializable {

    protected static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", insertable = false)
    protected Long id;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "created_dt", insertable = false, updatable = false)
    protected Date createdDt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "updated_dt", insertable = false, updatable = false)
    protected Date updatedDt;

    @Column(name = "created_by", insertable = false, updatable = false)
    protected String createdBy;

    @Column(name = "updated_by", insertable = false, updatable = false)
    protected String updatedBy;


}
