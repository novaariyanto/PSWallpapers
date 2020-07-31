package com.rumahdev.exo.chanyeol.ui.aboutus;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rumahdev.exo.chanyeol.R;
import com.rumahdev.exo.chanyeol.binding.FragmentDataBindingComponent;
import com.rumahdev.exo.chanyeol.databinding.FragmentAboutUsBinding;
import com.rumahdev.exo.chanyeol.ui.common.PSFragment;
import com.rumahdev.exo.chanyeol.utils.AutoClearedValue;
import com.rumahdev.exo.chanyeol.utils.Utils;
import com.rumahdev.exo.chanyeol.viewmodel.aboutus.AboutUsViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.databinding.DataBindingUtil;
import androidx.annotation.VisibleForTesting;


public class AboutUsFragment extends PSFragment {


    //region Variables

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private AboutUsViewModel aboutUsViewModel;

    @VisibleForTesting
    private
    AutoClearedValue<FragmentAboutUsBinding> binding;

    //endregion

    //region Override Methods

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        FragmentAboutUsBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_about_us, container, false, dataBindingComponent);

        binding = new AutoClearedValue<>(this, dataBinding);

        //binding.get().getRoot().setVisibility(View.INVISIBLE);

        return binding.get().getRoot();
    }

    @Override
    protected void initUIAndActions() {

    }

    @Override
    protected void initViewModels() {
        aboutUsViewModel = new ViewModelProvider(this, viewModelFactory).get(AboutUsViewModel.class);
    }

    @Override
    protected void initAdapters() {
    }

    @Override
    protected void initData() {

        aboutUsViewModel.setAboutUsObj("about us");
        aboutUsViewModel.getAboutUsData().observe(this, resource -> {

            if (resource != null) {

                Utils.psLog("Got Data" + resource.message + resource.toString());

                switch (resource.status) {
                    case LOADING:
                        // Loading State
                        // Data are from Local DB

                        if (resource.data != null) {

                            fadeIn(binding.get().getRoot());

                            binding.get().setAboutUs(resource.data);
                        }
                        break;
                    case SUCCESS:
                        // Success State
                        // Data are from Server

                        if (resource.data != null) {

                            binding.get().setAboutUs(resource.data);
                        }

                        break;
                    case ERROR:
                        // Error State

                        break;
                    default:
                        // Default

                        break;
                }

            } else {

                // Init Object or Empty Data
                Utils.psLog("Empty Data");

            }


            // we don't need any null checks here for the adapter since LiveData guarantees that
            // it won't call us if fragment is stopped or not started.
            if (resource != null) {
                Utils.psLog("Got Data Of About Us.");


            } else {

                Utils.psLog("No Data of About Us.");
            }
        });
    }

    //endregion


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
    }
}