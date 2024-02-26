package com.example.demoexample;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private final UserRepository userRepository;
    private final LiveData<List<User>> allUsers;
    private final MutableLiveData<Boolean> navigateToAddUser = new MutableLiveData<>();


    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        allUsers = userRepository.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void insertUser(String userName) {
        User user = new User();
        user.setName(userName);
        userRepository.insertUser(user);
    }

    public LiveData<Boolean> getNavigateToAddUser() {
        return navigateToAddUser;
    }

    public void onAddUserClicked() {
        navigateToAddUser.setValue(true);
    }

    public void onAddUserNavigated() {
        navigateToAddUser.setValue(false);
    }
}
