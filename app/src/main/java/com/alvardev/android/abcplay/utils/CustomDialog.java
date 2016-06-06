package com.alvardev.android.abcplay.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alvardev.android.abcplay.R;
import com.alvardev.android.abcplay.entities.UserEntity;

public class CustomDialog {

    private Dialog dialog;

    public Dialog manageStudent(final Context context, UserEntity student, int action) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View view = View.inflate(context, R.layout.layout_dialog_student, null);

        TextView  etNameStudent = (TextView) view.findViewById(R.id.et_name_student);
        TextView  etColorGroup = (TextView) view.findViewById(R.id.et_color_group);
        TextView  etColorSingle = (TextView) view.findViewById(R.id.et_color_single);
        TextView  etShapeGroup = (TextView) view.findViewById(R.id.et_shape_group);
        TextView  etShapeSingle = (TextView) view.findViewById(R.id.et_shape_single);
        TextView  etVowelGroup = (TextView) view.findViewById(R.id.et_vowel_group);
        TextView  etVowelSingle = (TextView) view.findViewById(R.id.et_vowel_single);

        etNameStudent.setText(student.getName());
        etColorGroup.setText(student.getsColorsGroup() == 0 ? null : String.valueOf(student.getsColorsGroup()));
        etColorSingle.setText(student.getsColorsSingle() == 0 ? null : String.valueOf(student.getsColorsSingle()));
        etShapeGroup.setText(student.getsShapesGroup() == 0 ? null : String.valueOf(student.getsShapesGroup()));
        etShapeSingle.setText(student.getsShapesSingle() == 0 ? null : String.valueOf(student.getsShapesSingle()));
        etVowelGroup.setText(student.getsVowelsGroup() == 0 ? null : String.valueOf(student.getsVowelsGroup()));
        etVowelSingle.setText(student.getsVowelsSingle() == 0 ? null : String.valueOf(student.getsVowelsSingle()));

        Button btSaveStudent = (Button) view.findViewById(R.id.bt_save_student);

        btSaveStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //int priority = spPriority.getSelectedItemPosition();
                //if( priority != Util.PRIORITY_NOT_ASSIGNED){
                    //updateTicket(idTicket,context,priority);
                    //mListenerPriority = (PriorityListener) context;
                    //mListenerPriority.onPrioritySelected(priority, idUser);
                    dialog.dismiss();
                //}
            }
        });



        builder.setView(view);
        dialog = builder.create();

        return dialog;
    }

}
