package com.rumahdev.exo.chanyeol.ui.common;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.fragment.app.FragmentActivity;

import com.rumahdev.exo.chanyeol.MainActivity;
import com.rumahdev.exo.chanyeol.R;
import com.rumahdev.exo.chanyeol.ui.aboutus.AboutUsFragment;
import com.rumahdev.exo.chanyeol.ui.category.list.CategoryListActivity;
import com.rumahdev.exo.chanyeol.ui.category.list.CategoryListFragment;
import com.rumahdev.exo.chanyeol.ui.claimpoint.ClaimPointActivity;
import com.rumahdev.exo.chanyeol.ui.claimpoint.ClaimPointFragment;
import com.rumahdev.exo.chanyeol.ui.contactus.ContactUsFragment;
import com.rumahdev.exo.chanyeol.ui.dashboard.DashboardFragment;
import com.rumahdev.exo.chanyeol.ui.dashboard.free.WallpaperContainerFragment;
import com.rumahdev.exo.chanyeol.ui.dashboard.gif.GifContainerFragment;
import com.rumahdev.exo.chanyeol.ui.dashboard.livewallpaper.LiveWallpaperContainerFragment;
import com.rumahdev.exo.chanyeol.ui.dashboard.premium.PremiumContainerFragment;
import com.rumahdev.exo.chanyeol.ui.dashboard.search.DashboardSearchFragment;
import com.rumahdev.exo.chanyeol.ui.download.DownloadLiveWallpaperListFragment;
import com.rumahdev.exo.chanyeol.ui.download.DownloadedListFragment;
import com.rumahdev.exo.chanyeol.ui.favorite.FavoriteListFragment;
import com.rumahdev.exo.chanyeol.ui.favorite.FavoriteLiveWallpaperFragment;
import com.rumahdev.exo.chanyeol.ui.forceupdate.ForceUpdateActivity;
import com.rumahdev.exo.chanyeol.ui.language.LanguageFragment;
import com.rumahdev.exo.chanyeol.ui.livewallpaper.detail.VideoPlayActivity;
import com.rumahdev.exo.chanyeol.ui.notification.NotificationSettingActivity;
import com.rumahdev.exo.chanyeol.ui.privacy.PrivacyActivity;
import com.rumahdev.exo.chanyeol.ui.privacypolicy.PrivacyPolicyActivity;
import com.rumahdev.exo.chanyeol.ui.search.SearchActivity;
import com.rumahdev.exo.chanyeol.ui.search.selection.categoryselection.CategorySelectionListActivity;
import com.rumahdev.exo.chanyeol.ui.search.selection.colorselection.ColorSelectionListActivity;
import com.rumahdev.exo.chanyeol.ui.search.selection.wallpapertypesselection.WallpaperTypesSelectionListActivity;
import com.rumahdev.exo.chanyeol.ui.setting.SettingFragment;
import com.rumahdev.exo.chanyeol.ui.upload.list.UploadedWallpaperListFragment;
import com.rumahdev.exo.chanyeol.ui.upload.upload.UploadWallpaperActivity;
import com.rumahdev.exo.chanyeol.ui.user.PasswordChangeActivity;
import com.rumahdev.exo.chanyeol.ui.user.ProfileEditActivity;
import com.rumahdev.exo.chanyeol.ui.user.ProfileFragment;
import com.rumahdev.exo.chanyeol.ui.user.UserForgotPasswordActivity;
import com.rumahdev.exo.chanyeol.ui.user.UserForgotPasswordFragment;
import com.rumahdev.exo.chanyeol.ui.user.UserLoginActivity;
import com.rumahdev.exo.chanyeol.ui.user.UserLoginFragment;
import com.rumahdev.exo.chanyeol.ui.user.UserRegisterActivity;
import com.rumahdev.exo.chanyeol.ui.user.UserRegisterFragment;
import com.rumahdev.exo.chanyeol.ui.user.phonelogin.PhoneLoginActivity;
import com.rumahdev.exo.chanyeol.ui.user.phonelogin.PhoneLoginFragment;
import com.rumahdev.exo.chanyeol.ui.user.verifyemail.VerifyEmailActivity;
import com.rumahdev.exo.chanyeol.ui.user.verifyemail.VerifyEmailFragment;
import com.rumahdev.exo.chanyeol.ui.user.verifyphone.VerifyMobileActivity;
import com.rumahdev.exo.chanyeol.ui.user.verifyphone.VerifyMobileFragment;
import com.rumahdev.exo.chanyeol.ui.wallpaper.detail.WallpaperDetailActivity;
import com.rumahdev.exo.chanyeol.ui.wallpaper.listwithfilter.WallpaperListWithFilterActivity;
import com.rumahdev.exo.chanyeol.utils.Constants;
import com.rumahdev.exo.chanyeol.utils.Utils;
import com.rumahdev.exo.chanyeol.viewobject.Wallpaper;
import com.rumahdev.exo.chanyeol.viewobject.holder.WallpaperParamsHolder;

