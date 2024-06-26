package com.example.rediska;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash("Seller")
@JsonSerialize
public class Seller implements Serializable {

    @Id
    private int id;
    private String firstname;
    private String lastname;
}
