package com.sumschol.service;

import com.sumschol.pojo.Admin;
import org.springframework.stereotype.Service;


public interface AdminService {

    Admin login(String name, String pwd);

    int register(Admin admin);

}
