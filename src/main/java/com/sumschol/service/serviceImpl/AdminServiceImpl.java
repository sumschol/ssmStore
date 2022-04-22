package com.sumschol.service.serviceImpl;

import com.sumschol.mapper.AdminMapper;
import com.sumschol.pojo.Admin;
import com.sumschol.pojo.AdminExample;
import com.sumschol.service.AdminService;
import com.sumschol.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin login(String name, String pwd) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andANameEqualTo(name);
        List<Admin> adminList = adminMapper.selectByExample(adminExample);
        if (adminList.size() > 0) {
            Admin admin = adminList.get(0);
            if (admin.getaPass().equals(MD5Util.getMD5(pwd))) {
                return admin;
            }
        }
        return null;
    }

    @Override
    public int register(Admin admin) {
        if (admin != null && admin.getaName() != "" & admin.getaPass() != "") {
            admin.setaPass(MD5Util.getMD5(admin.getaPass()));
            return adminMapper.insert(admin);
        }
        return 0;
    }
}
