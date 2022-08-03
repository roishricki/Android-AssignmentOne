package com.example.assignmentone.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignmentone.R;
import com.example.assignmentone.activitys.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LogFragment newInstance(String param1, String param2) {
        LogFragment fragment = new LogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log, container, false);
        Button regButtonLogFrag = (Button)view.findViewById(R.id.registerToRegFrag);
        Button logButtonLogFrag = (Button)view.findViewById(R.id.login_button);

        logButtonLogFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText emailEditText = view.findViewById(R.id.emailUser);
                String email = emailEditText.getText().toString();
                EditText passEditText =  view.findViewById(R.id.passwordUser);
                String password = passEditText.getText().toString();
                if(email.isEmpty()||password.isEmpty()){
                    Toast.makeText(getActivity(),"Some fields are empty",Toast.LENGTH_LONG).show();
                    return;
                }
                if(!((MainActivity)getActivity()).validateEmail(email)){
                    Toast.makeText(getActivity(),"Email address is not valid",Toast.LENGTH_LONG).show();
                    return;
                }
                ((MainActivity)getActivity()).loginFunc(view,email,password);
            }
        });

        regButtonLogFrag.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_logFragment_to_regFragment);
            }
        });
        return view;
    }
}