package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class SingleFragment extends Fragment {

    TextView Name;
    TextView Family;
    TextView Email;
    ImageView Avatar;

    public static SingleFragment newInstance(){

        SingleFragment fragment = new SingleFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.single_row,container,false);

        Bundle bundle = getArguments();
        String first_name = getArguments().getString("Firstname");
        String last_name = getArguments().getString("Lastname");
        String email = getArguments().getString("Email");
        String avatar = getArguments().getString("Avatar");

        Name = view.findViewById(R.id.name);
        Family = view.findViewById(R.id.family);
        Email = view.findViewById(R.id.emaill);
        Avatar = view.findViewById(R.id.avatarr);
        Name.setText(first_name);
        Family.setText(last_name);
        Email.setText(email);
        Glide.with(getContext()).load(avatar).into(Avatar);

        Toast.makeText(getContext(),"Transaction Succesfull",Toast.LENGTH_LONG).show();

        return view;

    }

    public void onBackPressed(){

    }
}
