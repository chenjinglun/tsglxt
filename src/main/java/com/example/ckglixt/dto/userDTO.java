package com.example.ckglixt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.crazycake.shiro.AuthCachePrincipal;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class userDTO implements Serializable, AuthCachePrincipal {
    private String userID;
    private String userName;
    private String userPassWord;

    @Override
    public String getAuthCacheKey() {
        return null;
    }
}
