package com.example.dietApplication.dao;

public interface UsersDao {
    //ユーザログイン
    UserLogin getUserLogin(UserFrom userFrom);

    //新規登録
    int insertUser(InsertUserFrom insertUserFrom);

    //ユーザ情報
    UserInfo getUserInfo(String userId);


    /* 管理者 */
    //全ユーザ数取得
    int getAllUserNum();

    //全ユーザ情報取得
    List<User> getAllUser();

    //管理者ID変更
    int updateAdminId(AdminIdForm adminIdFrom);

    //管理者パスワード変更
    int updateAdminPass(AdminPassForm adminPassForm);
}
