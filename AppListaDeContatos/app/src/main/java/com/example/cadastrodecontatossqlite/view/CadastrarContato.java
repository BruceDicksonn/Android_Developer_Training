package com.example.cadastrodecontatossqlite.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cadastrodecontatossqlite.R;
import com.example.cadastrodecontatossqlite.controller.ControllerContato;
import com.example.cadastrodecontatossqlite.controller.ControllerFoto;
import com.example.cadastrodecontatossqlite.databinding.ActivityCadastrarContatoBinding;
import com.example.cadastrodecontatossqlite.model.Contato;
import com.example.cadastrodecontatossqlite.model.Foto;
import com.example.cadastrodecontatossqlite.utils.Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CadastrarContato extends AppCompatActivity {

    ActivityCadastrarContatoBinding binding;
    static final int REQUEST_IMAGE_CODE = 200;
    static Uri uri_foto_perfil = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastrarContatoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), REQUEST_IMAGE_CODE);

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_IMAGE_CODE && resultCode == RESULT_OK && data != null) {

            // o caminho até a imagem no dispositivo do usuario
            Uri uri = data.getData();
            uri_foto_perfil = uri;

            if(uri != null) {

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    binding.foto.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

    }


    boolean checkEdits(){
        int count = binding.parentLinearLayout.getChildCount();
        for(int i = 0; i < count; i++){
            View view =  binding.parentLinearLayout.getChildAt(i);
            if(view instanceof TextInputEditText) {
                TextInputEditText edit = (TextInputEditText) view;
                if(edit.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Preencha os campos vazios", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.salvar:
                if(!checkEdits()) return false;

                try {

                    Contato contato = preencherObjeto();
                    Foto foto = new Foto(Utils.convertImageToByteArray(this, uri_foto_perfil));

                    ControllerFoto controllerFoto = new ControllerFoto(this);
                    ControllerContato controller = new ControllerContato(this);

                    foto = controllerFoto.inserir(foto);

                    if(foto != null) {

                        contato.setImage_id(foto.get_id());
                        if(controller.insert(contato)){
                            Toast.makeText(this, "Contato salvo com sucesso!", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    }


                    return false;

                } catch (IOException e) {
                    Log.e("Info_DB", e.getMessage());
                }


            break;
        }
        return super.onOptionsItemSelected(item);
    }

    Contato preencherObjeto() throws IOException {

        Contato contato = new Contato();
        contato.setNome(binding.nome.getText().toString());
        contato.setTelefone(binding.telefone.getText().toString());
        contato.setFoto(Utils.convertImageToByteArray(this, uri_foto_perfil));

        return contato;

    }
}