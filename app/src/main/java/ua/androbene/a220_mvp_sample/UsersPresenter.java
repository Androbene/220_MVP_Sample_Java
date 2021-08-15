package ua.androbene.a220_mvp_sample;

import android.content.ContentValues;
import android.text.TextUtils;

public class UsersPresenter {

    private UsersActivity view;
    private final UsersModel model;

    public UsersPresenter(UsersModel model) {
        this.model = model;
    }

    public void attachView(UsersActivity usersActivity) {
        view = usersActivity;
    }

    public void detachView() {
        view = null;
    }


    public void viewIsReady() {
        loadUsers();
    }

    public void loadUsers() {
        model.loadUsers(users -> view.showUsers(users));
    }

    public void add() {
        UsersData userData = view.getUserData();
        if (TextUtils.isEmpty(userData.getName()) || TextUtils.isEmpty(userData.getEmail())) {
            view.showToast(R.string.empty_values);
            return;
        }

        ContentValues cv = new ContentValues(2);
        cv.put(UserTable.COLUMN.NAME, userData.getName());
        cv.put(UserTable.COLUMN.EMAIL, userData.getEmail());
        view.showProgress();
        model.addUser(cv, () -> {
            view.hideProgress();
            loadUsers();
        });
    }

    public void clear() {
        view.showProgress();
        model.clearUsers(() -> {
            view.hideProgress();
            loadUsers();
        });
    }

}