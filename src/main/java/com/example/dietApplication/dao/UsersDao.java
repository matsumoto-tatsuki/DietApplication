package com.example.dietApplication.dao;


import com.example.dietApplication.entity.*;
import com.example.dietApplication.form.AdminIdForm;
import com.example.dietApplication.form.AdminPassForm;
import com.example.dietApplication.entity.UserInfo;
import com.example.dietApplication.form.AdminIdForm;
import com.example.dietApplication.form.AdminPassForm;
import com.example.dietApplication.form.InsertUserForm;
import com.example.dietApplication.form.UserForm;
import java.util.List;
public interface UsersDao {
    //ユーザログイン
    UserLogin getUserLogin(UserForm userFrom);

    //新規登録
    int insertUser(InsertUserForm insertUserFrom) ;

    UserLogin getUserIdCheck(InsertUserForm insertUserForm);

    //ユーザ情報
    UserInfo getUserInfo(String userId);


    /* 管理者 */
    //全ユーザ数取得
    int getAllUserNum();

    //全ユーザ情報取得
    List<User> getAllUser();

    //全ダイエット情報取得
    List<AdminDietInfo> getAllDiet();

    //全ダイエット数の取得
    int getAllDietNum();

    //管理者ID変更
    int updateAdminId(AdminIdForm adminIdFrom);

    //管理者パスワード変更
    int updateAdminPass(AdminPassForm adminPassForm);

    //user登録日検索
    List<User> userDate(AdminDateSearch adminDateSearch);

}
