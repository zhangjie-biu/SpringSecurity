package com.zhangjie.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements UserDetails {


    private User user;

    private List<String> permissions;



    @JSONField(serialize = false)
    //存储SpringSecurity所需的权限信息的集合
    //在向redis中存储对象的时候，此变量不会序列化到redis中
    private Set<SimpleGrantedAuthority> authorities;

    public LoginUser(User user, List<String> list) {
        this.user = user;
        this.permissions = list;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if(authorities!=null){
            return authorities;
        }
        //把permissions中String类型的权限信息封装成SimpleGrantedAuthority对象
//        List<SimpleGrantedAuthority> list = new ArrayList<>();
//        for (String permission : permissions) {
//            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(permission);
//            list.add(simpleGrantedAuthority);
//        }
        authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
