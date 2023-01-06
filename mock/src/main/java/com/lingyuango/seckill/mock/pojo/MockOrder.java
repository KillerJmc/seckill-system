package com.lingyuango.seckill.mock.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author ChaconneLuo
 */

@Data
@ToString
@Entity(name = "`order`")
@JsonIgnoreProperties({"id","gmtCreate","gmtModified"})
public class MockOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer orderId;

    private Boolean paySuccess;

    private Integer accountId;

    private double money;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
