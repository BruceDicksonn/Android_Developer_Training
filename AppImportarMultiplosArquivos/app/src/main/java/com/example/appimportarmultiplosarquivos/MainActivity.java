package com.example.appimportarmultiplosarquivos;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appimportarmultiplosarquivos.adapters.ArquivoAdapter;
import com.example.appimportarmultiplosarquivos.commons.ArquivoListeners;
import com.example.appimportarmultiplosarquivos.dal.DaoArquivo;
import com.example.appimportarmultiplosarquivos.databinding.ActivityMainBinding;

import com.example.appimportarmultiplosarquivos.model.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements ArquivoListeners {

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    ActivityMainBinding binding;

    DaoArquivo daoArquivo;
    List<Arquivo> listaArquivosSalvos = new ArrayList<>();

    ArquivoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        daoArquivo = MyApplication.database.daoArquivo();

    }

    @Override
    protected void onResume() {
        super.onResume();

        requestStoragePermission();

        executorService.execute(()-> {

            List<Arquivo> arquivos =  daoArquivo.obtemArquivos();
            if(arquivos != null) {

                listaArquivosSalvos = arquivos;
                adapter = new ArquivoAdapter(MainActivity.this, listaArquivosSalvos, daoArquivo, this);

                runOnUiThread(()->{
                    binding.recycler.setAdapter(adapter);
                    binding.recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
                });

            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0 && resultCode == RESULT_OK) {

            // clipData não ser null significa que mais de um arquivo foi selecionado
            if(data.getClipData() != null) {

                int count = data.getClipData().getItemCount();
                for(int i = 0; i < count; i++) {
                    Uri uri = data.getClipData().getItemAt(i).getUri();
                    salvarArquivo(uri);
                }

            // já o data não ser null indica que um arquivo apenas foi selecionado
            } else if( data.getData() != null ) {
                Uri uri = data.getData();
                salvarArquivo(uri);
            }

        }

    }

    private String obterExtensaoArquivo(Uri uri) {

        String mimeType = getContentResolver().getType(uri);

        if (mimeType != null) {
            return String.format(".%s", MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType));
        } else {
            // O URI pode não ter um MIME type, tente derivar da string do próprio caminho
            String caminho = uri.getPath();
            if (caminho != null) {
                int ultimoPonto = caminho.lastIndexOf('.');
                if (ultimoPonto != -1) {
                    return caminho.substring(ultimoPonto + 1); // Retorna a extensão
                }
            }
        }
        return null; // Extensão não encontrada
    }

    private void salvarArquivo(Uri uri) {

        if(!requestStoragePermission()) return;

        try {

            // Esse objeto stream conterá as informações e dados necessários para salvarmos uma cópia no diretório do app
            InputStream stream = getContentResolver().openInputStream(uri);

            /**
             * Caso queira salvar em alguma pasta pública, como Downloads, use a variável path quando for criar uma instância de File.
             *
             * Ex:
             *      File file = new File(path, filename);
             *
             * **/
            String path     = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
            String ext      = obterExtensaoArquivo(uri);
            String filename = new SimpleDateFormat("yyyyMMdd_hhmmss_SSSS").format(new Date()) + ext;

            /**
             *
             * @getExternalFilesDir(null) - encaminhará o arquivo para Android/Data/com.example.[nome_do_seu_app].files;
             * caso queira criar uma pasta específica para cada arquivo que sua aplicação salve de acordo com a extensão,
             * basta concatenar passando um nome específico da pasta que o arquivo deverá ser escrito;
             *
             * Ex: File file = new File(getExternalFilesDir(null) + "/imagens", filename);
             *
             * **/
            File file = new File(getExternalFilesDir(null) , filename);

            // cria o diretório se não existir no dispositivo
            if(!file.getParentFile().exists()) { file.getParentFile().mkdirs(); }

            FileOutputStream fos = new FileOutputStream( file );

            byte[] buffer = new byte[1024];
            int length;

            while( (length = stream.read(buffer)) > 0 ) {
                fos.write(buffer, 0, length);
            }

            stream.close();
            fos.close();

            executorService.execute(() -> {

                Arquivo arquivo = new Arquivo(file.getName(),file.getAbsolutePath(), ext);
                daoArquivo.insert(arquivo);

                listaArquivosSalvos.add(arquivo);

            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.file_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.importar) {

            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.setType("*/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            intent.addCategory(Intent.CATEGORY_OPENABLE);

            startActivityForResult(intent, 0);
        }

        return true;
    }

    private boolean requestStoragePermission(){
        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
            return false;
        }
        return true;
    }


    /**
     * Importante saber que sempre que formos implementar funções de visualização de arquivos
     * em nosso app, deve-se configurar o file provider no manifest, caso contrário o  sistema
     * lançará uma exception;
     *
     * Os passos para fazermos isso é simples:
     *
     *  1. Implemente o provider no seu arquivo manifest exatamente como no manifest desse app;
     *  2. Crie um arquivo xml/file_paths contendo a mesma estrutura do arquivo existente nesse
     *     projeto;
     *
     *  Obs: Não esqueça de mudar os names fazendo referência o seu appname
     *
     *
     * **/
    @Override
    public void visualizarArquivo(int pos) {

        try {

            Arquivo arquivo = adapter.getAdapterList().get(pos);
            File file = new File(arquivo.path);

            Uri uri = FileProvider.getUriForFile(
                    MainActivity.this,
                    "com.example.appimportarmultiplosarquivos.provider",
                    file
            );

            String mimeType = getContentResolver().getType(uri);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri, mimeType);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            startActivity(Intent.createChooser(intent, "Abrir com"));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void deletarArquivo(Arquivo arquivo) {
        executorService.execute((() -> {

            daoArquivo.delete(arquivo);
            adapter.getAdapterList().remove( arquivo );

            runOnUiThread(() -> {
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Arquivo deletado com sucesso", Toast.LENGTH_SHORT).show();
            });

        }));
    }
}