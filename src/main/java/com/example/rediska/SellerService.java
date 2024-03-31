package com.example.rediska;

import io.lettuce.core.RedisException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SellerService {

//    private final SellerRepo sellerRepo;
    private final RedisTemplate redisTemplate;
    public final static String HASH = "Product";

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

    @Cacheable(key = "#id", value = HASH)
    public Seller findById(int id) {
        try {
            System.out.println("Get from CACHE");
            return (Seller) redisTemplate.opsForHash().get(HASH, String.valueOf(id));
        } catch (RedisException ex) {
            throw new RedisException(ex.getMessage());
        }
    }

    public boolean deleteById(int id){
        try{
            redisTemplate.opsForHash().delete(HASH, String.valueOf(id));
            return true;
        }catch (RedisException ex) {
            throw new RedisException(ex.getMessage());
        }
    }
}
