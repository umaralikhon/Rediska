package com.example.rediska;

import io.lettuce.core.RedisException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SellerService {

//    private final SellerRepo sellerRepo;
    private final RedisTemplate redisTemplate;
    private final static String HASH = "Product";

    public Seller save(Seller seller) {
        try {
            redisTemplate.opsForHash().put(HASH, String.valueOf(seller.getId()), seller);
            return seller;
        } catch (RedisException ex) {
            throw new RedisException(ex.getMessage());
        }
    }

    public List findAll() {
        try {
            return redisTemplate.opsForHash().values(HASH);
        } catch (RedisException ex) {
            throw new RedisException(ex.getMessage());
        }
    }

    public Seller findById(int id) {
        try {
            return (Seller) redisTemplate.opsForHash().get(HASH, String.valueOf(id));
        } catch (RedisException ex) {
            throw new RedisException(ex.getMessage());
        }
    }


}
