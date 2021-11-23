// Generated by view binder compiler. Do not edit!
package com.example.myapplication.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.myapplication.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final AppCompatButton btn00;

  @NonNull
  public final AppCompatButton btn01;

  @NonNull
  public final AppCompatButton btn02;

  @NonNull
  public final AppCompatButton btn10;

  @NonNull
  public final AppCompatButton btn11;

  @NonNull
  public final AppCompatButton btn12;

  @NonNull
  public final AppCompatButton btn20;

  @NonNull
  public final AppCompatButton btn21;

  @NonNull
  public final AppCompatButton btn22;

  @NonNull
  public final AppCompatButton btn30;

  @NonNull
  public final AppCompatButton btn31;

  @NonNull
  public final AppCompatButton btn32;

  private ActivityMainBinding(@NonNull LinearLayout rootView, @NonNull AppCompatButton btn00,
      @NonNull AppCompatButton btn01, @NonNull AppCompatButton btn02,
      @NonNull AppCompatButton btn10, @NonNull AppCompatButton btn11,
      @NonNull AppCompatButton btn12, @NonNull AppCompatButton btn20,
      @NonNull AppCompatButton btn21, @NonNull AppCompatButton btn22,
      @NonNull AppCompatButton btn30, @NonNull AppCompatButton btn31,
      @NonNull AppCompatButton btn32) {
    this.rootView = rootView;
    this.btn00 = btn00;
    this.btn01 = btn01;
    this.btn02 = btn02;
    this.btn10 = btn10;
    this.btn11 = btn11;
    this.btn12 = btn12;
    this.btn20 = btn20;
    this.btn21 = btn21;
    this.btn22 = btn22;
    this.btn30 = btn30;
    this.btn31 = btn31;
    this.btn32 = btn32;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn00;
      AppCompatButton btn00 = ViewBindings.findChildViewById(rootView, id);
      if (btn00 == null) {
        break missingId;
      }

      id = R.id.btn01;
      AppCompatButton btn01 = ViewBindings.findChildViewById(rootView, id);
      if (btn01 == null) {
        break missingId;
      }

      id = R.id.btn02;
      AppCompatButton btn02 = ViewBindings.findChildViewById(rootView, id);
      if (btn02 == null) {
        break missingId;
      }

      id = R.id.btn10;
      AppCompatButton btn10 = ViewBindings.findChildViewById(rootView, id);
      if (btn10 == null) {
        break missingId;
      }

      id = R.id.btn11;
      AppCompatButton btn11 = ViewBindings.findChildViewById(rootView, id);
      if (btn11 == null) {
        break missingId;
      }

      id = R.id.btn12;
      AppCompatButton btn12 = ViewBindings.findChildViewById(rootView, id);
      if (btn12 == null) {
        break missingId;
      }

      id = R.id.btn20;
      AppCompatButton btn20 = ViewBindings.findChildViewById(rootView, id);
      if (btn20 == null) {
        break missingId;
      }

      id = R.id.btn21;
      AppCompatButton btn21 = ViewBindings.findChildViewById(rootView, id);
      if (btn21 == null) {
        break missingId;
      }

      id = R.id.btn22;
      AppCompatButton btn22 = ViewBindings.findChildViewById(rootView, id);
      if (btn22 == null) {
        break missingId;
      }

      id = R.id.btn30;
      AppCompatButton btn30 = ViewBindings.findChildViewById(rootView, id);
      if (btn30 == null) {
        break missingId;
      }

      id = R.id.btn31;
      AppCompatButton btn31 = ViewBindings.findChildViewById(rootView, id);
      if (btn31 == null) {
        break missingId;
      }

      id = R.id.btn32;
      AppCompatButton btn32 = ViewBindings.findChildViewById(rootView, id);
      if (btn32 == null) {
        break missingId;
      }

      return new ActivityMainBinding((LinearLayout) rootView, btn00, btn01, btn02, btn10, btn11,
          btn12, btn20, btn21, btn22, btn30, btn31, btn32);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
