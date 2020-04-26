package com.zrf.mylogin.service;

import com.zrf.mylogin.entity.User;

public interface UserService {

    User getByUNameAndPWord(String userName, String passoword);

}
