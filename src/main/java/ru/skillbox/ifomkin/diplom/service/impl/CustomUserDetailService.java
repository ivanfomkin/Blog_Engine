//package ru.skillbox.ifomkin.diplom.service.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import ru.skillbox.ifomkin.diplom.model.User;
//import ru.skillbox.ifomkin.diplom.repository.UserRepository;
//
//@Service
//public class CustomUserDetailService implements UserDetailsService {
//    private final UserRepository userRepository;
//
//    @Autowired
//    public CustomUserDetailService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(s);
//        if (user == null) {
//            throw new UsernameNotFoundException("Unknow user: " + s);
//        }
//        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
//                .username(user.getEmail())
//                .password(user.getPassword())
//                .build();
//        return userDetails;
//    }
//}
