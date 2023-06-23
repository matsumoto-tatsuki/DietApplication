package com.example.dietApplication.dao;


import com.example.dietApplication.entity.UserInfo;
import com.example.dietApplication.form.AdminIdForm;
import com.example.dietApplication.form.AdminPassForm;
import com.example.dietApplication.entity.User;
import com.example.dietApplication.entity.UserInfo;
import com.example.dietApplication.entity.UserLogin;
import com.example.dietApplication.form.AdminIdForm;
import com.example.dietApplication.form.AdminPassForm;
import com.example.dietApplication.form.InsertUserForm;
import com.example.dietApplication.form.UserForm;
import java.util.List;

@Repository
public class UsersDao {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //新規登録
    int insertUser(InsertUserForm insertUserFrom) ;

    UserLogin getUserIdCheck(InsertUserForm insertUserForm);

        param.addValue("userId", userInsertForm.getUserId());
        param.addValue("password", userInsertForm.getPassword());
        param.addValue("weight", weight);

        String sql = "INSERT INTO users (user_id,password,weight) VALUES(:userId,:password,:weight)";
        return namedParameterJdbcTemplate.update(sql, param);
    }

    public UserIdData UserIdCheck(UserInsertForm userInsertForm) {
        List<UserIdData> usersIdData = new ArrayList<>();
        var param = new MapSqlParameterSource();
        param.addValue("userId", userInsertForm.getUserId());
        String sql = "SELECT user_id FROM users WHERE user_id = :userId";
        usersIdData = namedParameterJdbcTemplate.query(sql, param, new DataClassRowMapper<>(UserIdData.class));
        return usersIdData.isEmpty() ? null : usersIdData.get(0);
    }

    public UserLoginData loginCheck(UserLoginForm userLoginForm) {
        List<UserLoginData> usersData = new ArrayList<>();
        var param = new MapSqlParameterSource();
        param.addValue("userId", userLoginForm.getUserId());
        String sql = "SELECT user_id,password FROM users WHERE user_id = :userId";
        usersData = namedParameterJdbcTemplate.query(sql, param, new DataClassRowMapper<>(UserLoginData.class));
        return usersData.isEmpty() ? null : usersData.get(0);
    }
}
//=======
//import com.example.dietApplication.entity.User;
//import com.example.dietApplication.entity.UserInfo;
//import com.example.dietApplication.entity.UserLogin;
//import com.example.dietApplication.form.AdminIdForm;
//import com.example.dietApplication.form.AdminPassForm;
//import com.example.dietApplication.form.InsertUserForm;
//import com.example.dietApplication.form.UserForm;
//
//import java.util.List;
//
//public interface UsersDao {
//    //ユーザログイン
//    UserLogin getUserLogin(UserForm userFrom);
//
//    //新規登録
//    int insertUser(InsertUserForm insertUserFrom);
//
//    //ユーザ情報
//    UserInfo getUserInfo(String userId);
//
//
//    /* 管理者 */
//    //全ユーザ数取得
//    int getAllUserNum();
//
//    //全ユーザ情報取得
//    List<User> getAllUser();
//
//    //管理者ID変更
//    int updateAdminId(AdminIdForm adminIdFrom);
//
//    //管理者パスワード変更
//    int updateAdminPass(AdminPassForm adminPassForm);
//>>>>>>> 785cf70ceb216fd620f0660aa76a7f0c4984c048
//}
