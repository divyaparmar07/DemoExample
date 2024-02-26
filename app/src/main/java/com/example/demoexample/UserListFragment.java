package com.example.demoexample;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.demoexample.databinding.FragmentUserListBinding;

public class UserListFragment extends Fragment {

    private UserViewModel userViewModel;
    private FragmentUserListBinding binding;
    private UserListAdapter userListAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_list, container, false);
        View view = binding.getRoot();

        Button btn = binding.addUserButton;

        binding.addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getContext(),"Clicked Button",Toast.LENGTH_SHORT).show();
                Navigation.findNavController(requireView()).navigate(R.id.action_userListFragment_to_addUserFragment);
                userViewModel.onAddUserClicked();
            }
        });

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userListAdapter = new UserListAdapter();

        binding.setLifecycleOwner(this);
        binding.setViewModel(userViewModel);

        binding.recyclerView.setAdapter(userListAdapter);

        userViewModel.getAllUsers().observe(getViewLifecycleOwner(), users -> {
            userListAdapter.submitList(users);
        });

        return view;
    }
}
