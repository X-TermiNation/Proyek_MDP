// Generated by view binder compiler. Do not edit!
package com.example.myapplication.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityHomeBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final FrameLayout FrameLayout;

  @NonNull
  public final BottomNavigationView bottomMenu;

  private ActivityHomeBinding(@NonNull LinearLayout rootView, @NonNull FrameLayout FrameLayout,
      @NonNull BottomNavigationView bottomMenu) {
    this.rootView = rootView;
    this.FrameLayout = FrameLayout;
    this.bottomMenu = bottomMenu;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.FrameLayout;
      FrameLayout FrameLayout = ViewBindings.findChildViewById(rootView, id);
      if (FrameLayout == null) {
        break missingId;
      }

      id = R.id.bottom_menu;
      BottomNavigationView bottomMenu = ViewBindings.findChildViewById(rootView, id);
      if (bottomMenu == null) {
        break missingId;
      }

      return new ActivityHomeBinding((LinearLayout) rootView, FrameLayout, bottomMenu);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}