package com.rumahdev.exo.chanyeol.ui.search.selection.wallpapertypesselection;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.DataBindingUtil;

import com.rumahdev.exo.chanyeol.R;
import com.rumahdev.exo.chanyeol.binding.FragmentDataBindingComponent;
import com.rumahdev.exo.chanyeol.databinding.FragmentWallpaperTypesSelectionListBinding;
import com.rumahdev.exo.chanyeol.ui.common.PSFragment;
import com.rumahdev.exo.chanyeol.utils.AutoClearedValue;
import com.rumahdev.exo.chanyeol.utils.Constants;
import com.rumahdev.exo.chanyeol.viewmodel.color.ColorViewModel;
//import com.panaceasoft.pswallpaper.databinding.FragmentWallpaperTypesSelectionListBinding;

public class WallpaperTypesSelectionListFragment extends PSFragment {

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private ColorViewModel colorViewModel;
    private String wallpaperType = "";

    @VisibleForTesting
    private AutoClearedValue<FragmentWallpaperTypesSelectionListBinding> binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentWallpaperTypesSelectionListBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_wallpaper_types_selection_list, container, false, dataBindingComponent);
        
        binding = new AutoClearedValue<>(this, dataBinding);

        binding.get().setLoadingMore(connectivity.isConnected());

        if (getActivity() != null) {
            Intent intent = getActivity().getIntent();
            this.wallpaperType = intent.getStringExtra(Constants.INTENT__WALLPAPER_TYPE);
        }

        return binding.get().getRoot();
    }

    @Override
    protected void initUIAndActions() {
        binding.get().freeWallpaperTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationController.navigateBackToUploadFragmentWithFree(WallpaperTypesSelectionListFragment.this.getActivity(),getString(R.string.free));
                if (WallpaperTypesSelectionListFragment.this.getActivity() != null) {
                    WallpaperTypesSelectionListFragment.this.getActivity().finish();
                }
            }
        });

        binding.get().premiumWallpaperTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationController.navigateBackToUploadFragmentWithPremium(WallpaperTypesSelectionListFragment.this.getActivity(),getString(R.string.premium));
                if (WallpaperTypesSelectionListFragment.this.getActivity() != null) {
                    WallpaperTypesSelectionListFragment.this.getActivity().finish();
                }
            }
        });
    }

    @Override
    protected void initViewModels() {

    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {

    }
}
