package com.example.naveed.darzii;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserUpdateFragment extends Fragment {


    public UserUpdateFragment() {
        // Required empty public constructor
    }

    String name, email, id;
    View view;
    EditText txtname, txtemail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user_update, container, false);
        txtname = view.findViewById(R.id.txtName);
        txtemail = view.findViewById(R.id.txtEmail);

        txtname.setText(name);
        txtemail.setText(email);
        return view;
    }

    public void setValues(String name, String email, String id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

}
