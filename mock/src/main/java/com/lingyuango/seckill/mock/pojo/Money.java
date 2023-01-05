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
@Entity(name = "money")
@JsonIgnoreProperties({"id","gmtCreate","gmtModified"})
public class Money {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer accountId;

    private Double money;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
