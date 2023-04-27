//package com.phanmem.cakeshop.security;
//
//import com.phanmem.cakeshop.entity.Admin;
//import com.phanmem.cakeshop.entity.Role;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//public class CustomUserDetail extends Admin implements UserDetails {
//   private Admin user;
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        for(Role role : user.getRoles()){
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassWord();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getUserName();
//    }
//}
