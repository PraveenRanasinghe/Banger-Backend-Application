package com.banger.backend.Service;

import com.banger.backend.Config.ApplicationUser;
import com.banger.backend.DTO.acceptUserDTO;
import com.banger.backend.DTO.userDTO;
import com.banger.backend.Entity.User;
import com.banger.backend.Repositary.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class userService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private emailService emailService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        } else {
            ArrayList<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getUserRole().toUpperCase(Locale.ROOT)));
            ApplicationUser users = new ApplicationUser(grantedAuthorities, user.getEmail(), user.getPassword(),
                    true, true, true, true);
            return users;
        }
    }


    public User getUserByID(String uId) {
        Optional<User> user = userRepo.findById(uId);
        User users = null;
        if (user.isPresent()) {
            users = user.get();
        }
        return users;
    }


    public User userRegistration(userDTO dto) throws ParseException {
        User user = new User();
            user.setfName(dto.getfName());
            user.setlName(dto.getlName());
            user.setEmail(dto.getEmail());
            user.setContactNum(dto.getContactNum());
            user.setPassword(encoder.encode(dto.getPassword()));
            user.setNicNumber(dto.getNicNumber());
            user.setIsBlackListed("False");
            user.setUserRole("Customer");
            user.setStatus("Accepted");
            user.setDob(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getDob()));
            user.setLicenceImg(dto.getLicenceImg());
            user.setProfileImage(dto.getProfileImage());
            user.setUtilityBill(dto.getUtilityBill());
//            emailService.EmailToNotifyAdminInNewUserRegistration("systemAdmin@gmail.com");
        return userRepo.save(user);
    }

    //            user.setDOB(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getDob()));

    public User updateUserProfile(userDTO dtoUser) {
        User user = new User();
        if (dtoUser != null) {
            user.setfName(dtoUser.getfName());
            user.setlName(dtoUser.getlName());
            user.setEmail(dtoUser.getEmail());
            user.setContactNum(dtoUser.getContactNum());
            user.setPassword(encoder.encode(dtoUser.getPassword()));
            user.setNicNumber(dtoUser.getNicNumber());
            user.setLicenceImg(dtoUser.getLicenceImg());
        }
        return userRepo.save(user);
    }

    //Get All Pending Users to the List
    public List<userDTO> getAllPendingUsersToList() {
        List<userDTO> list = new ArrayList<>();
        for (User user : userRepo.findAll()) {
            if (user.getUserRole().equals("Customer") && user.getStatus().equals("Pending")) {
                userDTO dto = new userDTO();
                dto.setUserRole(user.getUserRole());
                dto.setEmail(user.getEmail());
                dto.setfName(user.getfName());
                dto.setlName(user.getlName());
                dto.setContactNum(user.getContactNum());
                dto.setLicenceImg(user.getLicenceImg());
                list.add(dto);
            }
        }
        return list;
    }


    public User acceptUserAccount(acceptUserDTO dto) {
        User user = userRepo.findUserByEmail(dto.getEmail());
        user.setStatus("Accepted");
//        emailService.EmailForAccountAcceptance(dto.getEmail());
        return userRepo.save(user);
    }


    //Get All Registered Users In the System
    public List<userDTO> getAllAcceptedUsersToList() {
        List<userDTO> list = new ArrayList<>();
        for (User user : userRepo.findAll()) {
            if (user.getUserRole().equals("Customer") && user.getStatus().equals("Accepted") ) {
                userDTO dto = new userDTO();
                dto.setUserRole(user.getUserRole());
                dto.setEmail(user.getEmail());
                dto.setfName(user.getfName());
                dto.setlName(user.getlName());
                dto.setContactNum(user.getContactNum());
                list.add(dto);
            }
        }
        return list;
    }



    public void removeUser(User user) {
        userRepo.delete(user);
    }


}
