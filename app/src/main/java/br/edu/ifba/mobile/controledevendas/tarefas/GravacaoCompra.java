package br.edu.ifba.mobile.controledevendas.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.controledevendas.bd.Compras;
import br.edu.ifba.mobile.controledevendas.bd.FachadaBD;
import br.edu.ifba.mobile.controledevendas.bd.FachadaBDCompras;


/**
 * Created by alunoifba on 27/05/2016.
 */
public class GravacaoCompra extends  AsyncTask<Void, Void, String> {

    private  Context contexto = null;
    private Compras compras = null;

    public GravacaoCompra(Context contexto, Compras compras) {
        this.contexto = contexto;
        this.compras = compras;

    }

    @Override
    protected String doInBackground(Void... params) { // vai executar em segundo plano. O banco de dados [e executado em segundo plano
        String mensagem = "";

        long codigo = -1;

        if(compras.getCodigo() == -1)
        {
            codigo = FachadaBDCompras.getInstancia().inserir(compras);
        }
        else
        {
            codigo = FachadaBDCompras.getInstancia().atualizar(compras);
        }


        if (codigo > 0) {
            mensagem= "Crompra cadastrada";
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
