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
@Entity(name = "secret_key")
@JsonIgnoreProperties({"id", "gmtCreate", "gmtModified"})
public class SecretKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String appId;

    private String secretKey;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