import javax.inject.Inject;


/**
 * Created by Panacea-Soft on 11/17/17.
 * Contact Email : teamps.is.cool@gmail.com
 */

public class NavigationController {

    //region Variables

    private final int containerId;
    private RegFragments currentFragment;

    //endregion


    //region Constructor
    @Inject
    public NavigationController() {

        // This setup is for MainActivity
        this.containerId = R.id.content_frame;
    }

    //endregion


    //region default navigation

    public void navigateToMainActivity(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    public void navigateToUserLogin(MainActivity mainActivity) {
        if (checkFragmentChange(RegFragments.HOME_USER_LOGIN)) {
            try {
                UserLoginFragment fragment = new UserLoginFragment();
                mainActivity.getSupportFragmentManager().beginTransaction()
                        .replace(containerId, fragment)
                        .commitAllowingStateLoss();
            } catch (Exception e) {
                Utils.psErrorLog("Error! Can't replace fragment.", e);
            }
        }
    }

    public void navigateToUserProfile(MainActivity mainActivity) {
        if (checkFragmentChange(RegFragments.HOME_USER_LOGIN)) {
            try {
                ProfileFragment fragment = new ProfileFragment();
                mainActivity.getSupportFragmentManager().beginTransaction()
                        .replace(containerId, fragment)
                        .commitAllowingStateLoss();
            } catch (Exception e) {
                Utils.psErrorLog("Error! Can't replace fragment.", e);
            }
        }
    }

    public void navigateToUserRegister(MainActivity mainActivity) {
        if (checkFragmentChange(RegFragments.HOME_USER_REGISTER)) {
            try {
                UserRegisterFragment fragment = new UserRegisterFragment();
                mainActivity.getSupportFragmentManager().beginTransaction()
                        .replace(containerId, fragment)
                        .commitAllowingStateLoss();
            } catch (Exception e) {
                Utils.psErrorLog("Error! Can't replace fragment.", e);
            }
        }
    }

    public void navigateToUserForgotPassword(MainActivity mainActivity) {
        if (checkFragmentChange(RegFragments.HOME_USER_FOGOT_PASSWORD)) {
            try {
                UserForgotPasswordFragment fragment = new UserForgotPasswordFragment();
                mainActivity.getSupportFragmentManager().beginTransaction()
                        .replace(containerId, fragment)
                        .commitAllowingStateLoss();
            } catch (Exception e) {
                Utils.psErrorLog("Error! Can't replace fragment.", e);
            }
        }
    }

    public void navigateToVerifyEmail(MainActivity mainActivity) {
        if (checkFragmentChange(RegFragments.HOME_USER_EMAIL_VERIFY)) {
            try {
                VerifyEmailFragment fragment = new VerifyEmailFragment();
                mainActivity.getSupportFragmentManager().beginTransaction()
                        .replace(containerId, fragment)
                        .commitAllowingStateLoss();
            } catch (Exception e) {
                Utils.psErrorLog("Error! Can't replace fragment.", e);
            }
        }
    }

    public void navigateToAboutUs(MainActivity mainActivity) {
        if (checkFragmentChange(RegFragments.HOME_ABOUTUS)) {
            try {
                AboutUsFragment fragment = new AboutUsFragment();
                mainActivity.getSupportFragmentManager().beginTransaction()
                        .replace(containerId, fragment)
                        .commitAllowingStateLoss();
            } catch (Exception e) {
                Utils.psErrorLog("Error! Can't replace fragment.", e);
            }
        }
    }

    public void navigateToNotificationSetting(Activity activity) {
        Intent intent = new Intent(activity, NotificationSettingActivity.class);
        activity.startActivity(intent);
    }

    public void navigateToPrivacy(Activity activity) {
        Intent intent = new Intent(activity, PrivacyActivity.class);
        activity.startActivity(intent);
    }

    public void navigateToLanguageSetting(MainActivity mainActivity) {
        if (checkFragmentChange(RegFragments.HOME_LANGUAGE_SETTING)) {
            try {
                LanguageFragment fragment = new LanguageFragment();
                mainActivity.getSupportFragmentManager().beginTransaction()
                        .replace(containerId, fragment)
                        .commitAllowingStateLoss();
            } catch (Exception e) {
                Utils.psErrorLog("Error! Can't replace fragment.", e);
            }
        }
    }

    public void navigateToSetting(MainActivity mainActivity) {
        if (checkFragmentChange(RegFragments.HOME_SETTING)) {
            try {
                SettingFragment fragment = new SettingFragment();
                mainActivity.getSupportFragmentManager().beginTransaction()
                        .replace(containerId, fragment)
                        .commitAllowingStateLoss();
            } catch (Exception e) {
                Utils.psErrorLog("Error! Can't replace fragment.", e);
            }
        }
    }

    public void navigateToCategoryList(Activity activity) {
        Intent intent = new Intent(activity, CategoryListActivity.class);
        activity.startActivity(intent);
    }


    public void navigateToCategoryLists(MainActivity mainActivity) {
        if (checkFragmentChange(RegFragments.HOME_CATEGORY)) {
            try {
                CategoryListFragment fragment = new CategoryListFragment();
                mainActivity.getSupportFragmentManager().beginTransaction()
                        .replace(containerId, fragment)
                        .commitAllowingStateLoss();
            } catch (Exception e) {
                Utils.psErrorLog("Error! Can't replace fragment.", e);
            }
        }
    }

    public void navigateToWallpaperFragment(MainActivity mainActivity, String PREMIUM) {
        if (checkFragmentChange(RegFragments.HOME_WALLPAPER)) {
            try {
                Bundle bundle = new Bundle();
                bundle.putString(Constants.PREMIUM, PREMIUM);

                WallpaperContainerFragment fragment = new WallpaperContainerFragment();
                fragment.setArguments(bundle);
                mainActivity.getSupportFragmentManager().beginTransaction()
                        .replace(containerId, fragment)
                        .commitAllowingStateLoss();
            } catch (Exception e) {
                Utils.psErrorLog("Error! Can't replace fragment.", e);
            }
        }
    }

    public void navigateToPremiumFragment(MainActivity mainActivity, String PREMIUM) {
        if (checkFragmentChange(RegFragments.HOME_PREMIUM)) {
            try {
                Bundle bundle = new Bundle();
                bundle.putString(Constants.PREMIUM, PREMIUM);

                PremiumContainerFragment fragment = new PremiumContainerFragment();
                fragment.setArguments(bundle);
                mainActivity.getSupportFragmentManager().beginTransaction()
                        .replace(containerId, fragment)
                        .commitAllowingStateLoss();
            } catch (Exception e) {
                Utils.psErrorLog("Error! Can't replace fragment.", e);
            }
        }
    }

    public void navigateToGifFragment(MainActivity mainActivity, String GIF) {
        if (checkFragmentChange(RegFragments.HOME_GIF)) {
            try {
                Bundle bundle = new Bundle();
                bundle.putString(Constants.GIF, GIF);

                GifContainerFragment fragment = new GifContainerFragment();
                fragment.setArguments(bundle);
                mainActivity.getSupportFragmentManager().beginTransaction()
                        .replace(containerId, fragment)
                        .commitAllowingStateLoss();
            } catch (Exception e) {
                Utils.psErrorLog("Error! Can't replace fragment.", e);
            }
        }
    }

    public void navigateToLiveWallpaper(MainActivity mainActivity) {
        if (checkFragmentChange(RegFragments.HOME_LATEST_LIVE_WALLPAPER)) {
            try {
                LiveWallpaperContainerFragment fragment = new LiveWallpaperContainerFragment();
                mainActivity.getSupportFragmentManager().beginTransaction()
                        .replace(containerId, fragment)
                        .commitAllowingStateLoss();
            } catch (Exception e) {
                Utils.psErrorLog("Error! Can't replace fragment.", e);
            }
        }
    }

    public void navigateToClaimPoint(MainActivity mainActivity) {
        if (checkFragmentChange(RegFragments.HOME_CLAIM_POINT)) {
            try {
                ClaimPointFragment fragment = new ClaimPointFragment();
                mainActivity.getSupportFragmentManager().beginTransaction()
                        .replace(containerId, fragment)
                        .commitAllowingStateLoss();
            } catch (Exception e) {
                Utils.psErrorLog("Error! Can't replace fragment.", e);
            }
        }
    }

    public void navigateToSearchLists(MainActivity mainActivity) {
        if (checkFragmentChange(RegFragments.HOME_SEARCH)) {
            try {
                DashboardSearchFragment fragment = new DashboardSearchFragment();
                mainActivity.getSupportFragmentManager().beginTransaction()
                        .replace(containerId, fragment)
                        .commitAllowingStateLoss();
            } catch (Exception e) {
                Utils.psErrorLog("Error! Can't replace fragment.", e);
            }
        }
    }

    public void navigateToFavoriteLists(MainActivity mainActivity) {
        if (checkFragmentChange(RegFragments.HOME_FAVORITE)) {
            try {
                FavoriteListFragment fragment = new FavoriteListFragment();
                mainActivity.getSupportFragmentManager().beginTransaction()
                        .replace(containerId, fragment)
                        .commitAllowingStateLoss();
            } catch (Exception e) {
                Utils.psErrorLog("Error! Can't replace fragment.", e);
            }
        }
    }

    public void navigateToFavoriteLiveWallpaperLists(MainActivity mainActivity) {
        if (checkFragmentChange(RegFragments.HOME_FAVORITE_LIVE_WALLPAPER)) {
            try {
                FavoriteLiveWallpaperFragment fragment = new FavoriteLiveWallpaperFragment();
                mainActivity.getSupportFragmentManager().beginTransaction()
                        .replace(containerId, fragment)
                        .commitAllowingStateLoss();
            } catch (Exception e) {
                Utils.psErrorLog("Error! Can't replace fragment.", e);
            }
        }
    }

    public void navigateToDownloadLiveWallpaperLists(MainActivity mainActivity) {
        if (checkFragmentChange(RegFragments.HOME_DOWNLOADED_LIVE_WALLPAPER)) {
            try {
                DownloadLiveWallpaperListFragment fragment = new DownloadLiveWallpaperListFragment();
                mainActivity.getSupportFragmentManager().beginTransaction()
                        .replace(containerId, fragment)
                        .commitAllowingStateLoss();
            } catch (Exception e) {
                Utils.psErrorLog("Error! Can't replace fragment.", e);
            }
        }
    }

    public void navigateToUploadPhoto(MainActivity mainActivity) {
        if (checkFragmentChange(RegFragments.HOME_UPLOAD_PHOTO)) {
            try {
                UploadedWallpaperListFragment fragment = new UploadedWallpaperListFragment();
                mainActivity.getSupportFragmentManager().beginTransaction()
                        .replace(containerId, fragment)
                        .commitAllowingStateLoss();
            } catch (Exception e) {
                Utils.psErrorLog("Error! Can't replace fragment.", e);
            }
        }
    }


    public void navigateToDownloadedLists(MainActivity mainActivity) {
        if (checkFragmentChange(RegFragments.HOME_DOWNLOADED)) {
            try {
                DownloadedListFragment fragment = new DownloadedListFragment();
                mainActivity.getSupportFragmentManager().beginTransaction()
                        .replace(containerId, fragment)
                        .commitAllowingStateLoss();
            } catch (Exception e) {
                Utils.psErrorLog("Error! Can't replace fragment.", e);
            }
        }
    }

    public void navigateToLatestWallpaperList(Activity activity, WallpaperParamsHolder holder, String flag) {
        Intent intent = new Intent(activity, WallpaperListWithFilterActivity.class);
        intent.putExtra(Constants.INTENT__WALLPAPER_PARAM_HOLDER, holder);
        intent.putExtra(Constants.INTENT__FLAG, flag);
        activity.startActivity(intent);
    }

//    public void navigateToLatestLiveWallpaperList(Activity activity, LiveWallpaperParamsHolder holder) {
//        Intent intent = new Intent(activity, LiveWallpaperFilterListActivity.class);
//        intent.putExtra(Constants.INTENT__LIVE_WALLPAPER_PARAM_HOLDER, holder);
//        activity.startActivity(intent);
//    }

    public void navigateToWallpaperDetail(Activity activity, Wallpaper wallpaper, WallpaperParamsHolder wallpaperParamsHolder) {

        Intent intent = new Intent(activity, WallpaperDetailActivity.class);
        intent.putExtra(Constants.INTENT__WALLPAPER_ID, wallpaper.wallpaper_id);
        intent.putExtra(Constants.INTENT__WALLPAPER_PARAM_HOLDER, wallpaperParamsHolder);
        activity.startActivity(intent);

    }

    public void navigateToLiveWallpaperDetail(Activity activity,Wallpaper liveWallpaper, WallpaperParamsHolder liveWallpaperParamsHolder) {

        Intent intent = new Intent(activity, VideoPlayActivity.class);
        intent.putExtra(Constants.INTENT__LIVE_WALLPAPER_ID, liveWallpaper.wallpaper_id);
        intent.putExtra(Constants.INTENT__LIVE_WALLPAPER_PATH, liveWallpaper.default_video.img_path);
        intent.putExtra(Constants.INTENT__WALLPAPER_PARAM_HOLDER, liveWallpaperParamsHolder);
        activity.startActivity(intent);

    }

    public void navigateToFavWallpaperDetail(Activity activity, Wallpaper wallpaper, WallpaperParamsHolder wallpaperParamsHolder) {

        Intent intent = new Intent(activity, WallpaperDetailActivity.class);
        intent.putExtra(Constants.INTENT__WALLPAPER_ID, wallpaper.wallpaper_id);
        intent.putExtra(Constants.INTENT__WALLPAPER_PARAM_HOLDER, wallpaperParamsHolder);
        activity.startActivity(intent);

    }

    public void navigateToDashBoardSetting2(MainActivity mainActivity, Boolean replaceIt) {
        if (checkFragmentChange(RegFragments.HOME_DASHBOARD2) || replaceIt) {
            try {
                DashboardFragment fragment2 = new DashboardFragment();
                mainActivity.getSupportFragmentManager().beginTransaction()
                        .replace(containerId, fragment2)
                        .commitAllowingStateLoss();
            } catch (Exception e) {
                Utils.psErrorLog("Error! Can't replace fragment.", e);
            }
        }
    }

    public void navigateToProfileEditActivity(Activity activity) {
        Intent intent = new Intent(activity, ProfileEditActivity.class);
        activity.startActivity(intent);
    }

    public void navigateToSearchActivity(Activity activity, WallpaperParamsHolder filterHolder) {
        Intent intent = new Intent(activity, SearchActivity.class);
        intent.putExtra(Constants.INTENT__WALLPAPER_PARAM_HOLDER, filterHolder);

        activity.startActivityForResult(intent, Utils.REQUEST_TO_SEARCH);
    }

    public void navigateBackFromSearchActivity(Activity activity, WallpaperParamsHolder wallpaperParamsHolder) {

        Intent intent = new Intent();
        intent.putExtra(Constants.INTENT__WALLPAPER_PARAM_HOLDER, wallpaperParamsHolder);

        if (wallpaperParamsHolder == null) {
            activity.setResult(Utils.RESULT_FROM_SEARCH);
        } else {
            activity.setResult(Utils.RESULT_FROM_SEARCH, intent);
        }

    }

    public void navigateBackFromSearchLiveWallpaperActivity(Activity activity, WallpaperParamsHolder wallpaperParamsHolder) {

        Intent intent = new Intent();
        intent.putExtra(Constants.INTENT__WALLPAPER_PARAM_HOLDER, wallpaperParamsHolder);

        if (wallpaperParamsHolder == null) {
            activity.setResult(Utils.RESULT_FROM_SEARCH);
        } else {
            activity.setResult(Utils.RESULT_FROM_SEARCH, intent);
        }

    }

    public void navigateToUserLoginActivity(Activity activity) {
        Intent intent = new Intent(activity, UserLoginActivity.class);
        activity.startActivity(intent);
    }

    public void navigateToUserRegisterActivity(Activity activity) {
        Intent intent = new Intent(activity, UserRegisterActivity.class);
        activity.startActivity(intent);
    }

    public void navigateToVerifyEmailActivity(Activity activity) {
        Intent intent = new Intent(activity, VerifyEmailActivity.class);
        activity.startActivity(intent);
    }

//    public void navigateToCropImageActivity(Activity activity, String imageUri) {
//        Intent intent = new Intent(activity, UcropActivity.class);
//        intent.putExtra(Config.INTENT__IMAGE_PATH,imageUri);
//        activity.startActivity(intent);
//    }

    public void navigateToUserForgotPasswordActivity(Activity activity) {
        Intent intent = new Intent(activity, UserForgotPasswordActivity.class);
        activity.startActivity(intent);
    }

    public void navigateToPasswordChangeActivity(Activity activity) {
        Intent intent = new Intent(activity, PasswordChangeActivity.class);
        activity.startActivity(intent);
    }

    public void navigateToContactUs(MainActivity mainActivity) {
        if (checkFragmentChange(RegFragments.HOME_CONTACTUS)) {
            try {
                ContactUsFragment fragment = new ContactUsFragment();
                mainActivity.getSupportFragmentManager().beginTransaction()
                        .replace(containerId, fragment)
                        .commitAllowingStateLoss();
            } catch (Exception e) {
                Utils.psErrorLog("Error! Can't replace fragment.", e);
            }
        }
    }

    public void navigateToClaimPointActivity(Activity activity) {
        Intent intent = new Intent(activity, ClaimPointActivity.class);
        activity.startActivity(intent);
    }

    public void navigateBackToSearchFragment(FragmentActivity fragmentActivity, String cat_id, String cat_Name) {
        Intent intent = new Intent();
        intent.putExtra(Constants.INTENT__CAT_ID, cat_id);
        intent.putExtra(Constants.INTENT__CAT_NAME, cat_Name);

        fragmentActivity.setResult(Constants.RESULT_CODE__SEARCH_WITH_CATEGORY, intent);
    }

    public void navigateBackToUploadFragmentWithFree(FragmentActivity fragmentActivity, String free) {
        Intent intent = new Intent();
        intent.putExtra(Constants.INTENT__WALLPAPER_FREE, free);

        fragmentActivity.setResult(Constants.RESULT_CODE__WALLPAPER_FREE, intent);
    }

    public void navigateBackToUploadFragmentWithPremium(FragmentActivity fragmentActivity, String premium) {
        Intent intent = new Intent();
        intent.putExtra(Constants.INTENT__WALLPAPER_FREE, premium);

        fragmentActivity.setResult(Constants.RESULT_CODE__WALLPAPER_PREMIUM, intent);
    }

    public void navigateBackToSearchFragmentFromColor(FragmentActivity fragmentActivity, String color_id, String colorName, String colorCode) {
        Intent intent = new Intent();
        intent.putExtra(Constants.INTENT__COLOR_ID, color_id);
        intent.putExtra(Constants.INTENT__COLOR_NAME, colorName);
        intent.putExtra(Constants.INTENT__COLOR_CODE, colorCode);

        fragmentActivity.setResult(Constants.RESULT_CODE__SEARCH_WITH_COLOR, intent);
    }

    public void navigateToCategorySelectionActivity(FragmentActivity fragmentActivity, String cat_id) {
        Intent intent = new Intent(fragmentActivity, CategorySelectionListActivity.class);

        intent.putExtra(Constants.INTENT__CAT_ID, cat_id);

        fragmentActivity.startActivityForResult(intent, Constants.REQUEST_CODE__SEARCH_FRAGMENT);
    }

    public void navigateToWallpapersTypeActivity(FragmentActivity fragmentActivity, String wallpaperTypes) {
        Intent intent = new Intent(fragmentActivity, WallpaperTypesSelectionListActivity.class);

        intent.putExtra(Constants.INTENT__WALLPAPER_TYPE,wallpaperTypes);
        fragmentActivity.startActivityForResult(intent, Constants.REQUEST_CODE__SEARCH_FRAGMENT);
    }

    public void navigateToColorSelectionActivity(FragmentActivity fragmentActivity, String color_id) {
        Intent intent = new Intent(fragmentActivity, ColorSelectionListActivity.class);

        intent.putExtra(Constants.INTENT__COLOR_ID, color_id);

        fragmentActivity.startActivityForResult(intent, Constants.REQUEST_CODE__SEARCH_FRAGMENT);
    }

    public void navigateToImageUploadActivity(FragmentActivity fragmentActivity) {
        Intent intent = new Intent(fragmentActivity, UploadWallpaperActivity.class);

        fragmentActivity.startActivity(intent);
    }

    public void navigateToPrivacyPolicyActivity(Activity activity) {
        Intent intent = new Intent(activity, PrivacyPolicyActivity.class);
        activity.startActivity(intent);
    }

    public void navigateToImageUploadActivityForEdit(FragmentActivity fragmentActivity, String wallpaperId) {
        Intent intent = new Intent(fragmentActivity, UploadWallpaperActivity.class);

        intent.putExtra(Constants.WALLPAPER, wallpaperId);

        fragmentActivity.startActivity(intent);
    }

    public void getImageFromGallery(Activity activity) {
        if (Utils.isStoragePermissionGranted(activity)) {

            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            activity.startActivityForResult(Intent.createChooser(intent, "Select Photo"), Utils.RESULT_LOAD_IMAGE_CATEGORY);
        }

    }

    public void navigateToPlayStore(FragmentActivity fragmentActivity) {
//        try {
//            fragmentActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Config.PLAYSTORE_MARKET_URL)));
//        } catch (android.content.ActivityNotFoundException anfe) {
//            fragmentActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Config.PLAYSTORE_HTTP_URL)));
//        }

        Uri uri = Uri.parse(Constants.PLAYSTORE_MARKET_URL_FIX + fragmentActivity.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            fragmentActivity.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            fragmentActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.PLAYSTORE_HTTP_URL_FIX + fragmentActivity.getPackageName())));
        }
    }

    public void navigateToForceUpdateActivity(FragmentActivity fragmentActivity, String msg, String title) {

        Intent intent = new Intent(fragmentActivity, ForceUpdateActivity.class);

        intent.putExtra(Constants.APPINFO_FORCE_UPDATE_TITLE, title);
        intent.putExtra(Constants.APPINFO_FORCE_UPDATE_MSG, msg);

        fragmentActivity.startActivity(intent);
    }

    public void navigateToPhoneVerifyActivity(Activity activity,String number,String userName) {
        Intent intent = new Intent(activity, VerifyMobileActivity.class);
        intent.putExtra(Constants.USER_PHONE, number);
        intent.putExtra(Constants.USER_NAME, userName);
        activity.startActivity(intent);
    }

    public void navigateToPhoneVerifyFragment(MainActivity mainActivity, String number, String userName) {
        if (checkFragmentChange(RegFragments.HOME_PHONE_VERIFY)) {
            try {
                VerifyMobileFragment fragment = new VerifyMobileFragment();
                mainActivity.getSupportFragmentManager().beginTransaction()
                        .replace(containerId, fragment)
                        .commitAllowingStateLoss();

                Bundle args = new Bundle();
                args.putString(Constants.USER_PHONE, number);
                args.putString(Constants.USER_NAME, userName);
                fragment.setArguments(args);
            } catch (Exception e) {
                Utils.psErrorLog("Error! Can't replace fragment.", e);
            }
        }
    }

    public void navigateToPhoneLoginFragment(MainActivity mainActivity) {
        if (checkFragmentChange(RegFragments.HOME_PHONE_LOGIN)) {
            try {
                PhoneLoginFragment fragment = new PhoneLoginFragment();
                mainActivity.getSupportFragmentManager().beginTransaction()
                        .replace(containerId, fragment)
                        .commitAllowingStateLoss();
            } catch (Exception e) {
                Utils.psErrorLog("Error! Can't replace fragment.", e);
            }
        }
    }



    //region Private methods
    private Boolean checkFragmentChange(RegFragments regFragments) {
        if (currentFragment != regFragments) {
            currentFragment = regFragments;
            return true;
        }

        return false;
    }

    public void navigateToPhoneLoginActivity(Activity activity) {
        Intent intent = new Intent(activity, PhoneLoginActivity.class);
        activity.startActivity(intent);
    }

    //endregion

    /**
     * Remark : This enum is only for MainActivity,
     * For the other fragments, no need to register here
     **/
    private enum RegFragments {
        HOME_FRAGMENT,
        HOME_USER_LOGIN,
        HOME_USER_REGISTER,
        HOME_USER_FOGOT_PASSWORD,
        HOME_USER_EMAIL_VERIFY,
        HOME_ABOUTUS,
        HOME_PRIVACY,
        HOME_CONTACTUS,
        HOME_FAVORITE,
        HOME_FAVORITE_LIVE_WALLPAPER,
        HOME_UPLOAD_PHOTO,
        HOME_DOWNLOADED,
        HOME_DOWNLOADED_LIVE_WALLPAPER,
        HOME_TRENDING,
        HOME_SEARCH,
        HOME_LATEST_WALLPAPER,
        HOME_LATEST_LIVE_WALLPAPER,
        HOME_WALLPAPER,
        HOME_PREMIUM,
        HOME_CATEGORY,
        HOME_NOTI_SETTING,
        HOME_LANGUAGE_SETTING,
        HOME_DASHBOARD,
        HOME_DASHBOARD3,
        HOME_DASHBOARD2,
        HOME_CLAIM_POINT,
        HOME_SETTING,
        HOME_ABOUT,
        HOME_GIF,
        HOME_PHONE_VERIFY,
        HOME_PHONE_LOGIN
    }

    //endregion
}
