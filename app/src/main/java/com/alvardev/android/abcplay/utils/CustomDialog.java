package com.alvardev.android.abcplay.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alvardev.android.abcplay.BaseAppCompatActivity;
import com.alvardev.android.abcplay.R;
import com.alvardev.android.abcplay.entities.UserEntity;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class CustomDialog {

    private Dialog dialog;
    private static final String NAME_PREFERENCE = "com.alvardev.android.abcplay.preferences";
    private static final String DINO = "https://raw.githubusercontent.com/AlvarDev/ABCPlay/master/app/src/main/res/drawable/img_dino.png";
    private static final String DINA = "https://raw.githubusercontent.com/AlvarDev/ABCPlay/master/app/src/main/res/drawable/img_dina.png";

    private ManagerStudentListener mListener;

    public Dialog manageStudent(final Context context, final UserEntity student, final int action) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View view = View.inflate(context, R.layout.layout_dialog_student, null);

        final TextInputEditText etNameStudent = (TextInputEditText) view.findViewById(R.id.et_name_student);
        final TextInputEditText  etColorGroup = (TextInputEditText) view.findViewById(R.id.et_color_group);
        final TextInputEditText  etColorSingle = (TextInputEditText) view.findViewById(R.id.et_color_single);
        final TextInputEditText  etShapeGroup = (TextInputEditText) view.findViewById(R.id.et_shape_group);
        final TextInputEditText  etShapeSingle = (TextInputEditText) view.findViewById(R.id.et_shape_single);
        final TextInputEditText  etVowelGroup = (TextInputEditText) view.findViewById(R.id.et_vowel_group);
        final TextInputEditText  etVowelSingle = (TextInputEditText) view.findViewById(R.id.et_vowel_single);
        final RadioButton rbMale = (RadioButton) view.findViewById(R.id.rb_male);
        final RadioButton rbFemale = (RadioButton) view.findViewById(R.id.rb_female);

        etNameStudent.setText(student.getName());
        etColorGroup.setText(student.getsColorsGroup() == 0 ? null : String.valueOf(student.getsColorsGroup()));
        etColorSingle.setText(student.getsColorsSingle() == 0 ? null : String.valueOf(student.getsColorsSingle()));
        etShapeGroup.setText(student.getsShapesGroup() == 0 ? null : String.valueOf(student.getsShapesGroup()));
        etShapeSingle.setText(student.getsShapesSingle() == 0 ? null : String.valueOf(student.getsShapesSingle()));
        etVowelGroup.setText(student.getsVowelsGroup() == 0 ? null : String.valueOf(student.getsVowelsGroup()));
        etVowelSingle.setText(student.getsVowelsSingle() == 0 ? null : String.valueOf(student.getsVowelsSingle()));
        rbMale.setChecked(student.getDino().equals(DINO));
        rbFemale.setChecked(student.getDino().equals(DINA));

        Button btSaveStudent = (Button) view.findViewById(R.id.bt_save_student);

        btSaveStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!etNameStudent.getText().toString().isEmpty()){

                    boolean validate =
                            (validateScore(etColorGroup, context) &&
                                    validateScore(etColorSingle, context) &&
                                    validateScore(etShapeGroup, context) &&
                                    validateScore(etShapeSingle, context) &&
                                    validateScore(etVowelGroup, context) &&
                                    validateScore(etVowelSingle, context));

                    if(validate){

                        RealmConfiguration realmConfig = new RealmConfiguration.Builder(context).build();
                        Realm realm = Realm.getInstance(realmConfig);
                        realm.beginTransaction();

                        if(action == BaseAppCompatActivity.ADD_STUDENT){
                            student.setIdUser(getNewIdStudent(context));
                        }

                        int colorGroup = etColorGroup.getText().toString().isEmpty() ?
                                0 : Integer.parseInt(etColorGroup.getText().toString());
                        int colorSingle = etColorSingle.getText().toString().isEmpty() ?
                                0 : Integer.parseInt(etColorSingle.getText().toString());
                        int shapeGroup = etShapeGroup.getText().toString().isEmpty() ?
                                0 : Integer.parseInt(etShapeGroup.getText().toString());
                        int shapeSingle = etShapeSingle.getText().toString().isEmpty() ?
                                0 : Integer.parseInt(etShapeSingle.getText().toString());
                        int vowelGroup = etVowelGroup.getText().toString().isEmpty() ?
                                0 : Integer.parseInt(etVowelGroup.getText().toString());
                        int vowelSingle = etVowelSingle.getText().toString().isEmpty() ?
                                0 : Integer.parseInt(etVowelSingle.getText().toString());

                        student.setName(etNameStudent.getText().toString());
                        student.setNameSearch(student.getName());
                        student.setsColorsGroup(colorGroup);
                        student.setsColorsSingle(colorSingle);
                        student.setsShapesGroup(shapeGroup);
                        student.setsShapesSingle(shapeSingle);
                        student.setsVowelsGroup(vowelGroup);
                        student.setsVowelsSingle(vowelSingle);
                        student.setDino(rbMale.isChecked() ? DINO : DINA);

                        realm.copyToRealmOrUpdate(student);
                        realm.commitTransaction();

                        if(action == BaseAppCompatActivity.ADD_STUDENT){
                            saveLastIdStudent(student.getIdUser(), context);
                        }
                        mListener = (ManagerStudentListener) context;
                        mListener.onNewSearch(student.getNameSearch());
                        dialog.dismiss();
                    }

                }else{
                    etNameStudent.setError(context.getString(R.string.s_no_user));
                }
            }
        });



        builder.setView(view);
        dialog = builder.create();

        return dialog;
    }

    private int getNewIdStudent(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(NAME_PREFERENCE, Activity.MODE_PRIVATE);
        return preferences.getInt("lastIdStudent", 0)+1;
    }

    private void saveLastIdStudent(int lastIdNotification, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(NAME_PREFERENCE, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("lastIdStudent", lastIdNotification);
        editor.apply();
    }

    private boolean validateScore(TextInputEditText view, Context context){
        String text = view.getText().toString();
        if(!text.isEmpty()){
            int score = Integer.parseInt(text);
            if(score < 0 || score >20){
                view.setError(context.getString(R.string.s_out_of_range));
                return false;
            }
        }

        return true;
    }

    public interface ManagerStudentListener {
        void onNewSearch(String query);
    }

}
