package ua.androbene.a220_mvp_sample;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class UsersActivity extends AppCompatActivity {

    private UserAdapter userAdapter;

    private EditText editTextName;
    private EditText editTextEmail;
    private ProgressDialog progressDialog;

    private UsersPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        init();
    }

    private void init() {

        editTextName = findViewById(R.id.name);
        editTextEmail = findViewById(R.id.email);

        findViewById(R.id.add).setOnClickListener(v -> presenter.add());

        findViewById(R.id.clear).setOnClickListener(v -> presenter.clear());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        userAdapter = new UserAdapter();

        RecyclerView userList = findViewById(R.id.list);
        userList.setLayoutManager(layoutManager);
        userList.setAdapter(userAdapter);


        DbHelper dbHelper = new DbHelper(this);
        UsersModel usersModel = new UsersModel(dbHelper);
        presenter = new UsersPresenter(usersModel);
        presenter.attachView(this);
        presenter.viewIsReady();
    }

    public UsersData getUserData() {
        UsersData userData = new UsersData();
        userData.setName(editTextName.getText().toString());
        userData.setEmail(editTextEmail.getText().toString());
        return userData;
    }

    public void showUsers(List<UsersData> users) {
        userAdapter.setData(users);
    }

    public void showToast(int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }

    public void showProgress() {
        progressDialog = ProgressDialog.show(this, "", getString(R.string.please_wait));
    }

    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}