package br.edu.ifba.mobile.controledevendas.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.controledevendas.bd.Compras;
import br.edu.ifba.mobile.controledevendas.bd.FachadaBDCompras;


/**
 * Created by alunoifba on 27/05/2016.
 */
public class RemocaoCompra extends  AsyncTask<Void, Void, String> {

    private  Context contexto = null;
    private Compras compras = null;

    public RemocaoCompra(Context contexto, Compras compras)
    {
        this.contexto = contexto;
        this.compras = compras;

    }

    @Override
    protected String doInBackground(Void... params) { // vai executar em segundo plano. O banco de dados [e executado em segundo plano
       String mensagem = "";

        if(compras.getCodigo() != -1)  {
             if(FachadaBDCompras.getInstancia().remover(compras) == 0){
                 mensagem = " Problemas de remocao";
             } else {
                 mensagem = "Compra removida";
             }
        }
        else
        {
            mensagem = "Selecione uma compra";
        }

        return mensagem;
    }

    @Override
    protected void onPostExecute(String mensagem)//depois da execucao// somente aqui que deve exibir a mensagem
    {
        Toast.makeText(contexto, mensagem, Toast.LENGTH_LONG).show();//toast serve para  notificar. Sao as mensagens do android
    }
}
