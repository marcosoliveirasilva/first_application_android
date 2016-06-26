package br.edu.ifba.mobile.controledevendas.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.controledevendas.bd.Cliente;
import br.edu.ifba.mobile.controledevendas.bd.FachadaBD;


/**
 * Created by alunoifba on 27/05/2016.
 */
public class GravacaoCliente extends  AsyncTask<Void, Void, String> {

    private  Context contexto = null;
    private Cliente cliente = null;

    public GravacaoCliente(Context contexto, Cliente cliente) {
        this.contexto = contexto;
        this.cliente = cliente;

    }

    @Override
    protected String doInBackground(Void... params) { // vai executar em segundo plano. O banco de dados [e executado em segundo plano
       String mensagem = "";

        long codigo = -1;

        if(cliente.getCodigo() == -1)
        {
            codigo = FachadaBD.getInstancia().inserir(cliente);
        }
        else
        {
            codigo = FachadaBD.getInstancia().atualizar(cliente);
        }


        if (codigo > 0) {
            mensagem= "Cliente salvo com sucesso";
            // regisro inserido
        }
        else  {
            mensagem= "Erro de gravação";
            // falha inserido
        }


        return mensagem;
    }

    @Override
    protected void onPostExecute(String mensagem)//depois da execucao// somente aqui que deve exibir a mensagem
    {
        Toast.makeText(contexto, mensagem, Toast.LENGTH_LONG).show();//toast serve para  notificar. Sao as mensagens do android
    }
}
