package com.example.appwhowroteit;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

public class NetworkUtils {

    // Base URL for Books API.
    private static final String BOOK_BASE_URL =  "https://www.googleapis.com/books/v1/volumes?";
    // Parameter for the search string.
    private static final String QUERY_PARAM = "q";
    // Parameter that limits search results.
    private static final String MAX_RESULTS = "maxResults";
    // Parameter to filter by print type.
    private static final String PRINT_TYPE = "printType";

    private static  final String ACCESS_INFO = "accessInfo.epub";

    private static String LOG_TAG = NetworkUtils.class.getSimpleName();

    static String getBookInfo(String query) {

        HttpURLConnection urlConnection = null; // objeto responsável por criar as solicitações
        BufferedReader reader = null; // objeto responsável por ler dos dados recebidos como resposta da requisição
        String bookJSONString = null;

        try {

            // criamos um objeto Uri para que possamos montar nosso request antes de transformá-lo em URL.
            Uri builtUri = Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, query)
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .appendQueryParameter(ACCESS_INFO,"isAvailable=true")
                    .build();

            // criamos um objeto URL, poism iremos solicitar dados de uma url remota
            URL requestURL = new URL(builtUri.toString());

            // Primeiro passo: Abrir conexão HTTP
            urlConnection = (HttpURLConnection) requestURL.openConnection();

            // Segundo Passo: Definir o method da requisição
            urlConnection.setRequestMethod("GET");

            // Terceiro Passo: Ligar/Ativar a conexão - Esse método de fato irá executar nosso request.
            urlConnection.connect();


            /*
            *
            * A partir daqui, sua solicitação já foi processada e seu response já
            * estará disponível.
            *
            * */


            // Quarto Passo: Descerializar a resposta da conexão, para isso usamos um objeto InputStream.
            InputStream inputStream = urlConnection.getInputStream();

            // Quinto Passo: Definir o BufferedReader preenchido com um objeto inputStream
            reader = new BufferedReader(new InputStreamReader(inputStream));

            // (Opcional): Definir um StringBuilder para facilitar a concatenação dos valores.
            StringBuilder builder = new StringBuilder();


            /*
            *   Sexto Passo: Ler a primeira linha do objeto BufferedReader e em seguida criar um loop
            *   que percorra todos os dados restantes no mesmo, assim, configurando a StringBuilder
            *   da forma que você preferir.
            *
            *   OBS: atribua o valor da linha atual usando o readLine() diretamente no loop
            *
            * */

            String row;
            while((row = reader.readLine()) != null){
                builder.append(row);
                builder.append("\n"); // Ou seja, será gerado uma String com cada valor em linhas separadas
            }

            if(builder.length() == 0) {
                return null;
            }

            bookJSONString = builder.toString(); // Atribuir a StringBuilder montada a nossa variavel de retorno.

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            // Passo final: Fechar todos os nossos objetos: HttpUrlConnection e o BufferedReader.

            if(urlConnection != null) urlConnection.disconnect();
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        Log.d(LOG_TAG, bookJSONString);
        return bookJSONString;

    }

}
