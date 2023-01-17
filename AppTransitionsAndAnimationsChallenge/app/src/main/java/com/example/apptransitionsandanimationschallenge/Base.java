package com.example.apptransitionsandanimationschallenge;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

public class Base extends AppCompatActivity {


    // Constant used for the Intent to indicate the type of transition.
    private static final String TRANSITION_TYPE = "Transition Type";

    // Constants for shared element transitions.
    private static final String ANDROID_TRANSITION = "switchAndroid";
    private static final String BLUE_TRANSITION = "switchBlue";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         *
         * Verifica se foi passado o tipo da transição
         *
         * */
        if(getIntent().hasExtra(TRANSITION_TYPE)){
            switch (getIntent().getStringExtra(TRANSITION_TYPE)){
                case "Explode":
                    getWindow().setEnterTransition(new Explode());
                break;
                case "Slide":
                    getWindow().setEnterTransition(new Slide());
                break;
                case "Fade":
                    getWindow().setEnterTransition(new Fade());
                break;
                default: break;
            }
        }

    }

    /**
     * Reinicie a atividade com uma animação Explodir.
     *
     * @param context O contexto do aplicativo.
     * @param imageView O imageView que foi clicado.
     */
    protected void explodeTransition(final Context context, ImageView imageView) {
        imageView.setOnClickListener(new View.OnClickListener() {

            /**  Reinicia a atividade com as informações de transição. **/
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, context.getClass());
                intent.putExtra(TRANSITION_TYPE, "Explode");

                getWindow().setExitTransition(new Explode());

                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle());

            }
        });
    }


    /**
     * Reinicie a atividade com uma animação Fade.
     *
     * @param context O contexto do aplicativo.
     * @param imageView O imageView que foi clicado.
     */
    protected void fadeTransition(final Context context, ImageView imageView) {

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**  Reinicia a atividade com as informações de transição. **/
                Intent intent = new Intent(context, context.getClass());
                intent.putExtra(TRANSITION_TYPE, "Fade");

                getWindow().setExitTransition(new Fade());

                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(
                        (Activity) context).toBundle()
                );

            }
        });

    }

    /**
     *
     * * Animar um ImageView 360 graus e vice-versa.
     *
     * @param imageView O ImageView que foi clicado.
     *
     * **/
    protected  void rotateView(ImageView imageView) {

        // Cria o animador de objetos com as opções desejadas.
        final ObjectAnimator animator = ObjectAnimator.ofFloat(
                imageView, View.ROTATION, 0f, 360f
        );

        animator.setDuration(1000);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(1);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animator.start();
            }
        });

    }

    /**

     * Crie uma transição de elemento compartilhado entre as atividades
     * com dois elementos comuns
     *
     * @param imageView Imagem do Android que ativa o elemento compartilhado
     * transição quando clicado.
     * @param otherImage O ImageView que irá trocar com o Android
     * ImageView.
     * @param intent A intenção que contém a atividade atual e de destino.
     * @param context O contexto do aplicativo.

     */

    protected void switchAnimation(
            final ImageView imageView,
            final ImageView otherImage,
            final Intent intent,
            final Context context){

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Defina os detalhes da transição e inicie a segunda atividade.
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                        (Activity) context,
                        Pair.create(imageView,ANDROID_TRANSITION),
                        Pair.create(otherImage, BLUE_TRANSITION)
                );

                startActivity(intent, options.toBundle());

            }
        });

    }

}
