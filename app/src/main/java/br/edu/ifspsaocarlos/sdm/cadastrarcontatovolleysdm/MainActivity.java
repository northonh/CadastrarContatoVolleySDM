package br.edu.ifspsaocarlos.sdm.cadastrarcontatovolleysdm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    // Referências para os elementos de UI
    private EditText nomeCompletoEt;
    private EditText apelidoEt;

    // Constante para Web Services
    private final String URL_BASE = "http://www.nobile.pro.br/sdm/mensageiro/";
    private final String END_POINT = "contato";
    private final String CAMPO_NOME_JSON = "nome_completo";
    private final String CAMPO_APELIDO_JSON = "apelido";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Buscando referências para elementos de UI
        nomeCompletoEt = findViewById(R.id.et_nome_completo);
        apelidoEt = findViewById(R.id.et_apelido);
    }

    public void cadastrarNovoContato(View view) {
        // Cria um JSONObject que será enviado junto com a requisição no método POST
        JSONObject novoContatoJson = new JSONObject();
        try {
            // Preenche o JSONObject com os valores digitados pelo usuário
            novoContatoJson.put(CAMPO_NOME_JSON, nomeCompletoEt.getText().toString());
            novoContatoJson.put(CAMPO_APELIDO_JSON, apelidoEt.getText().toString());

            // Cria uma nova Fila de Requisições Volley
            RequestQueue filaRequisicoes = Volley.newRequestQueue(this);

            /* Cria uma Requisição para um JsonObject usando o método POST. Enviamos o parãmetro do
            método POST no terceiro parâmetro. Os Listeners sãousados para tratar a resposta da
            requisição */
            JsonObjectRequest requisicaoNovoContato = new JsonObjectRequest(Request.Method.POST, URL_BASE + END_POINT, novoContatoJson, new ResponseListener(), new ErrorListener());

            // Adiciona a requisição à fila de requisições
            filaRequisicoes.add(requisicaoNovoContato);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private class ResponseListener implements Response.Listener<JSONObject>{
        // Executado no caso de uma resposta
        @Override
        public void onResponse(JSONObject response) {
            // Recupera o JSONObject da resposta
            String resposta = response.toString();

            // Cria um novo objeto Gson para parsear o objeto
            Gson gson = new Gson();

            // Transforma a String num objeto da classe Contato usando REFLECTION
            Contato novoContato = gson.fromJson(resposta, Contato.class);

            // Mostra o id do novo usuário na tela
            Toast.makeText(MainActivity.this, "ID do novo contato: " + novoContato.getId(), Toast.LENGTH_LONG).show();
        }
    }

    private class ErrorListener implements Response.ErrorListener {
        // Executado no caso de um erro
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(), "Erro na resposta do WS", Toast.LENGTH_LONG).show();
        }
    }
}
