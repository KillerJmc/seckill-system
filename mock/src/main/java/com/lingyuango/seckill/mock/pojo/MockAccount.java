package com.lingyuango.seckill.mock.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ChaconneLuo
 */
@Data
@Entity(name = "account")
@JsonIgnoreProperties({"id","gmtCreate","gmtModified"})
public class MockAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idNumber;

    private String name;

    private Integer accountId;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

}
