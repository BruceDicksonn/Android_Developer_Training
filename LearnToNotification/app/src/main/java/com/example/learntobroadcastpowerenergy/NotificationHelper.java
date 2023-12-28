package com.example.learntobroadcastpowerenergy;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import java.util.ArrayList;

/*
 * Created by Bruce Dickson
 */


/**
 *
 * Essa classe está preparada para atender a diversos tipos diferentes de notificações e conseguir lidar
 * com as mesmas.
 *
 * Criei porque senti a necessidade de implementá-la no meu ambiente de trabalho para então adaptar
 * os serviços e conseguir gerenciar as notificações enviadas do app.
 *
 * **/
public class NotificationHelper {

    private static NotificationManager notificationManager;
    private Context context;

    /** Todas as notificações e canais são preenchidos em listas **/
    private static ArrayList<MyNotification> listNotifications = new ArrayList<MyNotification>();
    private static ArrayList<NotificationChannel> listChannels = new ArrayList<NotificationChannel>();


    /** Cada notificação do app deve ser registrada como um item do enum abaixo **/
    public enum EnumNotifications {

        ECO_ENERGY_MODE("Economia de energia", "O modo de economia de energia compromete a captura de pontos gps, por favor, desative-o.");

        public String title;
        public String content;
        EnumNotifications(String title, String content) {
            this.title = title;
            this.content = content;
        }

    }

    public NotificationHelper(Context context) {
        this.context = context;
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        populateListNotification();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) createNotificationChannels();
    }

    private void populateListNotification() {

        for( EnumNotifications item : EnumNotifications.values() ) {

            int requestCode = item.ordinal();

            Intent intent = new Intent( context, MainActivity.class );
            PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            RemoteViews customView =  new RemoteViews(context.getPackageName(), R.layout.custom_notification);
            customView.setTextViewText(R.id.custom_notification_title, item.title);
            customView.setTextViewText(R.id.custom_notification_content, item.content);

            MyNotification notification = (MyNotification) new MyNotification(context, requestCode , item.title, item.content)
                    .setSmallIcon(android.R.drawable.ic_dialog_info) /** escolha o icone que preferir aqui **/
//                    .setContentTitle( item.title )
                    .setContentIntent( pendingIntent )
                    .setContent( customView )
                    .setPriority( NotificationCompat.PRIORITY_HIGH )
                    .setAutoCancel(false) /** cancela a notificação ao clicar */
                    .setOngoing(false); /** não permite que o usuario feche a notificação **/

            /** estiliza a notificação para que seja possivel expandí-la e visualizar o conteúdo completo da mensagem **/
            NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
            bigTextStyle.setBigContentTitle( notification.getContentTitle() );
            bigTextStyle.bigText( notification.getContentMessage() );

//            notification.setStyle(bigTextStyle);

            listNotifications.add( notification );
        }

    }

    /**
     *
     * O código abaixo serve para criar canais específicos para cadas notificação
     * que seu app for enviar, esse tipo de abordagem necessita de um nível especifico
     * de API para que possa funcionar, mas a classe está preparada para lidar com dispositivos
     * mais antigos tambem.
     *
     * **/
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannels(){

         /**
         * No código abaixo crio um canal específico para notificar informações a respeito do estado
         * no dispositivo do usuário, se está ou não com o modo economia de energia ativo.
         **/

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        listNotifications.forEach(  notification -> {


            /**
             * @id: Para que possamos eventualmente ter acesso direto ao channel
             * @title: O que aparecerá na bandeja de notificações do dispositivo nas informacoes do app
             * @importance: O nivel de relevancia desse canal
             *
             * **/
            NotificationChannel channel = new NotificationChannel("CHANNEL_" + notification.getContentTitle(),notification.getContentTitle(),NotificationManager.IMPORTANCE_HIGH );
            notificationManager.createNotificationChannel( channel );

            /**
             * Podemos setar o channelID no construtor da notificacao, mas,
             * como trabalhamos em escopos diferentes para cada processo, definimos o channel via set
             * para cada Notification da lista
             **/
            notification.setChannelId( channel.getId() );

            listChannels.add( channel );

        });

    }

    public MyNotification searchNotification(EnumNotifications enumNotifications){

        for(MyNotification curr : listNotifications ) {
            if( curr.getContentTitle().equals( enumNotifications.title )){
                return curr;
            }
        }

        return null;
    }

    public synchronized void showNotification( EnumNotifications enumNotifications ){

        MyNotification notification = searchNotification( enumNotifications );

        if(notification != null && notificationManager != null) {
            notificationManager.notify( notification.getID(), notification.build() );
        }

    }

    public synchronized void cancelNotification( EnumNotifications enumNotifications ) {

        MyNotification notification = searchNotification( enumNotifications );

        if(notificationManager != null){
            notificationManager.cancel( notification.getID() );
        }
    }

    public static void clearAllNotifications( Context context ){
        for(MyNotification notification : listNotifications) {
            notificationManager.cancel( notification.getID() );
        }
    }

}