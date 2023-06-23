package com.example.dietApplication.service;

import com.example.dietApplication.dao.UsersDao;
import com.example.dietApplication.form.AdminIdForm;
import com.example.dietApplication.form.AdminPassForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminManagementService {

    @Autowired
    private UsersDao usersDao;

    public int updateAdminPass(AdminPassForm adminPassForm) {
        return usersDao.updateAdminPass(adminPassForm);
    }

    //管理者ID変更
    public int updateAdminId(AdminIdForm adminIdFrom) {
        return usersDao.updateAdminId(adminIdFrom);
    }

}
