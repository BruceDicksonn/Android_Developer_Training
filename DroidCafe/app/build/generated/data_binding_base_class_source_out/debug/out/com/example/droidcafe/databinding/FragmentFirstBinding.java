// Generated by view binder compiler. Do not edit!
package com.example.droidcafe.databinding;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.viewbinding.ViewBinding;
import android.viewbinding.ViewBindings;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.droidcafe.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentFirstBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonFirst;

  @NonNull
  public final ImageView donut;

  @NonNull
  public final TextView donutDecription;

  @NonNull
  public final ImageView froyo;

  @NonNull
  public final TextView froyoDecription;

  @NonNull
  public final ImageView iceCream;

  @NonNull
  public final TextView iceCreamDecription;

  @NonNull
  public final TextView textintro;

  private FragmentFirstBinding(@NonNull ConstraintLayout rootView, @NonNull Button buttonFirst,
      @NonNull ImageView donut, @NonNull TextView donutDecription, @NonNull ImageView froyo,
      @NonNull TextView froyoDecription, @NonNull ImageView iceCream,
      @NonNull TextView iceCreamDecription, @NonNull TextView textintro) {
    this.rootView = rootView;
    this.buttonFirst = buttonFirst;
    this.donut = donut;
    this.donutDecription = donutDecription;
    this.froyo = froyo;
    this.froyoDecription = froyoDecription;
    this.iceCream = iceCream;
    this.iceCreamDecription = iceCreamDecription;
    this.textintro = textintro;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentFirstBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentFirstBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_first, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentFirstBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button_first;
      Button buttonFirst = ViewBindings.findChildViewById(rootView, id);
      if (buttonFirst == null) {
        break missingId;
      }

      id = R.id.donut;
      ImageView donut = ViewBindings.findChildViewById(rootView, id);
      if (donut == null) {
        break missingId;
      }

      id = R.id.donut_decription;
      TextView donutDecription = ViewBindings.findChildViewById(rootView, id);
      if (donutDecription == null) {
        break missingId;
      }

      id = R.id.froyo;
      ImageView froyo = ViewBindings.findChildViewById(rootView, id);
      if (froyo == null) {
        break missingId;
      }

      id = R.id.froyo_decription;
      TextView froyoDecription = ViewBindings.findChildViewById(rootView, id);
      if (froyoDecription == null) {
        break missingId;
      }

      id = R.id.ice_cream;
      ImageView iceCream = ViewBindings.findChildViewById(rootView, id);
      if (iceCream == null) {
        break missingId;
      }

      id = R.id.ice_cream_decription;
      TextView iceCreamDecription = ViewBindings.findChildViewById(rootView, id);
      if (iceCreamDecription == null) {
        break missingId;
      }

      id = R.id.textintro;
      TextView textintro = ViewBindings.findChildViewById(rootView, id);
      if (textintro == null) {
        break missingId;
      }

      return new FragmentFirstBinding((ConstraintLayout) rootView, buttonFirst, donut,
          donutDecription, froyo, froyoDecription, iceCream, iceCreamDecription, textintro);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
