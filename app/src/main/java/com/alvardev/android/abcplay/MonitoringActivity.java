package com.alvardev.android.abcplay;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alvardev.android.abcplay.adapters.StudentAdapter;
import com.alvardev.android.abcplay.entities.UserEntity;
import com.alvardev.android.abcplay.utils.CustomDialog;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MonitoringActivity extends BaseAppCompatActivity {

    @InjectView(R.id.iv_exit) ImageView ivExit;
    @InjectView(R.id.et_search) EditText etSearch;
    @InjectView(R.id.iv_search) ImageView ivSearch;
    @InjectView(R.id.tv_no_results) TextView tvNoResults;
    @InjectView(R.id.rv_students) RecyclerView rvStudents;
    @InjectView(R.id.content) View content;
    @InjectView(R.id.fa_add_student) FloatingActionButton faAddStudent;
    //@InjectView(R.id.progress_bar) View progressBar;

    private RealmResults<UserEntity> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring);
        ButterKnife.inject(this);

        setActions();
        getStudents();
    }

    private void setActions(){
        ivExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = etSearch.getText().toString();
                search(query);
            }
        });

        faAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialogOk = new CustomDialog().manageStudent(
                        MonitoringActivity.this,
                        new UserEntity(),
                        ADD_STUDENT);
                dialogOk.show();
            }
        });
    }

    private void getStudents(){
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        Realm realm = Realm.getInstance(realmConfig);
        students = realm.where(UserEntity.class).equalTo("type", STUDENT).findAll();

        if (students.size() > 0) {
            realm.beginTransaction();
            setFinalScore();
            realm.copyToRealmOrUpdate(students);
            realm.commitTransaction();
            setRecyclerView();
        }else{
            tvNoResults.setVisibility(View.VISIBLE);
        }
    }

    private void setRecyclerView(){
        rvStudents.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvStudents.setLayoutManager(mLayoutManager);

        StudentAdapter mAdapter = new StudentAdapter(students, MonitoringActivity.this);
        rvStudents.setAdapter(mAdapter);
        rvStudents.setItemAnimator(new DefaultItemAnimator());
        rvStudents.setVisibility(View.VISIBLE);
        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int pos = rvStudents.getChildLayoutPosition(v);
                Dialog dialogOk = new CustomDialog().manageStudent(
                        MonitoringActivity.this,
                        students.get(pos),
                        UPDATE_STUDENT);

                dialogOk.show();

            }
        });

        tvNoResults.setVisibility(View.GONE);
    }

    private void search(String query){
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        Realm realm = Realm.getInstance(realmConfig);

        if(query.isEmpty()){
            students = realm.where(UserEntity.class)
                    .equalTo("type", STUDENT)
                    .findAll();
        }else {
            students = realm.where(UserEntity.class)
                    .equalTo("type", STUDENT)
                    .contains("nameSearch", query)
                    .findAll();
        }

        if (students.size() > 0) {
            realm.beginTransaction();
            setFinalScore();
            realm.copyToRealmOrUpdate(students);
            realm.commitTransaction();
            setRecyclerView();
        }else{
            tvNoResults.setVisibility(View.VISIBLE);
        }

    }

    private void setFinalScore(){

        for(int i=0; i<students.size(); i++){

            UserEntity student = students.get(i);
            String letter;
            int finalScore = student.getsColorsGroup() + student.getsColorsSingle() +
                    student.getsShapesGroup() + student.getsShapesSingle() +
                    student.getsVowelsGroup() + student.getsVowelsSingle();

            finalScore = Math.round(finalScore/6);

            if(finalScore <= 20 && finalScore >= 16){
                letter = "A";
            }else if(finalScore <= 15 && finalScore >= 11){
                letter = "B";
            }else if(finalScore <= 10 && finalScore >= 0){
                letter = "C";
            }else{
                letter = "--";
            }

            students.get(i).setFinalScore("Nota Final: "+finalScore+" - "+letter);

        }

    }

}
